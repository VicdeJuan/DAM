package hilos.Ejercicios.condicionesDeCarrera.Ejer03;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * EJEMPLO DE PRODUCTOR CONSUMIDOR ÓPTIMO
 */
class BQAgregarPedido implements Runnable {
    private ProcesadorDePedidosBlockingQueue procesador;
    // Más info en:
    // https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/util/concurrent/package-summary.html

    public BQAgregarPedido(ProcesadorDePedidosBlockingQueue procesador) {
        this.procesador = procesador;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            procesador.agregarPedido("Pedido " + i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class BQProcesarPedido implements Runnable {
    private ProcesadorDePedidosBlockingQueue procesador;

    public BQProcesarPedido(ProcesadorDePedidosBlockingQueue procesador) {
        this.procesador = procesador;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            procesador.procesarPedido();
            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


public class ProcesadorDePedidosBlockingQueue {
    private LinkedBlockingQueue<String> pedidos = new LinkedBlockingQueue<String>();

    public void agregarPedido(String pedido) {
        pedidos.add(pedido);
        System.out.println("Pedido agregado: " + pedido);
    }

    public void procesarPedido() {
    	
    	String pedido;
		try {
			//pedido = pedidos.poll(); Devuelve null si no hay ninguno. ¿Te interesa que el hilo 
			// espere a que llegue un pedido? Modelo PRODUCTOR-CONSUMIDOR.
			pedido = pedidos.take(); // Espera a que haya un pedido para procesar. 
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		} 
    	if (pedido != null) {    
            System.out.println("Pedido procesado: " + pedido);
        } else {
            System.out.println("No hay pedidos para procesar.");
        }
    }
    
    public static void main(String[] args) {
        ProcesadorDePedidosBlockingQueue procesador = new ProcesadorDePedidosBlockingQueue();

        Thread hiloAgregar = new Thread(new BQAgregarPedido(procesador));
        Thread hiloProcesar = new Thread(new BQProcesarPedido(procesador));

        hiloAgregar.start();
        hiloProcesar.start();

        try {
            hiloAgregar.join();
            hiloProcesar.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
