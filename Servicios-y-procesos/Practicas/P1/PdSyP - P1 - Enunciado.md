
### Práctica 1 - Programación de procesos en C


## Ejercicio 1

Crea un programa llamado `ejemploFork.c` en el que crees un proceso con `fork()` y el padre imprima por pantalla "Hola, soy el proceso padre con PID:%d y PPID:%d.\nHe creado un proceso con PID %d ", mientras que el hijo imprima por pantalla "Hola, soy el proceso hijo con PID:%d y PPID: %d". 

¿Coincide algún PID o algún PPID? ¿Por qué?

_Recuerda utilizar el `wait` para no generar procesos zombies y controlar el posible error de `fork()`_

## Ejercicio 2

Crea un programa en el que se cree la siguiente jerarquía de procesos:

Un padre tiene 2 hijos, H1 y H2. H2, a su vez, tiene 2 hijos. Cada uno deberá terminar con un entero que indique su nivel en la jerarquía (El padre 0, los hijos del padre 1, los nietos 2)

Lo único que tiene que hacer cada proceso es imprimir por pantalla un mensaje con la siguiente información:
- PID del proceso
- PPID del proceso
- PID del proceso hijo que ha creado (si es que ha creado uno).
- Código de retorno de los procesos creados.


_Recuerda utilizar el `wait` para no generar procesos zombies y controlar el posible error de `fork()`_

## Ejercicio 3:

Encuentra el/los errores del siguiente código, explica porqué son errores y escribe el código correcto. El padre debería preguntar qué tal y el hijo contestar "bien".

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

    printf("Hola, ¿qué tal? Todo bien, gracias.\n");

    if (pid == 0) {
        printf("\tSoy el proceso hijo, mi PID es %d\n", getpid());
	printf("Estoy bien, gracias por preguntar");
    }

    printf("Soy el proceso padre, mi PID es %d\n", getpid());
    
    return 0;
}
```


