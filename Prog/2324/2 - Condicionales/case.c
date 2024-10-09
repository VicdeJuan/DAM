#include <stdio.h>
#include <stdlib.h>

/**
*
*	Autor: Víctor de Juan
*/


/**
	if ( pregunta de sí o no){
		Código si respuesta afirmativa
	} 
	

*/

/** OPERADORES DE COMPARACIÓN SON:
 * 	> Mayor
*	< menor
*	<= menor o igual
 * 	>= mayor o igual
 *	== igual 
 * 	!= distinto
 */

int main(){
	int condicion; // El = es un operador de asignacion

	scanf("%d",&condicion);

	switch (condicion){
		case 0:
			printf("Vale 0\n");
			break;
		case 1: 
			printf("Vale 1\n");
			break;
		case 2: 
			printf("Vale 2\n");
			break;
		case 3:
			printf("Vale 3\n");
			break;
		default:
			printf("Ni 1, ni 2, ni 3, ni 0\n");
	}
		
	
	return EXIT_SUCCESS;
}
