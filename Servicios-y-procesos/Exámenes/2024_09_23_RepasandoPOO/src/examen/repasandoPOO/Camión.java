package examen.repasandoPOO;

public class Camión extends Vehículo {
    private int capacidadCarga; // Capacidad en toneladas

    // Constructor sin propietario
    public Camión(String marca, String modelo, int año, int capacidadCarga) {
        super(marca, modelo, año); // Llama al constructor de Vehículo
        this.capacidadCarga = capacidadCarga;
    }

    // Constructor con propietario
    public Camión(String marca, String modelo, int año, int capacidadCarga, Persona propietario) {
        super(marca, modelo, año, propietario); // Llama al constructor de Vehículo con propietario
        this.capacidadCarga = capacidadCarga;
    }

    // Getter para la capacidad de carga (si es necesario)
    public int getCapacidadCarga() {
        return capacidadCarga;
    }
}
