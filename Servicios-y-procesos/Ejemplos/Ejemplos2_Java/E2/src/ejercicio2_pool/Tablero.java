package ejercicio2_pool;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Tablero {

	ConcurrentHashMap<Posicion,ObjetoDelTerreno> cuadricula;
	ArrayList<Jugador> jugadores;
		
	public Tablero(
			ConcurrentHashMap<Posicion,ObjetoDelTerreno> cuadricula,   
			ArrayList<Jugador> jugadores
			) {
		
		this.cuadricula = cuadricula;
		this.jugadores = jugadores;
		
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
		Tablero tablero = new Tablero(new ConcurrentHashMap<Posicion,ObjetoDelTerreno>(), new ArrayList<Jugador>());
		
		Semaphore preparadosListosYa = new Semaphore(4);
		preparadosListosYa.drainPermits(); // Dejamos el contador a 0 para que no empiece ningún hilo;
		
		Semaphore esperamosALosDemas = new Semaphore(4);
		
		int numJugadores = 4;
		
		tablero.añadirObjetoDelTerreno(new Jugador(tablero,new Posicion(0,0),"Alfredo", preparadosListosYa,esperamosALosDemas));
		tablero.añadirObjetoDelTerreno(new Jugador(tablero,new Posicion(0,Posicion.max_y-1),"Bob", preparadosListosYa,esperamosALosDemas));
		tablero.añadirObjetoDelTerreno(new Jugador(tablero,new Posicion(Posicion.max_x-1,0),"Carla", preparadosListosYa,esperamosALosDemas));
		tablero.añadirObjetoDelTerreno(new Jugador(tablero,new Posicion(Posicion.max_x-1,Posicion.max_y-1),"Dwight", preparadosListosYa,esperamosALosDemas));
		
		tablero.rellenarPepitas(tablero.cuadricula,8);
		tablero.rellenarMinas(tablero.cuadricula, 4);
		
		System.out.println(tablero);
		
		// Tablero creado, vamos con los turnos.
		
		final ExecutorService pool = Executors.newFixedThreadPool(numJugadores);
		for (Jugador j : tablero.jugadores) {
			pool.submit(j);
		}
		
		esperamosALosDemas.drainPermits();
		
		while(tablero.todosVivos()) {
			preparadosListosYa.release(numJugadores);
			try {
				esperamosALosDemas.acquire(numJugadores);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Final");
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
