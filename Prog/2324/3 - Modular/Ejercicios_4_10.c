#include <stdio.h>
#include <stdlib.h>
#include <math.h>

/**
*
*	Autor: Víctor de Juan
*/


// Imprime por pantalla si el caracter recibido como argumento es una vocal o no.
void es_vocal(char letra){
	
	if (letra == 'a' || letra == 'e' ||	letra == 'i' ||	letra == 'o' ||	letra == 'u' ||	letra == 'A' ||	letra == 'E' ||	letra == 'I' ||	letra == 'O' || letra == 'U'){
		printf("La letra %c es una vocal.\n",letra);
	}else{
		printf("La letra %c no es una vocal.\n",letra);
	}
	// Como la función es void, no hay return.
}

void _calcular_area_circulo(){
	double radio,area;
	printf("Introduce el radio del círculo: ");
	scanf("%lf",&radio);
	// Calculamos el área
	area = M_PI * pow(radio,2);
	printf("El área del círculo es %lf\n",area);
}

void _calcular_area_cuadrado(){
	double lado,area;
	printf("Introduce el lado del cuadrado: ");
	scanf("%lf",&lado);
	area = pow(lado,2); // Calculamos el área
	printf("El área del cuadrado es %lf\n",area);
}

/**
 * Esta función debe permitir al usuario elegir entre dos figuras: círculo o cuadrado, y luego calcular el área correspondiente:

- Si se elige círculo, debes pedir el **radio** y calcular el área usando la fórmula: `A = π * radio^2` 
- Si se elige cuadrado, debes pedir la **longitud del lado** y calcular el área con la fórmula: `A = lado^2`.
- Formato de salida:
    - Para el círculo: `"El área del círculo con radio [radio] es [área]."`
    - Para el cuadrado: `"El área del cuadrado con lado [lado] es [área]."`
 */

void calcular_area_figura(){
	int opcion;
	printf("¿Qué figura deseas calcular (círculo=1, cuadrado=2)? ");
	scanf("%d",&opcion);
	if (opcion == 1){
		_calcular_area_circulo();
	} else if (opcion == 2) {
		_calcular_area_cuadrado();
	} else{
		printf("Error en la función calcular_area_figura\n");
	}
}

int main(){
	// Declaración de variables.	
	int opcion;
	char letra;

	// LÓGICA DEL PROGRAMA
	printf("Selecciona una opción:\n\
			1. Verificar si una letra es una vocal.\n\
			2. Calcular el área de una figura (círculo o cuadrado).\n\
			3. Calcular el promedio de 3 o 4 números.\n\
			4. Convertir una hora en formato de 24 horas a 12 horas.\n\
			5. Verificar si un número menor de 10 es primo.\n\
			6. Comparar tres números.\n\
			7. Salir.\n");
	printf("Selecciona una opción: ");
	scanf("%d", &opcion);

	switch(opcion){
		case 1:
			printf("Introduce una letra: ");
			scanf(" %c",&letra); // Pongo un espacio antes del %c porque el último scanf habrá dejado en el buffer de entrada un caracter \n que, si no pongo el espacio, se guardaría en la variable letra.
			es_vocal(letra);
			break;
		case 2:
			calcular_area_figura();
			break;
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		default:
	}




	return EXIT_SUCCESS;
}
