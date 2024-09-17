package Prácticas.Ecosistema;

public class Presa extends Animal{

	public Presa(Ecosistema e,Posicion pos) {
		super(e,pos);
	}

	@Override
	protected void actuar(Posicion posicionNueva, Animal animalEncontrado) {
		if (animalEncontrado instanceof Presa) {
			this.reproducirse();
		}else if (animalEncontrado instanceof Depredador) {
			this.matar();
		}
	}

}
