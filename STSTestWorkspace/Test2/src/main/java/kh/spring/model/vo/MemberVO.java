package kh.spring.model.vo;

import java.sql.Timestamp;

public class MemberVO {
	private int user_no;
	private String user_id;
	private String user_pw;
	private String user_name;
	private String uesr_addr;
	private Timestamp reg_date;
	
	public MemberVO() {
		super();
	}
	public MemberVO(int user_no, String user_id, String user_pw, String user_name, String uesr_addr,
			Timestamp reg_date) {
		super();
		this.user_no = user_no;
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.uesr_addr = uesr_addr;
		this.reg_date = reg_date;
	}

	public int getUser_no() {return user_no;}
	public void setUser_no(int user_no) {this.user_no = user_no;}
	public String getUser_id() {return user_id;}
	public void setUser_id(String user_id) {this.user_id = user_id;}
	public String getUser_pw() {return user_pw;}
	public void setUser_pw(String user_pw) {this.user_pw = user_pw;}
	public String getUser_name() {return user_name;}
	public void setUser_name(String user_name) {this.user_name = user_name;}
	public String getUesr_addr() {return uesr_addr;}
	public void setUesr_addr(String uesr_addr) {this.uesr_addr = uesr_addr;}
	public Timestamp getReg_date() {return reg_date;}
	public void setReg_date(Timestamp reg_date) {this.reg_date = reg_date;}
}
