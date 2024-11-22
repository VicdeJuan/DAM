package ejercicio2_2.JavadocCyclicBarrier;

/**
 * Clase abstracta que representa un robot genérico.
 * Proporciona la estructura básica para los robots exploradores y constructores.
 */
public abstract class Robot extends Thread {

    /** Nombre del robot. */
    protected String nombre;

    /** Tiempo requerido para completar una operación (en segundos). */
    protected int tiempoOperacion;

    /**
     * Constructor de la clase Robot.
     * @param nombre Nombre del robot.
     * @param tiempoOperacion Tiempo que tarda en realizar una operación (en segundos).
     */
    public Robot(String nombre, int tiempoOperacion) {
        this.nombre = nombre;
        this.tiempoOperacion = tiempoOperacion;
    }

    /**
     * Método abstracto que define la operación específica del robot.
     * Debe ser implementado por las subclases.
     */
    public abstract void operar();

    /**
     * Obtiene el nombre del robot.
     * @return Nombre del robot.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el tiempo de operación del robot.
     * @return Tiempo de operación en segundos.
     */
    public int getTiempoOperacion() {
        return this.tiempoOperacion;
    }
}
