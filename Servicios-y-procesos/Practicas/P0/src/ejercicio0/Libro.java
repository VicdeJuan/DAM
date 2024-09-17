package ejercicio0;


public class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private boolean disponible;

    // Constructor
    public Libro(String isbn, String titulo, String autor, int anioPublicacion) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.disponible = true;
    }

    // Métodos
    public void prestamo() {
        if (disponible) {
            disponible = false;
        } else {
            System.out.println("El libro ya está prestado.");
        }
    }

    public void devolucion() {
        disponible = true;
    }

    public String informacion() {
        return "ISBN: " + isbn + ", Título: " + titulo + ", Autor: " + autor + ", Año de Publicación: " + anioPublicacion + ", Disponible: " + disponible;
    }

    // Getters y setters
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
