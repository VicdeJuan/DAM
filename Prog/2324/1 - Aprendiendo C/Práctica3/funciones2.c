#include <stdio.h>
#include <stdlib.h>

int incrementar(int num){
	int a = num;
	a++;
	return a;
}

int main(){
	int num;
	printf("Introduzca el valor que desea:");
	scanf("%d",&num);
	num = incrementar(num);
	// La siguiente línea da error porque a no existe.
	//printf("El valor incrementado es %d\n",a);
	printf("El valor incrementado es %d\n",num);

}
