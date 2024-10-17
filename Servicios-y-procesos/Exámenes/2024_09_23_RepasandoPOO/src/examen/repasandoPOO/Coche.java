package examen.repasandoPOO;

public class Coche extends Vehículo {
    private int numeroPuertas;

    // Constructor sin propietario
    public Coche(String marca, String modelo, int año, int numeroPuertas) {
        super(marca, modelo, año); // Llama al constructor de Vehículo
        this.numeroPuertas = numeroPuertas;
    }

    // Constructor con propietario
    public Coche(String marca, String modelo, int año, int numeroPuertas, Persona propietario) {
        super(marca, modelo, año, propietario); // Llama al constructor de Vehículo con propietario
        this.numeroPuertas = numeroPuertas;
    }

    // Getter para el número de puertas (si es necesario)
    public int getNumeroPuertas() {
        return numeroPuertas;
    }
}
