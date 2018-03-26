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
		String sql="";
		switch(selectListSupport()) {
			case 3 : 
				sql = "SELECT f_no no, tname, addr, tel, (SELECT count(*) FROM USER_TAB_COLUMNS where table_name='FOODTRUCKS') FROM foodtrucks UNION ";
				sql+="SELECT s_no no, tname, addr, tel, (SELECT count(*) FROM USER_TAB_COLUMNS where table_name='SELLER') FROM seller";
				break;
			case 2 : 
				sql+="SELECT s_no no, tname, addr, tel, (SELECT count(*) FROM USER_TAB_COLUMNS where table_name='SELLER') FROM seller";
				break;
			default :
				sql = "SELECT f_no no, tname, addr, tel, (SELECT count(*) FROM USER_TAB_COLUMNS where table_name='FOODTRUCKS') FROM foodtrucks";
				break;
		}
		
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				MapDTO dto = new MapDTO();
				dto.setNo(rs.getString(1));
				dto.setTname(rs.getString(2));
				dto.setAddr(rs.getString(3));
				dto.setTel(rs.getString(4));
				dto.setColumnCount(rs.getString(5));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
		
	public int selectListSupport() {
		String sql = "SELECT f_no no, tname, addr, tel FROM foodtrucks UNION ";
		sql+="SELECT s_no no, tname, addr, tel FROM seller";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(rs.next()) return 3;//두 테이블에 다 데이터가 있다.
			else {
				sql="SELECT f_no no, tname, addr, tel FROM seller";
				psmt = conn.prepareStatement(sql);
				rs = psmt.executeQuery();
				if(rs.next()) return 2;//seller만 데이터를 갖고 있다.
				else return 1;//foodtrucks만 데이터를 갖고 있던지, 아니면 아무것도 없던지
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;//오류발생시 반환값
	}
	
	public List<MapDTO> selectListbyMember(String type) {
		List<MapDTO> list = new Vector();
		String sql="";
		switch(type) {
			case "yes" : 
				sql+="SELECT s_no no, tname, addr, tel, (SELECT count(*) FROM USER_TAB_COLUMNS where table_name='SELLER') FROM seller";
				break;
			case "no" : 
				sql = "SELECT f_no no, tname, addr, tel, (SELECT count(*) FROM USER_TAB_COLUMNS where table_name='FOODTRUCKS') FROM foodtrucks";
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
				dto.setTel(rs.getString(4));
				dto.setColumnCount(rs.getString(5));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public MapDTO selectOne(String no, String count) {
		MapDTO dto = new MapDTO();
		if(count.equals("9")) {//회원인 경우
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
					dto.setTel(rs.getString(7));
					dto.setColumnCount("9");
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
					dto.setTel(rs.getString(4));
					dto.setColumnCount("5");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
	
	
	public List<MapDTO> selectListSearchResult(String search) {
		List<MapDTO> list = new Vector();
		String sql="";
		
		switch(selectListSupport()) {
			case 3 : 
				sql = "SELECT f_no no, tname, addr, tel, (SELECT count(*) FROM USER_TAB_COLUMNS where table_name='FOODTRUCKS') FROM foodtrucks UNION ";
				sql+="SELECT s_no no, tname, addr, tel, (SELECT count(*) FROM USER_TAB_COLUMNS where table_name='SELLER') FROM seller";
				break;
			case 2 : 
				sql+="SELECT s_no no, tname, addr, tel, (SELECT count(*) FROM USER_TAB_COLUMNS where table_name='SELLER') FROM seller";
				break;
			default :
				sql = "SELECT f_no no, tname, addr, tel, (SELECT count(*) FROM USER_TAB_COLUMNS where table_name='FOODTRUCKS') FROM foodtrucks";
				break;
		}
		
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				MapDTO dto = new MapDTO();
				dto.setNo(rs.getString(1));
				dto.setTname(rs.getString(2));
				dto.setAddr(rs.getString(3));
				dto.setTel(rs.getString(4));
				dto.setColumnCount(rs.getString(5));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
}
