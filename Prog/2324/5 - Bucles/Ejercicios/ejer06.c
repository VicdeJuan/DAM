#include <stdio.h>
#include <stdlib.h>


int main(){
	int resultado = 0,num;
	printf("Introduce un número: ");
	scanf("%d",&num);
	for (int i = 1; i<=num; i++){
		resultado = resultado + i;
	}

	printf("La suma de los %d primeros números es: %d\n",num,resultado);	

	return EXIT_SUCCESS;
}
