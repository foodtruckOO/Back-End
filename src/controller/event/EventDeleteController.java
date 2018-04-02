package controller.event;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FileUtils;
import model.event.AdminEventDAO;
import model.event.AdminEventDTO;
import model.member.AdminDAO;
import model.member.AdminDTO;

public class EventDeleteController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AdminEventDAO dao = new AdminEventDAO(req.getServletContext());
		AdminEventDTO dto = dao.selectOne(req.getParameter("no"));
		int affected = dao.delete(req.getParameter("no"));
		dao.close();
		if(affected==1) {//글 삭제에 성공했다면 그 글에 따른 첨부파일도 삭제해야 한다.
			System.out.println("삭제성공함");
			String contentfileName = dto.getContentfile();
			//String contentfilePath = req.getServletContext().getRealPath("/backend/img/admin/"+dto.getId())+File.separator+contentfileName;
			String titlefileName = dto.getTitlefile();
			//String titlefilePath = req.getServletContext().getRealPath("/backend/img/admin/"+dto.getId())+File.separator+titlefileName;
			FileUtils.delete(req, "/backend/img/admin/"+dto.getId(), contentfileName);
			FileUtils.delete(req, "/backend/img/admin/"+dto.getId(), titlefileName);
		}
		req.setAttribute("WHERE", "EVENTDELETE");
		req.setAttribute("SUC_FAIL", affected);
		req.setAttribute("eno", req.getParameter("no"));
		req.getRequestDispatcher("/backend/pages/common/Fail.jsp").forward(req, resp);
	}
}
