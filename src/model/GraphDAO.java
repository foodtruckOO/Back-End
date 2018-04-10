package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

public class GraphDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;
	
	public GraphDAO(ServletContext context) {
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
	public List<GraphDTO> selectList(){
		List list = new Vector();
		String sql = "SELECT * FROM GRAPH";
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
		return list;
	}
	public String selectEventGraph(String year, String month){
		String count="0";
		String sql = "SELECT COUNT(*) FROM EVENT WHERE S_DATE BETWEEN TO_DATE(?,'YYYY-MM-DD') and TO_DATE(?,'YYYY-MM-DD') or E_DATE BETWEEN TO_DATE(?,'YYYY-MM-DD') and TO_DATE(?,'YYYY-MM-DD')";
		//String sql = "SELECT COUNT(*) FROM event WHERE TO_CHAR(S_DATE, 'YYYY-MM') <= ? AND TO_CHAR(E_DATE, 'YYYY-MM') >= ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, year+"-"+month+"-01");
			psmt.setString(3, year+"-"+month+"-01");
			if(month.equals("12")) {//다음년도 1월 1일 설정하기
				System.out.println("다음년도 1월로 설정하기 = "+Integer.toString(Integer.parseInt(year)+1)+"-01-01");
				psmt.setString(2, Integer.toString(Integer.parseInt(year)+1)+"-01-01");
				psmt.setString(4, Integer.toString(Integer.parseInt(year)+1)+"-01-01");
			}
			else {//이번년도 다음달 설정하기
				System.out.println("이번년도 다음달로 설정하기 = "+year+"-"+Integer.toString(Integer.parseInt(month)+1)+"-01");
				psmt.setString(2, year+"-"+Integer.toString(Integer.parseInt(month)+1)+"-01");
				psmt.setString(4, year+"-"+Integer.toString(Integer.parseInt(month)+1)+"-01");
			}
			rs=psmt.executeQuery();
			while(rs.next()) {
				count=rs.getString(1);
				System.out.println(count);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	public String sellerCount() {
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
	public String customerCount() {
		String count="0";
		String sql = "SELECT COUNT(*) FROM CUSTOMER";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				count=rs.getString(1);
			}
		} catch (Exception e) {e.printStackTrace();}
		return count;
	}
	
	public List<Map> selectSellerGraph() {
		List<Map> list = new Vector();
		String sql="select do, count(*) from (select substr(addr, 0,(instr(addr , ' ', 1)-1)) do from seller union all ";
		sql+="select substr(addr, 0,(instr(addr , ' ', 1)-1)) do from foodtrucks) group by do";
		
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				Map map = new HashMap();
				map.put("label", rs.getString(1));
				map.put("value", rs.getString(2));
				list.add(map);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Map> selectSalesGraph(String term){
		List<Map> list = new Vector();
		String sql="";
		switch(term) {
			case "weekly" : 
				sql+="select sum(food.price*ord.num), to_char(postdate, 'YYYY-MM-W') month from " + 
					 "food join seller sel on food.s_no=sel.s_no " + 
					 "join orderform ord on food.f_no=ord.f_no " + 
					 "join customer cus on ord.g_no=cus.g_no " + 
					 "where to_char(postdate, 'YYYY-MM-W')<=to_char(postdate, 'YYYY-MM-W') " + 
					 "group by to_char(postdate, 'YYYY-MM-W') " + 
					 "order by to_char(postdate, 'YYYY-MM-W')";
				break;
			case "monthly" : 
				sql+="select sum(food.price*ord.num), to_char(ord.postdate, 'YYYY-MM') month from " + 
					 "food join seller sel on food.s_no=sel.s_no " + 
					 "join orderform ord on food.f_no=ord.f_no " + 
					 "join customer cus on ord.g_no=cus.g_no " + 
					 "where add_months(ord.postdate, 0)>=add_months(sysdate, -1) " + 
					 "group by to_char(ord.postdate, 'YYYY-MM') " + 
					 "order by to_char(ord.postdate, 'YYYY-MM')";
				break;
			case "daily" : 
				sql+="select sum(food.price*ord.num), to_char(postdate, 'YYYY-MM-DD') month from food join seller sel on food.s_no=sel.s_no " + 
					 "join orderform ord on food.f_no=ord.f_no join customer cus on ord.g_no=cus.g_no where to_char(postdate-7, 'YYYY-MM-DD')<=to_char(sysdate, 'YYYY-MM-DD') " + 
					 "group by to_char(postdate, 'YYYY-MM-DD') order by to_char(postdate, 'YYYY-MM-DD')";
				break;
		}
		try {
			psmt = conn.prepareStatement(sql);
			System.out.println(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				Map map = new HashMap();
				map.put("매출액", rs.getString(1));
				System.out.println(rs.getString(1));
				map.put("기간", rs.getString(2));
				System.out.println(rs.getString(2));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
