package ejercicio2_basico;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


public class Jugador implements Runnable {

	protected final Tablero tablero;
	private Posicion pos;
	volatile boolean estaVivo;
	int tesoros;
	private static volatile CyclicBarrier barrera;
	String name;
	
	
	public Jugador(Tablero tablero,Posicion pos,String name, CyclicBarrier barrera) {
		this.tablero = tablero;
		this.setPos(pos);
		this.name = name;
		tesoros = 0;
		Jugador.barrera = barrera;
		this.estaVivo=true;
		tablero.inicializarPosicionJugador(pos);
	}
	


	@Override
	public void run() {
		// MÉTODO DEL TURNO DEL JUGADOR
		while(estaVivo && tablero.haTerminado() == false) {
				
			System.out.println("Turno de "+name);
			// 1. Conseguir una celda adyacente a la que poder moverme. Esto es una responsabilidad del tablero, por eso:
		
			Posicion nuevaPosicion = tablero.getAdyacenteLibre(this.getPos());
			
							
			// Esto encapsula la implemetnación concreta del tablero. Así, si se cambia el ConcurrentHashMap del tablero por otra cosa,
			// no hay que tocar el código del jugador.
			Elemento e = tablero.getElemento(nuevaPosicion);
			
			synchronized(tablero) { // Sincronizo en el tablero para asegurar que no hay otro jugador que se pueda mover a esta misma posición.
				// No se puede sincronizar sobre "e" porque no es el mismo objeto Elemento el que se recibe que el que está en el hashmap. Para poder
				// hacer un sincronizado fino, habría que crear un HashMap de locks y bloquear la posición. HashMap<Posicion,ReentranLock>.
				
				if (e == Elemento.MINA) {
					System.out.println("El jugador "+name+" ha encontrado una mina en "+nuevaPosicion);
					morir();
				
				} else if (e == Elemento.PEPITA){
					System.out.println("El jugador "+name+" ha encontrado una pepita en "+nuevaPosicion);
					tesoros++;
				} else if (e == Elemento.JUGADOR) {
					System.err.println("ERROR FATAL: había un jugador en una casilla donde no debería haberlo.");
				} 
				
				// 3. Moverme a esa casilla.
				tablero.eliminar(nuevaPosicion); // Eliminamos lo que hubiera en el tablero.
				tablero.actualizarPosicion(this,nuevaPosicion);
				this.setPos(nuevaPosicion);
			}
			
			// 5. Esperar en la barrera.
			
			try {
				System.out.println("Esperando en la barrera");
				barrera.await();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			} catch (BrokenBarrierException e1) {
				e1.printStackTrace();
			}
		}
		while(tablero.haTerminado() == false) {
			// Aunque el jugador haya muerto, sigue "mirando" hasta que termina la partida. Así evitamos recrear la barrera.
			try {
				barrera.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			} 
		}
		return;
		
	}
	
	
	private void morir() {
		estaVivo = false;
		tablero.unJugadorMenos();
		
		
	}


	/**
	*			GETTERS Y SETTERS
	*/
	
	public boolean estaVivo() {
		return estaVivo;
	}

	public void setEstaVivo(boolean estaVivo) {
		this.estaVivo = estaVivo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the pos
	 */
	public Posicion getPos() {
		return pos;
	}

	/**
	 * @param pos the pos to set
	 */
	public void setPos(Posicion pos) {
		this.pos = pos;
	}



	public int getTesoros() {
		return tesoros;
	}


}
