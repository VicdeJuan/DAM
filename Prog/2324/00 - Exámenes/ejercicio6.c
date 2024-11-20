#include <stdio.h>
#include <stdlib.h>

/**
*
*	Autor: Víctor de Juan
*/

int main(){

	int num,valido=0;
	
	do{
		scanf("%d",&num);
		if (num <=0 || num >10){
			printf("Número no válido.\n");
		}else{
			valido = 1;
		}
	} while (valido == 0);
	printf("Has ingresado el número %d.\n",num);
	return EXIT_SUCCESS;
}
