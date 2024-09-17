package hilos.Ejemplo1;

public class RatonParalelo extends Thread{

	private String nombre;
	private int tiempoAlimentacion;
	
	public RatonParalelo(String nombre, int tiempoAlimentacion) {
		super();
		this.nombre= nombre;
		this.tiempoAlimentacion = tiempoAlimentacion;
		
	}
	
	@Override
	public void run() {
		this.comer();
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
		RatonParalelo Arturo = new RatonParalelo("Arturo",4);
		RatonParalelo Bob = new RatonParalelo("Bob",5);
		RatonParalelo Carlos = new RatonParalelo("Carlos",3);
		RatonParalelo David = new RatonParalelo ("David",7);
		
		Arturo.start();
		Bob.start();
		Carlos.start();
		David.start();
		
	}

}
