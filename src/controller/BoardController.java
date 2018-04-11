package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.CustomerBoardDAO;
import model.board.CustomerBoardDTO;
import model.board.SellerBoardDAO;
import model.board.SellerBoardDTO;
import model.event.AdminEventDAO;
import model.event.AdminEventDTO;

public class BoardController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		switch(req.getParameter("type")) {
		case "admin" :
			List<AdminEventDTO> adminList;
			AdminEventDAO adminDao = new AdminEventDAO(req.getServletContext());
			if(req.getParameter("board").equals("recent")) {
				adminList = adminDao.selectList();
			}
			else adminList = adminDao.selectList(req.getParameter("board"));
			adminDao.close();
			req.setAttribute("list", adminList);
			req.getRequestDispatcher("/backend/event/EventList.jsp").forward(req, resp);
			break;
		case "customer" :
			CustomerBoardDAO customDao = new CustomerBoardDAO(req.getServletContext());
			List<CustomerBoardDTO> customList = customDao.selectList();
			customDao.close();
			req.setAttribute("list", customList);
			req.getRequestDispatcher("/backend/member/customer/Board.jsp").forward(req, resp);
			break;
		case "seller" :
			SellerBoardDAO sellDao = new SellerBoardDAO(req.getServletContext());
			List<SellerBoardDTO> sellList = sellDao.selectList();
			sellDao.close();
			req.setAttribute("list", sellList);
			req.getRequestDispatcher("/backend/member/seller/Board.jsp").forward(req, resp);
			break;
		}
	}
	 
}
