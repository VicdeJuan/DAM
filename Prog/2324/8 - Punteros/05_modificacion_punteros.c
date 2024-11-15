#include <stdio.h>
#include <stdlib.h>

/**
 *
 *	Autor: Víctor de Juan
 */

int main(){
	int arr[5] = {1, 2, 3, 4, 5};
	int *p = arr;

	printf("Elemento 1: %d\n", *p);     // 
	p++;
	printf("Elemento 2: %d\n", *p);     // 
	p += 2;
	printf("Elemento 4: %d\n", *p);     //
	printf("Elemento posición 0 %d\n",p[0]);
	printf("Elemento posición 0 %d\n",p[-2]);
	printf("Elemento posición 0 %d\n",p[1]);

	p = arr; // p[0] vuelve a ser 1

	return EXIT_SUCCESS;
}
