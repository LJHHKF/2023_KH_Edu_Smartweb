package kh.spring.dto;

public class FilesDTO {
	private int seq;
	private String OriName;
	private String sysName;
	private int parent_seq;
	public FilesDTO() {
		super();
	}
	public FilesDTO(int seq, String oriName, String sysName, int parent_seq) {
		super();
		this.seq = seq;
		OriName = oriName;
		this.sysName = sysName;
		this.parent_seq = parent_seq;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getOriName() {
		return OriName;
	}
	public void setOriName(String oriName) {
		OriName = oriName;
	}
	public String getSysName() {
		return sysName;
	}
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	public int getParent_seq() {
		return parent_seq;
	}
	public void setParent_seq(int parent_seq) {
		this.parent_seq = parent_seq;
	}
	
}
