package hilos.Ejemplo1.problemasConcurrencia;

public class RatonSinProblemasSyncronized implements Runnable {
	private String nombre;
	private int tiempoAlimentacion;
	int alimentoConsumido;
	
	public RatonSinProblemasSyncronized(String nombre, int tiempoAlimentacion) {
		super();
		this.nombre = nombre;
		this.tiempoAlimentacion = tiempoAlimentacion;
		
	}
	
	public void comer() {
		try {
			synchronized (this){ // Aquí colocado, no arregla nada porque es como si fuera secuencial
				System.out.printf("El ratón %s ha comenzado a alimentarse\n",nombre);
				Thread.sleep(tiempoAlimentacion*1000);
			//2º, colocarlo aquí: synchronized (this){
				alimentoConsumido++;
				System.out.printf("El ratón %s ha terminado de alimentarse\n", nombre);	
				System.out.printf("Alimento consumido: %d\n", alimentoConsumido);	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		this.comer();
	}

	public static void main(String[] args) {
		RatonSinProblemasSyncronized Arturo = new RatonSinProblemasSyncronized("Arturo",4);
		
		for	(int i=0; i<1000;i++) {
			new Thread(Arturo).start();	
		}
	}

}
