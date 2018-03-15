package controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.AdminDAO;
import model.member.AdminDTO;

public class DeleteController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AdminDAO dao = new AdminDAO(req.getServletContext());
		int affected = dao.delete(req.getParameter("no"));
		req.setAttribute("SUC_FAIL", affected);
		req.getRequestDispatcher("/backend/pages/common/Message.jsp").forward(req, resp);
	}
}
