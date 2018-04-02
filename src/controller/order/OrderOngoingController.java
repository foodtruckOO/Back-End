package controller.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderOngoingController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("주문현황체크로넘어옴");
		req.getRequestDispatcher("/backend/order/OrderOngoingList.jsp").forward(req, resp);
	}
}
