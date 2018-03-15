package model.event;

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

public class AdminEventDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;
	
	public AdminEventDAO(ServletContext context) {
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
	public List<AdminEventDTO> selectList(String type) {
		List<AdminEventDTO> list = new Vector();
		String sql = "SELECT ae.*, id FROM event ae JOIN administrator a ON ae.a_no=a.a_no WHERE boardtype= "+type+" ORDER BY eno DESC";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				AdminEventDTO dto = new AdminEventDTO();
				dto.setEno(rs.getString(1));
				dto.setA_no(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setTitlefile(rs.getString(5));
				dto.setContentfile(rs.getString(6));
				dto.setS_date(rs.getDate(7));
				dto.setE_date(rs.getDate(8));
				dto.setPostdate(rs.getDate(9));
				dto.setBoardtype(rs.getString(10));
				dto.setId(rs.getString(11));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/*public List<AdminEventDTO> selectList2(String type) {
		List<AdminEventDTO> list = new Vector();
		String sql = "SELECT ae.*, id FROM event ae JOIN administrator a ON ae.a_no=a.a_no  "+type+"  ORDER BY eno DESC";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				AdminEventDTO dto = new AdminEventDTO();
				dto.setEno(rs.getString(1));
				dto.setA_no(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setAttachedfile(rs.getString(5));
				dto.setBoardtype(rs.getString(6));
				dto.setS_date(rs.getDate(7));
				dto.setE_date(rs.getDate(8));
				dto.setPostdate(rs.getDate(9));
				dto.setId(rs.getString(10));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}*/
	
	public AdminEventDTO selectOne(String eno) {
		AdminEventDTO dto = new AdminEventDTO();
		String sql = "SELECT ae.*, id FROM event ae join administrator a on ae.a_no=a.a_no WHERE eno=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, eno);
			rs = psmt.executeQuery();
			while(rs.next()) {
				dto.setEno(rs.getString(1));
				dto.setA_no(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setTitlefile(rs.getString(5));
				dto.setContentfile(rs.getString(6));
				dto.setS_date(rs.getDate(7));
				dto.setE_date(rs.getDate(8));
				dto.setPostdate(rs.getDate(9));
				dto.setBoardtype(rs.getString(10));
				dto.setId(rs.getString(11));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public String selectToday(String today, String yesterday) {
		String count="";
		System.out.println("넘어온 값 : "+today);
		String sql = "SELECT count(*) FROM event WHERE postdate between TO_DATE(?,'YYYY-MM-DD') and TO_DATE(?, 'YYYY-MM-DD')";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, yesterday);
			psmt.setString(2, today);
			rs = psmt.executeQuery();
			if(rs.next()) {
				count=rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(count+"개");
		return count;
	}
	
	public int insert(AdminEventDTO dto) {
		int affected=0;
		String sql = "INSERT INTO event VALUES(seq_event.nextval, ?, ?, ?, ?,? ,?, ?, sysdate, ?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getA_no());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getTitlefile());
			psmt.setString(5, dto.getContentfile());
			psmt.setDate(6, dto.getS_date());
			psmt.setDate(7, dto.getE_date());
			psmt.setString(8, dto.getBoardtype());

			affected = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affected;
	}
	
	public int edit(AdminEventDTO dto) {
		int affected=0;
		String sql = "UPDATE EVENT SET title=?, content=?, titlefile=?, contentfile=?, s_date=?, e_date=?, boardtype=? WHERE eno=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getTitlefile());
			psmt.setString(4, dto.getContentfile());
			psmt.setDate(5, dto.getS_date());
			psmt.setDate(6, dto.getE_date());
			psmt.setString(7, dto.getBoardtype());
			psmt.setString(8, dto.getEno());
			affected = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affected;
	}
	public int delete(String eno) {
		int affected=0;
		String sql="DELETE FROM event WHERE eno=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, eno);
			affected = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return affected;
	}
}
