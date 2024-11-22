package ejercicio2_1;

public class RobotExplorador extends Robot implements Runnable {

    public RobotExplorador(String nombre, int tiempoOperacion) {
        super(nombre, tiempoOperacion);
    }

    @Override
    public void operar() {
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
        operar();
    }
}