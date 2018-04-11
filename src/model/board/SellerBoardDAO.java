package model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

public class SellerBoardDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;
	
	public SellerBoardDAO(ServletContext context) {
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
	public List<SellerBoardDTO> selectList(){
		List<SellerBoardDTO> list = new Vector();
		String sql="select sb.*, se.name from s_board sb join seller se on sb.s_no = se.s_no";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				SellerBoardDTO dto = new SellerBoardDTO();
				dto.setSb_no(rs.getString(1));
				dto.setS_no(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setAttachedfile(rs.getString(5));
				dto.setPostdate(rs.getDate(6));
				dto.setSname(rs.getString(7));
				list.add(dto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public SellerBoardDTO selectOne(String no) {
		SellerBoardDTO dto = new SellerBoardDTO();
		String sql="select sb.*, se.name from s_board sb join seller se on sb.s_no = se.s_no WHERE SB_NO= ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			while(rs.next()) {
				dto.setSb_no(rs.getString(1));
				dto.setS_no(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setAttachedfile(rs.getString(5));
				dto.setPostdate(rs.getDate(6));
				dto.setSname(rs.getString(7));
			}
		}catch(Exception e){
			e.printStackTrace();
			return dto;
		}
		return dto;
	}
	
}
