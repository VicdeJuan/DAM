#include <stdio.h>

int main() {
    char str1[5] ="hola";
    
    printf("La palabra es: %s\n",str1);
    printf("Caracter a caracter: %c%c%c%c%c%c%c%c\n",str1[0],str1[1],str1[2],str1[3],str1[4],str1[5],str1[6],str1[7]);
    printf("Tamaño de str1: %ld\n\tModificando la cadena...\n",sizeof(str1));
    str1[0] = 'B';
    str1[5] = 'O';
    str1[6] = 'S';
    printf("Caracter a caracter: %c%c%c%c%c%c%c%c\n",str1[0],str1[1],str1[2],str1[3],str1[4],str1[5],str1[6],str1[7]);
    printf("Cadena entera: %s\n",str1);
    
    str1[0] = 'B';
    str1[4] = 'S';
    
    printf("Ahora con el puts: ");
    puts(str1);
    
    printf("Cadena entera: %s\n",str1);
    return 0;
}
