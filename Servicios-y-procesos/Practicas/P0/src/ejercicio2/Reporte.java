package ejercicio2;

//Clase Reporte que implementa la interfaz Imprimible
class Reporte implements Imprimible {
 private String resumen;

 // Constructor
 public Reporte(String resumen) {
     this.resumen = resumen;
 }

 @Override
 public void imprimir() {
     System.out.println("Imprimiendo reporte:");
     System.out.println(resumen);
 }
}
