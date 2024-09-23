#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main(){
	double radio,area;
	double resultado_raiz;
	int valor;

	printf("Introduzca un número para calcular su raíz\n");
	scanf("%d",&valor);
	resultado_raiz = sqrt(valor);
	printf("La raiz cuadrada de %d es %lf\n",valor,resultado_raiz);

	printf("Introduzca el radio del círculo para calcular el área\n");
	scanf("%lf",&radio);
	area = M_PI * radio * radio;
	printf("El área del círuclo es %lf\n",area);
	return 0;
}
