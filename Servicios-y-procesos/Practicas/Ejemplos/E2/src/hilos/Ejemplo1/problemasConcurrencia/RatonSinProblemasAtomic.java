package hilos.Ejemplo1.problemasConcurrencia;

import java.util.concurrent.atomic.AtomicInteger;

public class RatonSinProblemasAtomic implements Runnable {
	private String nombre;
	private int tiempoAlimentacion;
	private AtomicInteger alimentoConsumido;
	
	public RatonSinProblemasAtomic(String nombre, int tiempoAlimentacion) {
		super();
		this.nombre = nombre;
		this.tiempoAlimentacion = tiempoAlimentacion;
		alimentoConsumido = new AtomicInteger();
		
	}
	
	public void comer() {
		try {
			System.out.printf("El ratón %s ha comenzado a alimentarse\n",nombre);
			Thread.sleep(tiempoAlimentacion*1000);
			alimentoConsumido.getAndIncrement(); //Esta operación es atómica
			System.out.printf("El ratón %s ha terminado de alimentarse\n", nombre);	
			System.out.printf("Alimento consumido: %s\n", alimentoConsumido.toString()); // Pero cuando la volvemos a leer aquí, ha podido pasar cualquier cosa.	
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		this.comer();
	}

	public static void main(String[] args) {
		RatonSinProblemasAtomic Arturo = new RatonSinProblemasAtomic("Arturo",4);
		
		for	(int i=0; i<1000;i++) {
			new Thread(Arturo).start();	
		}
	}

}
