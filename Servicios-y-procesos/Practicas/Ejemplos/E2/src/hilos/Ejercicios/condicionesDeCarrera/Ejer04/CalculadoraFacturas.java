package hilos.Ejercicios.condicionesDeCarrera.Ejer04;

class ProcesarFactura implements Runnable {
    private CalculadoraFacturas calculadora;
    private double monto;
    private double descuento;
    private double tasa;

    public ProcesarFactura(CalculadoraFacturas calculadora, double monto, double descuento, double tasa) {
        this.calculadora = calculadora;
        this.monto = monto;
        this.descuento = descuento;
        this.tasa = tasa;
    }

    @Override
    public void run() {
        calculadora.calcularFactura(monto, descuento, tasa);
    }
}




public class CalculadoraFacturas {
    private double total;

    public void calcularFactura(double monto, double descuento, double tasa) {
        double montoConDescuento = monto - (monto * descuento);
        double montoConTasa = montoConDescuento + (montoConDescuento * tasa);
        total += montoConTasa;
        System.out.println("Factura procesada: " + montoConTasa + ". Total acumulado: " + total);
    }

    public double getTotal() {
        return total;
    }
    
    public static void main(String[] args) {
        CalculadoraFacturas calculadora = new CalculadoraFacturas();

        Thread hilo1 = new Thread(new ProcesarFactura(calculadora, 100, 0.1, 0.15));
        Thread hilo2 = new Thread(new ProcesarFactura(calculadora, 200, 0.05, 0.2));
        Thread hilo3 = new Thread(new ProcesarFactura(calculadora, 150, 0.2, 0.1));

        hilo1.start();
        hilo2.start();
        hilo3.start();

        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Total final: " + calculadora.getTotal());
    }
}

