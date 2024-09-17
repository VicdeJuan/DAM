package ejercicio1;

public class MainEjer1 {
    public static void main(String[] args) {
        // Crear instancias de Perro y Gato
        Animal miPerro = new Perro();
        Animal miGato = new Gato();
        
        // Llamar al método hacerSonido() usando la referencia de tipo Animal
        miPerro.hacerSonido(); // Salida: El perro dice: ¡Guau!
        miGato.hacerSonido();  // Salida: El gato dice: ¡Miau!
    }
}
