#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* CÓMO FUNCIONAN LOS PUNTEROS CON LOS STRUCTS */

// Definimos las constantes
#define MAX_NOMBRE 20
#define MAX_ESTUDIANTES 20

typedef struct {
	char nombre[MAX_NOMBRE];
	int edad;
	float nota;
} Estudiante;

/* No es válida porque la variable creada.
   Estudiante inicializar(char * nombre, int edad, float nota){
// Creo una variable de tipo Estudiante
Estudiante estudiante_nuevo;

// Inicializo el estudiante. e.edad = edad; e....

return estudiante_nuevo;
}*/

/** Es válida, pero todavía no sabemos utilizarla.

  Estudiante * inicializar(char * nombre, int edad, float nota){
  Estudiante * estudiante_nuevo = malloc //Memoria dinámica

// Inicializo

return estudiante_nuevo;
}*/


void inicializar(Estudiante * estudiante_a_rellenar, char * nombre, int edad, float nota){

	//Esto no compila: estudiante_a_rellenar.edad = edad; porque estudiante_a_rellenar
	// es un puntero, no un estudiante. Habría que hacer (*estudiante_a_rellenar).edad, es decir,
	// acceder al contenido del puntero para poder así acceder al campo "edad" del Estudiante.
	// En lugar de hacer eso, lo que C permite es utilizar el operador "->".
	estudiante_a_rellenar->edad = edad;

	estudiante_a_rellenar->nota = nota;
	//estudiante_a_rellenar.nombre = nombre; es incorrecto por una movida muy chunga que no entiendo
	strcpy(estudiante_a_rellenar->nombre,nombre);
	

}

// No es necesario un valor de retorno. 
// Cumpleanero es un puntero porque va a ser modificado en la función
void cumpleanios(Estudiante * cumpleanero) {
	cumpleanero->edad++;

}


// Crea una función que cree una cadena de texto con la información del estudiante.
// (Puedes empezar creando una función que imprima por pantalla la info de un estudiante).

int main(){
	// Aquí se reserva la memoria para los estudiantes. Los 560 bytes se reservan aquí
	Estudiante listado[MAX_ESTUDIANTES];

	int num_estudiantes;

	int edad;
	float nota;
	char nombre[MAX_NOMBRE];

	printf("%p\n",listado); // 
	printf("Sizeof listado: %lu\n",sizeof(listado)); // 560 poorque...
	printf("Sizeof estudiante: %lu\n",sizeof(Estudiante)); // porque...

	printf("¿Cuántos estudiantes desea inicializar? ");
	scanf("%d",&num_estudiantes);

	for (int i = 0; i < num_estudiantes; i++){
		printf("Introduce la edad: ");
		scanf("%d",&edad);
		printf("Introduce la nota: ");
		scanf("%f",&nota);
		printf("Introduce el nombre: ");
		scanf("%s",nombre);

		inicializar(listado + i /*dirección memoria estudiante*/ ,nombre,edad,nota);
		//inicializar( &listado[i] /*dirección memoria estudiante*/ ,nombre,edad,nota);
	}
	
	// El estudiante de la primera posición ha cumplido años
	//printf("Edad antigua de %s: %d\n",listado[0].nombre,listado->edad);
	printf("Edad antigua de %s: %d\n",listado[0].nombre,listado[0].edad);
	cumpleanios(&listado[0]/*DIR DE MEMORIA*/ );
	//cumpleanios(listado);
	printf("Edad nueva: %d\n",listado[0].edad );
	// También es válido
	printf("Edad nueva: %d\n",listado->edad );
	printf("Edad nueva: %d\n",(*listado).edad );
	

	//printf("%d",(listado+6)->edad);

	return EXIT_SUCCESS;
}
