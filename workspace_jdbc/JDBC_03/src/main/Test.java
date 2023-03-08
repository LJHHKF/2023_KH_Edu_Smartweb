package main;

import java.text.SimpleDateFormat;

import commons.EncryptionUtils;

public class Test {
	public static void main(String[] args) throws Exception{
		//단방향 암호화 SHA 연습
		//5줄짜리 메서드 하나.
//		String result = EncryptionUtils.sha512("Test");
//		System.out.println(result);
//		System.out.println(result.length());
		//어떤 글자를 넣던 간에 64개의 글자가 나오며, 하나의 값만 바뀌어도 값이 매우 변함.
		//똑같은 값을 넣으면 똑같은 결과가 나옴. 난수는 아님.
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd hh:mm:ss");
		String result = sdf.format(System.currentTimeMillis());
		System.out.println(result);
	
	}
}
