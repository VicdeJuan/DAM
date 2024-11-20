#include <stdio.h>
#include <stdlib.h>

/**
*
*	Autor: Víctor de Juan
*/

int main(){
	int n,suma=0;
	scanf("%d",&n);
	
	for (int i = 1; i<=n; i++){
		suma += i;
	}
	printf("La suma de los %d números es %d.\n",n,suma);
	
	
	return EXIT_SUCCESS;
}
