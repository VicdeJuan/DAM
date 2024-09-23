#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>
#include <sys/types.h>

void main(){
	pid_t pid, Hijo_pid;
	pid = fork();
	int status;

	if (pid == -1)
	{
		printf("No se ha podido crear el proceso hijo...");
		exit(-1);
	}
	if (pid == 0) // Nos encontramos en el proceso hijo
	{	
		printf("Soy el proceso hijo\n\t Mi PID es %d, el PID de mi padre es: %d.\n",getpid(),getppid());
		printf("Proceso hijo puesto a dormir\n");
		sleep(12);
		printf("Soy el proceso hijo\n\t Mi PID es %d, el PID de mi padre es: %d.\n",getpid(),getppid());
		exit(42);
	}
	else // Nos encontramos en el proceso padre
	{

		printf("Soy el proceso padre: \n\tMi PID es %d, el PID de mi padre es: %d",getpid(),getppid());
		sleep(3);
		printf("Espera un poco y verás aparecer al proceso hijo colgando de otro PID que no soy yo\n");
		exit(0);
	}
}
