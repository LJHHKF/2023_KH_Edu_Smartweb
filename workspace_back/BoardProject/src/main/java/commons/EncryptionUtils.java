package commons;

import java.security.MessageDigest;

public class EncryptionUtils {
	//128 자릿수
	public static String sha512(String msg) throws Exception{
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(msg.getBytes());
		StringBuilder builder = new StringBuilder();
		for(byte b : md.digest()) {
			builder.append(String.format("%02x", b));
		}
		return builder.toString();
	}
	
	//64자릿수
	public static String sha256(String msg) throws Exception{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(msg.getBytes());
		StringBuilder builder = new StringBuilder();
		for(byte b : md.digest()) {
			builder.append(String.format("%02x", b));
		}
		return builder.toString();
	}
}
