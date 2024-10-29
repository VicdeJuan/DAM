package hilos.interbloqueos.ejer03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


class Archivo {
    private String nombre;

    public Archivo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    
    // Evidentemente, aquí estamos solamente utilizando una simulación de escritura en archivo.
    public void escribir(String mensaje) {
        System.out.println(Thread.currentThread().getName() + " escribió en " + nombre + ": " + mensaje);
    }
}

class TareaEscritura implements Runnable {
    private Archivo archivo1;
    private Archivo archivo2;
    private Archivo archivo3;

    public TareaEscritura(Archivo archivo1, Archivo archivo2, Archivo archivo3) {
        this.archivo1 = archivo1;
        this.archivo2 = archivo2;
        this.archivo3 = archivo3;
    }

    @Override
    public void run() {
        // Los hilos intentan bloquear los archivos en distintos órdenes para provocar el interbloqueo.
        synchronized (archivo1) {
            System.out.println(Thread.currentThread().getName() + " bloqueó " + archivo1.getNombre());
            try { Thread.sleep(50); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

            synchronized (archivo2) {
                System.out.println(Thread.currentThread().getName() + " bloqueó " + archivo2.getNombre());
                
                
                synchronized (archivo3) {
                    System.out.println(Thread.currentThread().getName() + " bloqueó " + archivo3.getNombre());
                    archivo1.escribir("Datos de " + Thread.currentThread().getName());
                    archivo2.escribir("Datos de " + Thread.currentThread().getName());
                    archivo3.escribir("Datos de " + Thread.currentThread().getName());
                }
            }
        }
    }
}

public class Main {
	public static void main(String[] args) {
		Archivo archivoA = new Archivo("Archivo A");
        Archivo archivoB = new Archivo("Archivo B");
        Archivo archivoC = new Archivo("Archivo C");

        // Pool de hilos con 3 hilos para acceder a los archivos
        ExecutorService pool = Executors.newFixedThreadPool(3);

        // Añadir tareas que intentan acceder a los archivos en distintos órdenes
        pool.submit(new TareaEscritura(archivoA, archivoB, archivoC));
        pool.submit(new TareaEscritura(archivoB, archivoC, archivoA));
        pool.submit(new TareaEscritura(archivoC, archivoA, archivoB));

        pool.shutdown();

        // Esperar a que todos los hilos finalicen
        try {
            if (!pool.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println("Tiempo de espera agotado. Hilos posiblemente en interbloqueo.");
                pool.shutdownNow();
            }
        } catch (InterruptedException e) {
            pool.shutdownNow();
        }
    }
}
