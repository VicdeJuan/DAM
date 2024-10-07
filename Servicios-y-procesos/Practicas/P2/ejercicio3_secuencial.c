#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int checkPrime(int num)
{
	// 0, 1 and negative numbers are not prime
	if(num < 2){
		return 0;
	}
	else{   
		// no need to run loop till num-1 as for any number x the numbers in
		// the range(num/2 + 1, num) won't be divisible anyways. 
		// Example 36 wont be divisible by anything b/w 19-35
		int x = num/2;
		for(int i = 2; i <=x; i++)
		{
			if(num % i == 0)
			{
				return 0;
			}
		}
	}
	// the number would be prime if we reach here
	return 1;
}

int isOdd(int num){
	return num%2;
}

int main(){

	unsigned long long int sumaimpares = 0;
	unsigned long long int sumaprimos = 0;
	printf("Empezando a calcular suma impares...\n");	
	for (int i=0;i<500000000;i++){
		if (isOdd(i)){
			sumaimpares += i;
		}
	}
	printf("La suma de losimpares es : %llu\n",sumaimpares);

	printf("Creando proceso de primos\n");
	for (int i = 0; i<100000;i++){
		if (checkPrime(i)){
			sumaprimos += i;
		}
	}
	printf("La suma de los primos es: %llu\n",sumaprimos);
	exit(0);
}       


