package paralelizado;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

//https://mvnrepository.com/artifact/com.google.code.gson/gson/2.11.0


import com.google.gson.Gson;

public class MainParalelizado {
	
	public final static int NUM_CLIENTES = 6;
	public final static int NUM_ARCHIVOS_TRANSACCION = 10;
	
    public static void main(String[] args) {
        // INICIALIZACIÓN DE VARIABLES
        
        
        Gson gson = new Gson();
        ConcurrentHashMap<String,Cliente> clientes = new ConcurrentHashMap<String,Cliente>(); 
        // HashMap de "id" -> Cliente. 
        // Se hace necesario para poder procesar las trasnferencias desde la clase transferencia.
        
        /**
         * Declaramos este array para almacenar los hilos creados y poder hacer join al finalizar.
         */
        Thread[] hilosDeLectura = new Thread[NUM_CLIENTES+NUM_ARCHIVOS_TRANSACCION];
        int contador = 0;
        
        /**
         * Utilizamos una lista concurrente
         */
        CopyOnWriteArrayList<ListadoTransferencias> todasLasTransferencias = new CopyOnWriteArrayList<ListadoTransferencias>();
        
        
        
        // LECTURA DE CLIENTES EN PARALELO
        for (int i=1; i<=NUM_CLIENTES;i++) {
        	String fileNameCliente = "data/Cliente"+i+".json";
        	hilosDeLectura[contador] = new Thread( () -> { // Con esta expresión lambda, creamos un hilo con un argumento de tipo Runnable.
	          	try {
	        		BufferedReader bufferClientes = new BufferedReader(new FileReader(fileNameCliente));
	    			if (bufferClientes != null) { // Para evitar nullPointerException
	    		        Cliente c = gson.fromJson(bufferClientes, Cliente.class);
	    		        clientes.put(c.getId(), c);
	    			}
	    		} catch (FileNotFoundException e) {
	    			System.err.println("Archivo " + fileNameCliente + " no encontrado");
	    			System.exit(-1);
	    		}	
	        });
        	hilosDeLectura[contador].start(); 
        	contador++;
        }
        // FIN LECTURA CLIENTES
        
                
        //LECTURA TRANSACCIONES EN PARALELO
        for (int i1=1; i1<=NUM_ARCHIVOS_TRANSACCION;i1++) {
        	String fileNameTransfer = "data/transferencias"+i1+".json"; // La declaramos fuera del hilo porque las variables que se utilicen
        	// en la expresión lambda deben ser finales o efectivametne finales. Debe ser así porque las expresiones lambda no crean copias
        	// de las variables para utilizarlas, sino que trabajan con la referencia a la variable. Si no son final, el contenido de las
        	// referencias podría cambiar resultando en comportamientos impredecibles. 
        	// En este caso, no está declarada como "final" sino que es "efectivamente final" porque no se modifica su valor. Además, 
        	// Al declararla en cada vuelta del bucle estamos creando muchas variables diferentes (aunque se llamen igual). Fíjate que, si
        	// declaras la variable fileNameTransfer antes del bucle y en cada vuelta del bucle le vas cambiando el valor, obtienes el error 
        	// de que la variable debe ser final.
        	
        	
        	hilosDeLectura[contador] = new Thread( () -> { // Con esta expresión lambda, creamos un hilo con un argumento de tipo Runnable.
	        	try {
	        		BufferedReader bufferTransferencias = new BufferedReader(new FileReader(fileNameTransfer));
	    			 
	    			todasLasTransferencias.add( // Añadimos un listado de transferencias a procesar.
							new ListadoTransferencias(
									Arrays.asList( // Lo transformamos a una lista para poder trabajar con el listado.
											gson.fromJson(bufferTransferencias, Transferencia[].class) 
											// Leemos el JSON como un array. La estructura del JSON empieza con un '[', no con un '{'. 
											// Eso significa que es un array de objetos. El quid de la cuestión está en que los JSON 
											// son, en el fondo, mapas/diccionarios que asocian una clave con un valor.
									)
							)
					);
	    			System.out.println("Procesadas " + todasLasTransferencias.getLast().size() + " transferencias del fichero " + fileNameTransfer);
	    			
	    		} catch (FileNotFoundException e) {
	    			System.err.println("Archivo " + fileNameTransfer + " no encontrado");
	    			System.exit(-1);
	    		}	
        	});
        	hilosDeLectura[contador].start();
        	contador++;
        }
        
        for (Thread t : hilosDeLectura) {
        	try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        	
        for (Cliente c : clientes.values()) {
        	System.out.println(c);
        }
        
        System.out.println("#########################################################################");
        
        for (ListadoTransferencias listado : todasLasTransferencias)
        	listado.procesarTodas(clientes);
        
        for (Cliente c : clientes.values()) {
        	System.out.println(c);
        }
        

        /*try {
            // Deserializar el JSON a una lista de productos
            //List<Producto> productos = objectMapper.readValue(json, new TypeReference<List<Producto>>() {});

            // Imprimir los productos resultantes
            System.out.println("JSON deserializado a lista de productos:");
            for (Producto producto : productos) {
                System.out.println(producto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
