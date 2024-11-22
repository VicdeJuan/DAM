package ejercicio2_1;

//Clase RobotConstructor que hereda de Robot
public class RobotConstructor extends Robot{

	// Contador estático compartido entre todos los robots constructores	
	private static int estructurasConstruidas = 0;

	 public int getEstructurasConstruidas() {
		 return RobotConstructor.estructurasConstruidas;
	 }

	 public RobotConstructor(String nombre, int tiempoOperacion) {
	     super(nombre, tiempoOperacion);
	 }


	// Método para incrementar el contador de estructuras construidas
	 public  void construir() {
	    estructurasConstruidas++;
	     System.out.printf("El robot constructor %s ha construido una estructura. Total estructuras: %d\n", nombre, getEstructurasConstruidas());
	 }
	
	 // Método para decrementar el contador de estructuras construidas
	 public  void destruir() {
	     if (estructurasConstruidas > 0) {
	         estructurasConstruidas--;
	         System.out.printf("El robot constructor %s ha destruido una estructura. Total estructuras: %d\n", nombre, getEstructurasConstruidas());
	     } else {
	         System.out.printf("El robot constructor %s intentó destruir una estructura, pero no hay ninguna.\n", nombre);
	     }
	 }
	
	 @Override
	 public void operar() {
	     if (getEstructurasConstruidas() % 2 == 0) {
	    	 for (int i=0;i<3;i++)
	    		 construir();
	    	 try {
				Thread.sleep(this.tiempoOperacion);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    	 
		     System.out.printf("El robot constructor %s ha construido tres estructuras. Total estructuras: %d\n", nombre, getEstructurasConstruidas());
	     }else {
	    	 if (estructurasConstruidas > 0) {
		         estructurasConstruidas--;
		    	 try {
						Thread.sleep(this.tiempoOperacion);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		         System.out.printf("El robot constructor %s ha destruido una estructura. Total estructuras: %d\n", nombre, getEstructurasConstruidas());
		     } else {
		         System.out.printf("El robot constructor %s intentó destruir una estructura, pero no hay ninguna.\n", nombre);
		     }
	     }
	 }

	@Override
	public void run() {
		this.operar();	
	}
}