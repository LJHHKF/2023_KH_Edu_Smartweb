package kh.spring.elec;

import kh.spring.interfaces.Speaker;
import kh.spring.interfaces.TV;

public class LGTV implements TV {
	private int price;
	private String brand;
	
	private Speaker speaker;
	
	public LGTV() {
		super();
		System.out.println("LG TV 생성!");
	}
	public LGTV(int price, String brand, Speaker speaker) {
		super();
		this.price = price;
		this.brand = brand;
		this.speaker = speaker;
	}
	@Override
	public void volUp() {
		speaker.volumeUp();
	}
	@Override
	public void volDown() {
		speaker.volumeDown();
	}
	@Override
	public void powerOn() {
		System.out.println("LG");
	}
	@Override
	public void powerOff() {}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Speaker getSpeaker() {
		return speaker;
	}
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
}
