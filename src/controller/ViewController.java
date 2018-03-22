package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.AdminDAO;
import model.member.AdminDTO;

public class ViewController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String no = req.getParameter("no");
		AdminDAO dao = new AdminDAO(req.getServletContext());
		AdminDTO dto = dao.selectOne(no);
		req.setAttribute("dtto", dto);
		req.getRequestDispatcher("/backend/member/administrator/View.jsp").forward(req, resp);
	}
}
