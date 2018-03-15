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

public class CalendarDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;
	
	public CalendarDAO(ServletContext context) {
		try {
			Context ctx = new InitialContext();
			DataSource source = (DataSource)ctx.lookup(context.getInitParameter("TOMCAT_JNDI_ROOT")+"/jndi/ft");
			conn=source.getConnection();
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public List<CalendarDTO> selectList() {
		List<CalendarDTO> list = new Vector();
		String sql = "SELECT * FROM event ORDER BY eno";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				CalendarDTO dto = new CalendarDTO();
				dto.setEno(rs.getString(1));
				dto.setA_no(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setAttachedfile(rs.getString(5));
				dto.setBoardtype(rs.getString(6));
				dto.setS_date(rs.getDate(7));
				dto.setE_date(rs.getDate(8));
				dto.setPostdate(rs.getDate(9));
				dto.setUrl("/Back/EventView.do?eno="+rs.getString(1));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public void close() {
		try {
			if(rs!=null)rs.close();
			if(psmt!=null)psmt.close();
			if(conn!=null)conn.close();
		} catch (Exception e) {}
	}
}
