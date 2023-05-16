package kh.spring.elec;

import kh.spring.interfaces.Speaker;

public class AppleSpeaker implements Speaker{

	@Override
	public void volumeUp() {
		System.out.println("Apple : VolumeUp");
	}

	@Override
	public void volumeDown() {
		System.out.println("Apple : VolumeDown");
	}
	
}
