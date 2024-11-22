package ejercicio2_2;

//Clase RobotConstructor que hereda de Robot
public class RobotConstructor extends Robot{

	// Contador estático compartido entre todos los robots constructores	
	private static int estructurasConstruidas = 0;

	 
	/**
	 * Creamos un objeto lock para sincronizar los hilos. Lo hacemos así porque la variable compartida es estática, compartida entre todos los objetos.
	 * Así, tendríamos que utilizar synchronized(RobotConstructor.class). Para evitarlo, creamos este objeto que resulta en una solución más elegante.
	 * 
	 * Es IMPRESCINDIBLE que sea static para que pertenezca a la clase y, así, se sincronicen los distintos robots. Asegúrate que entiendes esto.
	 */
	private static final Object lock = new Object();
	
	
	 public int getEstructurasConstruidas() {
		 /**
		  * Este lock es importante porque cada hilo puede mantener una copia en local que no está sincronizada por los demás hilos. 
		  * Recuerda que, la alternativa a este lock, es utilizar la palabra clave volatile en la declaración de la variable. Estos
		  * 2 sistemas aseguran que todas las modificaciones son visibles para todos los hilos.
		  */
		 synchronized(lock) {
			 return estructurasConstruidas;	 
		 }
	 }

	 public RobotConstructor(String nombre, int tiempoOperacion) {
	     super(nombre, tiempoOperacion);
	 }


	// Método para incrementar el contador de estructuras construidas
	 public  void construir() {
		 synchronized(lock) {
			 estructurasConstruidas++;
			 // Fíjate que dentro de este lock hay otro lock en el método getEstructuras. Como es el mismo hilo que ya tiene permiso para ejecutar, no 
			 // se produce ningún bloqueo. (Ver el concepto ReentrantLock)
			 // Innecesario: System.out.printf("El robot constructor %s ha construido una estructura. Total estructuras: %d\n", nombre, getEstructurasConstruidas());
		 }
	 }
	
	 // Método para decrementar el contador de estructuras construidas
	 public  void destruir() {
		 synchronized(lock) {
			 if (estructurasConstruidas > 0) {
		         estructurasConstruidas--;
		         System.out.printf("El robot constructor %s ha destruido una estructura. Total estructuras: %d\n", nombre, getEstructurasConstruidas());
		     } else {
		         System.out.printf("El robot constructor %s intentó destruir una estructura, pero no hay ninguna.\n", nombre);
		     }	 
		 }
	 }
	
	 @Override
	 public void operar() {
	     if (getEstructurasConstruidas() % 2 == 0) {
	    	 synchronized(lock) { // Este synchronized, opcional, asegura que se construyan las 3 estructuras por el mismo robot a la vez.
	    		 for (int i=0;i<3;i++)
		    		 construir();	 // Llamamos 3 veces al método construir porque las instrucciones imponen este método.
	    	 }
	    	  try {
				Thread.sleep(this.tiempoOperacion);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    	 
		     System.out.printf("El robot constructor %s ha construido tres estructuras. Total estructuras: %d\n", nombre, getEstructurasConstruidas());
	     }else {
	    	 destruir();
	    	 try {
					Thread.sleep(this.tiempoOperacion);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		   
	     }
	 }

	@Override
	public void run() {
		for(int i = 0; i<10; i++)
			this.operar();	
	}
}