package ejercicio2_2.JavadocCyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * Clase principal que coordina la misión espacial.
 * Gestiona la creación y operación de robots exploradores y constructores.
 */
public class MisionEspacial {

    /** 
     * Define si los robots constructores operan en paralelo o secuencialmente.
     */
    private final static boolean paralelo = true;

    /**
     * Método principal que coordina el lanzamiento de los robots.
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {

    
    	CyclicBarrier barrera = new CyclicBarrier(4,() -> System.out.println("Terminada ronda de exploración"));
    	
    	final int NUM_RONDAS = 6;
    	
        // Crear y lanzar los robots exploradores en paralelo
        Thread exploradores[] = {
            new Thread(new RobotExplorador("Curiosity", 3,barrera,NUM_RONDAS)),
            new Thread(new RobotExplorador("Perseverance", 2,barrera,NUM_RONDAS)),
            new Thread(new RobotExplorador("Opportunity", 5,barrera,NUM_RONDAS)),
            new Thread(new RobotExplorador("Spirit", 7,barrera,NUM_RONDAS))
        };

        // Crear y operar los robots constructores
        RobotConstructor constructores[] = {
            new RobotConstructor("VCE", 8),
            new RobotConstructor("Probe", 5),
            new RobotConstructor("Drone", 2)
        };

        // Iniciar los robots exploradores
        for (Thread t : exploradores) {
            t.start();
        }

        if (paralelo) {
            // Operar constructores en paralelo
            for (Thread t : constructores) {
                t.start();
            }
            // Esperar a que todos los constructores terminen
            for (Thread t : constructores) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // Operar constructores secuencialmente
            for (RobotConstructor c : constructores) {
                for (int i = 0; i < 10; i++) {
                    c.operar();
                }
            }
        }

        // Esperar a que los exploradores terminen
        for (Thread t : exploradores) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Mostrar las estructuras construidas por cada robot constructor
        System.out.println("Estructuras construidas por cada Robot:");
        for (RobotConstructor c : constructores) {
            System.out.println("\t- " + c.nombre + ": " + c.getEstructurasConstruidas());
        }
    }
}
