package model.map;

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

import model.event.CalendarDTO;

public class MapDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;
	
	public MapDAO(ServletContext context) {
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
	
	public List<MapDTO> selectListBasic() {
		List<MapDTO> list = new Vector();
		String sql = "SELECT f_no no, tname, addr, addr2, tel, attachedFile etc, (SELECT count(*) FROM USER_TAB_COLUMNS where table_name='FOODTRUCKS') type FROM foodtrucks";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				MapDTO dto = new MapDTO();
				dto.setNo(rs.getString(1));
				dto.setTname(rs.getString(2));
				dto.setAddr(rs.getString(3));
				dto.setAddr2(rs.getString(4));
				dto.setTel(rs.getString(5));
				dto.setEtc(rs.getString(6));
				dto.setColumnCount(rs.getString(7));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sql ="SELECT s_no no, tname, addr, addr2, tel, corporate_no etc, (SELECT count(*) FROM USER_TAB_COLUMNS where table_name='SELLER') type FROM seller";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				MapDTO dto = new MapDTO();
				dto.setNo(rs.getString(1));
				dto.setTname(rs.getString(2));
				dto.setAddr(rs.getString(3));
				dto.setAddr2(rs.getString(4));
				dto.setTel(rs.getString(5));
				dto.setEtc(rs.getString(6));
				dto.setColumnCount(rs.getString(7));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
		
	public List<MapDTO> selectListbyMember(String type) {
		List<MapDTO> list = new Vector();
		String sql="";
		switch(type) {
			case "yes" : 
				sql+="SELECT s_no no, tname, addr, addr2, tel, (SELECT count(*) FROM USER_TAB_COLUMNS where table_name='SELLER') FROM seller";
				break;
			case "no" : 
				sql = "SELECT f_no no, tname, addr, addr2, tel, (SELECT count(*) FROM USER_TAB_COLUMNS where table_name='FOODTRUCKS') FROM foodtrucks";
				break;
			default :
				return selectListBasic();
		}
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				MapDTO dto = new MapDTO();
				dto.setNo(rs.getString(1));
				dto.setTname(rs.getString(2));
				dto.setAddr(rs.getString(3));
				dto.setAddr2(rs.getString(4));
				dto.setTel(rs.getString(5));
				dto.setColumnCount(rs.getString(6));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public MapDTO selectOne(String no, String count) {
		MapDTO dto = new MapDTO();
		if(count.equals("10")) {//회원인 경우
			String sql = "SELECT * FROM seller where s_no=?";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);
				rs = psmt.executeQuery();
				while(rs.next()) {
					dto.setNo(rs.getString(1));
					dto.setSname(rs.getString(4));
					dto.setTname(rs.getString(5));
					dto.setAddr(rs.getString(6));
					dto.setAddr2(rs.getString(7));
					dto.setTel(rs.getString(8));
					dto.setEtc(rs.getString(9));
					dto.setColumnCount("10");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {//비회원인 경우
			String sql = "SELECT * FROM foodtrucks where f_no=?";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);
				rs = psmt.executeQuery();
				while(rs.next()) {
					dto.setNo(rs.getString(1));
					dto.setTname(rs.getString(2));
					dto.setAddr(rs.getString(3));
					dto.setAddr2(rs.getString(4));
					dto.setTel(rs.getString(5));
					dto.setEtc(rs.getString(6));
					dto.setColumnCount("6");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
	
}
