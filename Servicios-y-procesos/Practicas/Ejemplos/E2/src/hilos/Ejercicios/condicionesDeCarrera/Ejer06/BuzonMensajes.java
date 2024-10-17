package hilos.Ejercicios.condicionesDeCarrera.Ejer06;

import java.util.LinkedList;
import java.util.Queue;

class EnvioMensajes implements Runnable {
    private BuzonMensajes buzon;

    public EnvioMensajes(BuzonMensajes buzon) {
        this.buzon = buzon;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            buzon.enviarMensaje("Mensaje " + i);
        }
    }
}

class RecepcionMensajes implements Runnable {
    private BuzonMensajes buzon;

    public RecepcionMensajes(BuzonMensajes buzon) {
        this.buzon = buzon;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            buzon.recibirMensaje();
        }
    }
}


public class BuzonMensajes {
    private Queue<String> mensajes = new LinkedList<>();

    public void enviarMensaje(String mensaje) {
        mensajes.add(mensaje);
        System.out.println("Mensaje enviado: " + mensaje);
    }

    public void recibirMensaje() {
        if (!mensajes.isEmpty()) {
            String mensaje = mensajes.poll();
            System.out.println("Mensaje recibido: " + mensaje);
        } else {
            System.out.println("No hay mensajes para recibir.");
        }
    }
    
    public static void main(String[] args) {
        BuzonMensajes buzon = new BuzonMensajes();

        Thread hiloEnvio = new Thread(new EnvioMensajes(buzon));
        Thread hiloRecepcion = new Thread(new RecepcionMensajes(buzon));

        hiloEnvio.start();
        hiloRecepcion.start();
    }
}