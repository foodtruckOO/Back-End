package model.board;

import java.sql.Date;

public class SellerBoardDTO {
	private String sb_no;
	private String s_no;
	private String title;
	private String content;
	private String attachedfile;
	private Date postdate;
	private String sname;
	public String getSb_no() {
		return sb_no;
	}
	public void setSb_no(String sb_no) {
		this.sb_no = sb_no;
	}
	public String getS_no() {
		return s_no;
	}
	public void setS_no(String s_no) {
		this.s_no = s_no;
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
	public Date getPostdate() {
		return postdate;
	}
	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
}
