package Prácticas.Ecosistema;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Animal implements Runnable{
	
	protected final Ecosistema e;
	protected static int turnosEfectuados;
	private Posicion pos;
	volatile boolean estaVivo; 
	
	public Animal(Ecosistema e,Posicion pos) {
		this.pos = pos;
		estaVivo = true;
		this.e = e;
	}
	
	public void mover() {
		ArrayList<Posicion> adyacentes = pos.getAdyacentes();
		int index = ThreadLocalRandom.current().nextInt(0,adyacentes.size());
		Posicion paraComprobar = adyacentes.get(index);
		Animal animalEncontrado = e.getAnimalEnPosicion(paraComprobar);
		if (animalEncontrado == null){
			e.mover(this,pos,paraComprobar);
		}else {
			actuar(paraComprobar,animalEncontrado);
		}
		
	}
	
	protected abstract void actuar(Posicion posicionNueva,Animal animalEncontrado);
	
	public boolean estaVivo() {
		return estaVivo;
	}
	
	void matar() {
		estaVivo=false;
		e.eliminarAnimalMuerto(this);
	}
	
	protected void reproducirse() {
		// TODO Auto-generated method stub
	}
	
	public Posicion getPosicion() {
		return pos;
	}
	
	@Override
	public void run() {
		while(estaVivo) {
			mover();
			//Esperar a que todos los animales hayan hecho su turno.
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
