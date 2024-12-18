package ejercicio1.Paralelo_1_simple;

import java.util.Scanner;

import ejercicio1.Encoder;


public class MainDescifradorParalelo {
	

	public static void main(String[] args) {
		
		System.out.println("Array de 26 hilos");
		
		String contraseña;
		String descifrada = "";
		int numHilos = 26;
		
		/*
		 * Aunque el ejercicio no pedía esto, resulta más complejo. Por simplificar, daba ya el hash a descifrar. 
		 * 
		 * Para ver cómo sería el main, ver 
		 */
		Scanner scanner = new Scanner(System.in);
        
        
        System.out.print("Por favor, escribe la contraseña a descifrar: ");
         contraseña = scanner.nextLine();
	
		
	
        long startTime = System.currentTimeMillis();
        byte[] encodedhash = Encoder.getHash(contraseña);
        
        /**
         * Podría hacerse con:
         * 		
         * 		CountDownLatch doneSignal = new CountDownLatch(numHilos);
         * 
         * y después de llamar ejecutar a todos los hilos,  
         * 
         * 		doneSignal.await();           // wait for all to finish
         * 
         * incluyendo en el "run" de cada hilo una llamada a "doneSignal.countDown();" 
         * 	[por lo que hay que dar como argumento doneSignal al crear el runnable
         */
        
        Thread[] hilos = new Thread[numHilos]; // Este array de hilos permite hacer el join fácilmente.

        
        //DescifradorSecuencial desc = new DescifradorSecuencial();   
        		
        
        if (encodedhash != null) {
			//descifrada = desc.descifrarContraseña(encodedhash,contraseña.length());
        	int i=0;
        	for (char c1 = 'a'; c1 <= 'z'; c1++) {
        		hilos[i] = new Thread(new DescifradorParalelo(encodedhash,contraseña.length()-1,""+c1));
        		hilos[i].start();
        		i++;
     		}	
        	
						
			try {
				for(i=0;i<numHilos;i++){
					hilos[i].join();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			descifrada = DescifradorParalelo.getContraseñaEncontrada();
		}
   
        long endTime = System.currentTimeMillis();

        long tiempoTranscurrido = endTime - startTime;
        System.out.println("Tiempo de ejecución: " + tiempoTranscurrido + " ms");
		System.out.println("Contraseña descifrada: " + descifrada);
		
		scanner.close();
		
	}
}
