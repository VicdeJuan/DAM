#include <stdio.h>
#include <stdlib.h>

/**
*
*	Autor: Víctor de Juan
*/

int main(){
	
	int opcion = 0; // Es necesario 
	// (que no obligatorio) inicializar la variable
	// porque el primer uso de la variable es de 
	// lectura
	
	while(opcion != 4){
		//Código a ejecutar
		printf("Introduce una opción: (4 para salir)\n");
		scanf("%d", &opcion);
	}

	return EXIT_SUCCESS;
}
