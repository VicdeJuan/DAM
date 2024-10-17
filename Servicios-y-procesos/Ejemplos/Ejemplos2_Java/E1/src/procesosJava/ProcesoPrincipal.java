package procesosJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcesoPrincipal {

	public static void main(String[] args) {
		try {
			String[] infoProceso = {"java","sincronizacion.ProcesoSecundario"};
			Process proceso = Runtime.getRuntime().exec("pwd");
			//Process proceso = Runtime.getRuntime().exec(infoProceso);
			int valorRetorno = proceso.waitFor();
			if (valorRetorno == 0) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
				System.out.println("El proceso se ha completado satisfactoriamente ");
				String line;
	            while ((line = reader.readLine()) != null) {
	                System.out.println(line);
	            }
			} else {
				System.out.println("El proceso ha fallado. Código de error:" + valorRetorno);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
