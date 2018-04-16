package controller.event;

import java.io.File;
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


import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sun.glass.ui.Application;

import model.FileUtils;
import model.event.AdminEventDAO;
import model.event.AdminEventDTO;
import model.member.AdminDTO;

public class EventWriteController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("s_date")!=null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String[] datearray = req.getParameter("s_date").split(" ");
			String newDate = datearray[3]+"-"+getMonthNumberByName(datearray[1])+"-"+datearray[2];
			req.setAttribute("s_date", newDate);
			AdminDTO dtto = ((AdminDTO)req.getSession().getAttribute("dto"));
			req.getRequestDispatcher("/backend/event/EventWrite.jsp").forward(req, resp);
		}
		else resp.sendRedirect(req.getContextPath()+"/backend/event/EventWrite.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//한글깨짐관련 조치사항
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		AdminDTO dtto = ((AdminDTO)req.getSession().getAttribute("dto"));
		String savePath = req.getServletContext().getRealPath("/backend/img/admin/"+dtto.getId());
		AdminEventDAO dao = new AdminEventDAO(req.getServletContext());
		String nowNum = (dao.selectLatestNo()+1)+"_";
		//backend/img/admin/아이디로 할 생각
		File targetDir = new File(savePath);
		if(!targetDir.exists())targetDir.mkdirs();//디렉토리 없으면 만든다는 소리임
		//디렉토리생성 끝
		MultipartRequest mr = FileUtils.upload(req, savePath);//업로드하기
		/////////////////////////////////////////////파일 이름 바꾸는 로직(글번호_파일명 의 방식임)
		File contentFile = new File(req.getServletContext().getRealPath("/backend/img/admin/"+dtto.getId())+File.separator+mr.getOriginalFileName("contentFile"));
		File contentNewFile = new File(req.getServletContext().getRealPath("/backend/img/admin/"+dtto.getId())+File.separator+nowNum+mr.getOriginalFileName("contentFile"));
		File titleFile = new File(req.getServletContext().getRealPath("/backend/img/admin/"+((AdminDTO)req.getSession().getAttribute("dto")).getId())+File.separator+mr.getOriginalFileName("titleFile"));
		File titleNewFile = new File(req.getServletContext().getRealPath("/backend/img/admin/"+((AdminDTO)req.getSession().getAttribute("dto")).getId())+File.separator+nowNum+mr.getOriginalFileName("titleFile"));
		contentFile.renameTo(contentNewFile);
		titleFile.renameTo(titleNewFile);
		///////////////////////
		///////////////////////
		int affected=0;
		if(mr!=null) {//업로드가 성공되었을 때 한해서 입력조치를 한다.
			AdminEventDTO dto = new AdminEventDTO();
			dto.setA_no(((AdminDTO)req.getSession().getAttribute("dto")).getA_no());//글쓰는놈 관리자번호
			dto.setTitle(mr.getParameter("title"));
			dto.setContent(mr.getParameter("content"));
			dto.setBoardtype(mr.getParameter("boardtype"));
			dto.setTitlefile(nowNum+mr.getOriginalFileName("titleFile"));
			dto.setContentfile(nowNum+mr.getOriginalFileName("contentFile"));
			///////////////파일업로드 관련 조치사항들
			//디렉토리생성 관련
			try {//시간변환관련로직
				dto.setS_date(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(mr.getParameter("startdate")).getTime()));
				dto.setE_date(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(mr.getParameter("enddate")).getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			affected=dao.insert(dto);
			dao.close();
		}
		req.setAttribute("SUC_FAIL", affected);
		req.setAttribute("WHERE", "EVENTWRITE");		
		req.setAttribute("boardtype", mr.getParameter("boardtype"));
		req.getRequestDispatcher("/backend/pages/common/Fail.jsp?board=2").forward(req, resp);
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
