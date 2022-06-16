package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vo.Cashbook;
import vo.Hashtag;

public class CashbookDao {
	
	// 가계부월별 목록
	public List<Map<String, Object>> selectCashbookListByMonth(int y, int m, String memberId) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT"
				+ " 		cashbook_no cashbookNo"
				+ " 		,DAY(cash_date) day "
				+ " 		,kind"
				+ " 		,cash"
				+ " 		,LEFT(memo, 5) memo"
				+ "		 FROM cashbook"
				+ "		 WHERE YEAR(cash_date) = ? AND MONTH(cash_date) = ? AND member_id=?"
				+ "		 ORDER BY DAY(cash_date) ASC, KIND ASC";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, y);
			stmt.setInt(2, m);
			stmt.setString(3, memberId);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cashbookNo", rs.getInt("cashbookNo"));
				map.put("day", rs.getInt("day"));
				map.put("kind", rs.getString("kind"));
				map.put("cash", rs.getInt("cash"));
				map.put("memo", rs.getString("memo"));
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 가계부 상세보기
	public List<Map<String, Object>> selectCashBookOne(int cashbookNo) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT cashbook_no cashbookNo, cash_date cashDate, kind, cash, memo "
				+ "FROM cashbook "
				+ "WHERE cashbook_no = ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cashbookNo);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cashbookNo", rs.getInt("cashbookNo"));
				map.put("cashDate", rs.getString("cashDate"));
				map.put("kind", rs.getString("kind"));
				map.put("cash", rs.getInt("cash"));
				map.put("memo", rs.getString("memo"));
				list.add(map);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;	
	}
	
	// 가계부입력
	public void insertCashbook(Cashbook cashbook, List<String> hashtag, String memberId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false); // 자동커밋을 해제
			
			String sql = "INSERT INTO cashbook(cash_date,kind,cash,memo,update_date,create_date,member_id)"
					+ " VALUES(?,?,?,?,NOW(),NOW(),?)";
			
			// INSERT + SELECT 방금생성된 행의 키값 ex) select 방금입력한 cashbook_no from cashbook;
			stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); 
			stmt.setString(1, cashbook.getCashDate());
			stmt.setString(2, cashbook.getKind());
			stmt.setInt(3, cashbook.getCash());
			stmt.setString(4, cashbook.getMemo());
			stmt.setString(5, memberId);
			stmt.executeUpdate(); // INSERT
			rs = stmt.getGeneratedKeys(); // select 방금입력한 cashbook_no from cashbook
			int cashbookNo = 0;
			if(rs.next()) {
				cashbookNo = rs.getInt(1);
			}
			
			// hashtag를 저장하는 코드
			PreparedStatement stmt2 = null;
			for(String h : hashtag) {
				String sql2 = "INSERT INTO hashtag(cashbook_no, tag, create_date) VALUES(?, ?, NOW())";
				stmt2 = conn.prepareStatement(sql2);
				stmt2.setInt(1, cashbookNo);
				stmt2.setString(2, h);
				stmt2.executeUpdate();
			}
			
			conn.commit();
		} catch(Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 가계부삭제
	public void deleteCashbook(int cashbookNo) {
		// 자원 준비
		Connection conn = null;
		PreparedStatement hashStmt = null;
		PreparedStatement cashStmt = null;
		String hashSql = "DELETE FROM hashtag WHERE cashbook_no=?";
		String cashSql = "DELETE FROM cashbook WHERE cashbook_no=?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver"); // 드라이브 로딩
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook", "root", "java1234"); // DB 접속
			conn.setAutoCommit(false); // 자동커밋 X
			
			// hashtag테이블이 cashbook을 외래키로 가지고 있으므로 삭제할때는 hashtag의 정보를 삭제 후 cashbook의 정보를 삭제해야 한다
			// hashtag 먼저삭제
			hashStmt = conn.prepareStatement(hashSql);
			hashStmt.setInt(1, cashbookNo);
			hashStmt.executeUpdate();
			
			// cashbook 삭제
			cashStmt = conn.prepareStatement(cashSql); // 쿼리 작성
			cashStmt.setInt(1, cashbookNo);
			cashStmt.executeUpdate();
			
			conn.commit(); // 커밋 실행
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				hashStmt.close();
				cashStmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}