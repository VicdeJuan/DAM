#include <stdio.h>
#include <stdlib.h>

/**
*
*	Autor: Víctor de Juan
*/

int main(){
	
   float x = 2.1;
   int *p;  // Genera un warning, pero 
// si lo ejecutas, flipas de lo bonito que es.
   p = &x;

   printf("Valor de x: %b\n",x); // 10
   printf("Dirección de x: %p\n",&x);
   printf("Contenido de p: %b\n", *p);

   //printf("Contenido de x:%d\n",*x);
   
   //scanf("%d",&x);


	return EXIT_SUCCESS;
}
