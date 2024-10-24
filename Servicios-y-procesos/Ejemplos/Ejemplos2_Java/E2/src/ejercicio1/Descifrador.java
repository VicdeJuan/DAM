package ejercicio1;

import java.util.Scanner;


public class Descifrador {
	

	public static void main(String[] args) {
		
		String contraseña;
		String descifrada = "";
		int numHilos = 26;
		Scanner scanner = new Scanner(System.in);
        
        
        System.out.print("Por favor, escribe la contraseña a descifrar: ");
         contraseña = scanner.nextLine();
	
		
	
        long startTime = System.currentTimeMillis();
        byte[] encodedhash = Encoder.getHash(contraseña);
        Thread[] hilos = new Thread[numHilos];

        
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
