package ejercicio1.Paralelo_3_avanzados;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import java.util.concurrent.RecursiveTask;

import ejercicio1.Encoder;

import java.util.concurrent.ForkJoinPool;

@SuppressWarnings("serial") // No necesitamos que fuera serializable. 
public class DescifradorForkJoin extends RecursiveTask<String>{
	

	/**
	 * Para sumar los dígitos de un número, por ejemplo, 173, vamos dividiendo entre 10. 
	 * 
	 * 173/10 = 17    |    173 % 10 = 3.		Acumulamos el 3 y repetimos el proceso con el 17
	 * 17 / 10 = 1 	  |	   17 % 10 = 7			Acumulamos el 7 y repetimos el proceso con el 1.
	 * 1 / 10 = 0	  | 	1%10 = 1			Acumulamos el 1 y terminamos. 
	 * Resultado: 3+7+1.
	 * 
	 *  De esta forma, estamos codificando en un número entero las diferentes palabras. Estamos trabajando "en base 26". 
	 *  
	 *  Esta es la forma más eficiente porque dividimos las RecursiveTask según los números enteros. 
	 *  Otra opción sería conseguir restar cadenas. "zzzz" - "aaaa" y obtener, de esta manera, la cantidad de palabras intermedias o algo parecido.  
	 */
	private static String indexToWord(int index, int length) {
	    char[] word = new char[length];
	    for (int i = length - 1; i >= 0; i--) {
	        word[i] = (char) ('a' + index % 26);
	        index /= 26;
	    }
	    return new String(word);
	}
	

    private static final int UMBRAL = 4;
    private String valor;

	private final byte[] hashCodificado; // Variable de solo lectura que contiene el hash de la contraseña a descifrar

    
    private int longitud; // Longitud restante de la contraseña por descifrar
    private int indiceInicio; 
    private int indiceFinal; 

    public DescifradorForkJoin(byte[] hashCodificado,int inicio, int fin ,int longitud) {
		this.hashCodificado = hashCodificado;
		this.indiceInicio = inicio;
		this.indiceFinal= fin;
		this.longitud = longitud;
		this.valor = "";
    }
	
	@Override
	protected String compute() {    
        
		if (this.indiceFinal - this.indiceInicio >= UMBRAL) {
			
			int medio = Math.floorDiv(indiceFinal+indiceInicio, 2);
			DescifradorForkJoin izq = new DescifradorForkJoin(hashCodificado, indiceInicio, medio,longitud);
			DescifradorForkJoin dcha = new DescifradorForkJoin(hashCodificado, medio, indiceFinal,longitud);
			izq.fork();
			dcha.compute();
			izq.join();
			valor = izq.valor + dcha.valor; // Concatenamos las cadenas devueltas. Si no se encuentra la contraseña, se concatena la cadena vacía.
		} else {
			for (int i = indiceInicio; i<indiceFinal; i++) {
				String toTest = indexToWord(i,longitud);
				if (Arrays.equals(hashCodificado, Encoder.getHash(toTest)))
					valor = toTest;
			}	
		}
		
		return valor;
	}
	
	
	
	public static void main(String[] args) {
		
		String contraseña;
		String descifrada = "";
		int numHilos = 26;
		Scanner scanner = new Scanner(System.in);
		int numCaracteresPosibles = 'z'-'a'+1; // Ambas letras incluidas
        
        
        System.out.print("Por favor, escribe la contraseña a descifrar: ");
         contraseña = scanner.nextLine();
	
		
	
        long startTime = System.currentTimeMillis();
        byte[] encodedhash = Encoder.getHash(contraseña);
      
               		
        
       
       ForkJoinPool pool = new ForkJoinPool();

       // Crear la tarea 
       DescifradorForkJoin task = new DescifradorForkJoin(encodedhash,0,(int) Math.pow(numCaracteresPosibles,contraseña.length()),contraseña.length()); 

       // Ejecutar la tarea y obtener el resultado
       
       descifrada = pool.invoke(task);
       
   
        long endTime = System.currentTimeMillis();

        long tiempoTranscurrido = endTime - startTime;
        System.out.println("Tiempo de ejecución: " + tiempoTranscurrido + " ms");
        if (descifrada == ""){
        	System.out.println("Contraseña no encontrada");
        }else {
        	System.out.println("Contraseña descifrada: " + descifrada);
        }
		
		
		scanner.close();
		
	}

}
