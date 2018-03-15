package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javafx.scene.control.Alert;
import model.member.AdminDAO;

public class IdCheckController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getParameter("id"));
		String id = req.getParameter("id");
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		AdminDAO dao = new AdminDAO(req.getServletContext());
		String isNotDuple = dao.idCheck(id)?"사용 가능한 아이디입니다":"이미 같은 아이디가 존재합니다";
		dao.close();
		if(id.trim().length()<1) isNotDuple="아이디는 최소 2자 이상 입력하셔야 합니다";
		PrintWriter out = resp.getWriter();
/*		out.println("<script>");
		out.println("alert('"+isNotDuple+"');");
		out.println("window.close();");
		out.println("</script>");
		out.close();*/
		out.print(isNotDuple);
	}
}
