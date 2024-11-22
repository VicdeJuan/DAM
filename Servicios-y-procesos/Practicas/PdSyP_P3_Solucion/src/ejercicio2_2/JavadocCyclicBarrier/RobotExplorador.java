package ejercicio2_2.JavadocCyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Clase que representa un robot explorador.
 * Extiende la funcionalidad de la clase Robot e implementa la interfaz Runnable,
 * lo que permite ejecutar tareas de exploración en hilos concurrentes.
 * 
 * Utiliza una barrera cíclica ({@link CyclicBarrier}) para sincronizar la ejecución
 * de múltiples instancias de robots exploradores, garantizando que operen por turnos.
 * 
 * <p>
 * Características principales:
 * <ul>
 *     <li>Los robots realizan operaciones de exploración durante un tiempo determinado.</li>
 *     <li>Se pueden ejecutar múltiples rondas de exploración.</li>
 *     <li>El robot "Curiosity" puede "morir" simulando una reducción dinámica del número de hilos participantes.</li>
 * </ul>
 * </p>
 */
public class RobotExplorador extends Robot implements Runnable {

    /**
     * Barrera cíclica compartida entre todos los robots exploradores para sincronizar las rondas.
     * Se utiliza de forma `volatile` para garantizar visibilidad en entornos concurrentes.
     */
    static volatile CyclicBarrier barrera;

    /**
     * Número de rondas que este robot explorador debe completar.
     */
    final int rondas;

    /**
     * Constructor del robot explorador.
     * 
     * @param nombre          Nombre del robot explorador.
     * @param tiempoOperacion Tiempo requerido para completar una operación (en segundos).
     * @param barrera         La barrera cíclica que sincroniza la ejecución de los robots.
     * @param rondas          Número de rondas que el robot explorador debe realizar.
     */
    public RobotExplorador(String nombre, int tiempoOperacion, CyclicBarrier barrera, int rondas) {
        super(nombre, tiempoOperacion);
        this.barrera = barrera;
        this.rondas = rondas;
    }

    /**
     * Realiza una operación de exploración.
     * Simula la exploración de un área específica con una pausa que representa
     * el tiempo de operación definido.
     * 
     * Al completar la exploración, el robot espera a que todos los demás robots lleguen
     * a la barrera antes de continuar con la siguiente ronda.
     */
    @Override
    public void operar() {
        System.out.printf("El robot explorador %s ha comenzado a explorar su área asignada.\n", nombre);
        try {
            Thread.sleep(tiempoOperacion * 1000); // Pausa en segundos.
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        System.out.printf("El robot explorador %s ha terminado de explorar su área asignada.\n", nombre);

        try {
            barrera.await(); // Espera a que todos los robots lleguen a la barrera.
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sobrescribe el método {@code run()} para definir el comportamiento del robot cuando se ejecuta en un hilo.
     * 
     * El robot realiza las rondas de exploración y, si su nombre es "Curiosity",
     * simula su "muerte", lo que implica una reducción dinámica del número de participantes
     * en la barrera.
     */
    @Override
    public void run() {
        if ("Curiosity".equals(nombre)) { // Solamente es un ejemplo de recreación de la barrera.
            for (int i = 0; i < rondas / 2; i++) {
                operar();
            }
            try {
                morir(); // Simula la muerte del robot.
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        } else {
            for (int i = 0; i < rondas; i++) {
                operar();
            }
        }
    }

    /**
     * Simula la "muerte" del robot.
     * 
     * Este método modifica dinámicamente la barrera cíclica para reducir el número
     * de participantes, garantizando que ningún hilo quede bloqueado en la barrera
     * durante el proceso de modificación.
     * 
     * @throws InterruptedException      si el hilo actual es interrumpido mientras espera.
     * @throws BrokenBarrierException    si ocurre un error al interactuar con la barrera.
     */
    private void morir() throws InterruptedException, BrokenBarrierException {
        System.out.println("Robot " + nombre + " ha muerto. Modificando barrera.");

        synchronized (RobotExplorador.class) { // Exclusión mutua para evitar inconsistencias.
            System.out.println("Recreando la barrera.");
            int esperando = barrera.getNumberWaiting(); // Número de hilos esperando actualmente.
            barrera.reset(); // Libera a los hilos que esperan para evitar bloqueos.
            barrera = new CyclicBarrier(barrera.getParties() - 1, () -> 
                System.out.println("Terminada ronda con algún muerto")
            );
        }
    }
}
