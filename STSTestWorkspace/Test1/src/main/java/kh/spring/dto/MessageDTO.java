package kh.spring.dto;

import java.sql.Timestamp;

public class MessageDTO {
	private int seq;
	private String name;
	private String message;
	private Timestamp writeDate;
	public MessageDTO() {
		super();
	}
	public MessageDTO(int seq, String name, String message, Timestamp writeDate) {
		super();
		this.seq = seq;
		this.name = name;
		this.message = message;
		this.writeDate = writeDate;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Timestamp writeDate) {
		this.writeDate = writeDate;
	}
	
}
