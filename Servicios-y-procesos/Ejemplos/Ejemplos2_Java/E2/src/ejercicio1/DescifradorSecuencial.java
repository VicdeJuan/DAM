package ejercicio1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;
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
		 /*for (char c1 = 'a'; c1 <= 'z'; c1++) {
	            for (char c2 = 'a'; c2 <= 'z'; c2++) {
	                for (char c3 = 'a'; c3 <= 'z'; c3++) {
	                    for (char c4 = 'a'; c4 <= 'z'; c4++) {
	                        String combination = "" + c1 + c2 + c3 + c4;
	                        if (Arrays.equals(getHash(combination),hash)) {
	                        	return combination;
	                        }
	                    }
	                }
	            }
	        }
		 	return "No ha sido posible";*/
	
	}
	
	
}
