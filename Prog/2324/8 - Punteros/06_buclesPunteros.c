#include <stdio.h>
#include <stdlib.h>

/**
*
*	Autor: Víctor de Juan
*/

int main(){
	int arr[5] = {1,2,3,4,5};
	
	// Recorrer el array y sumarlo
	
	int numElementos = sizeof(arr)/sizeof(int);

	//for (int * p = arr; p < &arr[numElementos] ;p++)
	for (int * p = arr; p <= &arr[numElementos-1] ;p++)
		printf("%d\n",*p);
	

	printf("Sizeof arr: %ld\n",sizeof(arr));
	printf("Sizeof int: %ld\n",sizeof(int));
	printf("Elementos del array: %ld\n", sizeof(arr)/sizeof(int));





	char palabra[10] = "gratis";
	
	for (char * l = palabra; *l != '\0'; l++)
		printf("%c ",*l);
	
	printf("\n");

	return EXIT_SUCCESS;
}
