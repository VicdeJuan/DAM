package examen.repasandoPOO;

public class Vehículo {
    private String marca;
    private String modelo;
    private int año;
    private Persona propietario; // Es opcional tener un propietario
    private static int contador = 0; // Variable estática para contar los vehículos

    // Constructor sin propietario
    public Vehículo(String marca, String modelo, int año) {
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.propietario = null; // Sin propietario por defecto
        contador++; // Aumenta el contador al crear un vehículo
    }

    // Constructor con propietario
    public Vehículo(String marca, String modelo, int año, Persona propietario) {
        this(marca, modelo, año);
        this.propietario = propietario;
    }

    // Getter y Setter para el propietario
    public Persona getPropietario() {
        return propietario;
    }

    public void setPropietario(Persona propietario) {
        this.propietario = propietario;
    }

    // Método estático para obtener el contador de vehículos creados
    public static int getContador() {
        return contador;
    }
}
