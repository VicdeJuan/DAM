package secuencial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListadoTransferencias {
	List<Transferencia> listado;
	private final int limite_a_imprimir = 3;
	
	

	/**
	 * @param listado
	 */
	public ListadoTransferencias(List<Transferencia> listado) {
		super();
		this.listado = listado;
	}



	@Override
	public String toString() {
		String retval = "";
		int i=0;
		for (Transferencia t : listado) {
			retval += t.toString() + "\n";
		}
		return retval;
	}



	public void procesarTodas(HashMap<String, Cliente> clientes) {
		
		for (Transferencia t : listado) {
			t.procesar(clientes);
		}
		
	}



	public int size() {
		return listado.size();
	}
		
	
}
