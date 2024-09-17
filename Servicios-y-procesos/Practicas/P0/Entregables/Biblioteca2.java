package ejercicio0;

import java.util.Date;

public class Biblioteca2 {
	
    public static void main(String[] args) {

	    // Crear libros
	    Libro libro1 = new Libro("978-1-86197-876-9", "1984", "George Orwell", 1949);
	    Libro libro2 = new Libro("978-0-14-103613-7", "To Kill a Mockingbird", "Harper Lee", 1960);
	
	    // Crear usuario
	    Usuario usuario2 = new Usuario("u456", "Ana Gómez", "555-5678", "ana.gomez@example.com");
	    
	    // Crear préstamos
	    Date fechaPrestamo1 = new Date(); // Fecha actual
	    Préstamo prestamo1 = new Préstamo("p002", libro1, usuario2, fechaPrestamo1);
	    prestamo1.realizarPrestamo();
	
	    Date fechaPrestamo2 = new Date(); // Fecha actual
	    Préstamo prestamo2 = new Préstamo("p003", libro2, usuario2, fechaPrestamo2);
	    prestamo2.realizarPrestamo();
	
	    // Consultar los libros prestados por el usuario
	    System.out.println("Libros prestados por el usuario antes de la devolución:");
	    for (Libro libro : usuario2.consultarPrestamos()) {
	        System.out.println(libro.informacion());
	    }
	
	    // Devolver un libro
	    Date fechaDevolucion1 = new Date(); // Fecha actual
	    prestamo1.finalizarPrestamo(fechaDevolucion1);
	
	    // Consultar los libros prestados por el usuario después de la devolución
	    System.out.println("\nLibros prestados por el usuario después de la devolución:");
	    for (Libro libro : usuario2.consultarPrestamos()) {
	        System.out.println(libro.informacion());
	    }
    }
}
