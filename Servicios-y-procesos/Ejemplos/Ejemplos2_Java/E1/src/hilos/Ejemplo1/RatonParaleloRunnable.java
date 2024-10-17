package hilos.Ejemplo1;

public class RatonParaleloRunnable implements Runnable{

	private String nombre;
	private int tiempoAlimentacion;
	
	public RatonParaleloRunnable(String nombre, int tiempoAlimentacion) {
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
		RatonParaleloRunnable Arturo = new RatonParaleloRunnable("Arturo",4);
		RatonParaleloRunnable Bob = new RatonParaleloRunnable("Bob",5);
		RatonParaleloRunnable Carlos = new RatonParaleloRunnable("Carlos",3);
		RatonParaleloRunnable David = new RatonParaleloRunnable ("David",7);
		
		(new Thread(Arturo)).start();
		(new Thread(Bob)).start();
		(new Thread(Carlos)).start();
		(new Thread(David)).start();
		
	}

}
