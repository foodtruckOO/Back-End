package controller;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.event.AdminEventDAO;
import model.member.CustomerDAO;
import model.member.SellerDAO;

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
		AdminEventDAO admindao = new AdminEventDAO(req.getServletContext());
		String count = admindao.selectToday(fullToday);
		req.setAttribute("todayWriteCount", count);
		//////////////첫번쨰 꺼 조치 끝
		
		//////////////두번째 꺼 조치
		CustomerDAO customerdao = new CustomerDAO(req.getServletContext());
		SellerDAO sellerdao = new SellerDAO(req.getServletContext());
		int newMember = Integer.parseInt(customerdao.selectToday(fullToday))+Integer.parseInt(sellerdao.selectToday(fullToday));
		req.setAttribute("todayRegisterMemberCount", Integer.toString(newMember));
		req.getRequestDispatcher("/backend/pages/Index.jsp").forward(req, resp);
	}
}
