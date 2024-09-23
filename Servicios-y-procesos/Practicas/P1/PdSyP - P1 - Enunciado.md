
### Práctica 1 - Programación de procesos en C


## Ejercicio 1

Crea un programa llamado `ejemploFork.c` en el que crees un proceso con `fork()` y el padre imprima por pantalla "Hola, soy el proceso padre con PID:%d y PPID:%d ", mientras que el hijo imprima por pantalla "Hola, soy el proceso hijo con PID:%d y PPID: %d". 

¿Coincide algún PID o algún PPID? ¿Por qué?

## Ejercicio 2

Crea un programa en el que se cree la siguiente jerarquía de procesos:

Un padre tiene 2 hijos, H1 y H2. H2, a su vez, tiene 2 hijos. Lo único que tiene que hacer cada proceso es imprimri por pantalla el mismo mensaje que en el ejercicio 1.

## Ejercicio 3:

Encuentra el/los errores del siguiente código, explica porqué son errores y escribe el código correcto.

```
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int main() {
    int pid;

    // Crear un proceso hijo
    pid = fork();

    if (pid == -1) {
        // Error al crear el proceso hijo
        perror("fork failed");
        exit(1);
    }

    // Error común: No separar correctamente el código del hijo y del padre
    printf("Este mensaje lo imprimirán tanto el padre como el hijo.\n");

    // El proceso hijo debería terminar aquí, pero sigue ejecutando el código del padre
    if (pid == 0) {
        printf("Soy el proceso hijo, mi PID es %d\n", getpid());
        // El hijo no debería seguir ejecutando el código del padre
    }

    // El proceso padre no espera al hijo, lo que puede dejar al hijo en estado zombie
    printf("Soy el proceso padre, mi PID es %d\n", getpid());
    
    return 0;
}
```


