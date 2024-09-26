#include <stdio.h>
#include <stdlib.h>

int main(){
	int a;

	printf("Introduce un número para saber si es el 2\n");
	scanf("%d",&a);
	
	if (a==2){
		printf("Sí\n");
	}else{
		printf("No\n");
	}

	/**
	 *  Esta estructura es idéntica a la anterior. ¿Para qué sirve la anterior? 
	 *  Para tareas más complejas*/

	a == 2 ? printf("Sí\n") : printf("No\n");

	return EXIT_SUCCESS;
}
