package controller.member;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import model.FileUtils;
import model.member.AdminDTO;
import model.member.NoMemberDAO;
import model.member.NoMemberDTO;
import model.member.SellerDAO;
import model.member.SellerDTO;

public class TruckEditController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		int affected=0;
		String savePath = req.getServletContext().getRealPath("/backend/img/noMember");
		MultipartRequest mr = FileUtils.upload(req, savePath);
		//System.out.println("넘어옴"+req.getParameter("no")+", "+req.getParameter("name")+", "+req.getParameter("tel")+", "+req.getParameter("addr")+", "+req.getParameter("corpNo"));
		if(mr.getParameter("cc").equals("10")) {//회원트럭은 여기서 처리
			
			SellerDTO dto = new SellerDTO();
			dto.setS_no(mr.getParameter("no"));
			dto.setTname(mr.getParameter("name"));
			dto.setTel(mr.getParameter("tel"));
			dto.setAddr(mr.getParameter("addr"));
			dto.setAddr2(mr.getParameter("addr2"));
			dto.setCorporate_no(mr.getParameter("corpNo"));
			SellerDAO dao = new SellerDAO(req.getServletContext());
			affected = dao.updateTruckOnly(dto);
			dao.close();
		}
		else {//비회원트럭은 여기서 처리
			File targetDir = new File(savePath);
			if(!targetDir.exists())targetDir.mkdirs();//디렉토리 없으면 만든다는 소리임
			NoMemberDTO dto = new NoMemberDTO();
			NoMemberDAO dao = new NoMemberDAO(req.getServletContext());
			System.out.println("출력준비중");
			System.out.println(String.format("%s, %s, %s, %s, %s", mr.getParameter("no"), mr.getParameter("name"), mr.getParameter("tel"), mr.getParameter("addr"), mr.getParameter("addr2")));
			dto.setF_no(mr.getParameter("no"));
			dto.setTname(mr.getParameter("name"));
			dto.setTel(mr.getParameter("tel"));
			dto.setAddr(mr.getParameter("addr"));
			dto.setAddr2(mr.getParameter("addr2"));
			if(mr.getOriginalFileName("attachedFile")!=null) {
				File attachedFile = new File(req.getServletContext().getRealPath("/backend/img/noMember")+File.separator+mr.getOriginalFileName("attachedFile"));
				System.out.println(mr.getOriginalFileName("attachedFile"));
				File attachedNewFile = new File(req.getServletContext().getRealPath("/backend/img/noMember")+File.separator+mr.getParameter("no")+"_"+mr.getOriginalFileName("attachedFile"));
				attachedFile.renameTo(attachedNewFile);
				System.out.println(mr.getOriginalFileName("attachedFile"));
				dto.setAttachedFile(mr.getParameter("no")+"_"+mr.getOriginalFileName("attachedFile"));
			}
			else dto.setAttachedFile(dao.selectOne(mr.getParameter("no")).getAttachedFile());//파일 첨부 별도로 안 했다면(파일 안바꿨다면) 기존파일 그대로 넣도록 해 줘야 함.
			affected = dao.update(dto);
			dao.close();
		}
		req.setAttribute("WHERE", "NOMEMBEREDIT");
		req.setAttribute("SUC_FAIL", affected);
		req.getRequestDispatcher("/backend/pages/common/Fail.jsp").forward(req, resp);
	}
}
