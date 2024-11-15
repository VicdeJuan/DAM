#include <stdio.h>
#include <stdlib.h>

/**
 *
 *	Autor: Víctor de Juan
 */

void intercambiar(int * n1,int * n2){
	int aux = *n1;
	*n1 = *n2;
	*n2 = aux;
}

int main(){
	int n1 = 10;
	int n2 = 5;
	printf("Antes:\n");
	printf("%d\n",n1); //5
	printf("%d\n",n2); //10
	intercambiar(&n1,&n2); // Paso son las referencias de las variables, es decir, sus direcciones.
	printf("Después:\n");
	printf("%d\n",n1); //5
	printf("%d\n",n2); //10

	return EXIT_SUCCESS;
}
