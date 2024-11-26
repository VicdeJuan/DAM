package ejercicio2_basico;

import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Juego {

	/**
	 * Parámetros del juego.
	 */
	public final static int NUM_JUGADORES = 4;
	public final static double RATIO_PEPITA_JUGADOR = 0.5;
	public final static double RATIO_MINA_JUGADOR = 0.5;
	public final static int MAX_X = 3;
	public final static int MAX_Y = 3;
	
 
	
	public static void accionLevantarBarrera(Tablero tablero) {
		System.out.println(tablero+"\nFin de ronda." + tablero.resumen()); try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		// INICIALIZANDO VARIABLES
		Tablero tablero = new Tablero(
				new ConcurrentHashMap<Posicion, Elemento>(),
				jugadores,
				NUM_JUGADORES
			);
		
		CyclicBarrier barrera = new CyclicBarrier(NUM_JUGADORES, () -> accionLevantarBarrera(tablero));
		

		
		//public Jugador(Tablero tablero,Posicion pos,String name, CyclicBarrier barrera) 
		jugadores.add(new Jugador(tablero, tablero.getEmptyPos(),"Aaron",barrera));
		jugadores.add(new Jugador(tablero, tablero.getEmptyPos(),"Bob",barrera));
		jugadores.add(new Jugador(tablero, tablero.getEmptyPos(),"Carlos",barrera));
		jugadores.add(new Jugador(tablero, tablero.getEmptyPos(),"David",barrera));
	
				
		System.out.println(tablero);
		
		// Tablero creado, vamos con los turnos.
		
		final ExecutorService pool = Executors.newFixedThreadPool(NUM_JUGADORES);
		for (Jugador j : tablero.jugadores) {
			pool.submit(j);
		}
		
		
		
		pool.close();
		if (tablero.haTerminado()) {
			System.out.println("Juego terminado");
		}
		
	}

}
