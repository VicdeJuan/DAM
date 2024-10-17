package examen.repasandoPOO;

public class Main {
    public static void main(String[] args) {
        // Crear personas
        Persona persona1 = new Persona("Juan");
        Persona persona2 = new Persona("Ana");

        // Crear coches
        Coche coche1 = new Coche("Toyota", "Corolla", 2020, 4);
        Coche coche2 = new Coche("Honda", "Civic", 2021, 4);
        Coche coche3 = new Coche("Ford", "Focus", 2022, 4);

        // Asignar propietarios a los coches
        coche1.setPropietario(persona1); // Juan tiene el coche 1
        coche2.setPropietario(persona2); // Ana tiene el coche 2
        // El coche 3 no tiene propietario

        // Crear camión
        Camión camion1 = new Camión("Mercedes", "Actros", 2019, 18);
        camion1.setPropietario(persona1); // Juan tiene el camión

        // Mostrar información de los vehículos
        System.out.println("Total de vehículos creados: " + Vehículo.getContador());

        System.out.println("Coche 1 propietario: " + coche1.getPropietario().getNombre());
        System.out.println("Coche 2 propietario: " + coche2.getPropietario().getNombre());
        System.out.println("Coche 3 no tiene propietario.");

        System.out.println("Camión 1 propietario: " + camion1.getPropietario().getNombre());
    }
}
