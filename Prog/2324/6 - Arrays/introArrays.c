#include <stdio.h>
#include <stdlib.h>

/**
*
*	Autor: Víctor de Juan
*/

int main(){

	char palabra[10] = "Hola";
	//palabra[4] tiene un '\0'
	
	int numeros[5] = {10,20,30,40,50};
	
	for (int i=0;i<5;i++)
		printf("%d\n",numeros[i]);

	return EXIT_SUCCESS;
}
