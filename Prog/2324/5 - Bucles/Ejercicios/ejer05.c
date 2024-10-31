#include <stdio.h>
#include <stdlib.h>


int main(){
	
	int tabladel = 5;	
	int resultado = 1;	
	int num;

	printf("Introduce un número: ");
	scanf("%d",&num);
	/**for (int i = 1; i<=num; i++){
		resultado = resultado * i;
	}*/

	for (int i = num; i>1; i--){
		resultado = resultado * i;
	}
	printf("%d! = %d\n",num,resultado);	

	return EXIT_SUCCESS;
}
