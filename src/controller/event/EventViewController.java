package controller.event;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.event.AdminEventDAO;
import model.event.AdminEventDTO;

public class EventViewController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("뷰폼이고 eno 받아오는지 여부 : "+req.getParameter("eno"));
		AdminEventDAO dao = new AdminEventDAO(req.getServletContext());
		AdminEventDTO dto = dao.selectOne(req.getParameter("eno"));
		dao.close();
		String contentfilePath = "/backend/img/admin/"+dto.getId()+File.separator+dto.getContentfile();
		System.out.println(contentfilePath);
		String titlefilePath = "/backend/img/admin/"+dto.getId()+File.separator+dto.getTitlefile();
		dto.getContentfile();
		req.setAttribute("contentPath", contentfilePath);
		req.setAttribute("titlePath", titlefilePath);
		req.setAttribute("eventdto", dto);
		req.getRequestDispatcher("/backend/event/View.jsp").forward(req, resp);
	}
}
