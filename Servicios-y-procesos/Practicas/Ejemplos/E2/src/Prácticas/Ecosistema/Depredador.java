package Prácticas.Ecosistema;

public class Depredador extends Animal{

	private int alimentoConseguido;
	
	
	public Depredador (Ecosistema e,Posicion pos) {
		super(e,pos);
		this.alimentoConseguido=0;
		
	}
	
	public int comer() {
		alimentoConseguido++;
		return alimentoConseguido;
	}
	
	public int getAlimentoConseguido() {
		return alimentoConseguido;
	}
	

	@Override
	protected void actuar(Posicion posicionNueva, Animal animalEncontrado) {
		if (animalEncontrado instanceof Presa) {
			alimentoConseguido++;
			animalEncontrado.matar();
		}		
	}



}
