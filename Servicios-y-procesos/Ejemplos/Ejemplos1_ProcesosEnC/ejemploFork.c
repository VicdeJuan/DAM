#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

void main(){
	pid_t pid, Hijo_pid;
	pid = fork();

	if (pid == -1)
	{
		printf("No se ha podido crear el proceso hijo...");
		exit(-1);
	}
	if (pid == 0) // Nos encontramos en el proceso hijo
	{
		// 2º paso: sleep(20);
		printf("Soy el proceso hijo\n\t Mi PID es %d, el PID de mi padre es: %d.\n",getpid(),getppid());
	}
	else // Nos encontramos en el proceso padre
	{
		//3er paso
		//Hijo_pid = wait(NULL); //Espera la finalización del proceso hijo.
		printf("Soy el proceso padre: \n\tMi PID es %d, el PID de mi padre es: %d.\n\t.	Mi hijo: %d terminó.\n",getpid(),getppid(),pid);
	}
	exit(0);
}
