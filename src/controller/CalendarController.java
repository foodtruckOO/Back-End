package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import model.event.CalendarDAO;
import model.event.CalendarDTO;

public class CalendarController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CalendarDAO dao = new CalendarDAO(req.getServletContext());
		List<CalendarDTO> list = dao.selectList();
		List<Map> collections = new Vector();
		for(CalendarDTO dto : list) {
			Map record = new HashMap();
			record.put("title", dto.getTitle());
			record.put("start", dto.getS_date().toString());
			record.put("end", dto.getE_date().toString());
			record.put("url", req.getContextPath()+dto.getUrl());
			if(dto.getBoardtype().equals("1")) {
				record.put("color", "green");
			}
			else if(dto.getBoardtype().equals("2"))record.put("color", "orange");
			collections.add(record);
		}
		String data = JSONArray.toJSONString(collections);
		dao.close();
		req.setAttribute("calendar", data);
		req.getRequestDispatcher("/backend/event/Calendar.jsp").forward(req, resp);
	}
}
