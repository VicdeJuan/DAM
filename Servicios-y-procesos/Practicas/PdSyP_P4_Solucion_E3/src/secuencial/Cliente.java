package secuencial;

public class Cliente {
	private String id;
	private String nombre;
	private double saldo;
	private String numeroCuenta;
	private String direccion;
	
	public Cliente() {
		
	}

	
	/**
	 * Constructor de cliente.	
	 * @param id
	 * @param nombre
	 * @param saldo
	 * @param numeroCuenta
	 * @param direccion
	 */
	public Cliente(String id, String nombre, double saldo, String numeroCuenta, String direccion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.saldo = saldo;
		this.numeroCuenta = numeroCuenta;
		this.direccion = direccion;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the saldo
	 */
	public double getSaldo() {
		return saldo;
	}

	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	/**
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * @param numeroCuenta the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	@Override
	public String toString() {
		return "Cliente "+id+":\n\t- "+nombre + "\n\t- Num cuenta:"+numeroCuenta+"\n\t- Saldo: "+ String.format("%.02f", saldo);
	}


	public void retirar(double monto) {
		this.saldo -= monto;		
	}


	public void ingresar(double monto) {
		this.saldo += monto;
		
	}
	
}
