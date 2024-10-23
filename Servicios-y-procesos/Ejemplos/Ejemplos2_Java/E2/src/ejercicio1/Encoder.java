package ejercicio1;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

abstract class Encoder {
	
	public static byte[] getHash(String text) {
			
			byte[] encodedhash = null;
			MessageDigest digest;
			try {
				digest = MessageDigest.getInstance("SHA-256");
				encodedhash = digest.digest(
						  text.getBytes(StandardCharsets.UTF_8));
			} catch (NoSuchAlgorithmException e) {
				// 
				e.printStackTrace();
			}
			return encodedhash;
		}
}