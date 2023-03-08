package dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class MessagesDTO {
	private int seq;
	private String writer;
	private String message;
	private Timestamp write_date;
	
	public MessagesDTO() {super();}
	public MessagesDTO(int seq, String writer, String message, Timestamp write_date) {
		super();
		this.seq = seq;
		this.writer = writer;
		this.message = message;
		this.write_date = write_date;
	}
	public int getSeq() {return seq;}
	public void setSeq(int seq) {this.seq = seq;}
	public String getWriter() {return writer;}
	public void setWriter(String writer) {this.writer = writer;}
	public String getMessage() {return message;}
	public void setMessage(String message) {this.message = message;}
	public Timestamp getWrite_date() {return write_date;}
	public void setWrite_date(Timestamp write_date) {this.write_date = write_date;}
	
	public String getFormedDate() {
		
		String result = null;
		long diff = (System.currentTimeMillis() - this.write_date.getTime())/1000;
		
		if(diff < 60) {
			result = "방금 전";
		}else if(diff < 300) {
			result = "5분 이내";
		}else if(diff < 3600) {
			result = "1시간 이내";
		}else if(diff < 86400) {
			result = "오늘";
		}else {
			SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
			result = sdf.format(this.write_date);
		}
		return result;
	}
}
