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
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

public class IndexDAO extends HttpServlet {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;
	
	public IndexDAO(ServletContext context) {
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
	public List<Map> selectRecentContents(){
		List list = new Vector();
		String sql = "select rownum r, dats.* from(\r\n" + 
		"select sysdate-postdate dat, name something, (SELECT TABLE_NAME FROM USER_TABLES WHERE TABLE_NAME='S_BOARD') tablename from s_board join seller on s_board.s_no = seller.s_no union " + 
		"select sysdate-postdate dat, name something, (SELECT TABLE_NAME FROM USER_TABLES WHERE TABLE_NAME='C_BOARD') tablename from c_board join customer on c_board.g_no = customer.g_no union " + 
		"select sysdate-regidate dat, name something, (SELECT TABLE_NAME FROM USER_TABLES WHERE TABLE_NAME='SELLER') tablename from seller union " + 
		"select sysdate-regidate dat, name something, (SELECT TABLE_NAME FROM USER_TABLES WHERE TABLE_NAME='CUSTOMER') tablename from customer order by dat) dats where rownum between 1 and 10";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				Map map = new HashMap();
				map.put("index", rs.getString(1));
				map.put("date", rs.getString(2));
				map.put("who", rs.getString(3));
				map.put("from", rs.getString(4));
				list.add(map);
			}
		} catch (Exception e) {e.printStackTrace();}
		return list;
	}
}
