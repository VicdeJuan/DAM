#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>

void main(){
	pid_t pid, Hijo_pid;
	pid = fork();

	if (pid == -1) // Controlamos el posible error de fork
	{
		printf("No se ha podido crear el proceso hijo...");
		exit(-1);
	}
	if (pid == 0) // Nos encontramos en el proceso hijo
	{
		printf("Soy el proceso hijo\n\t Mi PID es %d, el PID de mi padre es: %d.\n",getpid(),getppid());
	}
	else // Nos encontramos en el proceso padre
	{
		printf("Soy el proceso padre: \n\tMi PID es %d, el PID de mi padre es: %d.\n\t.	He creado un proceso con PID: %d\n",getpid(),getppid(),pid);
		Hijo_pid = wait(NULL); //Espera la finalización del proceso hijo.
	}
	exit(0);
}

/**
 * Respuesta: coincide el PID del padre con el PPID del hijo porque PPID quiere decir "PID del Padre"
 */

