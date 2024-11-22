package ejercicio0;

import java.util.ArrayList;
import java.util.Random;

public class Carreras {
	
	// Declaramos una constante para el número de coches.
	public final static int NUM_COCHES = 4;

	public static void main(String[] args) {
		
		// Creación de los coches
		ArrayList<CocheRunnable> coches = new ArrayList<CocheRunnable>();
		ArrayList<Thread> hilosCoches = new ArrayList<Thread>();
		Random r = new Random();
		int velocidad, distanciaTotal;
		
		// Inicialización de los coches.
		for (int i =0; i<NUM_COCHES; i++) {
			velocidad = r.nextInt(5);
			distanciaTotal = r.nextInt(20);
			System.out.println("El coche "+i+ " tiene que recorrer "+ distanciaTotal + " a la velocidad de "+velocidad+ "segundos");
			coches.add(new CocheRunnable("Coche "+i,r.nextInt(5), r.nextInt(20)));
		}
		
		
		// Creo una lista de hilos para poder hacer el join más fácilmente.
		for (CocheRunnable coche : coches) {
			Thread t1 = new Thread(coche);
			hilosCoches.add(t1);
			t1.start();
		}
		
		// Hacemos esperar a que los hilos terminen. De no esperarlos, no se imprimiría el mensaje de "Carrera finalizada" al terminar.
		
		for (Thread t : hilosCoches) {
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("Carrera terminada");
		
		
		
	}
}
