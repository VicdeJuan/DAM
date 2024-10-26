package hilos.Ejercicios.condicionesDeCarrera.Ejer05;


import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

class Registro implements Runnable {
    private RegistroUsuarios registro;
    private String nombreUsuario;

    public Registro(RegistroUsuarios registro, String nombreUsuario) {
        this.registro = registro;
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    public void run() {
        registro.registrarUsuario(nombreUsuario);
    }
}



/**
 * Para la declaración del campo usuarios, es generalmente mejor utilizar la interfaz (Set<String>)
 * 		 en lugar de la implementación específica (ConcurrentSkipListSet<String>), 
 * 		porque esto mejora la flexibilidad y mantiene el código más desacoplado de una implementación 
 * 		en particular.
 * 
 *  Al usar la interfaz, podrías cambiar fácilmente la implementación subyacente a otra clase 
 *  		que implemente Set sin modificar el resto de tu código, siempre que siga cumpliendo 
 *  		con los mismos requisitos de concurrencia. Por ejemplo, podrías cambiar de 
 *  		ConcurrentSkipListSet a ConcurrentHashMap.newKeySet(), que puede ofrecer un mejor 
 *  		rendimiento en algunos casos de concurrencia.
 *  
 *  Observación: ¿Cuándo es útil ConcurrentHashMap.newKeySet()?
 *  	Es útil cuando necesitas un Set concurrente, pero no requieres un orden 
 *  	específico en los elementos (a diferencia de ConcurrentSkipListSet, que mantiene orden). 
 *  	También suele ofrecer mejor rendimiento que ConcurrentSkipListSet en ciertas aplicaciones 
 *  	concurrentes, especialmente cuando el orden de los elementos no es importante.
 */
public class RegistroUsuarios {
	private Set<String> usuarios = new ConcurrentSkipListSet<>();

    public void registrarUsuario(String nombreUsuario) {
        boolean agregado = usuarios.add(nombreUsuario); 
        if (agregado) {
            System.out.println("Usuario registrado: " + nombreUsuario);
        } else {
            System.out.println("El usuario " + nombreUsuario + " ya existe.");
        }
    }
    

    public static void main(String[] args) {
        RegistroUsuarios registro = new RegistroUsuarios();

        Thread hilo1 = new Thread(new Registro(registro, "usuario1"));
        Thread hilo2 = new Thread(new Registro(registro, "usuario3"));
        Thread hilo3 = new Thread(new Registro(registro, "usuario3"));
        Thread hilo4 = new Thread(new Registro(registro, "usuario1"));

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        
        try {
			hilo1.join();
			hilo2.join();
			hilo3.join();
			hilo4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
                
    }

}