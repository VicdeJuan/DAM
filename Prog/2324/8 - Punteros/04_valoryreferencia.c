#include <stdio.h>
#include <stdlib.h>

/**
*
* 	EJEMPLO DE PASO DE UN ARGUMENTO A UNA FUNCIÓN POR VALOR
*/

void incrementar(int n){
	printf("Valor recibido: %d\n",n);
	n++;
	printf("Valor incrementado en la función: %d\n",n);
}
int main(){
   int valor = 10;
   incrementar(valor); // Le estoy dando EL VALOR 10, no la variable.
   printf("Valor en el main: %d\n",valor); //Imprime un 10
   return EXIT_SUCCESS;
}
