package main;

import java.text.SimpleDateFormat;

import commons.EncryptionUtils;

public class Test {
	public static void main(String[] args) throws Exception{
		//�ܹ��� ��ȣȭ SHA ����
		//5��¥�� �޼��� �ϳ�.
//		String result = EncryptionUtils.sha512("Test");
//		System.out.println(result);
//		System.out.println(result.length());
		//� ���ڸ� �ִ� ���� 64���� ���ڰ� ������, �ϳ��� ���� �ٲ� ���� �ſ� ����.
		//�Ȱ��� ���� ������ �Ȱ��� ����� ����. ������ �ƴ�.
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd hh:mm:ss");
		String result = sdf.format(System.currentTimeMillis());
		System.out.println(result);
	
	}
}
