#include <stdio.h>
#include <stdlib.h>

/**
*
*	Autor: Víctor de Juan
*/

int main(){
	
   int i = 0;

   for ( /*valor inicial*/ i = 0 ; i<5 /*condicion*/ ; i = i + 1 /*Código a ejecutar entre vueltas*/ ){
	printf("%d,",i);
   }
   printf("Terminado\n");
	/** 
		1) inicializo i=0
		2) compruebo si i<5. Como se cumple, ejecuto el interior del bucle.
		3) printf
		4) Terminando el interior del bucle, ejecuto el "código entre vueltas": i=i+1
		5) Vuelvo al punto 2. Compruebo si i<5. Se cumple porque i==1. Ejecuto el interior.
		6) printf
		7) Terminando el interior del bucle, ejecuto: i=i+1. Ahora i vale 2.
		8) Vuelvo a la condición: ¿i<5? Sí. Interior del bucle.
		9) Printf
		10) Terminando el interior del bucle, ejecuto i=i+1. Ahora i vale 3.
		11) Comprobar la condicion. ¿i<5) Sí. Interior del bucle.
		12) 13) i vale 4 14) Condicion: ¿i<5? Sí. Interior. 
		15) 16) i vale 5. 17) Condición: ¿i<5? No. Salimos del bucle. 
	*/
	
   return EXIT_SUCCESS;
}
