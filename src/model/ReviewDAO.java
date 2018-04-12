package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

public class ReviewDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;
	
	public ReviewDAO(ServletContext context) {
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
		} catch (Exception e) {e.printStackTrace();}
	}
	
	private List<ReviewDTO> selectList(){
		List<ReviewDTO> list = new Vector();
		String sql = "SELECT * FROM review";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setR_no(rs.getString(1));
				dto.setS_no(rs.getString(2));
				dto.setG_no(rs.getString(3));
				dto.setStar(rs.getString(4));
				dto.setOnememo(rs.getString(5));
				dto.setPostdate(rs.getDate(6));
				list.add(dto);
			}
		} catch (Exception e) {e.printStackTrace();}
		return list;
	}
}
