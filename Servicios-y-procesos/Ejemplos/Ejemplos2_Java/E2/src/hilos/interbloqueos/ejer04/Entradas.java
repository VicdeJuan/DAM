package hilos.interbloqueos.ejer04;

public class Entradas{
    public static void main(String[] args) {
        Object entradas = new Object(); // Recurso para las entradas
        Object descuento = new Object(); // Recurso para aplicar descuentos

        // Crear varios clientes
        Runnable cliente = () -> {
            String nombreCliente = Thread.currentThread().getName();

            // Cada cliente intenta obtener recursos
            synchronized (entradas) {
                System.out.println(nombreCliente + " bloqueó las entradas.");
                try { Thread.sleep(50); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

                synchronized (descuento) {
                    System.out.println(nombreCliente + " bloqueó el descuento.");
                    System.out.println(nombreCliente + " compró las entradas con descuento.");
                }
            }
        };

        // Crear y arrancar varios hilos de clientes
        Thread cliente1 = new Thread(cliente, "Cliente 1");
        Thread cliente2 = new Thread(cliente, "Cliente 2");
        Thread cliente3 = new Thread(cliente, "Cliente 3");

        // Hilo para el cliente 1, primero bloquea entradas y luego descuento
        Thread cliente4 = new Thread(() -> {
            synchronized (descuento) {
                System.out.println(Thread.currentThread().getName() + " bloqueó el descuento.");
                try { Thread.sleep(50); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

                synchronized (entradas) {
                    System.out.println(Thread.currentThread().getName() + " bloqueó las entradas.");
                    System.out.println(Thread.currentThread().getName() + " compró las entradas con descuento.");
                }
            }
        }, "Cliente 4");

        // Iniciar los hilos
        cliente1.start();
        cliente2.start();
        cliente3.start();
        cliente4.start();

        // Esperar a que los hilos terminen
        try {
            cliente1.join();
            cliente2.join();
            cliente3.join();
            cliente4.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
