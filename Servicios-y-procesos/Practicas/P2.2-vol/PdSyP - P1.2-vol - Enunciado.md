

# Práctica 1 - Programación de procesos en Java

__Esta práctica es como la práctica 1, pero con procesos en Java en lugar de en C__

## Ejercicio 0

Investiga, por tu cuenta, cómo crear en Java procesos (`ProcessBuilder`)

## Ejercicio 1

Crea un programa llamado `ejemploFork.c` en el que crees un proceso  y el padre imprima por pantalla "Hola, soy el proceso padre con PID:%d y PPID:%d.\nHe creado un proceso con PID %d ", mientras que el hijo imprima por pantalla "Hola, soy el proceso hijo con PID:%d y PPID: %d". 

¿Coincide algún PID o algún PPID? ¿Por qué?

_Recuerda utilizar el `wait` para no generar procesos zombies y controlar el posible error de `fork()`_

## Ejercicio 2

Dale las gracias a tu profesor por haber trabajado procesos en `C` en lugar de en `Java`

