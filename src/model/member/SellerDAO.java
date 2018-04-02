package model.member;

import java.io.IOException;
import java.security.InvalidKeyException;
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
		} catch (Exception e) {}
	}
	
	public SellerDTO selectOne(String no) {	
		String sql = "SELECT * FROM seller WHERE s_no=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			while(rs.next()) {
				SellerDTO dto = new SellerDTO();
				dto.setS_no(rs.getString(1));
				dto.setId(rs.getString(2));
				dto.setPwd(rs.getString(3));
				dto.setName(rs.getString(4));
				dto.setTname(rs.getString(5));
				dto.setAddr(rs.getString(6));
				dto.setTel(rs.getString(7));
				dto.setCorporate_no(rs.getString(8));
				dto.setRegidate(rs.getDate(9));
				return dto;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public List<SellerDTO> selectList() {
		List<SellerDTO> list = new Vector();
		String sql = "SELECT * FROM seller ORDER BY s_no DESC";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				SellerDTO dto = new SellerDTO();
				dto.setS_no(rs.getString(1));
				dto.setId(rs.getString(2));
				dto.setPwd(rs.getString(3));
				dto.setName(rs.getString(4));
				dto.setTname(rs.getString(5));
				dto.setAddr(rs.getString(6));
				dto.setTel(rs.getString(7));
				dto.setCorporate_no(rs.getString(8));
				dto.setRegidate(rs.getDate(9));
				list.add(dto);
				//System.out.println("리스트에 담음 : "+dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public String selectToday(String today) {
		String count="";
		System.out.println("넘어온 값 : "+today);
		String sql = "SELECT count(*) FROM seller WHERE regidate >= TO_DATE(?,'YYYY-MM-DD')";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, today);
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
	
	public int delete(String no) {
		int affected=0;
		String sql = "DELETE FROM seller WHERE s_no=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, no);
			affected = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affected;
	}

	public int update(SellerDTO dto) {
		int affected=0;
		String sql="UPDATE seller SET id=?, pwd=?, name=?, tname=?, addr=?, tel=?, corporate_no=? WHERE s_no=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPwd());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getTname());
			psmt.setString(5, dto.getAddr());
			psmt.setString(6, dto.getTel());
			psmt.setString(7, dto.getCorporate_no());
			psmt.setString(8, dto.getS_no());
			affected = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affected;
	}
	
	public List<SellerDTO> selectForMap(){
		List<SellerDTO> list = new Vector();
		String sql = "SELECT * FROM seller ORDER BY s_no DESC";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				SellerDTO dto = new SellerDTO();
				dto.setS_no(rs.getString(1));
				dto.setId(rs.getString(2));
				dto.setPwd(rs.getString(3));
				dto.setName(rs.getString(4));
				dto.setTname(rs.getString(5));
				dto.setAddr(rs.getString(6));
				dto.setTel(rs.getString(7));
				dto.setCorporate_no(rs.getString(8));
				dto.setRegidate(rs.getDate(9));/*
				dto.setLatitude(Float.toString(geoCoding(rs.getString(6))[0]));
				dto.setLongtitude(Float.toString(geoCoding(rs.getString(6))[1]));*/
				list.add(dto);
				//System.out.println("리스트에 담음 : "+dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int updateTruckOnly(SellerDTO dto) {
		int affected=0;
		String sql="UPDATE seller SET tname=?, addr=?, tel=?, corporate_no=? WHERE s_no=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getTname());
			psmt.setString(2, dto.getAddr());
			psmt.setString(3, dto.getTel());
			psmt.setString(4, dto.getCorporate_no());
			psmt.setString(5, dto.getS_no());
			affected = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affected;
	}

}
