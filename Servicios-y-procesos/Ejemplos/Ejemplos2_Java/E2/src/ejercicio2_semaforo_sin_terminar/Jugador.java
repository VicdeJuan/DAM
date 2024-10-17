package ejercicio2_semaforo_sin_terminar;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
//import java.util.logging.Logger;

public class Jugador implements ObjetoDelTerreno,Callable<Object> {

	protected final Tablero tablero;
	private Posicion pos;
	volatile boolean estaVivo;
	int tesoros;

	private final Semaphore semaforoEmpezar; 
	private final Semaphore semaforoTerminar;
	private final CyclicBarrier barrera;
	

	
	
	//public Logger log;
	String name;
	
	public Jugador(Tablero tablero,Posicion pos,String name, Semaphore semaforoEmpezar,Semaphore semaforoTerminar) {
		this.tablero = tablero;
		this.setPos(pos);
		this.name = name;
		//log = Logger.getLogger("Jugador");	
		tesoros = 0;
		this.semaforoEmpezar = semaforoEmpezar;
		this.semaforoTerminar = semaforoTerminar;
		this.estaVivo=true;
		this.barrera = null;
	}
	
	public Jugador(Tablero tablero, Posicion posicion, String name, CyclicBarrier barrera) {
		this.tablero = tablero;
		this.setPos(posicion);
		this.name = name;
		this.semaforoEmpezar = null;
		this.semaforoTerminar = null;
		this.barrera = barrera;
		//log = Logger.getLogger("Jugador");	
		tesoros = 0;
		this.estaVivo=true;
		
	}

	@Override
	public Object call() throws Exception {
		while(this.estaVivo) {
			//this.semaforoEmpezar.acquire();
			System.out.println(this.getName() + " doing sutff");
			//Thread.sleep(3000);
			// DO STUFF
			System.out.println(this.getName() + " esperando en la barrera");
			barrera.await();
			//this.semaforoTerminar.release();
			System.out.println(this.getName()+" ha pasado la barrera");
		}
		
		return 0;
	}
	
	
	public void mover() {
		ArrayList<Posicion> adyacentes = pos.getAdyacentes();
		int index = ThreadLocalRandom.current().nextInt(0,adyacentes.size());
		Posicion paraComprobar = adyacentes.get(index);
		ObjetoDelTerreno objetoEncontrado = tablero.getObjetoEnPosicion(paraComprobar);
		if (objetoEncontrado == null){
			tablero.mover(this,pos,paraComprobar);
		}else if (objetoEncontrado instanceof Jugador) {
			System.out.println("ERROR: moviéndose hacia un jugador");
		}else {
			interactuar(paraComprobar,objetoEncontrado);
			tablero.mover(this,pos,paraComprobar);
		}
		
	}
	
	public void interactuar(Posicion posicionNueva, ObjetoDelTerreno objetoEncontrado) {
		if (Elementos.mina == (Elementos) objetoEncontrado) {
			estaVivo=false;
			//log.info(this.getName()+" ha encontrado una mina en " + posicionNueva);
		} else {
			tesoros++;			
			//log.info(this.getName()+" ha encontrado una pepita en " + posicionNueva);
		}
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

}
