package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.AdminDAO;
import model.member.AdminDTO;

public class UserListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getParameter("user")) {
		case "admin" :
			AdminDAO dao = new AdminDAO(req.getServletContext());
			List<AdminDTO> list = dao.selectList(); 
			req.setAttribute("list", list);
			req.getRequestDispatcher("/backend/member/administrator/AdminList.jsp").forward(req, resp);
			break;
		case "customer" :
			req.getRequestDispatcher("/backend/member/customer/List.jsp").forward(req, resp);
			break;
		case "seller" :
			req.getRequestDispatcher("/backend/member/seller/List.jsp").forward(req, resp);
			break;
		}
	}
}
