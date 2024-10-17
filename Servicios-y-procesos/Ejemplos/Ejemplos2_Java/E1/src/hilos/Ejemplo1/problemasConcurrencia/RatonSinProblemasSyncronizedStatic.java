package hilos.Ejemplo1.problemasConcurrencia;

public class RatonSinProblemasSyncronizedStatic implements Runnable {
	private String nombre;
	private int tiempoAlimentacion;
	static int alimentoConsumido;
	
	public RatonSinProblemasSyncronizedStatic(String nombre, int tiempoAlimentacion) {
		super();
		this.nombre = nombre;
		this.tiempoAlimentacion = tiempoAlimentacion;
		
	}
	
	public void comer() {
		try {
			synchronized (this){ 
				// Aquí colocado, no arregla nada porque es como si fuera secuencial
				// Ojo con lo que se sincroniza. Si la variable es estática, hay que sincronizar
				// la clase, no la instancia del objeto.
				//  synchronized (RatonSinProblemasSyncronizedStatic.class)
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
		for	(int i=0; i<1000;i++) {
			new Thread(new RatonSinProblemasSyncronized("Arturo",4)).start();	
		}
	}

}
