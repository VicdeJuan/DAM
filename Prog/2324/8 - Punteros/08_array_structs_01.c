#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/**
*
*	Autor: Víctor de Juan
*/

#define MAX_NOMBRE 20
#define MAX_BUFFER 1000

typedef struct{
	char nombre[MAX_NOMBRE];
	int edad;
	float nota;
} Estudiante;

 
void inicializar(Estudiante * destino,char * nombre, int edad, float nota){
}

int main() {
	Estudiante listado[20];
	int num,edad;
	char nombre[MAX_NOMBRE];
	float nota;
	char buffer[MAX_BUFFER];
	
	printf("¿Cuántos estudiantes desea inicializar? ");
	scanf("%d",&num);
	

    return 0;
}
