package ejercicio1;

import java.util.Scanner;


public class MainDescifradorSecuencial {
	

	public static void main(String[] args) {
		
		System.out.println("SECUENCIAL");
		String contraseña;
		String descifrada = "";
		int numHilos = 26;
		Scanner scanner = new Scanner(System.in);
        
        
        System.out.print("Por favor, escribe la contraseña a descifrar: ");
         contraseña = scanner.nextLine();
	
		
	
        long startTime = System.currentTimeMillis();
        byte[] encodedhash = Encoder.getHash(contraseña);
        Thread[] hilos = new Thread[numHilos];

        
        DescifradorSecuencial desc = new DescifradorSecuencial();   
        		
        
        if (encodedhash != null) {
			descifrada = desc.descifrarContraseña(encodedhash,contraseña.length());
        	
		}
   
        long endTime = System.currentTimeMillis();

        long tiempoTranscurrido = endTime - startTime;
        System.out.println("Tiempo de ejecución: " + tiempoTranscurrido + " ms");
		System.out.println("Contraseña descifrada: " + descifrada);
		
		scanner.close();
		
	}
}
