package ejercicio1.Paralelo_1_simple;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;

import ejercicio1.Encoder;

import java.nio.charset.StandardCharsets;



/**
 * La clase DescifradorParalelo implementa Runnable para descifrar contraseñas
 * mediante un enfoque recursivo en múltiples hilos.
 */
public class DescifradorParalelo implements Runnable {

    private final byte[] hashCodificado; // Variable de solo lectura que contiene el hash de la contraseña a descifrar

    private static String contraseñaEncontrada; // Variable compartida que contiene la contraseña encontrada
    
    private static volatile boolean encontrada = false; // Variable compartida que indica si ya se encontró la contraseña
    private static final Object bloqueo = new Object(); // Objeto de sincronización para asegurar exclusividad en la actualización de la contraseña encontrada

    private int longitud; // Longitud restante de la contraseña por descifrar
    private String prefijo; // Prefijo actual de la combinación de la contraseña
    
    /**
     * Constructor de la clase DescifradorParalelo.
     * 
     * @param hashCodificado El hash de la contraseña a descifrar
     * @param longitud Longitud de la parte restante de la contraseña por descifrar
     * @param prefijo Prefijo actual en la búsqueda de la contraseña
     */
    public DescifradorParalelo(byte[] hashCodificado, int longitud, String prefijo) {
        this.hashCodificado = hashCodificado;
        this.longitud = longitud;
        this.prefijo = prefijo;
    }

    /**
     * Método recursivo para descifrar la contraseña comparando con el hash proporcionado.
     * 
     * @param hash El hash de la contraseña a descifrar
     * @param prefijo El prefijo actual en la combinación de la contraseña
     * @param longitud Longitud restante de la parte de la contraseña por descifrar
     */
    private void descifrarRecursivo(byte[] hash, String prefijo, int longitud) {
        if (longitud == 0) {
        	 
        	// Pongo este print para que se vea que los hilos siguen ejecutando algo de código, hasta que llegan a la comprobación 
        	// del boolean "encontrada"
        	System.out.println("Probando " +  prefijo);
        	
        	
            String combinacion = prefijo;
            // Compara el hash de la combinación con el hash objetivo
            if (Arrays.equals(hash, Encoder.getHash(combinacion))) {
                synchronized (bloqueo) {
                    if (!encontrada) { // Verifica si ya se encontró la contraseña
                        encontrada = true;
                        DescifradorParalelo.setContraseñaEncontrada(combinacion);
                    }
                }
            }
        } else {
            // Genera combinaciones de contraseñas al añadir caracteres a la combinación actual
            for (char c = 'a'; c <= 'z'; c++) {
                String combinacion = prefijo + c;
                if (!encontrada) {
                    // Llama recursivamente para descifrar la siguiente parte de la contraseña
                    descifrarRecursivo(hash, combinacion, longitud - 1);
                }else {
                	return;
                }
            }
        }
    }

    /**
     * Método que inicia el proceso de desciframiento de la contraseña.
     */
    public void descifrarContraseña() {
        descifrarRecursivo(this.hashCodificado, prefijo, longitud);
    }

    @Override
    public void run() {
        // Inicia el proceso de descifrado.
        descifrarContraseña();
    }

    /**
     * @return El hash codificado de la contraseña
     */
    public byte[] getHashCodificado() {
        return hashCodificado;
    }

    /**
     * @return La contraseña encontrada por los hilos
     */
    public static String getContraseñaEncontrada() {
        return contraseñaEncontrada;
    }

    /**
     * Establece la contraseña encontrada.
     * 
     * @param combinacion La contraseña encontrada
     */
    private static void setContraseñaEncontrada(String combinacion) {
        contraseñaEncontrada = combinacion;
    }
}
