package hilos.robotsConstructores;

//Clase RobotConstructor que extiende Robot
public class RobotConstructor extends Robot implements Runnable{

 // Contador estático compartido entre todos los robots constructores
 private static int estructurasConstruidas = 0;

	 public int getEstructurasConstruidas() {
		 return estructurasConstruidas;
	 }

	 public RobotConstructor(String nombre, int tiempoOperacion) {
	     super(nombre, tiempoOperacion);
	 }


	// Método para incrementar el contador de estructuras construidas
	 public  void construir() {
	    estructurasConstruidas++;
	     System.out.printf("El robot constructor %s ha construido una estructura. Total estructuras: %d\n", nombre, estructurasConstruidas);
	 }
	
	 // Método para decrementar el contador de estructuras construidas
	 public  void destruir() {
	     if (estructurasConstruidas > 0) {
	         estructurasConstruidas--;
	         System.out.printf("El robot constructor %s ha destruido una estructura. Total estructuras: %d\n", nombre, estructurasConstruidas);
	     } else {
	         System.out.printf("El robot constructor %s intentó destruir una estructura, pero no hay ninguna.\n", nombre);
	     }
	 }
	
	 @Override
	 public void operar() {
	     // Ejemplo: Construir y luego destruir estructuras
	     construir();
	    
	     //destruir();
	 }

	@Override
	public void run() {
		this.operar();
		
	}
}