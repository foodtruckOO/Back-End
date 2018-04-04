package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import model.member.NoMemberDAO;
import model.member.NoMemberDTO;

public class NoMemberRegisterController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/backend/member/NoMemberTruckRegister.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String addr = req.getParameter("roadAddrPart1").equals("선택하신 곳에서 도로명주소를 얻을 수 없습니다.")?req.getParameter("roadAddrPart1_5"):req.getParameter("roadAddrPart1");
		String menuString = req.getParameter("menuList");//메뉴가 담긴 리스트임
		System.out.println(menuString);
		NoMemberDTO dto = new NoMemberDTO();
		NoMemberDAO dao = new NoMemberDAO(req.getServletContext());
		dto.setTname(req.getParameter("tname"));
		dto.setTel(req.getParameter("tel"));
		dto.setAddr(addr);
		dto.setAttachedFile(req.getParameter("attachedFile"));
		int affected = dao.insert(dto);
		if(affected==1&&menuString!=null) {//이 때 메뉴 추가조치 실시합니...다...
			//여기서 jsonString을 map으로 변환하거나 해야 할 듯 하다
		}
		dao.close();
		req.setAttribute("WHERE", "NOMEMBERREGISTER");
		req.setAttribute("SUC_FAIL", affected);
		req.getRequestDispatcher("/backend/pages/common/Fail.jsp").forward(req, resp);
	}
}
