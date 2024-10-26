package hilos.Ejercicios.condicionesDeCarrera.Ejer09;

import java.util.concurrent.atomic.AtomicInteger;

class UsuarioCajero implements Runnable {
    private CajeroAutomatico cajero;
    private int cantidad;

    public UsuarioCajero(CajeroAutomatico cajero, int cantidad) {
        this.cajero = cajero;
        this.cantidad = cantidad;
    }

    @Override
    public void run() {
        cajero.retirar(cantidad);
    }
}


/**
 * Evaluación de AtomicInteger vs synchronized en este contexto
 * AtomicInteger (compareAndSet()): Es muy rápido en operaciones concurrentes 
 * 		simples con baja o moderada contención. En condiciones de alta contención 
 * 		(muchos hilos concurrentes), su rendimiento puede degradarse debido a múltiples 
 * 		reintentos en el bucle.
 * 
 * synchronized con lock: Garantiza que un hilo tenga acceso exclusivo a la sección crítica 
 * 		y evita los reintentos, pero introduce sobrecarga debido al mecanismo de bloqueo 
 * 		y desbloqueo. En situaciones de baja o moderada contención, synchronized 
 * 		suele ser más lento que AtomicInteger. Sin embargo, en alta contención, 
 * 		synchronized podría ser más estable.
 * 
 * Decisión: Si estás trabajando con muchos hilos que compiten por modificar saldo o 
 * 			si los decrementos son muy frecuentes y simultáneos, probar con synchronized 
 * 			puede ser útil. Sin embargo, en entornos con baja contención o actualizaciones 
 * 			menos frecuentes, AtomicInteger sigue siendo la mejor opción debido a su eficiencia.
 * 			En este caso concreto en el que hablamos de un cajero automático, es un entorno de 
 * 			baja contención en el que no hay muchos hilos decrementando cantidad simultáneamente.
 * 
*/

public class CajeroAutomatico {
    private AtomicInteger saldo = new AtomicInteger(1000);

    /** Otra posible implementación utilizando funciones lambda:
      
    public void retirar(int cantidad) {
        int nuevoSaldo = saldo.updateAndGet(actual -> {
            if (actual - cantidad <= 0) {
                System.out.println("Operación no permitida: saldo insuficiente.");
                return actual; // No se actualiza el saldo
            } else {
                return actual - cantidad; // Se actualiza el saldo
            }
        });

        if (nuevoSaldo != saldo.get() + cantidad) {
            System.out.println("Retiro de " + cantidad + " exitoso. Saldo restante: " + nuevoSaldo);
        }
    }*/
    
    public void retirar(int cantidad) {
    	    	
    	 int actual;
    	 /**
    	  * Si solo hacemos una vez "compareAndSet", podría pasar que no se actualice el valor
    	  * porque otro hilo ha modificado el valor, pero que es posible retirar esa cantidad 
    	  * por lo que debería ejecutarse el método. Por este motivo, introducimos un bucle.
    	  */
	    do {
	        actual = saldo.get(); // Obtener el valor actual de saldo
	        if (actual - cantidad <= 0) {
	            System.out.println("Operación no permitida: saldo insuficiente.");
	            return; // Salir si el saldo sería negativo
	        }
	    } while (!saldo.compareAndSet(actual, actual - cantidad)); // Intentar actualizar saldo
	    
        System.out.println("Retiro de " + cantidad + " exitoso. Saldo restante: " + actual);
        
    }

    public int consultarSaldo() {
        return saldo.get();
    }
    

}
