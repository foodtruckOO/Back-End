package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.AdminDAO;
import model.member.AdminDTO;

public class EditController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String no = req.getParameter("no");
		AdminDAO dao = new AdminDAO(req.getServletContext());
		AdminDTO dto = dao.selectOne(no);
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/backend/member/administrator/AdminEdit.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String a_no = req.getParameter("a_no");
		String grade = req.getParameter("grade");
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		String email = req.getParameter("email");
		//System.out.println(String.format("저장 전 단계==>no=%s, grade=%s, id=%s, pwd=%s, email=%s", a_no, grade, id, pwd, email));
		AdminDTO dto = new AdminDTO();
		dto.setA_no(a_no);
		dto.setGrade(grade);
		dto.setId(id);
		dto.setPwd(pwd);
		dto.setEmail(email);
		//System.out.println(String.format("저장 후 단계==>no=%s, grade=%s, id=%s, pwd=%s, email=%s", dto.getA_no(), dto.getGrade(), dto.getId(), dto.getPwd(), dto.getEmail()));
		AdminDAO dao = new AdminDAO(req.getServletContext());
		int affected = dao.update(dto);
		req.setAttribute("SUC_FAIL", affected);
		req.setAttribute("WHERE", "EDT");
		req.getRequestDispatcher("/backend/pages/common/Message.jsp").forward(req, resp);
	}
}
