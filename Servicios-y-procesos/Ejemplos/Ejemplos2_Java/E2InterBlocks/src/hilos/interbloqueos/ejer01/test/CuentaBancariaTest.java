package hilos.interbloqueos.ejer01.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hilos.interbloqueos.ejer01.CuentaBancaria;

class CuentaBancariaTest {

	private CuentaBancaria cuenta;
	
	@BeforeEach
    public void setUp() {
        cuenta = new CuentaBancaria("Nombre",1000);
    }
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
