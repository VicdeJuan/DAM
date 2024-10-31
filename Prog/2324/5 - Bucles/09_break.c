#include <stdio.h>
#include <stdlib.h>

/**
*
*	Autor: Víctor de Juan
*/

int main(){

// ¿Cuál es el número en el que llego al 100
// si voy sumando de 1 en 1?

   int valor=0;	
   for (int i=0; i < 100 ; i = i + 2){
	valor += i;
	if (valor >= 100){
		// PARA
		break; // Sale del bucle for
	}
   }
   
   int i=0;
   while(valor <100){
	valor += i++;
//	valor = valor + i; i = i+1;
   }

   printf("Terminado\n");
	
   return EXIT_SUCCESS;
}
