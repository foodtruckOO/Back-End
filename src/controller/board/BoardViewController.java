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
import model.event.TruckEventDAO;
import model.event.TruckEventDTO;
import model.food.FoodDAO;
import model.food.FoodDTO;

public class BoardViewController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("selNo")!=null) {//판매자 회원 쪽의 것들임
			SellerBoardDAO boardMaindao = new SellerBoardDAO(req.getServletContext());
			String no = req.getParameter("selNo");
			SellerBoardDTO selBoard = boardMaindao.selectOne(no);
			boardMaindao.close();
			req.setAttribute("selBoard", selBoard);
			req.getRequestDispatcher("/backend/member/seller/View.jsp").forward(req, resp);
		}
		else if(req.getParameter("cusNo")!=null) {//소비자 회원측 요소들
			CustomerBoardDAO dao = new CustomerBoardDAO(req.getServletContext());
			String no = req.getParameter("cusNo");
			CustomerBoardDTO cusBoard = dao.selectOne(no);
			dao.close();
			req.setAttribute("cusBoard", cusBoard);
			req.getRequestDispatcher("/backend/member/customer/View.jsp").forward(req, resp);
		}
	}
	
}
