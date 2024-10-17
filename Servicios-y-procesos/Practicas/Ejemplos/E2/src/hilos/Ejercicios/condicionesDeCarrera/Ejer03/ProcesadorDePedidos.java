package hilos.Ejercicios.condicionesDeCarrera.Ejer03;
import java.util.ArrayList;
import java.util.List;

class AgregarPedido implements Runnable {
    private ProcesadorDePedidos procesador;

    public AgregarPedido(ProcesadorDePedidos procesador) {
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


class ProcesarPedido implements Runnable {
    private ProcesadorDePedidos procesador;

    public ProcesarPedido(ProcesadorDePedidos procesador) {
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


public class ProcesadorDePedidos {
    private List<String> pedidos = new ArrayList<>();

    public void agregarPedido(String pedido) {
        pedidos.add(pedido);
        System.out.println("Pedido agregado: " + pedido);
    }

    public void procesarPedido() {
        if (!pedidos.isEmpty()) {
            String pedido = pedidos.remove(0);
            System.out.println("Pedido procesado: " + pedido);
        } else {
            System.out.println("No hay pedidos para procesar.");
        }
    }
    
    public static void main(String[] args) {
        ProcesadorDePedidos procesador = new ProcesadorDePedidos();

        Thread hiloAgregar = new Thread(new AgregarPedido(procesador));
        Thread hiloProcesar = new Thread(new ProcesarPedido(procesador));

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
