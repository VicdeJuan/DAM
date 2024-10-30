#include <stdio.h>
#include <stdlib.h>

/**
*
*	Autor: Víctor de Juan
*/
#define LIMITE 90

int main(){
 // Sumar los números NO múltiplos de 3 hasta llegar a 100

	int suma_acumulada;

	for (int = 0; i<100; i++){
		if (i % 3 == 0){
			// Ir a la siguente vuelta
			continue;
		}
		suma_acumulada += i;
		if (suma_acumulada >= LIMITE){
			break;
		}
	}
	// Este bucle hace lo mismo
	for (int i = 0; i<100; i++){
		if (i%3 != 0){
			suma_acumulada+=i;
			if (suma_acumulada >=LIMITE)
				break;
		}
	}

	return EXIT_SUCCESS;
}
