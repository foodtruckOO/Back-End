package controller.event;

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
		int affected = 0;
		AdminDTO writerDTO = (AdminDTO)req.getSession().getAttribute("dto");
		AdminEventDAO dao = new AdminEventDAO(req.getServletContext());
		AdminEventDTO dto = new AdminEventDTO();
		String savePath = req.getServletContext().getRealPath("/backend/img/admin/"+writerDTO.getId());
		MultipartRequest mr = FileUtils.upload(req, savePath);//업로드하기 - 파일명중복이면 큰일날듯...
		System.out.println("타이틀파일:"+mr.getParameter("titleFile"));
		System.out.println("컨텐츠파일:"+mr.getParameter("contentFile"));
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
				System.out.println("시간 변환 과정에서 문제 발생함");
				e.printStackTrace();
			}
			//파일 바꿨는지 안바꿨는지에 따라서 조치하는 로직 시작//
			
			if(mr.getOriginalFileName("titleFile")!=null) {
				dto.setTitlefile(nowNum+mr.getOriginalFileName("titleFile"));
				System.out.println(mr.getOriginalFileName("titleFile")+", "+dto.getTitlefile());
			}
			else dto.setTitlefile(originalDTO.getTitlefile());
			
			if(mr.getOriginalFileName("contentFile")!=null) {
				dto.setContentfile(nowNum+mr.getOriginalFileName("contentFile"));//새로운 이름의 파일을 저장함
				//기존파일 삭제하는 로직을 여기 넣어야 하는데... 싫다. 일단 저 안에 같은 파일이 존재하냐 마냐도 따져야 할 거 같은데 어렵다.일단은.
				System.out.println(mr.getOriginalFileName("contentFile")+", "+dto.getContentfile());
			}
			else dto.setContentfile(originalDTO.getContentfile());
			
			System.out.println(String.format("%s, %s, %s, %s, %s, %s", mr.getParameter("no"), mr.getParameter("title"), mr.getParameter("content"),
					mr.getParameter("boardtype"), mr.getOriginalFileName("titleFile"), mr.getOriginalFileName("contentFile")));
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
