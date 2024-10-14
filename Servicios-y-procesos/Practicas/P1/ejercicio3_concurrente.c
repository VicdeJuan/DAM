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
	
	pid_t pid1,pid2;

	pid1=fork();

	 if (pid1 == -1)
        {                                                                                   
                printf("No se ha podido crear el proceso hijo...");                         
                exit(-1);                                                                   
        }                                                                                   
        if (pid1 == 0) // Nos encontramos en el primer subproceso. Vamos a calcular los números primos
	{	
		printf("Empezando a calcular suma impares...\n");	
		unsigned long long int sumaimpares = 0;
		for (int i=0;i<500000000;i++){
			if (isOdd(i)){
				sumaimpares += i;
			}
		}
		printf("La suma de losimpares es : %llu\n",sumaimpares);
		return 0;
        }
	
	pid2 = fork();
	if (pid2 == -1){
		printf("No se ha podido crear el segundo proceso\n");
		exit(-1);
	}

	if (pid2 == 0){
		// Segundo proceso hijo.
		printf("Creando proceso de primos\n");
		unsigned long long int sumaprimos = 0;
		for (int i = 0; i<100000;i++){
			if (checkPrime(i)){
				sumaprimos += i;
			}
		}
		printf("La suma de los primos es: %llu\n",sumaprimos);
		exit(0);
	}
	else // Nos encontramos en el proceso padre                                         
        {
		int status;
		pid_t proceso_terminado;
		for (int i=0;i<2;i++){
                proceso_terminado = wait(&status);
		if (proceso_terminado = pid1){
			printf("Terminó el de los impares\n");
                	if (WIFEXITED(status)) {
				printf("Proceso hijo terminó con estado %d.\n", WEXITSTATUS(status));
			} else {
				printf("El proceso hijo terminó de manera anormal.\n");                 
                	}
		} else if (proceso_terminado = pid2){
			printf("Terminó el de los primos\n");
                	if (WIFEXITED(status)) {
				printf("Proceso hijo terminó con estado %d.\n", WEXITSTATUS(status));
			} else {
				printf("El proceso hijo terminó de manera anormal.\n");                 
                	}
		}else{
			printf("No sé cuál ha terminado...\n");
		}
		}
                exit(0);                                                                    
        }       

}
