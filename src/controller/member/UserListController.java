package controller.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.AdminDAO;
import model.member.AdminDTO;
import model.member.CustomerDAO;
import model.member.CustomerDTO;
import model.member.SellerDAO;
import model.member.SellerDTO;

public class UserListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getParameter("user")) {
		case "admin" :
			AdminDAO admindao = new AdminDAO(req.getServletContext());
			List<AdminDTO> adminlist = admindao.selectList(); 
			req.setAttribute("list", adminlist);
			admindao.close();
			req.getRequestDispatcher("/backend/member/administrator/AdminList.jsp").forward(req, resp);
			break;
		case "customer" :
			CustomerDAO cusdao = new CustomerDAO(req.getServletContext());
			List<CustomerDTO> cuslist = cusdao.selectList();
			cusdao.close();
			req.setAttribute("list", cuslist);
			req.getRequestDispatcher("/backend/member/customer/List.jsp").forward(req, resp);
			break;
		case "seller" :
			SellerDAO seldao = new SellerDAO(req.getServletContext());
			List<SellerDTO> sellist = seldao.selectList();
			seldao.close();
			req.setAttribute("list", sellist);
			req.getRequestDispatcher("/backend/member/seller/List.jsp").forward(req, resp);
			break;
		}
	}
}
