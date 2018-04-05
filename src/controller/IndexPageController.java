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
		
		//////////////////////아직 손못댄 세번째꺼 조치관련
		
		/////////////////////네번째꺼 조치
		int todayEventCount = aedao.todayEventCount();
		req.setAttribute("todayEventCount", todayEventCount);
		/////////////////////네번째꺼 조치 끝
		
		
		///////////////////첫번째 표 조치 시작 - 주문상황으로?
		OrderDAO orderDao = new OrderDAO(req.getServletContext());
		List<OrderDTO> firstList =  OrderOngoingController.orderMigrater(orderDao.selectList());
		req.setAttribute("firstList", firstList);
		///////////////////첫번째 표 조치 끝
		
		
		

		aedao.close();
		customerdao.close();
		sellerdao.close();
		orderDao.close();
		req.getRequestDispatcher("/backend/pages/Index.jsp").forward(req, resp);
	}
}
