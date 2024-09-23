

# Práctica 1 - Programación de procesos en Java

__Esta práctica es como la práctica 1, pero con procesos en Java en lugar de en C__

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

