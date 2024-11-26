package paralelizado;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private boolean enStock;

    // Constructor vacío
    public Producto() {
    }

    // Constructor con parámetros
    public Producto(int id, String nombre, double precio, boolean enStock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.enStock = enStock;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public boolean isEnStock() { return enStock; }
    public void setEnStock(boolean enStock) { this.enStock = enStock; }

    @Override
    public String toString() {
        return "Producto{" +
               "id=" + id +
               ", nombre='" + nombre + '\'' +
               ", precio=" + precio +
               ", enStock=" + enStock +
               '}';
    }
}
