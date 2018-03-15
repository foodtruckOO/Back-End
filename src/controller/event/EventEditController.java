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

public class EventEditController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("파라미터값 : "+req.getParameter("no"));
		String eno=req.getParameter("no");
		AdminEventDAO dao = new AdminEventDAO(req.getServletContext());
		AdminEventDTO dto = dao.selectOne(eno);
		dao.close();
		System.out.println(dto.getContent());
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
		dto.setBoardtype(req.getParameter("boardtype"));
		try {
			dto.setS_date(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("startdate")).getTime()));
			dto.setE_date(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("enddate")).getTime()));
		} catch (ParseException e) {
			System.out.println("시간 변환 과정에서 문제 발생함");
			e.printStackTrace();
		}
		dto.setAttachedfile(req.getParameter("attachedfile"));
		System.out.println(dto.getEno()+dto.getAttachedfile()+dto.getId()+dto.getTitle()+dto.getContent());
		int affected = dao.edit(dto);
		System.out.println(affected+"행이 영향받음");
		dao.close();
		req.setAttribute("WHERE", "EVENTEDIT");
		if(affected==0) {//fail시
			System.out.println("수정실패");
			req.setAttribute("SUC_FAIL", "0");
		}//<c:url value='/Back/EventView.do?eh_no=${item.eno}'/>
		else {
			System.out.println("수정성공");
			req.setAttribute("SUC_FAIL", "1");
			req.setAttribute("eno", dto.getEno());
		}
		req.getRequestDispatcher("/backend/pages/common/Fail.jsp").forward(req, resp);
	}
}
