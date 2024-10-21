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