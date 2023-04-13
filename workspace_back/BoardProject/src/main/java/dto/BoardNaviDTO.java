package dto;

import java.util.ArrayList;

public class BoardNaviDTO {
	private ArrayList<Integer> naviList;
	private boolean needPrev;
	private boolean needNext;
	public BoardNaviDTO(ArrayList<Integer> naviList, boolean needPrev, boolean needNext) {
		super();
		this.naviList = naviList;
		this.needPrev = needPrev;
		this.needNext = needNext;
	}
	public ArrayList<Integer> getNaviList() {
		return naviList;
	}
	public void setNaviList(ArrayList<Integer> naviList) {
		this.naviList = naviList;
	}
	public boolean getNeedPrev() {
		return needPrev;
	}
	public void setNeedPrev(boolean needPrev) {
		this.needPrev = needPrev;
	}
	public boolean getNeedNext() {
		return needNext;
	}
	public void setNeedNext(boolean needNext) {
		this.needNext = needNext;
	}
	
}
