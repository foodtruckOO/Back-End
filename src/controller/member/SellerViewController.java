package controller.member;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.event.TruckEventDAO;
import model.event.TruckEventDTO;
import model.food.FoodDAO;
import model.food.FoodDTO;
import model.member.SellerDAO;
import model.member.SellerDTO;

public class SellerViewController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String s_no = req.getParameter("s_no");
		SellerDAO sellerDao = new SellerDAO(req.getServletContext());
		SellerDTO sellerInfo = sellerDao.selectOne(s_no);
		FoodDAO fooddao = new FoodDAO(req.getServletContext());
		List<FoodDTO> foodList = fooddao.selectList(s_no);
		TruckEventDAO tedao = new TruckEventDAO(req.getServletContext());
		List<TruckEventDTO> tEventList = tedao.selectList(s_no);
		sellerDao.close();
		fooddao.close();
		tedao.close();
		
		req.setAttribute("ip", InetAddress.getLocalHost().getHostAddress());
		req.setAttribute("sellerInfo", sellerInfo);
		req.setAttribute("foodList", foodList);
		req.setAttribute("tEventList", tEventList);		
		req.getRequestDispatcher("/backend/member/seller/SellerView.jsp").forward(req, resp);
	}
}
