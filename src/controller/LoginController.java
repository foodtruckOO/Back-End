package controller;

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

public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath()+"/backend/pages/Login.jsp");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println("post방식으로 로그인처리해서 인덱스처리");
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		AdminDAO dao = new AdminDAO(req.getServletContext());
		AdminDTO dto = dao.logIn(req.getParameter("id"), req.getParameter("password"));
		if(dto!=null) {
			System.out.println("로그인성공");
			req.getSession().setAttribute("dto", dto);
			resp.sendRedirect(req.getContextPath()+"/Back/Index.do");
		}
		else {
			System.out.println("로그인실패");
			req.setAttribute("message", "로그인 실패. 아이디 혹은 비밀번호를 확인해 주십시오");
			req.getRequestDispatcher("/backend/pages/Login.jsp").forward(req, resp);
		}
	}
}
