package model.board;

import java.sql.Date;

public class CustomerBoardDTO {
	private String cb_no;
	private String g_no;
	private String title;
	private String content;
	private String attachedfile;
	private Date postdate;
	private String name;
	public String getCb_no() {
		return cb_no;
	}
	public void setCb_no(String cb_no) {
		this.cb_no = cb_no;
	}
	public String getG_no() {
		return g_no;
	}
	public void setG_no(String g_no) {
		this.g_no = g_no;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
