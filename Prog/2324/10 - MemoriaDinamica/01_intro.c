#include <stdio.h>
#include <stdlib.h>

/**
*
*	Autor: Víctor de Juan
*/

int main(){
 	int * p_error = malloc(sizeof(int)); // Reservar espacio para un int
    // Aquí hay un casting implícito. 
    // malloc devuelve un void*. Hay que 
    // convertirlo para poder utilizarlo.



    int *p = (int *) malloc(sizeof(int));
    // Esta es la buena práctica.

    if (p == NULL){ // Malloc ha fallado
    	// porque no hay memoria disponible.
    	printf("ERROR FATAL\n");
    	return 1; // El programa ha fallado.
    }


    *p = 42; // Usamos la memoria
    printf("El valor es: %d\n", *p);
    free(p); // Liberamos la memoria reservada
    free(p_error); // Liberamos

	return EXIT_SUCCESS;
}
