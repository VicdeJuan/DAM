package hilos.Ejercicios.condicionesDeCarrera.Ejer10;


class Transferencia implements Runnable {
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
        for (int i = 0; i < 1000000; i++) {
            origen.transferir(destino, cantidad);
        }
    }
}


public class CuentaBancaria {
    private int balance;

    public CuentaBancaria(int balanceInicial) {
        this.balance = balanceInicial;
    }

    public synchronized void depositar(int cantidad) {
        balance += cantidad;
    }

    public synchronized void retirar(int cantidad) {
        balance -= cantidad;
    }

    public void transferir(CuentaBancaria destino, int cantidad) {
    	/* ¿Qué ocurre si este método se interrumpe?
    	 * 
    	 * Si este método se interrumpe, se ha retirado una cantidad de una cuenta que ha pasado
    	 * a estar en un "limbo". Un dinero que no está en ninguna cuenta, solo tiene constancia
    	 * de ese dinero el hilo que está haciendo la transferencia.
    	 * 
    	 * Para eviarlo, sincronizamos el método en los 2 objetos, pero debemos asegurarnos
    	 * que lo hacemos siempre en el mismo orden para evitar interbloqueos.
    	 */
    	Object first,second;
    	if (System.identityHashCode(this) < System.identityHashCode(destino)) {
    		first = this;
    		second = destino;
    	} else {
    		first = destino;
    		second = this;
    	}
    	synchronized (first) {
    		synchronized (destino) {
    	    	this.retirar(cantidad);
    	    	destino.depositar(cantidad);    			
    		}
    	}
    }

    // Podríamos haber declarado como volatile, pero por variar, sincronizamos este método.
    public synchronized int getBalance() {
        return balance;
    }
    
    public static void main(String[] args) {
        CuentaBancaria cuenta1 = new CuentaBancaria(100000);
        CuentaBancaria cuenta2 = new CuentaBancaria(100000);

        // Modificamos las cantidades para que se vea que funcionan correctamente.
        Thread hilo1 = new Thread(new Transferencia(cuenta1, cuenta2, 10));
        Thread hilo2 = new Thread(new Transferencia(cuenta2, cuenta1, 10));

        hilo1.start();
        hilo2.start();
        
        try{
        	hilo1.join();
        	hilo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Balance final de cuenta1: " + cuenta1.getBalance());
        System.out.println("Balance final de cuenta2: " + cuenta2.getBalance());
    }
}

