package ejercicio2_semaforo_sin_terminar;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Tablero {

	ConcurrentHashMap<Posicion,ObjetoDelTerreno> cuadricula;
	ArrayList<Jugador> jugadores;
	final CyclicBarrier barrera;
	int numJugadores;
	public Tablero(
			ConcurrentHashMap<Posicion,ObjetoDelTerreno> cuadricula,   
			ArrayList<Jugador> jugadores,
			int numJugadores
			) {
		
		this.cuadricula = cuadricula;
		this.jugadores = jugadores;
		this.barrera = new CyclicBarrier(numJugadores);
		this.numJugadores = numJugadores;
	}
	
	
	public ObjetoDelTerreno getObjetoEnPosicion(Posicion pos) {
		return cuadricula.get(pos);
	}
	
	public void añadirObjetoDelTerreno(Jugador jugador) {
		this.jugadores.add(jugador);
		cuadricula.put(jugador.getPos(),jugador);
	}

	public void añadirObjetoDelTerreno(Elementos elem, Posicion pos) {
		cuadricula.put(pos,elem);
	}
	
	public void mover(Jugador jugador, Posicion posicionAntigua, Posicion posicionNueva ) {
		cuadricula.remove(posicionAntigua);
		cuadricula.put(posicionNueva, jugador);
	}
	
	public void eliminarJugadorMuerto(Jugador jugador) {
		cuadricula.remove(jugador.getPos());
	}
	
	public static void main(String[] args) {
		
		// INICIALIZANDO VARIABLES
		int numJugadores = 4;
		
		Tablero tablero = new Tablero(new ConcurrentHashMap<Posicion,ObjetoDelTerreno>(), new ArrayList<Jugador>(),numJugadores);
		
		Semaphore preparadosListosYa = new Semaphore(4);
		preparadosListosYa.drainPermits(); // Dejamos el contador a 0 para que no empiece ningún hilo;
		
		Semaphore esperamosALosDemas = new Semaphore(4);
		

		
		tablero.añadirObjetoDelTerreno(new Jugador(tablero,new Posicion(0,0),"Alfredo", tablero.barrera));
		tablero.añadirObjetoDelTerreno(new Jugador(tablero,new Posicion(0,Posicion.max_y-1),"Bob",tablero.barrera));
		tablero.añadirObjetoDelTerreno(new Jugador(tablero,new Posicion(Posicion.max_x-1,0),"Carla",tablero.barrera));
		tablero.añadirObjetoDelTerreno(new Jugador(tablero,new Posicion(Posicion.max_x-1,Posicion.max_y-1),"Dwight", tablero.barrera));
		
		tablero.rellenarPepitas(tablero.cuadricula,8);
		tablero.rellenarMinas(tablero.cuadricula, 4);
		
		System.out.println(tablero);
		
		// Tablero creado, vamos con los turnos.
		
		final ExecutorService pool = Executors.newFixedThreadPool(numJugadores);
		for (Jugador j : tablero.jugadores) {
			pool.submit(j);
		}
		
		
		
	}


	private void rellenarMinas(ConcurrentHashMap<Posicion,ObjetoDelTerreno> tablero,int i) {
		// TODO Auto-generated method stub
		for (int j = 0; j<=i;j++) {
			tablero.put(new Posicion(3,j), Elementos.mina);	
		}
		
	}

	public boolean todosVivos() {
		return true;
	}

	private void rellenarPepitas(ConcurrentHashMap<Posicion,ObjetoDelTerreno> tablero,int i) {
		// TODO Auto-generated method stub
		for (int j = 0; j<=Posicion.max_x ;j++) {
			tablero.put(new Posicion(1,j), Elementos.pepita);	
		}		
	}
	
	@Override
	public String toString() {
		String retval="";
		
		for (int i=0;i<Posicion.max_x;i++) {
			for (int j=0;j<Posicion.max_y;j++) {
				Posicion pos = new Posicion(i,j);
				ObjetoDelTerreno a = null;
				if (cuadricula.containsKey(pos)) {
					a = cuadricula.get(pos);
				}
				
				if ( a instanceof Jugador) {
					retval+="J";
					Jugador d = (Jugador) a;
					
				}else if (a instanceof Elementos) {
					if ((Elementos) a == Elementos.mina){
						retval += "m";
					}else {
						retval += "p";
					}
				} else {
					retval +="·";
				}
			}
			retval+="\n__________________\n";
		}
		
		return retval;
	}
	
	

}
