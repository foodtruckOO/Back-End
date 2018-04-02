package controller.event;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jni.File;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sun.glass.ui.Application;

import model.FileUtils;
import model.event.AdminEventDAO;
import model.event.AdminEventDTO;
import model.member.AdminDTO;

public class EventWriteControllerOriginal extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("s_date")!=null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String[] datearray = req.getParameter("s_date").split(" ");
			String newDate = datearray[3]+"-"+getMonthNumberByName(datearray[1])+"-"+datearray[2];
			System.out.println(newDate);
			req.setAttribute("s_date", newDate);
			req.getRequestDispatcher("/backend/event/EventWrite.jsp").forward(req, resp);
		}
		else resp.sendRedirect(req.getContextPath()+"/backend/event/EventWrite.jsp");
	}
	/*@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getParameter("startdate"));
		System.out.println(req.getParameter("enddate"));
		AdminEventDTO dto = new AdminEventDTO();
		//한글깨짐관련 조치사항
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		
		int affected=0;
		dto.setA_no(((AdminDTO)req.getSession().getAttribute("dto")).getA_no());
		dto.setTitle(req.getParameter("title"));
		dto.setContent(req.getParameter("content"));
		dto.setBoardtype(req.getParameter("boardtype"));
		dto.setTitlefile(req.getParameter("titleFile"));
		dto.setContentfile(req.getParameter("contentFile"));
		
		///////////////파일업로드 관련 조치사항들
		//디렉토리생성 관련
		String savePath = req.getServletContext().getRealPath("/backend/img/admin/"+((AdminDTO)req.getSession().getAttribute("dto")).getId());
		//backend/img/admin/아이디로 할 생각
		java.io.File targetDir = new java.io.File(savePath);
		if(!targetDir.exists())targetDir.mkdirs();//디렉토리 없으면 만든다는 소리임
		//디렉토리생성 끝
		if(req.getParameter("contentFile")!=null) {
			MultipartRequest mr = FileUtils.upload(req, savePath);//업로드하기
			
		}
		try {
			dto.setS_date(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("startdate")).getTime()));
			dto.setE_date(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("enddate")).getTime()));
		} catch (ParseException e) {
			System.out.println("시간 변환 과정에서 문제 발생함");
			e.printStackTrace();
		}
		AdminEventDAO dao = new AdminEventDAO(req.getServletContext());
		String sf="";
		affected=dao.insert(dto);
		if(affected==0) {
			sf="0";
		}
		else sf="1";
		dao.close();	
		req.setAttribute("SUC_FAIL", sf);
		req.setAttribute("WHERE", "EVENTWRITE");		
		req.setAttribute("boardtype", req.getParameter("boardtype"));
		req.getRequestDispatcher("/backend/pages/common/Fail.jsp").forward(req, resp);
		//타입 2 = 지역이벤트
	}*/
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//한글깨짐관련 조치사항
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String savePath = req.getServletContext().getRealPath("/backend/img/admin/"+((AdminDTO)req.getSession().getAttribute("dto")).getId());
		//backend/img/admin/아이디로 할 생각
		java.io.File targetDir = new java.io.File(savePath);
		if(!targetDir.exists())targetDir.mkdirs();//디렉토리 없으면 만든다는 소리임
		//디렉토리생성 끝
		MultipartRequest mr = FileUtils.upload(req, savePath);//업로드하기
		int affected=0;
		
		AdminEventDTO dto = new AdminEventDTO();
		dto.setA_no(((AdminDTO)req.getSession().getAttribute("dto")).getA_no());
		dto.setTitle(mr.getParameter("title"));
		dto.setContent(mr.getParameter("content"));
		dto.setBoardtype(mr.getParameter("boardtype"));
		dto.setTitlefile(mr.getOriginalFileName("titleFile"));
		dto.setContentfile(mr.getOriginalFileName("contentFile"));
		///////////////파일업로드 관련 조치사항들
		//디렉토리생성 관련
		try {
			dto.setS_date(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(mr.getParameter("startdate")).getTime()));
			dto.setE_date(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(mr.getParameter("enddate")).getTime()));
		} catch (ParseException e) {
			System.out.println("시간 변환 과정에서 문제 발생함");
			e.printStackTrace();
		}
		AdminEventDAO dao = new AdminEventDAO(req.getServletContext());
		String sf="";
		affected=dao.insert(dto);
		if(affected==0) {
			sf="0";
		}
		else sf="1";
		dao.close();
		req.setAttribute("SUC_FAIL", sf);
		req.setAttribute("WHERE", "EVENTWRITE");		
		req.setAttribute("boardtype", req.getParameter("boardtype"));
		req.getRequestDispatcher("/backend/pages/common/Fail.jsp").forward(req, resp);
		//타입 2 = 지역이벤트
	}
	private String getMonthNumberByName(String name) {
		String result = "0";
		switch(name) {
			case "Jan":result="01";break;
			case "Feb":result="02";break;
			case "Mar":result="03";break;
			case "Apr":result="04";break;
			case "May":result="05";break;
			case "Jun":result="06";break;
			case "Jul":result="07";break;
			case "Aug":result="08";break;
			case "Sep":result="09";break;
			case "Oct":result="10";break;
			case "Nov":result="11";break;
			case "Dec":result="12";break;		
		}
		return result;
	}
}
