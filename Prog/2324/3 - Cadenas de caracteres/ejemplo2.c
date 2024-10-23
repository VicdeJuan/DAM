#include <stdio.h>
#include <stdlib.h>

int main(){
   char str1[100];
   char str2[100];

   printf("Introduce un número:");
   scanf("%s",str1); // No hace falta & porque ...
   printf("Introduce un número:");
   scanf("%s",str2); // No hace falta & porque ...
   printf("%s+%s\n",str1,str2);

   return EXIT_SUCCESS;
}
