#include <stdio.h>
#include <stdlib.h>

/**
*
*	Autor: Víctor de Juan
*/

int main(){
	int i = 0;
	
	i = i + 1;
	printf("%d\n",i); //1
	
	i += 1; // Incrementa en 1
	printf("%d\n",i); // 2

	i += 3; // Incrementa en 3
	printf("%d\n",i); // 5 (2+3)

	i -= 5; // Decrementa en 5
	printf("%d\n",i); // 0 (5-5)

	i++;// i += 1; i = i + 1 Son 3 formas equivalentes
	printf("%d\n",i); // 1
	i--; // i-=1 ; i = i - 1
	printf("%d\n",i); // 0

	// Nivel avanzado

	printf("Nivel avanzado: \n");
	printf("%d\n",i++); // 0 porque incrementa después de leer el valor.
	printf("%d\n",i); // 1
	printf("%d\n",++i); // 2 porque primero incrementa y después devuelve el valor.
	
	// Análogamente ocurre con i--; --i;

	return EXIT_SUCCESS;
}
