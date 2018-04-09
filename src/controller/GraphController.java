package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import model.GraphDAO;
import model.GraphDTO;

public class GraphController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		GraphDAO dao = new GraphDAO(req.getServletContext());
		System.out.println(req.getParameter("id"));
		String id = req.getParameter("id");
		/*List<GraphDTO> list =  dao.selectList();
		List<Map> collections = new Vector<Map>();
		for(GraphDTO dto : list) {
			Map record = new HashMap();
			record.put("period", dto.getDate().toString());
			//System.out.println(dto.getDate());
			record.put("fir", Integer.parseInt(dto.getFir()));
			//System.out.println(dto.getFir());
			record.put("sec", Integer.parseInt(dto.getSec()));
			record.put("thr", Integer.parseInt(dto.getThr()));
			collections.add(record);
		}
		String data = JSONArray.toJSONString(collections);
		//System.out.println(data);
		req.setAttribute("data", data);*/
		
		//1번째 그래프 구하기 - 달별 이벤트갯수 출력하기용
		Calendar cal = Calendar.getInstance();
		
		int year = cal.get(cal.YEAR);
		int month = cal.get(cal.MONTH)+1;
		List<Map> eventCounts = new Vector<Map>();
		for(int i=0;i<6;i++) {
			Map record = new HashMap();
			
			if(month<10) record.put("period", Integer.toString(year)+" 0"+Integer.toString(month));
			else record.put("period", Integer.toString(year)+" "+Integer.toString(month));
			
			record.put("count", dao.selectEventGraph(Integer.toString(year), Integer.toString(month)));
			if(month==12) {//12월이라 다음달이 1월이라는소리
				year++;
				month=1;
			}
			else month++;
			eventCounts.add(record);
		}		
		String eventData = JSONArray.toJSONString(eventCounts);
		req.setAttribute("eventData", eventData);
		//1번째그래프 완료
		
		//2번째그래프 시작
		List<Map> memberCounts = new Vector<Map>();
		Map rc1 = new HashMap();
		rc1.put("label", "소비자회원 수");
		rc1.put("value", dao.customerCount());
		Map rc2 = new HashMap();
		rc2.put("label", "판매자회원 수");
		rc2.put("value", dao.sellerCount());
		memberCounts.add(rc1);
		memberCounts.add(rc2);
		String memberCountData = JSONArray.toJSONString(memberCounts);
		System.out.println(memberCountData);
		req.setAttribute("memberCounts", memberCountData);
		//2번쨰그래프 끝
		//3번째그래프시작
		List<Map> salesGraphList = dao.selectSalesGraph("daily");
		String salesData = JSONArray.toJSONString(salesGraphList);
		System.out.println(salesData+"가 출력");
		req.setAttribute("salesData", salesData);
		
		
		dao.close();
		//System.out.println(eventData);
		req.getRequestDispatcher("/backend/member/statistics/Graph.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String eventData="";
		resp.setCharacterEncoding("UTF-8");
		GraphDAO dao = new GraphDAO(req.getServletContext());
		//////////////////////////////////첫번째 그래프 관련 요청일 경우 얘가 조치함
		if(req.getParameter("term")!=null) {
			System.out.println(req.getParameter("id"));
			String id = req.getParameter("id");
			System.out.println("기간선택버튼 클릭함 : "+req.getParameter("term"));
			Calendar cal = Calendar.getInstance();
			int year = cal.get(cal.YEAR);
			int month = cal.get(cal.MONTH)+1;
			List<Map> eventCounts = new Vector<Map>();
			int num=Integer.parseInt(req.getParameter("term"));
			for(int i=0;i<num;i++) {
				Map record = new HashMap();
				record.put("period", Integer.toString(year)+"-"+Integer.toString(month));
				record.put("count", dao.selectEventGraph(Integer.toString(year), Integer.toString(month)));
				if(month==12) {//12월이라 다음달이 1월이라는소리
					year++;
					month=1;
				}
				else month++;
				eventCounts.add(record);
			}
			dao.close();
			eventData = JSONArray.toJSONString(eventCounts);
		}
		////////////////////////////2번쨰 도넛그래프 요청은 얘가 처리함

		
		else if(req.getParameter("type")!=null) {
			if(req.getParameter("type").equals("all")) {
				List<Map> memberCounts = new Vector<Map>();
				Map rc1 = new HashMap();
				rc1.put("label", "소비자회원 수");
				rc1.put("value", dao.customerCount());
				Map rc2 = new HashMap();
				rc2.put("label", "판매자회원 수");
				rc2.put("value", dao.sellerCount());
				memberCounts.add(rc1);
				memberCounts.add(rc2);
				eventData = JSONArray.toJSONString(memberCounts);
				System.out.println(eventData);
			}
			else if(req.getParameter("type").equals("seller")) {
				List<Map> list = dao.selectSellerGraph();
				
				///////////////////////도로명(풀명칭)과 지번(약칭)을 통합하는 과정
				//서울 = 서울+서울특별시, 인천 = 인천+인천광역시...
				List<Map> migratedList = mapMigrator(list);
				///////////////////////////////
				eventData = JSONArray.toJSONString(migratedList);
				System.out.println(eventData);
			}
		}
		else if(req.getParameter("revenue")!=null) {
			List<Map> salesGraphList = dao.selectSalesGraph(req.getParameter("revenue"));
			eventData = JSONArray.toJSONString(salesGraphList);
		}
		PrintWriter out = resp.getWriter();
		out.print(eventData);
		out.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//////////////////////////////행정구역의 약칭과 총명칭을 통합하는 용도의 메소드
	private List<Map> mapMigrator(List<Map> list){
		List<Map> collections = new Vector();
		String jsonString = JSONArray.toJSONString(list);
		
		int seoul=0;
		int incheon=0;
		int daejeon=0;
		int gwangju=0;
		int daegu=0;
		int busan=0;
		int ulsan=0;
		int gyeongi=0;
		int gangwon=0;
		int chungnam=0;
		int chungbuk=0;
		int jeonbuk=0;
		int jeonnam=0;
		int gyeongbuk=0;
		int gyeongnam=0;
		///////list.reomove파트에서 에러남 - 반복도는도중에 제거 안된다 어쩌구 같음
		for(Iterator<Map> ite = list.iterator(); ite.hasNext();) {
			Map map = ite.next();
			if(map.get("label").equals("서울")) {
				if(jsonString.indexOf("서울특별시")!=-1) {//서울만 있고 서울특별시는 없다면?
					seoul=Integer.parseInt(map.get("value").toString());
					ite.remove();//만약 서울특별시가 있으면 얘는 지움. 아니면 서울특별시로 바꿈
				}
				else map.put("label", "서울특별시");
			}
			else if(map.get("label").equals("인천")) {
				if(jsonString.indexOf("인천광역시")!=-1) {
					incheon=Integer.parseInt(map.get("value").toString());
					ite.remove();//만약 서울특별시가 있으면 얘는 지움. 아니면 서울특별시로 바꿈
				}
				else map.put("label", "인천광역시");
			}
			else if(map.get("label").equals("대전")) {
				if(jsonString.indexOf("대전광역시")!=-1) {
					daejeon=Integer.parseInt(map.get("value").toString());
					ite.remove();//만약 서울특별시가 있으면 얘는 지움. 아니면 서울특별시로 바꿈
				}
				else map.put("label", "대전광역시");
			}
			else if(map.get("label").equals("대구")) {
				if(jsonString.indexOf("대구광역시")!=-1) {
					daegu=Integer.parseInt(map.get("value").toString());
					ite.remove();//만약 서울특별시가 있으면 얘는 지움. 아니면 서울특별시로 바꿈
				}
				else map.put("label", "대구광역시");
			}
			else if(map.get("label").equals("광주")) {
				if(jsonString.indexOf("광주광역시")!=-1) {
					gwangju=Integer.parseInt(map.get("value").toString());
					ite.remove();//만약 서울특별시가 있으면 얘는 지움. 아니면 서울특별시로 바꿈
				}
				else map.put("label", "광주광역시");
			}
			else if(map.get("label").equals("부산")) {
				if(jsonString.indexOf("부산광역시")!=-1) {
					busan=Integer.parseInt(map.get("value").toString());
					ite.remove();//만약 서울특별시가 있으면 얘는 지움. 아니면 서울특별시로 바꿈
				}
				else map.put("label", "부산광역시");
			}
			else if(map.get("label").equals("울산")) {
				if(jsonString.indexOf("울산광역시")!=-1) {
					ulsan=Integer.parseInt(map.get("value").toString());
					ite.remove();//만약 서울특별시가 있으면 얘는 지움. 아니면 서울특별시로 바꿈
				}
				else map.put("label", "울산광역시");
			}
			else if(map.get("label").equals("경기")) {
				if(jsonString.indexOf("경기도")!=-1) {
					gyeongi=Integer.parseInt(map.get("value").toString());
					ite.remove();//만약 서울특별시가 있으면 얘는 지움. 아니면 서울특별시로 바꿈
				}
				else map.put("label", "경기도");
			}
			else if(map.get("label").equals("강원")) {
				if(jsonString.indexOf("강원도")!=-1) {
					gangwon=Integer.parseInt(map.get("value").toString());
					ite.remove();//만약 서울특별시가 있으면 얘는 지움. 아니면 서울특별시로 바꿈
				}
				else map.put("label", "강원도");
			}
			else if(map.get("label").equals("충남")) {
				if(jsonString.indexOf("충청남도")!=-1) {
					chungnam=Integer.parseInt(map.get("value").toString());
					ite.remove();//만약 서울특별시가 있으면 얘는 지움. 아니면 서울특별시로 바꿈
				}
				else map.put("label", "충청남도");
			}
			else if(map.get("label").equals("충북")) {
				if(jsonString.indexOf("충청북도")!=-1) {
					chungbuk=Integer.parseInt(map.get("value").toString());
					ite.remove();//만약 서울특별시가 있으면 얘는 지움. 아니면 서울특별시로 바꿈
				}
				else map.put("label", "충청북도");
			}
			else if(map.get("label").equals("전남")) {
				if(jsonString.indexOf("전라남도")!=-1) {
					jeonnam=Integer.parseInt(map.get("value").toString());
					ite.remove();//만약 서울특별시가 있으면 얘는 지움. 아니면 서울특별시로 바꿈
				}
				else map.put("label", "전라남도");
			}
			else if(map.get("label").equals("전북")) {
				if(jsonString.indexOf("전라북도")!=-1) {
					jeonbuk=Integer.parseInt(map.get("value").toString());
					ite.remove();//만약 서울특별시가 있으면 얘는 지움. 아니면 서울특별시로 바꿈
				}
				else map.put("label", "전라북도");
			}
			else if(map.get("label").equals("경남")) {
				if(jsonString.indexOf("경상남도")!=-1) {
					gyeongnam=Integer.parseInt(map.get("value").toString());
					ite.remove();//만약 서울특별시가 있으면 얘는 지움. 아니면 서울특별시로 바꿈
				}
				else map.put("label", "경상남도");
			}
			else if(map.get("label").equals("경북")) {
				if(jsonString.indexOf("경상북도")!=-1) {
					gyeongbuk=Integer.parseInt(map.get("value").toString());
					ite.remove();//만약 서울특별시가 있으면 얘는 지움. 아니면 서울특별시로 바꿈
				}
				else map.put("label", "경상북도");
			}
		}//1. 뽑아내기
		
		for(Map map : list) {
			if(map.get("label").equals("서울특별시")) {
				map.put("value", Integer.parseInt(map.get("value").toString())+seoul);
			}
			else if(map.get("label").equals("인천광역시")) {
				map.put("value", Integer.parseInt(map.get("value").toString())+incheon);
			}
			else if(map.get("label").equals("대전광역시")) {
				map.put("value", Integer.parseInt(map.get("value").toString())+daejeon);
			}
			else if(map.get("label").equals("대구광역시")) {
				map.put("value", Integer.parseInt(map.get("value").toString())+daegu);
			}
			else if(map.get("label").equals("광주광역시")) {
				map.put("value", Integer.parseInt(map.get("value").toString())+gwangju);
			}
			else if(map.get("label").equals("부산광역시")) {
				map.put("value", Integer.parseInt(map.get("value").toString())+busan);
			}
			else if(map.get("label").equals("울산광역시")) {
				map.put("value", Integer.parseInt(map.get("value").toString())+ulsan);
			}
			else if(map.get("label").equals("경기도")) {
				map.put("value", Integer.parseInt(map.get("value").toString())+gyeongi);
			}
			else if(map.get("label").equals("강원도")) {
				map.put("value", Integer.parseInt(map.get("value").toString())+gangwon);
			}
			else if(map.get("label").equals("충청남도")) {
				map.put("value", Integer.parseInt(map.get("value").toString())+chungnam);
			}
			else if(map.get("label").equals("충청북도")) {
				map.put("value", Integer.parseInt(map.get("value").toString())+chungbuk);
			}
			else if(map.get("label").equals("전라남도")) {
				map.put("value", Integer.parseInt(map.get("value").toString())+jeonnam);
			}
			else if(map.get("label").equals("전라북도")) {
				map.put("value", Integer.parseInt(map.get("value").toString())+jeonbuk);
			}
			else if(map.get("label").equals("경상북도")) {
				map.put("value", Integer.parseInt(map.get("value").toString())+gyeongbuk);
			}
			else if(map.get("label").equals("경상남도")) {
				map.put("value", Integer.parseInt(map.get("value").toString())+gyeongnam);
			}
		}
		return list;
	}
}
