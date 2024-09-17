package hilos.problemasConcurrencia;

public class RatonSimple implements Runnable {
	private String nombre;
	private int tiempoAlimentacion;
	private int alimentoConsumido;
	
	public RatonSimple(String nombre, int tiempoAlimentacion) {
		super();
		this.nombre = nombre;
		this.tiempoAlimentacion = tiempoAlimentacion;
		
	}
	
	public void comer() {
		try {
			System.out.printf("El ratón %s ha comenzado a alimentarse\n",nombre);
			Thread.sleep(tiempoAlimentacion*1000);
			alimentoConsumido++;
			System.out.printf("El ratón %s ha terminado de alimentarse\n", nombre);	
			System.out.printf("Alimento consumido: %d\n", alimentoConsumido);	
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		this.comer();
	}

	public static void main(String[] args) {
		RatonSimple Arturo = new RatonSimple("Arturo",4);
		
		for	(int i=0; i<1000;i++) {
			new Thread(Arturo).start();	
		}
	}

}
