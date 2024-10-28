package src.hilos.interbloqueos.ejer01;

class CuentaBancaria {
    private int balance;
    private String nombre;

    public CuentaBancaria(String nombre, int balanceInicial) {
        this.nombre = nombre;
        this.balance = balanceInicial;
    }

    public void depositar(int cantidad) {
        balance += cantidad;
    }

    public void retirar(int cantidad) {
        balance -= cantidad;
    }

    public int getBalance() {
        return balance;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String toString() {
    	return nombre + " con " + balance + "€";
    }
}

public class Transferencia implements Runnable {
    private CuentaBancaria origen;
    private CuentaBancaria destino;
    private int cantidad;

    public Transferencia(CuentaBancaria origen, CuentaBancaria destino, int cantidad) {
        this.origen = origen;
        this.destino = destino;
        this.cantidad = cantidad;
    }

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



