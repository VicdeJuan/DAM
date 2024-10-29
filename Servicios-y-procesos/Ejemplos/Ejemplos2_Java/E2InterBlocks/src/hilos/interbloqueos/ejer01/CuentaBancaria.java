package hilos.interbloqueos.ejer01;


/**
 * Clase que representa una cuenta bancaria con un balance y un nombre de titular.
 */
public class CuentaBancaria {
    private int balance; // Balance actual de la cuenta
    private String nombre; // Nombre del titular de la cuenta

    /**
     * Constructor de la clase CuentaBancaria.
     *
     * @param nombre Nombre del titular de la cuenta.
     * @param balanceInicial Balance inicial de la cuenta.
     */
    public CuentaBancaria(String nombre, int balanceInicial) {
        this.nombre = nombre;
        this.balance = balanceInicial;
    }

    /**
     * Método para depositar una cantidad en la cuenta.
     *
     * @param cantidad Cantidad a depositar.
     */
    public void depositar(int cantidad) {
        balance += cantidad;
    }

    /**
     * Método para retirar una cantidad de la cuenta.
     *
     * @param cantidad Cantidad a retirar.
     */
    public void retirar(int cantidad) {
        balance -= cantidad;
    }

    /**
     * Método que devuelve el balance actual de la cuenta.
     *
     * @return Balance actual de la cuenta.
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Método que devuelve el nombre del titular de la cuenta.
     *
     * @return Nombre del titular de la cuenta.
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Método que devuelve una representación en cadena de la cuenta.
     *
     * @return Cadena con el nombre del titular y el balance de la cuenta.
     */
    @Override
    public String toString() {
        return nombre + " con " + balance + "€";
    }
}
