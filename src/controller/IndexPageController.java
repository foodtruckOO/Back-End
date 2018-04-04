package controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.order.OrderOngoingController;
import model.event.AdminEventDAO;
import model.member.CustomerDAO;
import model.member.SellerDAO;
import model.order.OrderDAO;
import model.order.OrderDTO;

public class IndexPageController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//////////첫번째 꺼 조치
		Calendar cal = Calendar.getInstance();
		int year = cal.get(cal.YEAR);
		int month = cal.get(cal.MONTH)+1;
		int day = cal.get(cal.DATE);
		String fullToday = ""+year+"-"+month+"-"+day;
/*		cal.add(Calendar.DATE, -1);
		year = cal.get(cal.YEAR);
		month = cal.get(cal.MONTH)+1;
		day = cal.get(cal.DATE);
		String fullYesterday = ""+year+"-"+month+"-"+day;*/
		AdminEventDAO aedao = new AdminEventDAO(req.getServletContext());
		String count = aedao.selectToday(fullToday);
		req.setAttribute("todayWriteCount", count);
		//////////////첫번쨰 꺼 조치 끝
		
		//////////////두번째 꺼 조치
		CustomerDAO customerdao = new CustomerDAO(req.getServletContext());
		SellerDAO sellerdao = new SellerDAO(req.getServletContext());
		int newMember = Integer.parseInt(customerdao.selectToday(fullToday))+Integer.parseInt(sellerdao.selectToday(fullToday));
		req.setAttribute("todayRegisterMemberCount", Integer.toString(newMember));
		aedao.close();
		customerdao.close();
		sellerdao.close();
		////////////////////두번쨰꺼 조치 끝
		
		///////////////////첫번째 표 조치 시작 - 주문상황으로?
		OrderDAO dao = new OrderDAO(req.getServletContext());
		List<OrderDTO> firstList =  OrderOngoingController.orderMigrater(dao.selectList());
		req.setAttribute("firstList", firstList);
		///////////////////첫번째 표 조치 끝
		
		
		
		
		req.getRequestDispatcher("/backend/pages/Index.jsp").forward(req, resp);
	}
}
