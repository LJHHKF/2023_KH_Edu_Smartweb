package kh.spring.elec;

import kh.spring.interfaces.TV;

public class SamsungTV implements TV {
	private int price;
	private String brand;
	
	public SamsungTV() {
		super();
		System.out.println("Samsung TV 생성!");
	}
	@Override
	public void powerOn() {
		System.out.println("Samsung");
	}
	@Override
	public void powerOff() {}
	@Override
	public void volUp() {
	}
	@Override
	public void volDown() {
	}
}
