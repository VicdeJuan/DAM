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
	// VERSIONADO
	int condicion; // El = es un operador de asignacion

	scanf("%d",&condicion);

	if ( condicion == 0 ){ 
		printf("Condicion vale 0\n");
	}
	if (condicion == 1){
		printf("Condición vale 1\n");
	} 
	if (condicion != 0 && condicion != 1)
	{ // Solo es del if del == 1.
		printf("Condicion no vale ni 0 ni 1\n");
	}
	
	return EXIT_SUCCESS;
}
