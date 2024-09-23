#include <stdio.h>
#include <stdlib.h>
#include <math.h>

double areaCirculo(){
	double retval;
	double radio;
	printf("Introduzca el radio del círculo para calcular el área\n");
	scanf("%lf",&radio);
	retval = M_PI * radio * radio;

	return retval;
}

double raiz(){
	double resultado_raiz;
	int valor;

	printf("Introduzca un número para calcular su raíz\n");
	scanf("%d",&valor);
	resultado_raiz = sqrt(valor);
	
	return resultado_raiz;
}

int main(){
	double area;
	double resultado_raiz;

	resultado_raiz = raiz();
	// Esta línea da error porque valor ya no está.
	// printf("La raiz cuadrada de %d es %lf\n",valor,resultado_raiz);
	printf("La raiz cuadrada es %lf\n",resultado_raiz);

	area = areaCirculo();
	printf("El área del círuclo es %lf\n",area);
	return 0;
}
