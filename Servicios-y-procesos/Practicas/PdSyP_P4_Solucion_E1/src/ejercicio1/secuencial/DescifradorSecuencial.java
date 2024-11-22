package ejercicio1.secuencial;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;
import ejercicio1.Encoder;
import java.nio.charset.StandardCharsets;

public class DescifradorSecuencial {
	
	private String encontrada;
	
	public DescifradorSecuencial() {
		encontrada = "";
		
	}
	
	
	
	private String descifrarRecursivo(byte[] hash,String prefix,int len) {
		if (len == 0) {
			String combination = prefix;
            if (Arrays.equals(Encoder.getHash(combination),hash)) {
            	return combination;
            }
            return "";
		}
		else {
			for (char c1 = 'a'; c1 <= 'z'; c1++) {
				String combination = prefix + c1;
				encontrada = descifrarRecursivo(hash,combination,len-1);
				if (encontrada != "")
					return encontrada;
			}	
			return "";
		}
		
		
	}
	
	public String descifrarContraseña(byte[] hash,int len) {
		return descifrarRecursivo(hash,"",len);
	}
	
	
}
