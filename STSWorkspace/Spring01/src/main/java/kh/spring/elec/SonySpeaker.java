package kh.spring.elec;

import kh.spring.interfaces.Speaker;

public class SonySpeaker implements Speaker{
	public void volumeUp() {
		System.out.println("Sony : VolumeUp");
	}
	public void volumeDown() {
		System.out.println("Sony : VolumeDown");
	}
}
