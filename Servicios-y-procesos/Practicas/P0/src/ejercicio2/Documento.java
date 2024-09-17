package ejercicio2;

//Clase Documento que implementa la interfaz Imprimible

public class Documento implements Imprimible {
 private String contenido;

 // Constructor
 public Documento(String contenido) {
     this.contenido = contenido;
 }

 @Override
 public void imprimir() {
     System.out.println("Imprimiendo documento:");
     System.out.println(contenido);
 }
}
