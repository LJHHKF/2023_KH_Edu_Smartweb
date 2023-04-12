package dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardDTO {
	private int seq;
	private String writer;
	private String title;
	private String contents;
	private int view_count;
	private Timestamp write_date;
	
	public BoardDTO(int seq, String writer, String title, String contents, int view_count, Timestamp write_date) {
		super();
		this.seq = seq;
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.view_count = view_count;
		this.write_date = write_date;
	}
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getView_count() {
		return view_count;
	}
	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
	public Timestamp getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Timestamp write_date) {
		this.write_date = write_date;
	}
	
	public String getFormedJoinDate_list() {
		//ms 단위? 1000으로 나눠주면 1초단위가 됨.
		long diff_date = (System.currentTimeMillis() - this.write_date.getTime())/1000;
		
		if(diff_date < 60) {
			return "방금 전";
		}else if(diff_date < 300) {
			return "5분 이내";
		}else if(diff_date < 3600) {
			return "1시간 이내";
		}else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yy년 MM월 dd일");
			return dateFormat.format(this.write_date);
		}
	}
	
	
}
