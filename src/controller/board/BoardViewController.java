package controller.board;

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

public class BoardViewController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("selNo")!=null) {
			SellerBoardDAO dao = new SellerBoardDAO(req.getServletContext());
			String no = req.getParameter("selNo");
			SellerBoardDTO selBoard = dao.selectOne(no);
			req.setAttribute("selBoard", selBoard);
			req.getRequestDispatcher("/backend/member/seller/View.jsp").forward(req, resp);
		}
		else if(req.getParameter("cusNo")!=null) {
			CustomerBoardDAO dao = new CustomerBoardDAO(req.getServletContext());
			String no = req.getParameter("cusNo");
			CustomerBoardDTO cusBoard = dao.selectOne(no);
			req.setAttribute("cusBoard", cusBoard);
			req.getRequestDispatcher("/backend/member/customer/View.jsp").forward(req, resp);
		}
	}
	
}
