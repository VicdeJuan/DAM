package ejercicio2_basico;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Tablero {

	ConcurrentHashMap<Posicion,Elemento> cuadricula;
	ArrayList<Jugador> jugadores;
	int numMinas,numMinasRestantes;
	int numPepitas,numPepitasRestantes;
	int numJugadoresRestantes;
	Random r;
	boolean terminado;
	
    private static final String ANSI_RESET = "\u001B[0m";
    
    private static final String NEGRO ="\u001B[30m	";
    private static final String FONDO_NEGRO ="\u001B[40m";
    private static final String ROJO ="\u001B[31m	";
    private static final String FONDO_ROJO ="\u001B[41m";
    private static final String VERDE ="\u001B[32m	";
    private static final String FONDO_VERDE ="\u001B[42m";
    private static final String AMARILLO ="\u001B[33m	";
    private static final String FONDO_AMARILLO ="\u001B[43m";
    private static final String AZUL ="\u001B[34m	";
    private static final String FONDO_AZUL ="\u001B[44m";
    private static final String PÚRPURA ="\u001B[35m	";
    private static final String FONDO_PÚRPURA ="\u001B[45m";
    private static final String CIAN ="\u001B[36m	";
    private static final String FONDO_CIAN ="\u001B[46m";
    private static final String BLANCO ="\u001B[37m	";
    private static final String FONDO_BLANCO ="\u001B[47m";

		
	public Tablero(
			ConcurrentHashMap<Posicion,Elemento> cuadricula,   
			ArrayList<Jugador> jugadores,
			int numJugadores
			) {
		
		this.cuadricula = cuadricula;
		this.jugadores = jugadores;
		numMinas = (int) (numJugadores * Juego.RATIO_MINA_JUGADOR);
		numPepitas = (int) (numJugadores * Juego.RATIO_PEPITA_JUGADOR);
		numJugadoresRestantes = numJugadores;
		numPepitasRestantes = numPepitas;
		numMinasRestantes = numMinas;
		terminado = false;
		
		r = new Random();
		rellenarMinasAleatorio();
		rellenarPepitas();
		
		
	}
	

	//MÉTODOS DE INICIALIZACIÓN QUE JAMÁS SE EJECUTARÁN EN PARALELO, POR LO QUE NO NECESITAN SINCRONIZACIÓN
	private void _rellenar_aleatorio(Elemento E, int numObjetos) {
		for (int i = 0; i< numObjetos;i++) {
			cuadricula.put(getEmptyPos(),E);
		}
	}
	private void rellenarMinasAleatorio() {
		_rellenar_aleatorio(Elemento.MINA,numMinas);
	}

	private void rellenarPepitas() {
		_rellenar_aleatorio(Elemento.PEPITA,numPepitas);
	}

	
	public boolean todosVivos() {
		boolean retval = true;
		for (Jugador j : jugadores)
			retval = retval && j.estaVivo;
		return retval;
	}

	
	// MÉTODOS DE SOLO LECTURA QUE TAMPOCO NECESITAN SINCRONIZACIÓN PORQUE SE EJECUTAN AL LEVANTAR LA BARRERA O PORQUE NO MODIFICAN ATRIBUTOS DE LA CLASE
	@Override
	public String toString() {
		String retval = "___".repeat(Posicion.max_x) +"\n";
		Elemento e = null;
		for (int j=0;j<Posicion.max_y;j++) {
			for (int i=0;i<Posicion.max_x;i++) {
						
				e = cuadricula.get(new Posicion(i,j));

				if ( e == Elemento.JUGADOR) {
					
					retval+=AZUL+"J "+ANSI_RESET;
								
				}else if (e == Elemento.MINA) {
					 retval += ROJO+"m "+ANSI_RESET;
				}else if (e == Elemento.PEPITA) {
					 retval += VERDE+"p "+ANSI_RESET;
				} else {
					retval +=" · ";
				}
			}
			retval+="\n";
		}
		retval+= "___".repeat(Posicion.max_x) +"\n";
		return retval;
	}

	public String resumen() {
		String retval = "\n";
		
		retval += "\tQuedan "+ numPepitasRestantes +" pepitas\n";
		retval += "\tQuedan "+ numMinasRestantes + " minas\n";
		retval += "\tQuedan "+ numJugadoresRestantes + " jugadores\n";
		retval += "\n\n";
		
		retval += "Marcador: \n";
		
		for (Jugador j : jugadores) {
			retval += "\t - "+j.getName() + " ("+j.getTesoros()+")\n";
		}
		
		
		return retval;
	}

	
	public Posicion getEmptyPos() {
		Posicion p;
		do {
			p = Posicion.getRandomPos();
		} while (cuadricula.containsKey(p));
		return p;
	}


	public void inicializarPosicionJugador(Posicion pos) {
		cuadricula.put(pos, Elemento.JUGADOR);
	}
	
	


	public Elemento getElemento(Posicion nuevaPosicion) {
		return cuadricula.get(nuevaPosicion);
	}
	
	

	/**************************************************************************************************
	 **************************************************************************************************
	 **************************************************************************************************
	 *
	 * 								MÉTODOS SINCRONIZADOS
	 * 
	 **************************************************************************************************
	 **************************************************************************************************
	 **************************************************************************************************
	 *************************************************************************************************/
	
	/**
	 * Aunque sea un concurrentHashMap, podría ocurrir que justo se mueven 2 jugadores a la misma posición.
	 * 
	 * @param pos
	 * @return Una posición adyacente en la que no haya un jugador
	 */
	public synchronized Posicion getAdyacenteLibre(Posicion pos) {
		
		// TODO: Esta función podría quedar en un bucle infinito si no hay posiciones disponibles porque un jugador está totalmente rodeado de jugadores.
		Posicion p = null;
		Elemento e = null;
		int x = 0,nueva_x;
		int y = 0,nueva_y;
		boolean faltanPorProbar = false;
		
		do {
			x = r.nextInt(-1, 2); // Aleatorio entre -1 y 1
			y = r.nextInt(-1, 2); // Aleatorio entre -1 y 1
			nueva_x = pos.getX()+x;
			nueva_y = pos.getY()+y;
			p = new Posicion(nueva_x,nueva_y);
			e = cuadricula.get(p);
			//Genero nuevas posiciones hasta que encuentre una vacía.
		} while (e == Elemento.JUGADOR || nueva_x >= Posicion.max_x || nueva_y >= Posicion.max_y || nueva_x < 0 || nueva_y < 0 || faltanPorProbar);
		
		return new Posicion(nueva_x ,nueva_y);
	}
	
	
	public synchronized void eliminar(Posicion nuevaPosicion) {
		if (cuadricula.get(nuevaPosicion) == Elemento.MINA)
			unaMinaMenos();
		if (cuadricula.get(nuevaPosicion) == Elemento.PEPITA)
			unaPepitaMenos();
		
		cuadricula.remove(nuevaPosicion);
	}


	/**
	 * Aunque el HashMap sea concurrente, aquí se hacen 2 operaciones seguidas que deben ser atomizadas.
	 * @param jugador
	 * @param nuevaPosicion
	 */
	public synchronized void actualizarPosicion(Jugador jugador, Posicion nuevaPosicion) {
		cuadricula.remove(jugador.getPos());
		cuadricula.put(nuevaPosicion, Elemento.JUGADOR);
		
	}


	
	public synchronized void unaMinaMenos() {
		numMinasRestantes--;
	}
	
	public synchronized void unaPepitaMenos() {
		numPepitasRestantes--;
		if (numPepitasRestantes == 0) {
			terminado = true;
		}
	}
	
	public synchronized void unJugadorMenos() {
		numJugadoresRestantes--;
		if (numJugadoresRestantes == 0) {
			GAMEOVER();
		}
	}

	public synchronized void GAMEOVER() {
		terminado = true;
	}

	/**
	 * Aunque está declarada como volatile y no sería necesario synchronized, lo declaramos también así aunque sea redundante.
	 * @return
	 */
	public synchronized boolean haTerminado() {
		return terminado;
	}


	

}
