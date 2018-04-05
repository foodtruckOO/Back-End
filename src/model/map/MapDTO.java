package model.map;

public class MapDTO {
	private String no;//인덱스번호 그거 ->사실 rownum으로 받을거라서 딱히 상관없음
	private String tname;//트럭이름
	private String tel;//연락처
	private String columnCount;//회원트럭인지 비회원트럭 하드로 치는지. 이거에 따라 어떤 테이블에 넣을지를 갈라야 한다.
	private String sname;//해당 상호명 담는거
	private String etc;
	private String addr;//한글주소 담을거
	private String addr2;//한글주소 담을거
	
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getColumnCount() {
		return columnCount;
	}
	public void setColumnCount(String columnCount) {
		this.columnCount = columnCount;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
}
