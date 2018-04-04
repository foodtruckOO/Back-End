package controller.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.food.FoodDAO;
import model.food.FoodDTO;
import model.member.AdminDAO;
import model.member.CustomerDAO;
import model.member.SellerDAO;

public class MemberDeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type=req.getParameter("type");
		String no = req.getParameter("no");
		int affected=0;
		if(type.length()==8) {//고객
			CustomerDAO dao = new CustomerDAO(req.getServletContext());
			affected = dao.delete(no);
		}
		else {//판매자
			SellerDAO dao = new SellerDAO(req.getServletContext());
			affected = dao.delete(no);
			if(affected==1) {//해당 사용자와 관련된 모든 메뉴도 삭제하는 로직이 필요할 것 같다?
				FoodDAO fooDao = new FoodDAO(req.getServletContext());
				List<FoodDTO> list = fooDao.selectList(no);
				if(list.size()>0) {
					fooDao.delete(no, fooDao.DELETE_WITH_SNO);
					
				}
			}
		}
		req.setAttribute("SUC_FAIL", affected);
		req.getRequestDispatcher("/backend/pages/common/Message.jsp").forward(req, resp);
	}
}
