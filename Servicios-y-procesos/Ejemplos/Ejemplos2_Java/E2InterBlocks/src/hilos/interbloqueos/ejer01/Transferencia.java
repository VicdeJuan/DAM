package hilos.interbloqueos.ejer01;

/**
 * Clase que implementa una transferencia bancaria entre dos cuentas utilizando hilos.
 */
public class Transferencia implements Runnable {
    private CuentaBancaria origen; // Cuenta de origen de la transferencia
    private CuentaBancaria destino; // Cuenta de destino de la transferencia
    private int cantidad; // Cantidad a transferir

    /**
     * Constructor de la clase Transferencia.
     *
     * @param origen Cuenta bancaria desde la que se realizará la transferencia.
     * @param destino Cuenta bancaria a la que se realizará la transferencia.
     * @param cantidad Cantidad a transferir.
     */
    public Transferencia(CuentaBancaria origen, CuentaBancaria destino, int cantidad) {
        this.origen = origen;
        this.destino = destino;
        this.cantidad = cantidad;
    }

    /**
     * Método que ejecuta la transferencia de dinero entre cuentas, implementando el bloqueo
     * para evitar condiciones de carrera.
     */
    @Override
    public void run() {
        synchronized (origen) {
            System.out.println(Thread.currentThread().getName() + " bloqueó " + origen.getNombre());
            try {
                Thread.sleep(100); // Simula el tiempo de procesamiento
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            synchronized (destino) {
                System.out.println(Thread.currentThread().getName() + " bloqueó " + destino.getNombre());
                if (origen.getBalance() >= cantidad) {
                    origen.retirar(cantidad);
                    destino.depositar(cantidad);
                    System.out.println(Thread.currentThread().getName() + " transfirió " + cantidad + " de " + origen.getNombre() + " a " + destino.getNombre());
                } else {
                    System.out.println("Fondos insuficientes en " + origen.getNombre());
                }
            }
        }
    }
    
    /**
     * Método principal para iniciar el ejemplo de transferencias.
     *
     * @param args Argumentos de línea de comando (no se utilizan).
     */
    public static void main(String[] args) {
        // Crear cuentas con saldos iniciales
        CuentaBancaria cuentaA = new CuentaBancaria("Cuenta A", 1000);
        CuentaBancaria cuentaB = new CuentaBancaria("Cuenta B", 1000);

        // Crear tareas de transferencia
        Transferencia tarea1 = new Transferencia(cuentaA, cuentaB, 100);
        Transferencia tarea2 = new Transferencia(cuentaB, cuentaA, 200);

        // Crear hilos para realizar las transferencias
        Thread hilo1 = new Thread(tarea1, "Hilo 1");
        Thread hilo2 = new Thread(tarea2, "Hilo 2");

        // Mostrar el saldo inicial de las cuentas
        System.out.println("Saldo inicial:");
        System.out.println(cuentaA.toString());
        System.out.println(cuentaB.toString());

        // Iniciar hilos de transferencia
        System.out.println("Iniciando transferencias...");
        hilo1.start();
        hilo2.start();

        // Esperar a que todos los hilos terminen
        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            System.out.println("Error: hilo interrumpido.");
        }

        // Mostrar el saldo final de las cuentas
        System.out.println("Saldo final:");
        System.out.println(cuentaA);
        System.out.println(cuentaB);
    }
}
