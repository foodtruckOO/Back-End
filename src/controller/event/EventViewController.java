package controller.event;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.event.AdminEventDAO;
import model.event.AdminEventDTO;

public class EventViewController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("뷰폼이고 eno 받아오는지 여부 : "+req.getParameter("eno"));
		AdminEventDAO dao = new AdminEventDAO(req.getServletContext());
		AdminEventDTO dto = dao.selectOne(req.getParameter("eno"));
		dao.close();
		req.setAttribute("eventdto", dto);
		req.getRequestDispatcher("/backend/event/View.jsp").forward(req, resp);
	}
}
