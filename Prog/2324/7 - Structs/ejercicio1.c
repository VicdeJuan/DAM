//Solución

#include <stdlib.h>
#include <stdio.h>

struct Estudiante { // Declara el tipo de dato
	char nombre[50];
	int edad;
	float promedio;
};

int main(){
	struct Estudiante persona;
	printf("Introduce el nombre del estudiante: ");
	scanf("%s",persona.nombre);
	printf("Introduce la edad del estudiante: ");
	scanf("%d",&persona.edad);
	printf("Introduce el promedio del estudiante: ");
	scanf("%f",&persona.promedio);
	
	printf("Los datos del estudiante son:\n\tNombre:%s\n\tEdad:%d\n\tPromedio:%f\n",persona.nombre, persona.edad,persona.promedio);
	return EXIT_SUCCESS;
}
