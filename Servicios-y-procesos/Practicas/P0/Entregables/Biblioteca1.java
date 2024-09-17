package ejercicio0;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Biblioteca1 {
    public static void main(String[] args) {
    	// Crear un libro
        Libro libro1 = new Libro("978-3-16-148410-0", "Cien años de soledad", "Gabriel García Márquez", 1967);
        System.out.println("Información del libro:");
        System.out.println(libro1.informacion());

        // Crear un usuario
        Usuario usuario1 = new Usuario("u123", "Juan Pérez", "555-1234", "juan.perez@example.com");
        
        // Crear un préstamo
        Date fechaPrestamo = new Date(); // Fecha actual
        Préstamo prestamo1 = new Préstamo("p001", libro1, usuario1, fechaPrestamo);
        
        // Realizar el préstamo
        prestamo1.realizarPrestamo();
        System.out.println("\nInformación del libro después del préstamo:");
        System.out.println(libro1.informacion());

        // Consultar los libros prestados por el usuario
        System.out.println("\nLibros prestados por el usuario:");
        for (Libro libro : usuario1.consultarPrestamos()) {
            System.out.println(libro.informacion());
        }

        // Devolver el libro
        Date fechaDevolucion = new Date(); // Fecha actual
        prestamo1.finalizarPrestamo(fechaDevolucion);
        System.out.println("\nInformación del libro después de la devolución:");
        System.out.println(libro1.informacion());
    }
}