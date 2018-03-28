package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.NoMemberDAO;
import model.member.SellerDAO;

public class TruckDeleteController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String no=req.getParameter("no");
		int affected=0;
		if(req.getParameter("cc").equals("9")) {//회원트럭삭제 - 회원자체를 삭제하게 됨...
			SellerDAO dao = new SellerDAO(req.getServletContext());
			affected = dao.delete(no);
			dao.close();
		}
		else {
			NoMemberDAO dao = new NoMemberDAO(req.getServletContext());
			affected = dao.delete(no);
			dao.close();
		}
		req.setAttribute("WHERE", "TRUCKDELETE");
		req.setAttribute("SUC_FAIL", affected);
		req.getRequestDispatcher("/backend/pages/common/Fail.jsp").forward(req, resp);
	}
}
