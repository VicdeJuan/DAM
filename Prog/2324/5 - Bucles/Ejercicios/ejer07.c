#include <stdio.h>
#include <stdlib.h>


int main(){
	int resultado = 0,num;
	printf("Introduce un número: ");
	scanf("%d",&num);
	for (int i = 1; i<=num; i++){
	   for (int j = 1; j<=num; j++){ //
		printf("*");
	   }
	   printf("\n");
	}
	printf("\n");
	return EXIT_SUCCESS;
}
