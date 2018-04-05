package model.order;

import java.sql.Date;

public class OrderDTO {
	private String o_no;
	private String g_no;
	private String f_no;
	private String num;
	private Date timeOfReceipt;//수령예정시간
	private String content;
	private Date postdate;//주문시간
	////////아래는 Join할거 대비 편의상 추가
	private String gname;
	private String fname;
	private String sname;
	private String price;
	
	public String getO_no() {
		return o_no;
	}
	public void setO_no(String o_no) {
		this.o_no = o_no;
	}
	public String getG_no() {
		return g_no;
	}
	public void setG_no(String g_no) {
		this.g_no = g_no;
	}
	public String getF_no() {
		return f_no;
	}
	public void setF_no(String f_no) {
		this.f_no = f_no;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Date getTimeOfReceipt() {
		return timeOfReceipt;
	}
	public void setTimeOfReceipt(Date timeOfReceipt) {
		this.timeOfReceipt = timeOfReceipt;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPostdate() {
		return postdate;
	}
	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}
}
