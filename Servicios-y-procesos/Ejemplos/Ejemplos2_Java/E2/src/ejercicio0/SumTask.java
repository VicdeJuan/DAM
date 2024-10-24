package ejercicio0;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class SumTask extends RecursiveTask<Integer> {
	
	private static final long serialVersionUID = 1L;
	
	private static final int THRESHOLD = 10;
	private int[] array;
	private int start;
	private int end;

	public SumTask(int[] array, int start, int end) {
	    this.array = array;
	    this.start = start;
	    this.end = end;
	}

	@Override
	protected Integer compute() {
	    if (end - start <= THRESHOLD) {
	        int sum = 0;
	        for (int i = start; i < end; i++) {
	            sum += array[i];
	        }
	        return sum;
	    } else {
	        int mid = (start + end) / 2;
	        SumTask leftTask = new SumTask(array, start, mid);
	        SumTask rightTask = new SumTask(array, mid, end);
	        leftTask.fork(); // Divide la tarea en subtareas
	        int rightResult = rightTask.compute();
	        int leftResult = leftTask.join();
	        return leftResult + rightResult;
	    }
	}
	

    public static void main(String[] args) {
        // Crear un arreglo de ejemplo
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1; // Llenar el arreglo con los números del 1 al 100
        }

        // Crear una instancia de ForkJoinPool
        ForkJoinPool pool = new ForkJoinPool();

        // Crear la tarea SumTask para sumar los elementos del arreglo
        SumTask task = new SumTask(array, 0, array.length);

        // Ejecutar la tarea y obtener el resultado
        
        int result = pool.invoke(task);

        // Mostrar el resultado
        System.out.println("La suma de los elementos del arreglo es: " + result);
    }

}