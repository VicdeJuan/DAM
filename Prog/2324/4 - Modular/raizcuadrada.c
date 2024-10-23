#include <stdio.h>
#include <stdlib.h>
#include <math.h>

// Esto es un comentario

/**
*	Este es un código de pruebas de programación.
*
*	Autor: Víctor de Juan
*/

int main() {
	double raiz;
	int valor;
	double radio,area;
	

	// PARTE DEL CÁLCULO DE UNA RAÍZ CUADRADA
	printf("Introduzca un número para calcular su raíz\n");
	scanf("%d",&valor);
	raiz = sqrt(valor);
	printf("La raíz cuadrada de %d es %lf\n",valor,raiz);

	
	// PARTE DEL CÁLCULO DEL ÁRE DEL CÍRCULO
	printf("Introduzca el radio del círculo para calcular su área\n");
	scanf("%lf",&radio);
	area = radio * radio * M_PI; //M_PI está definido en math.h. Es una constante con el valor de pi.
	printf("El área del círculo es %lf\n",area);


	return 0;
}

