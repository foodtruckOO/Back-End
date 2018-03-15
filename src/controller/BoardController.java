package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import model.event.AdminEventDAO;
import model.event.AdminEventDTO;

public class BoardController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<AdminEventDTO> list;
		switch(req.getParameter("type")) {
		case "admin" :
			AdminEventDAO dao = new AdminEventDAO(req.getServletContext());
			list = dao.selectList();
			dao.close();
			req.setAttribute("list", list);
			req.getRequestDispatcher("/backend/event/EventList.jsp").forward(req, resp);
			break;
		case "customer" :
			req.getRequestDispatcher("/backend/member/customer/Board.jsp").forward(req, resp);
			break;
		case "seller" :
			req.getRequestDispatcher("/backend/member/seller/Board.jsp").forward(req, resp);
			break;
		}
	}
}
