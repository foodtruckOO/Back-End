package controller.member;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.oreilly.servlet.MultipartRequest;

import model.FileUtils;
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
		//파일이 추가되어 multifile어쩌구를 써야 함
		NoMemberDAO dao = new NoMemberDAO(req.getServletContext());
		NoMemberDTO dto = new NoMemberDTO();
		int max = dao.getNextVal();
		String savePath = req.getServletContext().getRealPath("/backend/img/noMember");
		File targetDir = new File(savePath);
		if(!targetDir.exists())targetDir.mkdirs();//디렉토리 없으면 만든다는 소리임
		MultipartRequest mr = FileUtils.upload(req, savePath);//업로드하기
		File contentFile = new File(req.getServletContext().getRealPath("/backend/img/noMember")+File.separator+mr.getOriginalFileName("attachedFile"));
		File contentNewFile = new File(req.getServletContext().getRealPath("/backend/img/noMember/"+(max+1))+File.separator+mr.getOriginalFileName("attachedFile"));
		contentFile.renameTo(contentNewFile);
		String addr = mr.getParameter("roadAddrPart1").equals("선택하신 곳에서 도로명주소를 얻을 수 없습니다.")?mr.getParameter("roadAddrPart1_5"):mr.getParameter("roadAddrPart1");
		dto.setTname(mr.getParameter("tname"));
		dto.setTel(mr.getParameter("tel"));
		dto.setAddr(addr);
		dto.setAddr2(mr.getParameter("addrDetail"));
		dto.setAttachedFile(mr.getOriginalFileName("attachedFile"));
		int affected = dao.insert(dto);
		dao.close();
		req.setAttribute("WHERE", "NOMEMBERREGISTER");
		req.setAttribute("SUC_FAIL", affected);
		req.getRequestDispatcher("/backend/pages/common/Fail.jsp").forward(req, resp);
	}
}
