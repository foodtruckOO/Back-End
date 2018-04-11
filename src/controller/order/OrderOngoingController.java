package controller.order;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import model.order.OrderDAO;
import model.order.OrderDTO;

public class OrderOngoingController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		OrderDAO dao = new OrderDAO(req.getServletContext());
		List<OrderDTO> list = dao.selectList();
		if(list==null) req.setAttribute("list", list);
		else {
			List<OrderDTO> migratedList = orderMigrater(list);
			req.setAttribute("list", migratedList);
		}
		req.getRequestDispatcher("/backend/order/OrderOngoingList.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		OrderDAO dao = new OrderDAO(req.getServletContext());
		List<OrderDTO> list = dao.selectList();
		PrintWriter out = resp.getWriter();
		String jsonArray;
		if(list==null) {
			jsonArray = JSONArray.toJSONString(list);
			out.print(jsonArray);
		}
		else {
			List<Map> migratedList = orderMigraterForMap(list);
			jsonArray = JSONArray.toJSONString(migratedList);
			URLEncoder.encode(jsonArray, "UTF-8");
			out.print(jsonArray);
		}
		System.out.println(jsonArray);
		out.close();
	}
	
	
	
	
	public static List<OrderDTO> orderMigrater(List<OrderDTO> list){
		
		List<OrderDTO> resultList = new Vector();
		OrderDTO dto = null;
		int currento_no=0;
		for(OrderDTO dtos : list) {
			if(Integer.parseInt(dtos.getO_no())!=currento_no) {
				currento_no=Integer.parseInt(dtos.getO_no());//다음 번호로 넘어갔다는 것을 공지시킴
				if(dto!=null)resultList.add(dto);//null이 아닐때만 추가해 준다
				dto = new OrderDTO();//여기서 새롭게 만들어냄
				dto.setFname("");
				dto.setPrice("0");
				dto.setO_no(dtos.getO_no());
				dto.setSname(dtos.getSname());
				dto.setGname(dtos.getGname());
				dto.setTimeOfReceipt(dtos.getTimeOfReceipt());
				dto.setContent(dtos.getContent());
				dto.setStringPostdate(dtos.getStringPostdate());//위 항목들은 중복되는 감이 있다. 따라서 1번만 더해주면 된다. 새로 바뀐 시점에...
			}
			if(dto.getFname().length()==0) dto.setFname(dtos.getFname()+"*"+dtos.getNum());//맨처음추가할때 </br>추가되는거 방지용
			else dto.setFname(dto.getFname()+"</br>"+dtos.getFname()+"*"+dtos.getNum());//음식이름 누적해 나감. 이때 음식이름*주문갯수 형태로
			dto.setPrice(Integer.toString(Integer.parseInt(dto.getPrice())+
					(Integer.parseInt(dtos.getPrice())*Integer.parseInt(dtos.getNum()))));//음식가격합산하는거
			
		}
		resultList.add(dto);//맨 마지막꺼 돌고 끝날 때 추가해주는 로직이 없으니까...셀프로 1개 추가해줄 필요 있음
		return resultList;
	}
	public static List<Map> orderMigraterForMap(List<OrderDTO> list){
		List<Map> resultList = new Vector();
		Map map = null;
		int currento_no=0;
		for(OrderDTO dtos : list) {
			if(Integer.parseInt(dtos.getO_no())!=currento_no) {//새로만드는단계에서 조치하기
				currento_no=Integer.parseInt(dtos.getO_no());//다음 번호로 넘어갔다는 것을 공지시킴
				if(map!=null)resultList.add(map);//기존까지 작업한 것들 넣기 위함...null이 아닐때만 추가해 준다
				if(currento_no==6)System.out.println("6번관련조치하는거들어옴");
				map = new HashMap();//여기서 새롭게 만들어냄
				map.put("fname", "");
				map.put("price", "0");
				map.put("o_no", dtos.getO_no());
				map.put("sname", dtos.getSname());
				map.put("gname", dtos.getGname());
				map.put("timeOfReceipt", dtos.getTimeOfReceipt());
				map.put("content", dtos.getContent());
				map.put("stringPostdate", dtos.getStringPostdate());//위 항목들은 중복되는 감이 있다. 따라서 1번만 더해주면 된다. 새로 바뀐 시점에...
			}
			if(map.get("fname").toString().length()==0) map.put("fname", dtos.getFname()+"*"+dtos.getNum());//맨처음추가할때 </br>추가되는거 방지용
			else map.put("fname", map.get("fname")+"</br>"+dtos.getFname()+"*"+dtos.getNum());//음식이름 누적해 나감. 이때 음식이름*주문갯수 형태로
			map.put("price", (Integer.parseInt(map.get("price").toString())+Integer.parseInt(dtos.getPrice())*Integer.parseInt(dtos.getNum())));
		}
		resultList.add(map);//맨 마지막꺼 돌고 끝날 때 추가해주는 로직이 없으니까...셀프로 1개 추가해줄 필요 있음
		return resultList;
	}
	
	
	
	
}

/*
 
			dto.setFname(dto.getFname()+"\r\n"+dtos.getFname()+"*"+dtos.getNum());
			dto.setPrice(Integer.toString(Integer.parseInt(dto.getPrice())+(Integer.parseInt(dtos.getPrice())*Integer.parseInt(dtos.getNum()))));
			dto.setO_no(dtos.getO_no());
			dto.setSname(dtos.getSname());
			dto.setGname(dtos.getGname());
  
 */
