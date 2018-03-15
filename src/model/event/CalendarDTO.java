package model.event;

public class CalendarDTO {
	private String eno;
	private String a_no;
	private String title;
	private String content;
	private String attachedfile;
	private String boardtype;
	private java.sql.Date s_date;
	private java.sql.Date e_date;
	private java.sql.Date postdate;
	private String url;
	public String getEno() {
		return eno;
	}
	public void setEno(String eno) {
		this.eno = eno;
	}
	public String getA_no() {
		return a_no;
	}
	public void setA_no(String a_no) {
		this.a_no = a_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAttachedfile() {
		return attachedfile;
	}
	public void setAttachedfile(String attachedfile) {
		this.attachedfile = attachedfile;
	}
	public String getBoardtype() {
		return boardtype;
	}
	public void setBoardtype(String boardtype) {
		this.boardtype = boardtype;
	}
	public java.sql.Date getS_date() {
		return s_date;
	}
	public void setS_date(java.sql.Date s_date) {
		this.s_date = s_date;
	}
	public java.sql.Date getE_date() {
		return e_date;
	}
	public void setE_date(java.sql.Date e_date) {
		this.e_date = e_date;
	}
	public java.sql.Date getPostdate() {
		return postdate;
	}
	public void setPostdate(java.sql.Date postdate) {
		this.postdate = postdate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
