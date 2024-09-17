package ejercicio0; 

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String idUsuario;
    private String nombre;
    private String telefono;
    private String email;
    private List<Préstamo> prestamos;

    // Constructor
    public Usuario(String idUsuario, String nombre, String telefono, String email) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.prestamos = new ArrayList<>();
    }

    // Métodos
    public void registrar() {
        // Lógica para registrar un nuevo usuario, podría incluir agregar al sistema.
    }

    public void actualizarInfo(String nombre, String telefono, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public List<Libro> consultarPrestamos() {
        List<Libro> librosPrestados = new ArrayList<>();
        for (Préstamo prestamo : prestamos) {
            librosPrestados.add(prestamo.getLibro());
        }
        return librosPrestados;
    }

    // Getters y setters
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Préstamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Préstamo> prestamos) {
        this.prestamos = prestamos;
    }
}
