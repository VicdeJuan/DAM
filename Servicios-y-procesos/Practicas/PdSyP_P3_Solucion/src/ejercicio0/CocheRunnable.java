package ejercicio0;

public class CocheRunnable implements Runnable {

	
	String nombre;
	int velocidad; // Tiempo en milisegundos que tarda el coche en avanzar un kilómetro.
	int distanciaTotal;
	int kmRecorridos;
	
	public CocheRunnable(String nombre, int velocidad, int distanciaTotal) {
		this.nombre = nombre;
		this.velocidad = velocidad;
		this.distanciaTotal = distanciaTotal;
		kmRecorridos = 0;
	}
	
	
	@Override
	public void run() {
		while (kmRecorridos < distanciaTotal) {
			kmRecorridos++;
			System.out.println("El coche "+nombre + " ha avanzado otro km, en total "+kmRecorridos);
			
			try {
				Thread.sleep(velocidad*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("El coche "+ nombre +" ha terminado la carrera");
	}
	
}
