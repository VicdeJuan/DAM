#include <stdio.h>
#include <stdlib.h> // Para malloc, free, etc.

int main() {

	// Declara un array de tamaño 3 utilizando memoria dinámica

	int * arr = (int *)malloc(3*sizeof(int));

	// SIEMPRE
	if (arr == NULL){
		printf("Error fatal, no hay memoria.\n");
		return EXIT_FAILURE;
	}

	// Inicializa los valores del array con los números 1,3 y 5.
	//Inicialización básica
	arr[0] = 1;
	arr[1] = 3;
	arr[2] = 5;

	// Inicialización pro. Elige tu favorita.
	for (int i = 0; i < 3; ++i){
		arr[i] = 2*i+1; 
	}

	/*for (int i = 0,j=1; i < 3; i++,j+=2){
		arr[i] = j; 
	}*/

	// VAMOS A AMPLIAR EL ARRAY A 5 ELEMENTOS
	int * arr_ampliado = (int *) realloc(arr,5*sizeof(int));
	
	if (arr_ampliado == NULL){
		free(arr); // Realloc no libera la memoria antigua.
		printf("Error: no se ha podido redimensionar\n");
		return EXIT_FAILURE;
	} 

	// Para evitar confusiones entre arr_ampliado y arr, es recomendable añadir:
	// arr = arr_ampliado

	// MALA PRÁCTICA!! arr se ha quedado "colgando"

	// Terminamos de inicializar
	arr_ampliado[3] = 7;
	arr_ampliado[4] = 9;

	for (int i = 0; i < 5; i++){
		printf("arr_ampliado[%d]: %d\n",i,arr_ampliado[i]);
	}

	// ¿Qué tengo que liberar?
	free(arr_ampliado);

	//free(arr); // ERROR: Double free detected.
    
    return EXIT_SUCCESS;
}
