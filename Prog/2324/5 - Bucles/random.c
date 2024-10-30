#include <stdio.h>
#include <stdlib.h>
#include <time.h>

/**
*
*	Autor: Víctor de Juan
*/

int main(){
		
	int r;
	
	//srand(10);
	
	srand(time(NULL)); // Cada segundo
	//srand(getpid()); // Identificador del proceso

	for (int i = 0;i<4;i++){
		//srand(10); esto provocaría que se calculara siempre el mismo número aleatorio.
		r = (rand() % 5)+1; 
// rand()%5 genera aleatorio entre 0 y 4 incluidos. Al sumar 1, consigo números aleatorios entre 1 y 5.
		printf("%d\n",r);
	}

	return EXIT_SUCCESS;
}

/**
 * Construir una función que calcule un número
 * aleatorio entre dos valores dados como
 * argumento.
 *
 * 
 *
 */
