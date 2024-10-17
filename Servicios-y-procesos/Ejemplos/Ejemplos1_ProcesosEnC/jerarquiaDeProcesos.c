#include <stdio.h>
#include <stdlib.h>

void main(){
	printf("\nEjemplo de uso de system():");

	printf("%d",system("ls > fichsalida"));
	printf("\nAbrimos el fichero...");

	printf("%d", system("vim fichsalida"));
	printf("\nFin del programa...\n");
}
