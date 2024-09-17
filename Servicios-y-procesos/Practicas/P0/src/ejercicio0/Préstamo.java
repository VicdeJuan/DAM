package ejercicio0;

import java.util.Date;

public class Préstamo {
    private String idPrestamo;
    private Libro libro;
    private Usuario usuario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    // Constructor
    public Préstamo(String idPrestamo, Libro libro, Usuario usuario, Date fechaPrestamo) {
        this.idPrestamo = idPrestamo;
        this.libro = libro;
        this.usuario = usuario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = null; // Al inicio no hay fecha de devolución
    }

    // Métodos
    public void realizarPrestamo() {
        libro.prestamo();
        usuario.getPrestamos().add(this);
    }

    public void finalizarPrestamo(Date fechaDevolucion) {
        libro.devolucion();
        this.fechaDevolucion = fechaDevolucion;
    }

    // Getters y setters
    public String getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(String idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}
