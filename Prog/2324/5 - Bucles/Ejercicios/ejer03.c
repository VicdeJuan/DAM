#include <stdio.h>
#include <stdlib.h>


int main(){
	
	int suma = 0;
	for (int i = 1; i<=10; i++){
		suma += i;
	}
	
	//for (int i = 0; i<=10; suma += i++ );


	printf("La suma de los 10 primeros números es: %d\n",suma);

	return EXIT_SUCCESS;
}
