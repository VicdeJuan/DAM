package Prácticas.Ecosistema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Ecosistema {

	ConcurrentHashMap<Posicion,Animal> cuadricula;
	ArrayList<Depredador> depredadores;
	ArrayList<Presa> presas;
	Posicion esquinaInferiorDerecha;
	private final int max_x = 5;
	private final int max_y = 5;
	public Ecosistema(
			ConcurrentHashMap<Posicion,Animal> cuadricula,
			ArrayList<Depredador> depredadores,
			ArrayList<Presa> presas
			) {	
		this.cuadricula = cuadricula;
		this.depredadores = depredadores;
		this.presas = presas;
		this.esquinaInferiorDerecha = new Posicion(max_x,max_y);
		
	}
	
	public Animal getAnimalEnPosicion(Posicion p){
		return cuadricula.get(p);
	}


	public void mover(Animal animal, Posicion posicionAntigua, Posicion posicionNueva) {
		assert(cuadricula.containsKey(posicionNueva)) : "Se supone que la celda " +posicionNueva.toString() + "debería estar vacía, pero no lo está";
		cuadricula.remove(posicionAntigua);
		cuadricula.put(posicionNueva, animal);
	}

	public void set(Posicion pos, Animal animal) {
		cuadricula.put(pos, animal);
	}
	
	private void añadirAnimalLista(Depredador animal) {
		this.depredadores.add(animal);
		set(animal.getPosicion(),animal);
	}

	private void añadirAnimalLista(Presa animal) {
		this.presas.add(animal);
		set(animal.getPosicion(),animal);
	}

	@Override
	public String toString() {
		String retval="";
		
		for (int i=0;i<max_x;i++) {
			for (int j=0;j<=max_y;j++) {
				Posicion pos = new Posicion(i,j);
				Animal a = null;
				if (cuadricula.containsKey(pos)) {
					a = cuadricula.get(pos);
				}
				
				if ( a instanceof Depredador) {
					retval+="d";
					if (a.estaVivo) {
						retval+="+|";
					}else {
						retval += "-|";
					}
				}else if (a instanceof Presa) {
					retval+="p";
					if (a.estaVivo) {
						retval+="+|";
					}else {
						retval += "-|";
					}
				} else {
					retval+="  |";
				}
			}
			retval+="\n__________________\n";
		}
		
		return retval;
	}
	
	
	public void eliminarAnimalMuerto(Animal a) {
		cuadricula.remove(a.getPosicion());
	}
	
	
	public static void main(String[] args) {
		
		// Inicialización de los animales y la cuadrícula
		Ecosistema eco = new Ecosistema(new ConcurrentHashMap<Posicion,Animal>(), new ArrayList<Depredador>(), new ArrayList<Presa>());
		
		eco.añadirAnimalLista(new Depredador(eco,new Posicion(1,1)));
		eco.añadirAnimalLista(new Depredador(eco,new Posicion(0,1)));
		eco.añadirAnimalLista(new Depredador(eco,new Posicion(1,0)));
		
		eco.añadirAnimalLista(new Presa(eco,new Posicion(0,0)));
		eco.añadirAnimalLista(new Presa(eco,new Posicion(0,2)));
		eco.añadirAnimalLista(new Presa(eco,new Posicion(1,2)));
		eco.añadirAnimalLista(new Presa(eco,new Posicion(2,0)));
		eco.añadirAnimalLista(new Presa(eco,new Posicion(2,1)));
		eco.añadirAnimalLista(new Presa(eco,new Posicion(2,2)));
	
		
		System.out.println(eco.toString());
		
		// Llamada a start de cada objeto
		eco.depredadores.forEach(animal -> new Thread(animal).start());
		eco.presas.forEach(animal -> new Thread(animal).start());
		
		// Bucle de prints
		int i=0;
		while(i<=5) {
			System.out.println("Nueva cuadrícula(" + i + "): ");
			System.out.println(eco.toString());
			i++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}


}
