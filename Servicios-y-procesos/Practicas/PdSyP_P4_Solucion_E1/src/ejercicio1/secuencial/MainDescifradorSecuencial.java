package ejercicio1.secuencial;

import java.util.Scanner;

import ejercicio1.Encoder;


public class MainDescifradorSecuencial {
	

	public final static String HashEjemplo = "b221d9dbb083a7f33428d7c2a3c3198ae925614d70210e28716ccaa7cd4ddb79";
	
	public static void main(String[] args) {
		
		System.out.println("SECUENCIAL");
		String contraseña;
		String descifrada = "";
		int numHilos = 26;
		int longitud = 4;
		Scanner scanner = new Scanner(System.in);
        
		
		long startTime = System.currentTimeMillis();
        
		
		/*
         * Este código se incluye porque facilita la comprobación y la medición de tiempos de distintos escenarios
        System.out.print("Por favor, escribe la contraseña a descifrar: ");
        contraseña = scanner.nextLine();
		// Para no copiar y pegar el código de "getHash" en todas las soluciones implementadas, está en una clase a la que accedemos directamente.
        byte[] encodedhash = Encoder.getHash(contraseña);
        longitud = contraseña.length()
        
         */
		     
        // Para no copiar y pegar el código de "getHash" en todas las soluciones implementadas, está en una clase a la que accedemos directamente.		
		byte[] encodedhash = Encoder.hexStringToByteArray(HashEjemplo);
		
 
               
        DescifradorSecuencial desc = new DescifradorSecuencial();   
        		        
        if (encodedhash != null) {
			descifrada = desc.descifrarContraseña(encodedhash,longitud);
        	
		}
   
        long endTime = System.currentTimeMillis();

        long tiempoTranscurrido = endTime - startTime;
        System.out.println("Tiempo de ejecución: " + tiempoTranscurrido + " ms");
		System.out.println("Contraseña descifrada: " + descifrada);
		
		scanner.close();
		
	}
}
