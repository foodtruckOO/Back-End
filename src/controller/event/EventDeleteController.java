package controller.event;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.event.AdminEventDAO;
import model.member.AdminDAO;
import model.member.AdminDTO;

public class EventDeleteController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AdminEventDAO dao = new AdminEventDAO(req.getServletContext());
		int affected = dao.delete(req.getParameter("no"));
		req.setAttribute("WHERE", "EVENTDELETE");
		req.setAttribute("SUC_FAIL", affected);
		req.setAttribute("eno", req.getParameter("no"));
		req.getRequestDispatcher("/backend/pages/common/Fail.jsp").forward(req, resp);
	}
}
