#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/wait.h>

int main() {
	pid_t pid;
	// Crear un proceso hijo
	printf("Hola, ¿qué tal?\n"); // Lo ponemos antes del fork para obligar a que esta instrucción del padre se ejecute antes.
	pid = fork();
	if (pid == -1) {
		// Error al crear el proceso hijo
		perror("fork failed");
		exit(1);
	}
	if (pid == 0) {
		printf("Estoy bien, gracias por preguntar\n");
		exit(0); // Salida con código de OK.
	}
	wait(NULL); // El padre espera a que el hijo conteste.
	return 0;
}
