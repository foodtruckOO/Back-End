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

import model.member.SellerDTO;

public class TruckEventDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;
	
	public TruckEventDAO(ServletContext context) {
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
	public List<TruckEventDTO> selectList(String s_no) {
		List<TruckEventDTO> list = new Vector();
		String sql = "SELECT TRUCK_EVENT.*, TNAME FROM TRUCK_EVENT JOIN SELLER ON TRUCK_EVENT.S_NO = SELLER.S_NO WHERE TRUCK_EVENT.S_NO=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, s_no);
			rs = psmt.executeQuery();
			while(rs.next()) {
				TruckEventDTO dto = new TruckEventDTO();
				dto.setEno(rs.getString(1));
				dto.setS_no(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setTitlefile(rs.getString(5));
				dto.setContentfile(rs.getString(6));
				dto.setS_date(rs.getDate(7));
				dto.setE_date(rs.getDate(8));
				dto.setPostdate(rs.getDate(9));
				dto.setName(rs.getString(10));
				list.add(dto);
				//System.out.println("리스트에 담음 : "+dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<TruckEventDTO> selectOne(String eno) {
		List<TruckEventDTO> list = new Vector();
		String sql = "SELECT TRUCK_EVENT.*, TNAME FROM TRUCK_EVENT JOIN SELLER ON TRUCK_EVENT.S_NO = SELLER.S_NO WHERE ENO=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, eno);
			rs = psmt.executeQuery();
			while(rs.next()) {
				TruckEventDTO dto = new TruckEventDTO();
				dto.setEno(rs.getString(1));
				dto.setS_no(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setTitlefile(rs.getString(5));
				dto.setContentfile(rs.getString(6));
				dto.setS_date(rs.getDate(7));
				dto.setE_date(rs.getDate(8));
				dto.setPostdate(rs.getDate(9));
				dto.setName(rs.getString(10));
				list.add(dto);
				//System.out.println("리스트에 담음 : "+dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public int update(TruckEventDTO dto) {
		int affected=0;
		String sql="UPDATE TRUCK_EVENT SET TITLE=?, CONTENT=?, TITLEFILE=?, CONTENTFILE=?, S_DATE=?, E_DATE=? WHERE ENO=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getTitlefile());
			psmt.setString(4, dto.getContentfile());
			psmt.setDate(5, dto.getS_date());
			psmt.setDate(6, dto.getE_date());
			psmt.setString(7, dto.getEno());
			affected = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affected;
	}
	
	public int delete(String eno) {
		int affected=0;
		String sql = "DELETE FROM Truck_event WHERE eno=?";
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
