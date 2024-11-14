#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/**
*
*	Autor: Víctor de Juan
*/

typedef struct{
	char nombre[100];
	int edad;
	float nota;
} Estudiante;

void renombrar(Estudiante * estudiante, char * nuevo_nombre){
	strcpy(estudiante->nombre,nuevo_nombre);
}

int main() {
    Estudiante est1 = {"Dani",20,8.5};

    printf("Nombre: %s\n",est1.nombre);
    renombrar(&est1,"Óscar");
    printf("Nombre: %s\n",est1.nombre);

    

    return 0;
}
