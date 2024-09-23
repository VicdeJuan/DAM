package hilos.Ejemplo1;

public class RatonSecuencial {

	private String nombre;
	private int tiempoAlimentacion;
	
	public RatonSecuencial(String nombre, int tiempoAlimentacion) {
		super();
		this.nombre= nombre;
		this.tiempoAlimentacion = tiempoAlimentacion;
	}
	
	public void comer() {
		try {
			System.out.printf("El ratón %s ha comenzado a alimentarse\n",nombre);
			Thread.sleep(tiempoAlimentacion*1000);
			System.out.printf("El ratón %s ha terminado de alimentarse\n", nombre);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		RatonSecuencial Arturo = new RatonSecuencial("Arturo",4);
		RatonSecuencial Bob = new RatonSecuencial("Bob",5);
		RatonSecuencial Carlos = new RatonSecuencial("Carlos",3);
		RatonSecuencial David = new RatonSecuencial ("David",7);
		
		Arturo.comer();
		Bob.comer();
		Carlos.comer();
		David.comer();
		
	}

}
