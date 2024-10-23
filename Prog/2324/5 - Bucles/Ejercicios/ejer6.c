#include <stdio.h>
#include <stdlib.h>


int main(){
	
	// Solución Arnau
	for(int i = 0; i<21;  i+=2){ // 11 vueltas
		printf("%d",i); // 11 veces
		if (i<20)	// 11 veces
			printf(","); // 10 veces
	}
	// Total de líneas ejecutadas: 32 + el bucle
	printf(".");
	printf("\n");

	// Solución segunda, parecida.
	for(int i = 0; i<=20;  i+=2){ // 11 vueltas
		if (i<20){
			printf("%d,",i);
		} else{
			printf("%d.",i);
		}
	}
	printf("\n");
	// Solución tercera
	int i;
	for (i=0; i<19; i+=2){ // 10 veces
		printf("%d,",i); // 10 veces
	}
	// Total de líneas ejecutadas: 10 + bucle
	// Al salir del bucle, i vale 20.
	printf("%d.",i);
	printf("\n");

	return EXIT_SUCCESS;
}
