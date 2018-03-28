package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.NoMemberDAO;
import model.member.NoMemberDTO;
import model.member.SellerDAO;
import model.member.SellerDTO;

public class TruckEditController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		int affected=0;
		//System.out.println("넘어옴"+req.getParameter("no")+", "+req.getParameter("name")+", "+req.getParameter("tel")+", "+req.getParameter("addr")+", "+req.getParameter("corpNo"));
		if(req.getParameter("cc").equals("9")) {//회원트럭은 여기서 처리
			SellerDTO dto = new SellerDTO();
			dto.setS_no(req.getParameter("no"));
			dto.setTname(req.getParameter("name"));
			dto.setTel(req.getParameter("tel"));
			dto.setAddr(req.getParameter("addr"));
			dto.setCorporate_no(req.getParameter("corpNo"));
			SellerDAO dao = new SellerDAO(req.getServletContext());
			affected = dao.updateTruckOnly(dto);
			dao.close();
		}
		else {//비회원트럭은 여기서 처리
			NoMemberDTO dto = new NoMemberDTO();
			dto.setF_no(req.getParameter("no"));
			dto.setTname(req.getParameter("name"));
			dto.setTel(req.getParameter("tel"));
			dto.setAddr(req.getParameter("addr"));
			dto.setCorporate_no(req.getParameter("corpNo"));
			NoMemberDAO dao = new NoMemberDAO(req.getServletContext());
			affected = dao.update(dto);
			dao.close();
		}
		req.setAttribute("WHERE", "NOMEMBEREDIT");
		req.setAttribute("SUC_FAIL", affected);
		req.getRequestDispatcher("/backend/pages/common/Fail.jsp").forward(req, resp);
	}
}
