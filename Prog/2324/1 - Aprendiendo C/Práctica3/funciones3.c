#include <stdio.h>
#include <stdlib.h>

void incrementar(int a){
	a++;
}

int main(){
	int num;
	printf("Introduzca el valor que desea:");
	scanf("%d",&num);
	incrementar(num);
	printf("El valor incrementado es %d\n",num);
	// ¿Se ha incrementado? Paso por valor...

}
