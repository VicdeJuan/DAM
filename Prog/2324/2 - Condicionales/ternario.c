#include <stdio.h>
#include <stdlib.h>

/**
 *
 *	Autor: Víctor de Juan
 */



int main(){
	int condicion; 
	char c;
	scanf("%d",&condicion);
	c =  ! (condicion % 2) ? 'P' : 'I';
	printf("%c\n",c);
	return EXIT_SUCCESS;
}
