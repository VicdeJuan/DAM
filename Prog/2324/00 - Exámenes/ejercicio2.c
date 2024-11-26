#include <stdio.h>
#include <stdlib.h>

/**
*
*	Autor: Víctor de Juan
*/

int main(){

	int num;
	scanf("%d",&num);
	switch(num){
		case 1:
			printf("Hoy es ");
			printf("lunes.");
			break;
		case 2:
			printf("Hoy es ");
			printf("martes.");
			break;
		case 3:
			printf("Hoy es ");
			printf("miércoles.");
			break;
		case 4:
			printf("Hoy es ");
			printf("jueves.");
			break;
		case 5:
			printf("Hoy es ");
			printf("viernes.");
			break;
		case 6:
			printf("Hoy es ");
			printf("sábado.");
			break;
		case 7:
			printf("Hoy es ");
			printf("domingo.");
			break;
		default: 
			printf("Número no válido");
	}
	printf("\n");
	return EXIT_SUCCESS;
}
