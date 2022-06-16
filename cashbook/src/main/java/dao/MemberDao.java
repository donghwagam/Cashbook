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

import vo.Member;

public class MemberDao {
	
	// 로그인(아이디,비밀번호를 확인하여 아이디는 반환)
	public String selectMemberByIdPw(Member member) {
	
		String memberId = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT member_id memberId FROM member WHERE member_id=? AND member_pw=PASSWORD(?)";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			rs = stmt.executeQuery();
			while(rs.next()) {
				memberId = rs.getString("memberId");
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
		return memberId;
		
	}
	
	// 회원가입(INSERT)
	public void insertMember(Member member) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			
			String sql = "INSERT INTO member(member_id, member_pw, create_date)\r\n"
					+ "VALUES (?, PASSWORD(?), NOW())";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			
			int row = stmt.executeUpdate();
			
			if(row == 1) {
				System.out.println("입력성공");
			} else {
				System.out.println("입력실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 회원비밀번호수정(UPDATE)
	public int updateMemberPw(String memberId, String memberPw , String memberPw2) {
		int row = 0;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			
			String sql = "UPDATE member SET member_pw = PASSWORD(?) WHERE member_id = ? AND member_pw = PASSWORD(?)";		
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberPw2);
			stmt.setString(2, memberId);
			stmt.setString(3, memberPw);
			
			row = stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}
	
	// 회원탈퇴(DELETE)
	public int delelteMember(String memberId, String memberPw) {
		int row = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false);
			
			String deleteCashbookSql = "DELETE FROM cashbook WHERE member_id = ?";
			String deleteMemberSql = "DELETE FROM member WHERE member_id = ? AND member_pw = PASSWORD(?) ";
			
			stmt = conn.prepareStatement(deleteCashbookSql);
			stmt.setString(1, memberId);
			row = stmt.executeUpdate();
			
			stmt = conn.prepareStatement(deleteMemberSql);
			stmt.setString(1, memberId);
			stmt.setString(2, memberPw);
			row = stmt.executeUpdate();
			
			conn.commit();
			
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		return row;
	}
	
	// 회원정보(SELECT)
	public List<Map<String, Object>> selectMemberOne(String memberId) {
		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			String sql = "SELECT member_id memberId, member_pw memberPw, create_date createDate FROM  member WHERE member_id = ?";
			stmt= conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("memberId", rs.getString("memberId"));
				map.put("memberPw", rs.getString("memberPw"));
				map.put("createDate", rs.getString("createDate"));
				list.add(map);
			}
		} catch (Exception e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	return list;	
	}
		
}
