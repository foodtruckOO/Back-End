package controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import controller.order.OrderOngoingController;
import model.IndexDAO;
import model.event.AdminEventDAO;
import model.member.CustomerDAO;
import model.member.SellerDAO;
import model.order.OrderDAO;
import model.order.OrderDTO;

public class IndexPageController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//////////첫번째 꺼 조치
		AdminEventDAO aedao = new AdminEventDAO(req.getServletContext());
		String count = aedao.selectToday();
		req.setAttribute("todayWriteCount", count);
		//////////////첫번쨰 꺼 조치 끝
		
		//////////////두번째 꺼 조치
		CustomerDAO customerdao = new CustomerDAO(req.getServletContext());
		SellerDAO sellerdao = new SellerDAO(req.getServletContext());
		int newMember = Integer.parseInt(customerdao.selectToday())+Integer.parseInt(sellerdao.selectToday());
		req.setAttribute("todayRegisterMemberCount", Integer.toString(newMember));
		////////////////////두번쨰꺼 조치 끝
		
		//////////////////////세번째꺼 조치 아직 못함
		OrderDAO orderDao = new OrderDAO(req.getServletContext());
		int orderCount = orderDao.selectOrderCount();
		req.setAttribute("orderCount", orderCount);
		//////////////////////아직 손못댄 세번째꺼 조치관련
		
		/////////////////////네번째꺼 조치
		int todayEventCount = aedao.todayEventCount();
		req.setAttribute("todayEventCount", todayEventCount);
		/////////////////////네번째꺼 조치 끝
		
		///////////////////첫번째 표 조치 시작 - 주문상황으로?
		List<OrderDTO> firstList =  OrderOngoingController.orderMigrater(orderDao.selectList());
		req.setAttribute("firstList", firstList);
		///////////////////첫번째 표 조치 끝
		
		///////////////////우측에 있는 표 시작
		IndexDAO idxDao = new IndexDAO(req.getServletContext());		
		List<Map> list = idxDao.selectRecentContents();
		List<Map> newList = recentChangeChatcher(list);
		req.setAttribute("recentChanges", newList);
		///////////////////우측에 있는 표 끝
		
		aedao.close();
		customerdao.close();
		sellerdao.close();
		orderDao.close();
		idxDao.close();
		req.getRequestDispatcher("/backend/pages/Index.jsp").forward(req, resp);
	}
	
	public List<Map> recentChangeChatcher(List<Map> list){
		List<Map> listMk2 = new Vector();
		for(Map map : list) {
			Map newMap = new HashMap();
			newMap.put("index", map.get("index"));
			newMap.put("text", whereAreYourFrom(map.get("who").toString(), map.get("from").toString()));
			newMap.put("dateTime", timeCalculator(map.get("date").toString()));
			listMk2.add(newMap);
		}
		return listMk2;
	}
	
	public String whereAreYourFrom(String who, String from) {
		switch(from){
			case "S_BOARD" : return who+" 님 사장게시판 글 작성";
			case "SELLER" : return who+" 님 판매자회원 가입";
			case "CUSTOMER" : return who+" 님 소비자회원 가입";
			case "C_BOARD" : return who+" 님 손님게시판 글 작성";
			default : return "";
		}
	}
	
	public String timeCalculator(String time) {
		float floatTime = Float.parseFloat(time);
		if(floatTime<0) return "미래로부터";
		else if(floatTime>=1) return (int)Math.floor(floatTime)+"일 전";
		else if(floatTime*24>=1) return (int)Math.floor(floatTime*24)+"시간 전";
		else if(floatTime*24*60>=1) return (int)Math.floor(floatTime*24*60)+"분 전";
		else return "방금 전";
	}
}
