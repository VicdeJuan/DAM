package ejercicio2;

public class MainEjer2 {
    public static void main(String[] args) {
        // Crear instancias de Documento y Reporte
        Imprimible doc = new Documento("Este es el contenido del documento.");
        Imprimible rep = new Reporte("Este es el resumen del reporte.");

        // Llamar al método imprimir() usando la referencia de tipo Imprimible
        doc.imprimir(); // Salida: Imprimiendo documento: Este es el contenido del documento.
        rep.imprimir(); // Salida: Imprimiendo reporte: Este es el resumen del reporte.
    }
}
