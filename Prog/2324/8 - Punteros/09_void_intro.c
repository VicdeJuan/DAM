#include <stdio.h>
#include <stdlib.h>


// *ptr será lo que hay imprimir
// tipo indica el tipo de variable
void printValor(void * ptr, char tipo){

	switch(tipo){
		case 'd':
			//printf("Double %lf\n", (double *)ptr); Esto no es correcto porque no estamos accediendo al contenido del puntero
			printf("Double %lf\n", *((double *)ptr));
			break;
		case 'i':
			printf("Entero: %d\n", *((int *)ptr ));
			break;
		defaut:
			printf("Error");
	}
}

int main(){
	double num = 2.1;
	void * dir = &num;
	printValor(dir,'d');	
	printValor(dir,'i');	
	return EXIT_SUCCESS;
}
