#include <stdio.h>
#include <stdlib.h>
#include <math.h>

// Esto es un comentario

/**
*	Este es un código de pruebas de programación.
*
*	Autor: Víctor de Juan
*/

int calcularRaiz(){
	int valor; double raiz;
	printf("Introduzca un número para calcular su raíz\n");
	scanf("%d",&valor);
	raiz = sqrt(valor);
	printf("La raíz cuadrada de %d es %lf\n",valor,raiz);

	return 0; // Como todo ha ido bien, devuelvo un 0.	
}

void calcularArea(){
	double radio,area;
	printf("Introduzca el radio del círculo para calcular su área\n");
	scanf("%lf",&radio);
	area = radio * radio * M_PI; //M_PI está definido en math.h. Es una constante con el valor de pi.
	printf("El área del círculo es %lf\n",area);
}

int main() {
	calcularRaiz();
	calcularRaiz();
	calcularRaiz();

	calcularArea();
	calcularArea();
	calcularArea();

	return 0;
}

