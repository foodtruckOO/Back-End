package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapSession;

public class AdminDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;
	
/*	@Resource(name="sqlMapClient")
	private SqlMapClient sqlMapper;*/
	
	public AdminDAO(ServletContext context) {
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
	public boolean idCheck(String id) {
		String sql = "SELECT * FROM administrator WHERE id=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				System.out.println("같은 아이디가 존재함");
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	public AdminDTO logIn(String id, String password) {
		String sql = "SELECT * FROM administrator WHERE id=? and pwd=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, password);
			rs = psmt.executeQuery();
			while(rs.next()) {
				AdminDTO dto = new AdminDTO();
				dto.setA_no(rs.getString(1));
				dto.setId(rs.getString(2));
				dto.setPwd(rs.getString(3));
				dto.setGrade(rs.getString(4));
				dto.setEmail(rs.getString(5));
				return dto;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	
	public AdminDTO selectOne(String no) {
		String sql = "SELECT * FROM administrator WHERE a_no=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			while(rs.next()) {
				AdminDTO dto = new AdminDTO();
				dto.setA_no(rs.getString(1));
				dto.setId(rs.getString(2));
				dto.setPwd(rs.getString(3));
				dto.setGrade(rs.getString(4));
				dto.setEmail(rs.getString(5));
				return dto;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public List<AdminDTO> selectList() {
		List<AdminDTO> list = new Vector();
		String sql = "SELECT * FROM administrator ORDER BY a_no";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				AdminDTO dto = new AdminDTO();
				dto.setA_no(rs.getString(1));
				dto.setId(rs.getString(2));
				dto.setPwd(rs.getString(3));
				dto.setGrade(rs.getString(4));
				dto.setEmail(rs.getString(5));
				list.add(dto);
				System.out.println("리스트에 담음 : "+dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int delete(String no) {
		int affected=0;
		String sql = "DELETE FROM administrator WHERE a_no=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, no);
			affected = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affected;
	}
	
	public int update(AdminDTO dto) {
		int affected=0;
		String sql="UPDATE administrator SET grade=?, id=?, pwd=?, email=? WHERE a_no=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getGrade());
			psmt.setString(2, dto.getId());
			psmt.setString(3, dto.getPwd());
			psmt.setString(4, dto.getEmail());
			psmt.setString(5, dto.getA_no());
			affected = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affected;
	}
	public int insert(AdminDTO dto) {
		int affected=0;
		String sql="";
		if(adminCount()==0) sql+="INSERT INTO administrator VALUES(seq_admin.nextval, ?, ?, '1', ?)";//아무도 없는 경우는 바로 1권한 부여
		else sql+="INSERT INTO administrator VALUES(seq_admin.nextval, ?, ?, '3', ?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPwd());
			psmt.setString(3, dto.getEmail());
			affected = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affected;
	}
	public int adminCount() {
		int count=0;
		String sql="SELECT count(*) FROM administrator";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
}
