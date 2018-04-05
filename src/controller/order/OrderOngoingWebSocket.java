package controller.order;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.istack.internal.logging.Logger;

import model.order.OrderDAO;
import model.order.OrderDTO;
@WebServlet()
public class OrderOngoingWebSocket extends HttpServlet {
	private Logger logger = Logger.getLogger(getClass());
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
				dto.setGname(dtos.getGname());//위 3항목은 중복되는 감이 있다. 따라서 1번만 더해주면 된다. 새로 바뀐 시점에...
			}
			if(dto.getFname().length()==0) dto.setFname(dtos.getFname()+"*"+dtos.getNum());//맨처음추가할때 </br>추가되는거 방지용
			else dto.setFname(dto.getFname()+"</br>"+dtos.getFname()+"*"+dtos.getNum());//음식이름 누적해 나감. 이때 음식이름*주문갯수 형태로
			dto.setPrice(Integer.toString(Integer.parseInt(dto.getPrice())+
					(Integer.parseInt(dtos.getPrice())*Integer.parseInt(dtos.getNum()))));//음식가격합산하는거
			
		}
		resultList.add(dto);//맨 마지막꺼 돌고 끝날 때 추가해주는 로직이 없으니까...셀프로 1개 추가해줄 필요 있음
		return resultList;
	}
	private void processConnectionRequest(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("RECEIVER ENTER REQUEST");
		resp.setContentType("text/html; charset=UTF-8");
        resp.setHeader("Cache-Control", "private");
        resp.setHeader("Pragma", "no-cache");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out;
        try {
			out = resp.getWriter();
		} catch (IOException e) {e.printStackTrace();}
        AsyncContext asynCtx = req.startAsync();
        
	}
}

