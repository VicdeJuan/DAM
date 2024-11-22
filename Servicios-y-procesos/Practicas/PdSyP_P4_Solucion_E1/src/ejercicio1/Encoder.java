package ejercicio1;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Encoder {
	
	public static byte[] hexStringToByteArray(String hex) {
        int length = hex.length();
        byte[] byteArray = new byte[length / 2];

        for (int i = 0; i < length; i += 2) {
            String byteString = hex.substring(i, i + 2);
            byteArray[i / 2] = (byte) Integer.parseInt(byteString, 16);
        }

        return byteArray;
    }

	
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
