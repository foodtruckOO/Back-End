package model.event;

public class AdminEventDTO {
	private String eno;
	private String a_no;
	private String title;
	private String content;
	private String titlefile;
	private String contentfile;
	private java.sql.Date s_date;
	private java.sql.Date e_date;
	private java.sql.Date postdate;
	private String dateString;
	private String boardtype;
	private String id;
	
	public String getEno() {
		return eno;
	}
	public void setEno(String eh_no) {
		this.eno = eh_no;
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
	
	public String getTitlefile() {
		return titlefile;
	}
	public void setTitlefile(String titlefile) {
		this.titlefile = titlefile;
	}
	public String getContentfile() {
		return contentfile;
	}
	public void setContentfile(String contentfile) {
		this.contentfile = contentfile;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBoardtype() {
		return boardtype;
	}
	public void setBoardtype(String boardtype) {
		this.boardtype = boardtype;
	}
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	
}
