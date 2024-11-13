#include <stdio.h>
#include <stdlib.h>

/**
 *
 *	Autor: Víctor de Juan
 */

int main(){
	int arr[5] = {1, 2, 3, 4, 5};
	int *p = arr;

	printf("Elemento 1: %d\n", *p);     // 1
	p++;
	printf("Elemento 2: %d\n", *p);     // 2
	p += 2;
	printf("Elemento 4: %d\n", *p);     // 4
	printf("Elemento posición "0": %d\n",p[0]);

	return EXIT_SUCCESS;
}
