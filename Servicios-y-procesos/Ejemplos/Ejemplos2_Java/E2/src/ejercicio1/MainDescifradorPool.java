package ejercicio1;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class MainDescifradorPool {
	
	

	public static void main(String[] args) {
		
		System.out.println("Pool de hilos");
		String contraseña;
		String descifrada = "";
		int numHilos = 4;
		Scanner scanner = new Scanner(System.in);
		
        
        
        System.out.print("Por favor, escribe la contraseña a descifrar: ");
         contraseña = scanner.nextLine();
	
		
	
        long startTime = System.currentTimeMillis();
        byte[] encodedhash = Encoder.getHash(contraseña);
        

        

        
        //DescifradorSecuencial desc = new DescifradorSecuencial();   
        		
        //final ExecutorService pool = Executors.newCachedThreadPool();
        final ExecutorService pool = Executors.newFixedThreadPool(3);
        
        if (encodedhash != null) {
	     	for (char c1 = 'a'; c1 <= 'z'; c1++) {
        		pool.submit(new DescifradorParalelo(encodedhash,contraseña.length()-1,""+c1));      		
     		}	
        }
        try {
        	pool.shutdown();
			if (!pool.awaitTermination(20000, TimeUnit.MILLISECONDS))
				System.out.println("Timeout");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        pool.close();
        descifrada = DescifradorParalelo.getContraseñaEncontrada();
        long endTime = System.currentTimeMillis();

        long tiempoTranscurrido = endTime - startTime;
        System.out.println("Tiempo de ejecución: " + tiempoTranscurrido + " ms");
		System.out.println("Contraseña descifrada: " + descifrada);
		
		scanner.close();
		
	}
}
