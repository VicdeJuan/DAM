#include <stdio.h>
#include <stdlib.h>

/**
 *
 *	Autor: Víctor de Juan
 */

int main() {
   int arr[5] = {10, 20, 30, 40, 50};
   int *p = arr;
   int suma = 0;

   for (int i = 0; i < 5; i++) {
	suma += *(p+i);
	//nivel pro: suma += *(p++)
   }

   printf("suma: %d\n\n",suma);


   return 0;
}
