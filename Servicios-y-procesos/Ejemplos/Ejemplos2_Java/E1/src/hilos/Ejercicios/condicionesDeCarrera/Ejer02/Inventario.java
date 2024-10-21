package hilos.Ejercicios.condicionesDeCarrera.Ejer02;

class Venta implements Runnable {
    private Inventario inventario;
    private int cantidad;

    public Venta(Inventario inventario, int cantidad) {
        this.inventario = inventario;
        this.cantidad = cantidad;
    }

    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            inventario.venderProducto(cantidad);
        }
    }
}


class Reabastecimiento implements Runnable {
    private Inventario inventario;
    private int cantidad;

    public Reabastecimiento(Inventario inventario, int cantidad) {
        this.inventario = inventario;
        this.cantidad = cantidad;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            inventario.agregarProducto(cantidad);
        }
    }
}

public class Inventario {
	volatile private int stock;
	private final Object lock = new Object();

    public Inventario(int stockInicial) {
        this.stock = stockInicial;
                
    }

    /**
     * Problema con sincronizar sobre un Integer mutable:
			Inmutabilidad aparente de Integer: En Java, los objetos de tipo Integer son inmutables. 
				Sin embargo, cuando realizas operaciones aritméticas como stock += cantidad;, 
				en realidad estás creando un nuevo objeto Integer y cambiando la referencia 
				de la variable stock para que apunte al nuevo objeto.

			Cambio de referencia del objeto de sincronización: Al sincronizar sobre stock y 
				luego modificar su valor (lo que cambia su referencia), el objeto sobre el cual 
				estás sincronizando deja de ser el mismo. Esto significa que diferentes hilos 
				pueden estar sincronizando sobre diferentes objetos, lo que rompe la exclusión 
				mutua y puede llevar a condiciones de carrera.    
     */
    public void agregarProducto(int cantidad) {
    	synchronized(lock) {
    		stock += cantidad;
            System.out.println("Se agregaron " + cantidad + " productos. Stock actual: " + stock);	
    	}
        
    }

    public void venderProducto(int cantidad) {
    	synchronized(lock) {
	        if (stock >= cantidad) {
	            stock -= cantidad;
	            System.out.println("Se vendieron " + cantidad + " productos. Stock actual: " + stock);
	        } else {
	            System.out.println("No hay suficiente stock para vender " + cantidad + " productos.");
	        }
        }
    }

    /**
     * Aquí hay que sincronizar también para asegurar que se devuelve el último valor. 
     * Los hilos guardan variables a veces en cachés. 
     * La sincronización establece un "happens-before" (sucede antes) en el modelo de memoria de Java:
	 *
	 *	Cuando un hilo libera un lock (sale de un bloque sincronizado), 
	 *		todos los cambios realizados dentro de ese bloque son escritos en memoria principal.
	 *	
	 *	Cuando otro hilo adquiere ese mismo lock (entra en un bloque sincronizado), 
	 *		ve todos los cambios que fueron realizados por otros hilos antes de liberar el lock.
     *
     *	Otra opción es utilizar "volatile" para garantizar la lectura.
     */
    public int getStock() {
        return stock;
    }
    
    
    public static void main(String[] args) {
        Inventario inventario = new Inventario(50);

        Thread hiloVentas = new Thread(new Venta(inventario, 2));
        Thread hiloReabastecimiento = new Thread(new Reabastecimiento(inventario, 5));

        hiloVentas.start();
        hiloReabastecimiento.start();

        try {
            hiloVentas.join();
            hiloReabastecimiento.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Stock final: " + inventario.getStock());
    }
    
    
}
