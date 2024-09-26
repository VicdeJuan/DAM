
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

### Parte 1 
Crea un programa que calcular la suma de los números primos entre 1 y 1000000 y, después, que calcule la suma de los números impares entre 1 y 5000000.

_Puedes utilizar `unsigned long long int` como tipo de dato_
### Parte 2
Crea un programa que utilice la programación concurrente de procesos para computar en paralelo dos cálculos complejos.

El primer subproceso debe calcular la suma de los números primos entre 1 y 1,000,000.
El segundo subproceso debe calcular el producto de los números impares entre 1 y 500,000 (por simplicidad, puedes limitar el resultado en caso de desbordamiento utilizando el tipo de dato unsigned long long int).

*Recuerda hacer `wait` tantas veces como forks hayas hecho*

Puedes ayudarte de las siguientes funciones:
```

/**
 * Function to compute if a number given is even (par) or odd (impar).
 * returns 0 if the number is even, 1 if the number is odd.
 */
int isOdd(int num){
	return num%2;
}

/** 
 * Function to compute if a number is prime or not.
 * returns 1 if the number is prime or 0 if it is not prime
 */
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

```

## Ejercicio 4:

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


