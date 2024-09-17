package hilos.robotsConstructores;

public abstract class Robot {
    protected String nombre;
    protected int tiempoOperacion;

    public Robot(String nombre, int tiempoOperacion) {
        this.nombre = nombre;
        this.tiempoOperacion = tiempoOperacion;
    }

    // Método abstracto que debe ser implementado por las subclases
    public abstract void operar();
    
    public String getNombre() {return nombre;}
    public int getTiempoOperacion() {return this.tiempoOperacion;}
    
}