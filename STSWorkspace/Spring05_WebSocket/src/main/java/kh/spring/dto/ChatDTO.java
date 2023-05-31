package kh.spring.dto;

import java.sql.Timestamp;

public class ChatDTO {
	private int seq;
	private String sender;
	private String message;
	private Timestamp write_date;
	
	public ChatDTO(int seq, String sender, String message, Timestamp write_date) {
		super();
		this.seq = seq;
		this.sender = sender;
		this.message = message;
		this.write_date = write_date;
	}
	public ChatDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Timestamp write_date) {
		this.write_date = write_date;
	}
}
