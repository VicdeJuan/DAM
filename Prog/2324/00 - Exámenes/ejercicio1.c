#include <stdio.h>
#include <stdlib.h>

/**
*
*	Autor: Víctor de Juan
*/

int main(){
	int num;

	scanf("%d",&num);

	if(num>0){
		printf("El número %d es positivo\n",num);
	} else if (num <0) {
		printf("El número %d es positivo\n",num);
	} else{
		printf("El número ingresado es cero.\n");
	}

	return EXIT_SUCCESS;
}
