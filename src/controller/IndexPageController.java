package controller;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.event.AdminEventDAO;

public class IndexPageController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(cal.YEAR);
		int month = cal.get(cal.MONTH)+1;
		int day = cal.get(cal.DATE);
		String fullToday = ""+year+"-"+month+"-"+day;
		cal.add(Calendar.DATE, -1);
		year = cal.get(cal.YEAR);
		month = cal.get(cal.MONTH)+1;
		day = cal.get(cal.DATE);
		String fullYesterday = ""+year+"-"+month+"-"+day;
		AdminEventDAO dao = new AdminEventDAO(req.getServletContext());
		String count = dao.selectToday(fullToday, fullYesterday);
		req.setAttribute("todayWriteCount", count);
		req.getRequestDispatcher("/backend/pages/Index.jsp").forward(req, resp);
	}
}
