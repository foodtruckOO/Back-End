package controller.event;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.event.AdminEventDAO;
import model.event.AdminEventDTO;

public class EventEditControllerOriginal extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String eno=req.getParameter("no");
		AdminEventDAO dao = new AdminEventDAO(req.getServletContext());
		AdminEventDTO dto = dao.selectOne(eno);
		dao.close();
		dto.setContent(dto.getContent().replaceAll("</br>", "\r\n"));
		req.setAttribute("eventdto", dto);
		req.getRequestDispatcher("/backend/event/EventEdit.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String eno = req.getParameter("no");
		AdminEventDAO dao = new AdminEventDAO(req.getServletContext());
		AdminEventDTO dto = new AdminEventDTO();
		dto.setEno(eno);
		dto.setTitle(req.getParameter("title"));
		dto.setContent(req.getParameter("content"));
		if(req.getParameter("attachedfile").equals("") || req.getParameter("attachedfile")==null) {
			dto.setTitlefile(dao.selectOne(eno).getTitlefile());
		}
		else {
			dto.setTitlefile(req.getParameter("attachedfile"));
		}
		dto.setBoardtype(req.getParameter("boardtype"));
		try {
			dto.setS_date(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("startdate")).getTime()));
			dto.setE_date(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("enddate")).getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//System.out.println(dto.getEno()+dto.getAttachedfile()+dto.getId()+dto.getTitle()+dto.getContent());
		int affected = dao.edit(dto);
		dao.close();
		req.setAttribute("WHERE", "EVENTEDIT");
		if(affected==0) {//fail시
			req.setAttribute("SUC_FAIL", "0");
		}//<c:url value='/Back/EventView.do?eh_no=${item.eno}'/>
		else {
			req.setAttribute("SUC_FAIL", "1");
			req.setAttribute("eno", dto.getEno());
		}
		req.getRequestDispatcher("/backend/pages/common/Fail.jsp").forward(req, resp);
	}
}
