package hilos.interbloqueos.ejer02;

public class ColaArticulos {
    private int[] articulos = new int[10];
    private int indice = 0;

    public void agregar(int articuloId) {
        articulos[indice++] = articuloId;
    }

    public int remover() {
        return articulos[--indice];
    }

    public boolean estaVacia() {
        return indice == 0;
    }

    public boolean estaLlena() {
        return indice == articulos.length;
    }
    
    public static void main(String[] args) {
        ColaArticulos colaPendientes = new ColaArticulos();
        ColaArticulos colaRevisados = new ColaArticulos();

        Productor escritor = new Productor(colaPendientes, colaRevisados);
        Consumidor revisor = new Consumidor(colaPendientes, colaRevisados);

        Thread hiloEscritor = new Thread(escritor, "Escritor 1");
        Thread hiloEscritor1 = new Thread(escritor, "Escritor 2");
        Thread hiloEscritor2 = new Thread(escritor, "Escritor 3");
        Thread hiloRevisor = new Thread(revisor, "Revisor");

        hiloEscritor.start();
        hiloEscritor1.start();
        hiloEscritor2.start();
        hiloRevisor.start();
        
        try {
			hiloEscritor.join();
			hiloEscritor1.join();
			hiloEscritor2.join();
		} catch (InterruptedException e) {
			System.out.println("Hilo escritor no ha terminado normalmente");
			e.printStackTrace();
		}
        
        try {
        	hiloRevisor.join();	
        } catch (InterruptedException e) {
        	System.out.println("Hilo revisor no ha terminado normalmente.");
        }
        
    }
}

class Productor implements Runnable {
    private ColaArticulos colaPendientes;
    private ColaArticulos colaRevisados;

    public Productor(ColaArticulos colaPendientes, ColaArticulos colaRevisados) {
        this.colaPendientes = colaPendientes;
        this.colaRevisados = colaRevisados;
    }

    @Override
    public void run() {
    	
        synchronized (colaPendientes) {
            System.out.println(Thread.currentThread().getName() + " bloqueó Cola de Artículos Pendientes");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            synchronized (colaRevisados) {
                System.out.println(Thread.currentThread().getName() + " bloqueó Cola de Artículos Revisados");
                // Agregar un artículo a la cola pendientes
                if (!colaPendientes.estaLlena()) {
                    int articuloId = (int) (Math.random() * 1000); // Simulación de artículo
                    colaPendientes.agregar(articuloId);
                    System.out.println("Artículo " + articuloId + " agregado a la Cola de Pendientes");
                }
            }
        }
    }
}

class Consumidor implements Runnable {
    private ColaArticulos colaPendientes;
    private ColaArticulos colaRevisados;

    public Consumidor(ColaArticulos colaPendientes, ColaArticulos colaRevisados) {
        this.colaPendientes = colaPendientes;
        this.colaRevisados = colaRevisados;
    }

    @Override
    public void run() {
        synchronized (colaRevisados) {
            System.out.println(Thread.currentThread().getName() + " bloqueó Cola de Artículos Revisados");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            synchronized (colaPendientes) {
                System.out.println(Thread.currentThread().getName() + " bloqueó Cola de Artículos Pendientes");
                // Remover un artículo de la cola de pendientes
                if (!colaPendientes.estaVacia()) {
                    int articuloId = colaPendientes.remover();
                    System.out.println("Artículo " + articuloId + " removido de la Cola de Pendientes y listo para revisar");
                }
            }
        }
    }
}
