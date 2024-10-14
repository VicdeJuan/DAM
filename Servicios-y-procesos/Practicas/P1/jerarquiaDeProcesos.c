#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

void main(){

	pid_t pidh1,pidh2,pidn1,pidn2; // PIDs de los procesos que se van a crear.

	/** 
	 * Variables necesarias para esperar a los subprocesos
	 */
	int status;
	pid_t proceso_terminado;


	/**
	 * Comienza la lógica del programa
	 */

	pidh1 = fork(); 
	
	if (pidh1 == -1) {
		printf("Error creando un subproceso\n");
		exit(-1);
	}
	if (pidh1 == 0) // Proceso hijo 1
	{
		printf("\tSoy el hijo 1 con PID: %d y PPID: %d\n",getpid(),getppid());
		exit(1);
	}
	// Código que solo ejecuta el padre porque el hijo tiene un exit.
	pidh2 = fork();
	if (pidh2 == -1) {
		printf("Error creando un subproceso\n");
		exit(-1);
	}
	if (pidh2 == 0)
	{
		pidn1 = fork();
		if (pidn1 == -1) {
			printf("Error creando un subproceso\n");
			exit(-1);
		}
		if (pidn1 == 0){
			printf("\t\tSoy el nieto 1 con PID: %d y PPID: %d\n",getpid(),getppid());
			exit(2);
		}
		// Como el nieto tiene un exit, a partir de aquí solo lo ejecuta H2.
		// Creamos el otro nieto.
		pidn2 = fork();
		if (pidn2 == -1) {
			printf("Error creando un subproceso\n");
			exit(-1);
		}
		if (pidn2 == 0){
			printf("\t\tSoy el nieto 2 con PID: %d y PPID: %d\n",getpid(),getppid());
			exit(2);
		}
		printf("\tSoy el hijo 2 con PID: %d y PPID: %d\n",getpid(),getppid());
		printf("\tSoy el hijo 2 y he creado dos hijos con PID: %d y PID: %d\n",pidn1,pidn2);
		// Ya creados los 2 nietos, el hijo 2 puede terminar.
		// PERO antes de terminar tiene que esperar a sus hijos.
		for (int i = 0; i<2;i++){
			proceso_terminado = wait(&status);
			if(WIFEXITED(status)){
				printf("Proceso %d terminado con estado %d.\n",proceso_terminado,WEXITSTATUS(status));
			} else{
				printf("Proceso hijo terminó de manera anormal.\n");
			}
		}
		exit(1);
	}
	printf("Soy el padre con PID: %d y PPID: %d\n",getpid(),getppid());
	printf("\tSoy el padre y he creado dos hijos con PID: %d y PID: %d\n",pidh1,pidh2);
	// Ya creados los 2 hijos, el padre puede terminar.
	// PERO antes de terminar tiene que esperar a sus hijos.
	for (int i = 0; i<2;i++){
		proceso_terminado = wait(&status);
		if(WIFEXITED(status)){
			printf("Proceso %d terminado con estado %d.\n",proceso_terminado,WEXITSTATUS(status));
		} else{
			printf("Proceso hijo terminó de manera anormal.\n");
		}
	}
	exit(0);
}

