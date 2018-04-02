package controller.map;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;


import model.map.MapDAO;
import model.map.MapDTO;
import model.member.SellerDAO;
import model.member.SellerDTO;

public class MapController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getMethod());

		MapDAO dao = new MapDAO(req.getServletContext());
		List<MapDTO> list = dao.selectListBasic();
		List<Map> collections = new Vector();
		
		for(MapDTO dto : list) {
			Map map = new HashMap();
			String content = "";

			content+="<div style='height:100px;'>업체명 : "+dto.getTname()+"</br>주소 : "+dto.getAddr();
			map.put("content", content);
			map.put("location", dto.getAddr());
			map.put("tel", dto.getTel());
			map.put("tname", dto.getTname());
			map.put("no", dto.getNo());
			map.put("cc", dto.getColumnCount());
			map.put("corpNo", dto.getCorpNo());
			collections.add(map);
		}
		String jsonString = JSONArray.toJSONString(collections);
		req.setAttribute("json", jsonString);
		System.out.println(jsonString);
		//req.getRequestDispatcher("/backend/member/Map.jsp").forward(req, resp);
		req.getRequestDispatcher("/backend/member/Map2.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//여기서는 검색결과에 해당하는 녀석들만을 보여주는 것으로 해야 할 것 같은 느낌이다!
		String type=req.getParameter("member");
		MapDAO dao = new MapDAO(req.getServletContext());
		List<MapDTO> list = dao.selectListbyMember(type);
		///////////////////////////////////////////////////////
		List<Map> collections = new Vector();
		for(MapDTO dto : list) {
			Map map = new HashMap();
			String content = "";

			content+="<div style='height:100px;'>업체명 : "+dto.getTname()+"</br>주소 : "+dto.getAddr()+"</br>";
			map.put("content", content);
			map.put("location", dto.getAddr());
			map.put("tel", dto.getTel());
			map.put("tname", dto.getTname());
			map.put("no", dto.getNo());
			map.put("cc", dto.getColumnCount());
			collections.add(map);
		}
		String jsonString = JSONArray.toJSONString(collections);
		PrintWriter out = resp.getWriter();
		out.print(jsonString);
		out.close();
	}
}
