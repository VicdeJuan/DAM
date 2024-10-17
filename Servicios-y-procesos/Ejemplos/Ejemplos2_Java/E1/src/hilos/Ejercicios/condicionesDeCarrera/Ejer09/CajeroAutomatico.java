package hilos.Ejercicios.condicionesDeCarrera.Ejer09;


class UsuarioCajero implements Runnable {
    private CajeroAutomatico cajero;
    private int cantidad;

    public UsuarioCajero(CajeroAutomatico cajero, int cantidad) {
        this.cajero = cajero;
        this.cantidad = cantidad;
    }

    @Override
    public void run() {
        cajero.retirar(cantidad);
    }
}


public class CajeroAutomatico {
    private int saldo = 1000;

    public void retirar(int cantidad) {
        if (saldo >= cantidad) {
            saldo -= cantidad;
            System.out.println("Retiro de " + cantidad + " exitoso. Saldo restante: " + saldo);
        } else {
            System.out.println("Fondos insuficientes para retirar " + cantidad);
        }
    }

    public int consultarSaldo() {
        return saldo;
    }
    

}

