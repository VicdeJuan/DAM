#include <stdio.h>
#include <stdlib.h>

/**
*
*	Autor: Víctor de Juan
*/

int main(){
	char str1[8] = "iguales";
	char str2[8] = "iguales";
	
	char str3[8];

	if (str1 == str2){
		printf("Son iguales\n");
	} else{
		printf("Son distintas\n");
	}

	if (str1 < str2){
		printf("str1 es menor\n");
	} else{
		printf("str2 es menor\n");
	}

	// Comparar caracter a caracter
	for (int i=0; i<8; i++){
		if (str1[i] == str2[i]){
			printf("%c",str1[i]);
		}
	}
	printf("\n");
	
	/**
	str3 = str1; Esta asignación no está permitida.
	str1[0] = 'I';
	printf("%c",str3[0]);	*/

	return EXIT_SUCCESS;
}
