package model.food;

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

import model.member.AdminDTO;

public class FoodDAO {
	public static final int DELETE_WITH_FNO = 125;
	public static final int DELETE_WITH_SNO = 250;
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;
	
	public FoodDAO(ServletContext context) {
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
	public FoodDTO selectOne(String no) {
		String sql = "SELECT * FROM food WHERE f_no=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			if(rs.next()) {
				FoodDTO dto = new FoodDTO();
				dto.setF_no(rs.getString(1));
				dto.setS_no(rs.getString(2));
				dto.setT_no(rs.getString(3));
				dto.setFname(rs.getString(4));
				dto.setContent(rs.getString(5));
				dto.setPicture(rs.getString(6));
				dto.setPrice(rs.getString(7));
				return dto;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public List<FoodDTO> selectList() {
		List<FoodDTO> list = new Vector();
		String sql = "SELECT food.*, type FROM food join foodtype on food.t_no = foodtype.t_no ORDER BY f_no";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				FoodDTO dto = new FoodDTO();
				dto.setF_no(rs.getString(1));
				dto.setS_no(rs.getString(2));
				dto.setT_no(rs.getString(3));
				dto.setFname(rs.getString(4));
				dto.setContent(rs.getString(5));
				dto.setPicture(rs.getString(6));
				dto.setPrice(rs.getString(7));
				dto.setType(rs.getString(8));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<FoodDTO> selectList(String s_no) {//특정 사람의 음식들만 갖고오는경우
		List<FoodDTO> list = new Vector();
		String sql = "SELECT food.*, type FROM food join foodtype on food.t_no = foodtype.t_no where s_no=? ORDER BY f_no";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, s_no);
			rs = psmt.executeQuery();
			while(rs.next()) {
				FoodDTO dto = new FoodDTO();
				dto.setF_no(rs.getString(1));
				dto.setS_no(rs.getString(2));
				dto.setT_no(rs.getString(3));
				dto.setFname(rs.getString(4));
				dto.setContent(rs.getString(5));
				dto.setPicture(rs.getString(6));
				dto.setPrice(rs.getString(7));
				dto.setType(rs.getString(8));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int delete(String no, int identifier) {
		int affected=0;
		String sql="";
		if(identifier==DELETE_WITH_FNO) sql = "DELETE FROM food WHERE f_no=?";
		else if(identifier==DELETE_WITH_SNO) sql = "DELETE FROM food WHERE s_no=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, no);
			affected = psmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return affected;
	}
	
	public int update(FoodDTO dto) {
		int affected=0;
		String sql="UPDATE food SET s_no=?, t_no=?, fname=?, content=?, picture=?, price=? WHERE f_no=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getS_no());
			psmt.setString(2, dto.getT_no());
			psmt.setString(3, dto.getFname());
			psmt.setString(4, dto.getContent());
			psmt.setString(5, dto.getPicture());
			psmt.setString(6, dto.getPrice());
			psmt.setString(7, dto.getF_no());
			affected = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affected;
	}
	public int insert(FoodDTO dto) {
		int affected=0;
		String sql="INSERT INTO food VALUES(seq_food.nextval, ?, ?, ?, ?, ?, ?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getS_no());
			psmt.setString(2, dto.getT_no());
			psmt.setString(3, dto.getFname());
			psmt.setString(4, dto.getContent());
			psmt.setString(5, dto.getPicture());
			psmt.setString(6, dto.getPrice());
			affected = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affected;
	}
	
}
