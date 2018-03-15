package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import model.event.AdminEventDAO;
import model.event.AdminEventDTO;

public class AjaxController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.removeAttribute("tableString");
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		//System.out.println("파라미터확인 : "+req.getParameter("type"));//파라미터로 넘어온다는 사실은 일단 체크되었다. 이 이후 어떻게 맞출 것인가...
		AdminEventDAO dao = new AdminEventDAO(req.getServletContext());
		String boardtype="";
		if(req.getParameter("type")!=null) {
			if(req.getParameter("type").equals("1"))boardtype="WHERE boardtype=1";
			else if(req.getParameter("type").equals("2"))boardtype="WHERE boardtype=2";
		}
		List<AdminEventDTO> list = dao.selectList2(boardtype);
		List<Map> collections = new Vector();
		for(AdminEventDTO dto : list) {
			Map record = new HashMap();
			record.put("eno", dto.getEno());
			record.put("id", dto.getId());
			record.put("title", dto.getTitle());
			record.put("boardtype", dto.getBoardtype());
			record.put("s_date", dto.getS_date());
			record.put("e_date", dto.getE_date());
			collections.add(record);
		}
		dao.close();
		String jsonString = JSONArray.toJSONString(collections);
		String json = JSONArray.toJSONString(list);
		//req.getSession().setAttribute("json", json);
		//req.getSession().setAttribute("list", list);
		String tableString="<div id='dataTables-example_wrapper' class='dataTables_wrapper form-inline dt-bootstrap no-footer'>";
		tableString+="<div class='row'>";
			tableString+="<div class='col-sm-6'>";
				tableString+="<div class='dataTables_length' id='dataTables-example_length'>";
					tableString+="<label>";
						tableString+="<select name='dataTables-example_length' aria-controls='dataTables-example' class='form-control input-sm'>";
							tableString+="<option value='10'>10</option><option value='25'>25</option><option value='50'>50</option>";
						tableString+="</select>개 표시하기";
					tableString+="</label>";
				tableString+="</div>";
			tableString+="</div>";
			tableString+="<div class='col-sm-6'>";
				tableString+="<div id='dataTables-example_filter' class='dataTables_filter'>";
					tableString+="<label>";
						tableString+="'검색:'";
						tableString+="<input type='search' class='form-control input-sm' placeholder aria-controls='dataTables-example'>";
					tableString+="</label>";
				tableString+="</div>";
			tableString+="</div>";
		tableString+="</div>";
		tableString+="<div class='row'>";
			tableString+="<dlv class='col-sm-12'>";
				tableString+="<table width='100%' class='table table-striped table-bordered table-hover dataTable no-footer dtr-inline'";
				tableString+="id='dataTables-example' role='grid' aria-describedby='dataTables-example_info' style='width:100%;'>";
				tableString+="<thead><tr role='row'>";
				tableString+="<th width='8%'>글번호</th>";
				tableString+="<th width='10%'>작성자</th>";
				tableString+="<th width='35%'>제목</th>";
				tableString+="<th width='14%'>분류</th>";
				tableString+="<th width='18%'>기간</th>";
				tableString+="<th width='15%'>편집</th>";
				tableString+="</tr></thead><tbody>";
				for(AdminEventDTO dt : list) {
					tableString+="<tr class='gradeA'>";
					tableString+="<td>"+dt.getEno()+"</td>";
					tableString+="<td>"+dt.getId()+"</td>";
					tableString+="<td>"+dt.getTitle()+"</td>";
					tableString+="<td>"+dt.getBoardtype()+"</td>";
					tableString+="<td>"+dt.getS_date()+"</td>";
					tableString+="<td>편집코너</td>";
					tableString+="</tr>";
				}
				tableString+="</tbody></table>";
			tableString+="</div>";
		tableString+="</div>";
				
				
				
				
		System.out.println("json결과:"+jsonString);
		PrintWriter out = resp.getWriter();
		//out.print(JSONArray.toJSONString(collections));//이게 있어야 데이터를 전달하는 게 가능한 것 같다... 아마도
		out.print(jsonString);
		//System.out.println("json데이터 넣음"+json);
		req.getRequestDispatcher("/backend/event/EventListTest.jsp").forward(req, resp);
	}
}
