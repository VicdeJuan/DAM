#include <stdio.h>
#include <stdlib.h>

/**
*
*	Autor: Víctor de Juan
*/

int main(){
	int num;
	scanf("%d",&num);

	printf("El número %d es ",num);
	num % 2 == 0 ? printf("par"):printf("impar");
	printf(".\n");

	return EXIT_SUCCESS;
}
