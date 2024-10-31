#include <stdio.h>
#include <stdlib.h>

/**
*
*	Autor: Víctor de Juan
*/

int main(){
	
   for (int i=0; i < 100 ; i = i + 2){
// Este bucle, así escrito, no imprime el 100.
// En la última ejecución del bucle i vale 98.
// Por tanto, al incrementar i, i vale 100.
// Y, no cumple la condición

// Para imprimir el 100 hay 2 posibilidades:
// 		i <= 100 en la condición
// 		i < 101
	printf("%d,",i);
   }
   printf("Terminado\n");
	
   return EXIT_SUCCESS;
}
