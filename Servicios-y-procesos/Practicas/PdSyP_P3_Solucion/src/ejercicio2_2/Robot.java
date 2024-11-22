package ejercicio2_2;


// Clase abstracta porque no permite instancias y obliga a implementar algunos métodos y utilizar algunos atributos.
public abstract class Robot extends Thread{
	
    protected String nombre;
    protected int tiempoOperacion;

    public Robot(String nombre, int tiempoOperacion) {
        this.nombre = nombre;
        this.tiempoOperacion = tiempoOperacion;
    }

    // Método abstracto que debe ser implementado por las subclases
    public abstract void operar();
    
    
    public String getNombre() {
    	return nombre;
    }
    
    public int getTiempoOperacion() {
    	return this.tiempoOperacion;
    }
    
}