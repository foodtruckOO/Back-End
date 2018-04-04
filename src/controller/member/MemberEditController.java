package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.CustomerDAO;
import model.member.CustomerDTO;
import model.member.SellerDAO;
import model.member.SellerDTO;

public class MemberEditController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type");
		String no = req.getParameter("no");
		System.out.println(type);
		System.out.println(no);
		if(type.equals("customer")) {
			CustomerDAO dao = new CustomerDAO(req.getServletContext());
			req.setAttribute("editDto", dao.selectOne(no));
			dao.close();
			req.getRequestDispatcher("/backend/member/customer/MemberEdit.jsp").forward(req, resp);
		}
		
		else if(type.equals("seller")) {//판매자꺼
			SellerDAO dao = new SellerDAO(req.getServletContext());
			req.setAttribute("editDto", dao.selectOne(no));
			dao.close();
			req.getRequestDispatcher("/backend/member/seller/MemberEdit.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type=req.getParameter("type");
		String no = req.getParameter("no");
		int affected = 0;
		if(type.equals("customer")) {//고객꺼 수정
			CustomerDTO dto = new CustomerDTO();
			dto.setG_no(no);
			dto.setId(req.getParameter("id"));
			dto.setPwd(req.getParameter("pwd"));
			dto.setName(req.getParameter("name"));
			dto.setTel(req.getParameter("tel"));
			CustomerDAO dao = new CustomerDAO(req.getServletContext());
			affected = dao.update(dto);
			dao.close();
			req.setAttribute("WHERE", "CUSTOMEREDIT");
		}
		else if(type.equals("seller")) {//업자꺼 수정
			SellerDTO dto = new SellerDTO();
			dto.setS_no(no);
			dto.setId(req.getParameter("id"));
			dto.setPwd(req.getParameter("pwd"));
			dto.setName(req.getParameter("name"));
			dto.setTname(req.getParameter("tname"));
			dto.setAddr(req.getParameter("addr"));
			dto.setTel(req.getParameter("tel"));
			dto.setCorporate_no(req.getParameter("corpno"));
			SellerDAO dao = new SellerDAO(req.getServletContext());
			affected = dao.update(dto);
			dao.close();
			req.setAttribute("WHERE", "SELLEREDIT");
		}
		req.setAttribute("SUC_FAIL", affected);
		req.getRequestDispatcher("/backend/pages/common/Fail.jsp").forward(req, resp);
	}
}
