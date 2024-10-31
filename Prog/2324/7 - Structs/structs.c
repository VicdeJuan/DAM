#include <stdio.h>
#include <stdlib.h>

/**
*
*	Autor: Víctor de Juan
*/

struct fecha {
	int mes;
	int dia;
	int anio;
};

struct cuenta {
	int num_cuenta;
	char tipo_cuenta;
	char nombre[80];
	float saldo;
	struct fecha ultimopago;
};

int main(){
	
	struct cuenta cliente[100];

	/**Esta forma de inicializar solo es válida en la declaración:
	cliente[0] = {12345, 'R', "José",586.30,5,24,1990};*/

	cliente[0].num_cuenta = 12345;
	cliente[0].tipo_cuenta = 'R';
	strcpy(cliente[0].nombre, "José");  // Usar strcpy para copiar cadenas en C
	cliente[0].saldo = 586.30;
	cliente[0].ultimopago.mes = 5;
	cliente[0].ultimopago.dia = 24;
	cliente[0].ultimopago.anio = 1990;

	printf("%d",cliente[0].tipo_cuenta);

	return EXIT_SUCCESS;
}
