package ejercicio1;

import java.util.Scanner;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class DescifradorForkJoin extends RecursiveTask<String>{
	
	
    private static final int UMBRAL = 4;


	private final byte[] hashCodificado; // Variable de solo lectura que contiene el hash de la contraseña a descifrar

    
    private static volatile boolean encontrada = false; // Variable compartida que indica si ya se encontró la contraseña


    private int longitud; // Longitud restante de la contraseña por descifrar
    private char prefijoInicial; 
    private char prefijoFinal; 

    public DescifradorForkJoin(byte[] hashCodificado,char prefijoInicial,char prefijoFinal,int longitud) {
		this.hashCodificado = hashCodificado;
		this.prefijoInicial = prefijoInicial;
		this.prefijoFinal= prefijoFinal;
		this.longitud = longitud;
    }
	
	@Override
	protected String compute() {
		if (this.prefijoFinal - this.prefijoInicial <= UMBRAL) {
			char medio = (char) Math.floorDiv(prefijoFinal-prefijoInicial, 2);
			DescifradorForkJoin izq = new DescifradorForkJoin(hashCodificado, prefijoInicial,medio,longitud);
			DescifradorForkJoin dcha = new DescifradorForkJoin(hashCodificado, medio,prefijoFinal,longitud);
			izq.fork();
		} else {
			
		}
		
		return null;
	}
	
	
	
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
