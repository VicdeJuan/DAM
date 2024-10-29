#include <stdio.h>
#include <stdlib.h>

/**
*
*	Autor: Víctor de Juan
*/

int main(){
	
	int opcion; // No es necesario inicializarla
	// porque el primer uso de la variable es
	// de escritura.
	
	do {
		//Código a ejecutar
		printf("Introduce una opción: (4 para salir)\n");
		scanf("%d", &opcion);
	} while(opcion != 4);

	return EXIT_SUCCESS;
}
