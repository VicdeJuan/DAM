#include <stdio.h>
#include <stdlib.h>

/**
*
*	Autor: Víctor de Juan
*/

int main(){
	int numero,cociente,resto=0;

	printf("Introduce un número: ");
	scanf("%d",&numero);	

	//numero = 2791	

	do{
		cociente = numero / 10;	
		resto = resto * 10 + (numero % 10);
		numero = cociente;
	} while (cociente != 0);

	printf("%d\n", resto);
	
	return EXIT_SUCCESS;
}
