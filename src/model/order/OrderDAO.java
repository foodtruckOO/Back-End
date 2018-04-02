package model.order;

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

public class OrderDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;
	
	public OrderDAO(ServletContext context) {
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
	
	public List<OrderDTO> selectList(){
		List<OrderDTO> list = new Vector();
		String sql = "select ord.o_no, cus.name customer, sel.name seller,"+
				" food.fname food, food.price, ord.num from\r\n" + 
				"food join seller sel on food.s_no=sel.s_no\r\n" + 
				"join orderform ord on food.f_no=ord.f_no\r\n" + 
				"join customer cus on ord.g_no=cus.g_no order by o_no";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				OrderDTO dto = new OrderDTO();
				dto.setO_no(rs.getString(1));
				dto.setGname(rs.getString(2));
				dto.setSname(rs.getString(3));
				dto.setFname(rs.getString(4));
				dto.setPrice(rs.getString(5));
				dto.setNum(rs.getString(6));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return list;
	}
	
	public List<OrderDTO> selectOne(String o_no){
		List<OrderDTO> list = new Vector();
		String sql = "select ord.o_no, cus.name customer, sel.name seller,"+
				" food.fname food, food.price, ord.num from\r\n" + 
				"food join seller sel on food.s_no=sel.s_no\r\n" + 
				"join orderform ord on food.f_no=ord.f_no\r\n" + 
				"join customer cus on ord.g_no=cus.g_no where o_no=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, o_no);
			rs = psmt.executeQuery();
			while(rs.next()) {
				OrderDTO dto = new OrderDTO();
				dto.setO_no(rs.getString(1));
				dto.setGname(rs.getString(2));
				dto.setSname(rs.getString(3));
				dto.setFname(rs.getString(4));
				dto.setPrice(rs.getString(5));
				dto.setNum(rs.getString(6));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
