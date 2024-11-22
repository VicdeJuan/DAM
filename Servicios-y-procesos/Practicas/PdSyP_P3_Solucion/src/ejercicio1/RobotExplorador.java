package ejercicio1;

public class RobotExplorador extends Thread{

	private String nombre;
	private int tiempoOperacion;
    
	
	public RobotExplorador(String nombre, int tiempoOperacion) {
        this.nombre = nombre;
        this.tiempoOperacion = tiempoOperacion;
    }

    
    public void explorar() {
        System.out.printf("El robot explorador %s ha comenzado a explorar su área asignada.\n", nombre);
        try {
            Thread.sleep(tiempoOperacion * 1000); // Pausa en segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("El robot explorador %s ha terminado de explorar su área asignada.\n", nombre);
    }

    @Override
    public void run() {
    	explorar();
    }
}