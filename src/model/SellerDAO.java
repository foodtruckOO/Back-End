package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.sql.DataSource;


public class SellerDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;
	
	public SellerDAO(ServletContext context) {
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
	public String count() {
		String count="0";
		String sql = "SELECT COUNT(*) FROM SELLER";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				count=rs.getString(1);
			}
		} catch (Exception e) {e.printStackTrace();}
		return count;
	}
	public List<SellerDTO> selectList(){
		List list = new Vector();
		String sql = "SELECT * FROM SELLER";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				GraphDTO dto = new GraphDTO();
				dto.setIdx(rs.getString(1));
				dto.setDate(rs.getDate(2));
				dto.setFir(rs.getString(3));
				dto.setSec(rs.getString(4));
				dto.setThr(rs.getString(5));
				list.add(dto);
			}
		} catch (Exception e) {e.printStackTrace();}
		System.out.println("성공적으로 데이터 받았을지도 모름");
		System.out.println("list사이즈 = "+list.size());
		return list;
	}
	public List<SellerDTO> selectOne(String s_no){
		List list = new Vector();
		String sql = "SELECT * FROM SELLER where s_no=?";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				GraphDTO dto = new GraphDTO();
				dto.setIdx(rs.getString(1));
				dto.setDate(rs.getDate(2));
				dto.setFir(rs.getString(3));
				dto.setSec(rs.getString(4));
				dto.setThr(rs.getString(5));
				list.add(dto);
			}
		} catch (Exception e) {e.printStackTrace();}
		System.out.println("성공적으로 데이터 받았을지도 모름");
		System.out.println("list사이즈 = "+list.size());
		return list;
	}
}
