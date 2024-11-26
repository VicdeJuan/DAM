package secuencial;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

//https://mvnrepository.com/artifact/com.google.code.gson/gson/2.11.0


import com.google.gson.Gson;

public class MainSecuencial {
	
	public final static int NUM_CLIENTES = 6;
	public final static int NUM_ARCHIVOS_TRANSACCION = 10;
	
    public static void main(String[] args) {
        // INICIALIZACIÓN DE VARIABLES
        String fileName;
        BufferedReader buffer;
        Gson gson = new Gson();
        HashMap<String,Cliente> clientes = new HashMap<String,Cliente>(); 
        // HashMap de "id" -> Cliente. 
        // Se hace necesario para poder procesar las trasnferencias desde la clase transferencia.
        
        
        ArrayList<ListadoTransferencias> todasLasTransferencias = new ArrayList<ListadoTransferencias>();
        
        
        
        // LECTURA DE CLIENTES
        for (int i=1; i<=NUM_CLIENTES;i++) {
        	
        	fileName = "data/Cliente"+i+".json";
        	try {
    			buffer = new BufferedReader(new FileReader(fileName));
    			if (buffer != null) { // Para evitar nullPointerException
    		        Cliente c = gson.fromJson(buffer, Cliente.class);
    		        clientes.put(c.getId(), c);
    			}
    		} catch (FileNotFoundException e) {
    			System.err.println("Archivo " + fileName + " no encontrado");
    			System.exit(-1);
    		}	
        }
        // FIN LECTURA CLIENTES
        
        
        
        //LECTURA TRANSACCIONES
        for (int i=1; i<=NUM_ARCHIVOS_TRANSACCION;i++) {
        	fileName = "data/transferencias"+i+".json";
        	try {
    			buffer = new BufferedReader(new FileReader(fileName));
    			 
    			todasLasTransferencias.add( // Añadimos un listado de transferencias a procesar.
						new ListadoTransferencias(
								Arrays.asList( // Lo transformamos a una lista para poder trabajar con el listado.
										gson.fromJson(buffer, Transferencia[].class) 
										// Leemos el JSON como un array. La estructura del JSON empieza con un '[', no con un '{'. 
										// Eso significa que es un array de objetos. El quid de la cuestión está en que los JSON 
										// son, en el fondo, mapas/diccionarios que asocian una clave con un valor.
								)
						)
				);
    			System.out.println("Procesadas " + todasLasTransferencias.getLast().size() + " transferencias del fichero " + fileName);
    			
    		} catch (FileNotFoundException e) {
    			System.err.println("Archivo " + fileName + " no encontrado");
    			System.exit(-1);
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
