package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class HashtagDao {
	public List<Map<String,Object>> selectTagRankList(String sessionMemberId) {
		List<Map<String,Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","mariadb1234");
			String sql = "SELECT kind, tag, cnt, RANK() over(ORDER BY cnt DESC) rank"
					+ "			FROM (SELECT c.kind kind, h.tag tag, COUNT(*) cnt, member_id memberId"
					+ "			FROM cashbook c JOIN hashtag h"
					+ "			ON c.cashbook_no = h.cashbook_no"
					+ "			GROUP BY h.tag) t"
					+ "			WHERE memberId = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sessionMemberId);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("kind", rs.getString("kind"));
				map.put("tag", rs.getString("tag"));
				map.put("cnt", rs.getInt("t.cnt"));
				map.put("rank", rs.getInt("rank"));
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public List<Map<String, Object>> tagKindSearchList(String kind, String memberId) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT RANK() over(ORDER BY t.cnt DESC) rank, t.tag tag, t.cnt cnt"
				+ "		FROM (SELECT t.tag tag, COUNT(*) cnt, c.member_id memberId"
				+ "				FROM hashtag t INNER JOIN cashbook c"
				+ "		 		ON t.cashbook_no = c.cashbook_no"
				+ "		 		WHERE c.kind = ? "
				+ "				GROUP BY t.tag) t"
				+ "		WHERE memberId = ?";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","mariadb1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, kind);
			stmt.setString(2, memberId);
			rs = stmt.executeQuery();
	         while(rs.next()) {
	             Map<String, Object> map = new HashMap<>();
	             map.put("rank", rs.getInt("rank"));
	             map.put("tag", rs.getString("tag"));
	             map.put("cnt", rs.getInt("cnt"));
	             list.add(map);
	         }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public List<Map<String, Object>> tagDateSearchList(String cashDate, String memberId) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT h.tag tag, COUNT(*) cnt, RANK() over(ORDER BY cnt DESC) ranking"
				+ "		FROM hashtag h INNER JOIN cashbook c"
				+ "		ON h.cashbook_no = c.cashbook_no"
				+ "		WHERE c.cash_date=? AND c.member_id = ?"
				+ "		GROUP BY tag";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","mariadb1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cashDate);
			stmt.setString(2, memberId);
			rs = stmt.executeQuery();
	         while(rs.next()) {
	             Map<String, Object> map = new HashMap<>();
	             map.put("tag", rs.getString("tag"));
	             map.put("cnt", rs.getInt("cnt"));
	             map.put("ranking", rs.getInt("ranking"));
	             list.add(map);
	         }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public List<Map<String, Object>> selectTagOne(String tag) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT h.tag tag, c.cash_date cashDate, c.kind kind, c.cash cash, c.memo memo"
					+ " FROM cashbook c INNER JOIN hashtag h"
					+ "		ON c.cashbook_no=h.cashbook_no"
					+ " WHERE h.tag=?"
					+ " ORDER BY c.cash_date";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","mariadb1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tag);
			rs = stmt.executeQuery();
	         while(rs.next()) {
	             Map<String, Object> map = new HashMap<>();
	             map.put("tag", rs.getString("tag"));
	             map.put("cashDate", rs.getString("cashDate"));
	             map.put("kind", rs.getString("kind"));
	             map.put("memo", rs.getString("memo"));
	             list.add(map);
	         }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
