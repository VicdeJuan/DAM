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

 
char * toString(Estudiante estu){
}


void inicializar(Estudiante * destino,char * nombre, int edad, float nota){
	strcpy(destino->nombre,nombre);
	destino->edad = edad;
	destino->nota = nota;
}

int main() {
	Estudiante listado[20];
	int num,edad;
	char nombre[MAX_NOMBRE];
	float nota;
	char buffer[MAX_BUFFER];
	
	printf("¿Cuántos estudiantes desea inicializar? ");
	scanf("%d",&num);
	for (int i = 0; i<num; i++){
		printf("Inicializando estudiante %d:\n",i);
		printf("\tIntroduzca el nombre: ");
		scanf("%s",nombre);
		printf("\tIntroduzca la edad: ");
		scanf("%d",&edad);
		printf("\tIntroduzca la nota: ");
		scanf("%f",&nota);
		inicializar(&listado[i],nombre,edad,nota);
	}
	
	for (int i = 0; i<num; i++){
   		printf("%s\n\n",toString(listado[i])); 
	}
	

    return 0;
}
