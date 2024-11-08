#Ejercicio 1: Gestión de Cuentas Bancarias
Descripción:

Desarrolla un programa en C que gestione la información de clientes bancarios. El programa debe utilizar estructuras para representar los datos y permitir la manipulación de la información de los clientes.

##Requerimientos:

Definir las siguientes estructuras:

fecha: debe contener los campos dia, mes y anio, todos de tipo entero.
cuenta: debe contener los siguientes campos:
num_cuenta (entero): número de cuenta del cliente.
tipo_cuenta (carácter): tipo de cuenta ('A' para ahorros, 'C' para corriente).
nombre (cadena de caracteres de hasta 80 caracteres): nombre completo del cliente.
saldo (flotante): saldo actual de la cuenta.
ultimopago (estructura fecha): fecha del último pago o transacción.
Implementar las siguientes funcionalidades:

Declarar un array de estructuras cuenta para almacenar hasta 100 clientes.
Inicializar al menos tres clientes con datos de ejemplo.
Mostrar en pantalla la información de todos los clientes inicializados, incluyendo el número de cuenta, tipo de cuenta, nombre, saldo y fecha del último pago en formato legible (dd/mm/aaaa).
Ejemplo de salida esperada:

```
Cliente 1:
Número de Cuenta: 12345

Tipo de Cuenta: A
Nombre: María López
Saldo: $1500.50
Último Pago: 12/05/2022

Cliente 2:
Número de Cuenta: 67890
Tipo de Cuenta: C
Nombre: Juan Pérez
Saldo: $3000.75
Último Pago: 25/06/2022

...
```

#Ejercicio 2: Registro y Consulta de Estudiantes
Descripción:

Crea un programa en C que permita registrar información de estudiantes y consultar sus datos. Utiliza estructuras para organizar la información y facilitar su acceso.

##Requerimientos:

Definir las siguientes estructuras:

fecha: con los campos dia, mes y anio (enteros), representando la fecha de nacimiento.
estudiante: que incluya:
matricula (entero): número de matrícula del estudiante.
nombre (cadena de caracteres de hasta 80 caracteres): nombre completo del estudiante.
carrera (cadena de caracteres de hasta 50 caracteres): carrera que estudia.
promedio (flotante): promedio general.
fecha_nacimiento (estructura fecha): fecha de nacimiento del estudiante.
Implementar las siguientes funcionalidades:

Declarar un array de estructuras estudiante para almacenar hasta 50 estudiantes.
Inicializar al menos dos estudiantes con datos de ejemplo.
Permitir al usuario ingresar el número de matrícula y mostrar en pantalla la información completa del estudiante correspondiente.
Si la matrícula no corresponde a ningún estudiante registrado, mostrar un mensaje de error.
Ejemplo de interacción con el usuario:

```Ingrese el número de matrícula del estudiante: 2021001


Información del Estudiante:
Matrícula: 2021001
Nombre: Ana Gómez
Carrera: Ingeniería de Sistemas
Promedio: 8.75
Fecha de Nacimiento: 15/08/2000

Ingrese el número de matrícula del estudiante: 2021999
Estudiante no encontrado.
```
Estos ejercicios te ayudarán a practicar:

La definición y uso de estructuras anidadas en C.
La inicialización de estructuras y el manejo de arrays de estructuras.
El acceso y modificación de los campos de las estructuras.
La interacción básica con el usuario mediante la entrada y salida estándar.
Consejos:

Recuerda incluir las librerías necesarias, como <stdio.h> para las funciones de entrada y salida, y <string.h> si necesitas manipular cadenas de caracteres.
Asegúrate de utilizar correctamente strcpy para copiar cadenas en los campos de tipo cadena de las estructuras.
Presta atención al formato de salida para que la información sea clara y legible.
