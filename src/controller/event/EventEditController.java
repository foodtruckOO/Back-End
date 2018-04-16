package controller.event;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import model.FileUtils;
import model.event.AdminEventDAO;
import model.event.AdminEventDTO;
import model.member.AdminDTO;

public class EventEditController extends HttpServlet{
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
		int affected = 0;
		AdminDTO writerDTO = (AdminDTO)req.getSession().getAttribute("dto");
		AdminEventDAO dao = new AdminEventDAO(req.getServletContext());
		AdminEventDTO dto = new AdminEventDTO();
		String savePath = req.getServletContext().getRealPath("/backend/img/admin/"+writerDTO.getId());
		File targetDir = new File(savePath);
		if(!targetDir.exists())targetDir.mkdirs();//상황을 보니 메타데이터에 올라가는거다 보니까 매번 해 줘야 하는 것 같다... 아마도
		MultipartRequest mr = FileUtils.upload(req, savePath);//업로드하기 - 파일명중복이면 큰일날듯...
		if(mr!=null) {
			dto.setA_no(writerDTO.getA_no());//글쓰는놈 관리자번호
			String nowNum = (dao.selectLatestNo()+1)+"_";
			AdminEventDTO originalDTO = dao.selectOne(mr.getParameter("no"));
			dto.setEno(mr.getParameter("no"));
			dto.setTitle(mr.getParameter("title"));
			dto.setContent(mr.getParameter("content"));
			dto.setBoardtype(mr.getParameter("boardtype"));
			try {//시간변환관련로직-sdate,edate관련파트
				dto.setS_date(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(mr.getParameter("startdate")).getTime()));
				dto.setE_date(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(mr.getParameter("enddate")).getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			//파일 바꿨는지 안바꿨는지에 따라서 조치하는 로직 시작//
			if(mr.getOriginalFileName("titleFile")!=null) {//파일변경
				File titleFile = new File(req.getServletContext().getRealPath("/backend/img/admin/"+writerDTO.getId())+File.separator+mr.getOriginalFileName("titleFile"));
				File titleNewFile = new File(req.getServletContext().getRealPath("/backend/img/admin/"+writerDTO.getId())+File.separator+nowNum+mr.getOriginalFileName("titleFile"));
				dto.setTitlefile(nowNum+mr.getOriginalFileName("titleFile"));
				titleFile.renameTo(titleNewFile);
			}
			else dto.setTitlefile(originalDTO.getTitlefile());
			
			if(mr.getOriginalFileName("contentFile")!=null) {//파일변경
				File contentFile = new File(req.getServletContext().getRealPath("/backend/img/admin/"+writerDTO.getId())+File.separator+mr.getOriginalFileName("contentFile"));
				File contentNewFile = new File(req.getServletContext().getRealPath("/backend/img/admin/"+writerDTO.getId())+File.separator+nowNum+mr.getOriginalFileName("contentFile"));
				contentFile.renameTo(contentNewFile);
				dto.setContentfile(nowNum+mr.getOriginalFileName("contentFile"));//새로운 이름의 파일을 저장함
				//기존파일 삭제하는 로직을 여기 넣어야 하는데... 싫다. 일단 저 안에 같은 파일이 존재하냐 마냐도 따져야 할 거 같은데 어렵다.일단은.
			}
			else dto.setContentfile(originalDTO.getContentfile());
			//파일 바꿨는지 안바꿨는지에 따라서 조치하는 로직 끝//
			affected = dao.edit(dto);
		}
		dao.close();
		req.setAttribute("WHERE", "EVENTEDIT");
		req.setAttribute("SUC_FAIL", affected);
		req.setAttribute("eno", dto.getEno());
		req.setAttribute("boardtype", mr.getParameter("boardtype"));
		req.getRequestDispatcher("/backend/pages/common/Fail.jsp?board=2").forward(req, resp);
	}
}
