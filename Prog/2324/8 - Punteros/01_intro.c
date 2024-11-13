#include <stdio.h>
#include <stdlib.h>

/**
*
*	Autor: Víctor de Juan
*/

int main(){
	
   int x = 10;
   int *p; // Esta variable es de tipo "int *", es decir, puntero a entero.
	// Esto quiere decir que va a almacenar la dirección de memoria 
	// de una variable de tipo entero.
   p = &x;

   printf("Valor de x: %d\n",x); // 10
   printf("Dirección de x: %p\n",&x);
		//0x7fffbe446b1c
		//01111111111111111011111001
		//0001000110101100011100
   printf("Valor de p: %p\n",p);
   // ¿Cómo accedo al contenido de una dirección de memoria?
   printf("Contenido de p: %d\n", *p);

   printf("Dirección de memoria de la variable p: %p\n", &p);
   //printf("Contenido de x:%d\n",*x);
   
   //scanf("%d",&x);


	return EXIT_SUCCESS;
}
