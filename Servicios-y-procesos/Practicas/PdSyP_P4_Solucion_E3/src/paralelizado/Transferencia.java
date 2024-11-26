package paralelizado;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Transferencia {
	
	
	private String origen;
	private String destino;
	private double monto;
	
	
	/**
	 * @param origen
	 * @param destino
	 * @param monto
	 */
	public Transferencia(String origen, String destino, double monto) {
		super();
		this.origen = origen;
		this.destino = destino;
		this.monto = monto;
	}
	/**
	 * @return the origen
	 */
	public String getOrigen() {
		return origen;
	}
	/**
	 * @param origen the origen to set
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	/**
	 * @return the destino
	 */
	public String getDestino() {
		return destino;
	}
	/**
	 * @param destino the destino to set
	 */
	public void setDestino(String destino) {
		this.destino = destino;
	}
	/**
	 * @return the monto
	 */
	public double getMonto() {
		return monto;
	}
	/**
	 * @param monto the monto to set
	 */
	public void setMonto(double monto) {
		this.monto = monto;
	}
	
	@Override
	public String toString() {
		return "Transferencia [origen=" + origen + ", destino=" + destino + ", monto=" + monto + "]";
	}

	public void procesar(ConcurrentHashMap<String, Cliente> clientes) {
		Cliente clienteOrigen = clientes.get(origen);
		Cliente clienteDestino = clientes.get(destino);
		
		// CONTROL DE ERRORES
		if (clienteOrigen == null) {
			System.err.println("Error. Cliente "+origen + " no encontrado en el HashMap");
		}
		else if (clienteDestino == null) {
			System.err.println("Error. Cliente "+destino + " no encontrado en el HashMap");
		}
		
		// PARA EVITAR INTERBLOQUEOS EN LA SINCRONIZACIÓN
		Object first,second;
		if (clienteOrigen.hashCode() < clienteDestino.hashCode()) {
			first = clienteOrigen;
			second = clienteDestino;
		} else {
			first = clienteDestino;
			second = clienteOrigen;
		}
		
		synchronized(first) {
			synchronized(second) {
				//if (true) {
				if (clienteOrigen.getSaldo() >= this.monto) {		
					clienteOrigen.retirar(this.monto);
					clienteDestino.ingresar(this.monto);
				}else {
					System.out.println("Saldo insuficiente para procesar "+this);
				}
			}
		}
		
		
		
	}
	
	
	
	
}
