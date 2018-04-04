package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

public class NoMemberDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;
	
	public NoMemberDAO(ServletContext context) {
		try {
			Context ctx = new InitialContext();
			DataSource source = (DataSource)ctx.lookup(context.getInitParameter("TOMCAT_JNDI_ROOT")+"/jndi/ft");
			conn=source.getConnection();
		} catch (Exception e) {e.printStackTrace();}
	}

	public void close() {
		try {
			if(rs!=null)rs.close();
			if(psmt!=null)psmt.close();
			if(conn!=null)conn.close();
		} catch (Exception e) {}
	}
	
	public NoMemberDTO selectOne(String no) {	
		String sql = "SELECT * FROM foodtrucks WHERE s_no=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			while(rs.next()) {
				NoMemberDTO dto = new NoMemberDTO();
				psmt.setString(1, dto.getF_no());
				psmt.setString(2, dto.getTname());
				psmt.setString(3, dto.getAddr());
				psmt.setString(4, dto.getTel());
				psmt.setString(5, dto.getAttachedFile());
				return dto;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public List<NoMemberDTO> selectList() {
		List<NoMemberDTO> list = new Vector();
		String sql = "SELECT * FROM foodtrucks ORDER BY f_no DESC";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				NoMemberDTO dto = new NoMemberDTO();
				psmt.setString(1, dto.getF_no());
				psmt.setString(2, dto.getTname());
				psmt.setString(3, dto.getAddr());
				psmt.setString(4, dto.getTel());
				psmt.setString(5, dto.getAttachedFile());
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int insert(NoMemberDTO dto) {
		int affected=0;
		String sql="INSERT INTO foodtrucks VALUES(seq_foodtrucks.nextval, ?, ?, ?, ?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getTname());
			psmt.setString(2, dto.getAddr());
			psmt.setString(3, dto.getTel());
			psmt.setString(4, dto.getAttachedFile());
			affected = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affected;
	}
	
	
	
	public int delete(String f_no) {
		int affected=0;
		String sql = "DELETE FROM foodtrucks WHERE f_no=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, f_no);
			affected = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affected;
	}

	public int update(NoMemberDTO dto) {
		int affected=0;
		String sql="UPDATE foodtrucks SET tname=?, addr=?, tel=?, attachedFile=? WHERE f_no=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getTname());
			psmt.setString(2, dto.getAddr());
			psmt.setString(3, dto.getTel());
			psmt.setString(4, dto.getAttachedFile());
			psmt.setString(5, dto.getF_no());
			affected = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affected;
	}
}
