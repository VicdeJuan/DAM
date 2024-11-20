#include <stdio.h>
#include <stdlib.h>

/**
*
*	Autor: Víctor de Juan
*/

int main(){
	int ingresado=0,acumulado = 0;

	while(ingresado>=0){
		acumulado+=ingresado;
		scanf("%d",&ingresado);
	}
	
	printf("La suma de los números ingresados es %d.\n",acumulado);

	return EXIT_SUCCESS;
}
