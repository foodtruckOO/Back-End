package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.AdminDAO;
import model.member.AdminDTO;

public class RegisterController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println("회원가입버튼 거쳐서 컨트롤러 타고 왔다");
		req.getRequestDispatcher("/backend/pages/Register.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println("회원정보 기입후 포스트방식으로 넘어왔다");
		req.setAttribute("WHERE", "REGISTER");
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		//System.out.println(String.format("아이디:%s, 비번:%s, 비번확인:%s, 이메일:%s", req.getParameter("id"),req.getParameter("pwd"), req.getParameter("pwd2"), req.getParameter("email")));
		//아이디 중복여부 체크하기
		AdminDAO dao = new AdminDAO(req.getServletContext());
		boolean isNotExist = dao.idCheck(req.getParameter("id"));
		if(!isNotExist) {
			System.out.println("컨트롤러 : 아이디 중복");
			req.setAttribute("DUPLE", "YES");
			req.setAttribute("SUC_FAIL", 0);
			req.getRequestDispatcher("/backend/pages/common/Fail.jsp").forward(req, resp);
		}
		else {//아이디 중복이 아님
			System.out.println("컨트롤러 : 아이디가 중복되지 않음");
			AdminDTO dto = new AdminDTO();
			dto.setId(req.getParameter("id"));
			dto.setPwd(req.getParameter("pwd"));
			dto.setEmail(req.getParameter("email"));
			int affected = 0;
			affected= dao.insert(dto);
			//System.out.println(affected);
			req.setAttribute("DUPLE", "NO");
			req.setAttribute("SUC_FAIL", affected);
			/*if(affected==0) {
				//req.getRequestDispatcher("/backend/pages/Register.jsp").forward(req, resp);
			}
			else{
				//req.setAttribute("message", "회원가입에 성공했습니다. 마스터 관리자에게 권한 부여를 요청하십시오. 권한부여 전까지는 로그인이 불가능합니다");
				//req.getRequestDispatcher("/backend/pages/Login.jsp").forward(req, resp);
			}*/
			req.getRequestDispatcher("/backend/pages/common/Fail.jsp").forward(req, resp);
		}
		//req.getRequestDispatcher("/backend/pages/common/Fail.jsp").forward(req, resp);
	}
}
