## Ejercicio 0: "Carrera de coches con hilos"

En este ejercicio, deberás simular una carrera de coches utilizando hilos. Cada coche representará un participante en la carrera, y el tiempo que tardan en completar la carrera dependerá de su velocidad.

### Requisitos del ejercicio:

1. Crea una clase `CocheRunnable` que implemente la interfaz `Runnable`.
2. La clase debe tener los siguientes atributos:
    - `nombre`: el nombre del coche.
    - `velocidad`: tiempo en milisegundos que tarda el coche en avanzar un tramo.
    - `distanciaTotal`: la distancia total que tiene que recorrer el coche.
3. El método `run()` debe simular el avance del coche tramo a tramo, imprimiendo un mensaje cuando el coche avanza y cuando ha terminado la carrera. Usa `Thread.sleep(velocidad)` para simular el tiempo entre tramos.
4. En la clase principal, crea al menos 4 coches con diferentes velocidades y distancias, y haz que compitan entre sí en hilos paralelos.

### Pistas:

- Cada coche debe ir avanzando tramo a tramo, y cuando haya completado su distancia total, debe imprimir que ha terminado su recorrido.
- Usa `Thread.sleep(velocidad)` dentro de un bucle para simular el avance de los coches.
- Ojo, como cada coche puede ejecutarse en un hilo diferente y no comparten recursos, no son necesarios los mecanismos de sincronización.

---

# Ejercicio 1

**Título:** Robots Exploradores en una Misión Espacial (Concurrente)

**Descripción:**

En este ejercicio, vas a simular una misión espacial en la que varios robots exploradores están encargados de investigar diferentes áreas de un planeta. Cada robot tiene un nombre y un tiempo específico que necesita para explorar su área asignada. La tarea consiste en crear una clase que simule su proceso de exploración en paralelo a los demás robots.

**Requisitos:**

1. **Crear la clase `RobotExplorador`**:
    - La clase debe extender `Thread`.
    - Debe tener dos atributos:
        - `nombre`: El nombre del robot (de tipo `String`).
        - `tiempoExploracion`: El tiempo que el robot tarda en explorar su área asignada (de tipo `int`), en segundos.
    - Debe tener un constructor que reciba el nombre del robot y el tiempo de exploración.
2. **Método `run()`**:
    - Sobrescribe el método `run()` para que llame a un método `explorar()`.
    - El método `explorar()` debe simular el proceso de exploración del robot:
        - Imprimir un mensaje indicando que el robot ha comenzado a explorar su área asignada.
        - Hacer que el hilo "duerma" (es decir, que espere) por el tiempo especificado en segundos (`Thread.sleep(tiempoExploracion * 1000)`).
        - Imprimir un mensaje indicando que el robot ha terminado de explorar su área.
3. **Simulación en el método `main`**:
    - Crea una clase que contenga el método `main`.
    - Dentro del `main`, crea 4 instancias de la clase `RobotExplorador` con diferentes nombres y tiempos de exploración.
    - Lanza cada robot (hilo) usando el método `start()` para que exploren en paralelo.

# Ejercicio 2

## Ejercicio 2.1

**Título:** Misión Espacial con Robots Exploradores y Constructores **SECUENCIAL**

**Descripción:**

En este ejercicio, vas a simular una misión espacial más compleja en la que participan diferentes tipos de robots. Además de los `RobotExplorador` encargados de investigar el terreno, también hay `RobotConstructor`, cuyo trabajo es construir y destruir estructuras en el planeta. Los robots constructores compartirán un contador común que mantendrá el número de estructuras construidas.

**Requisitos:**

1. **Crear la clase/interface `Robot`**:
    - La clase `Robot` debe no admitir instancias directas. Es decir, no pueden existir objetos que sean únicamente de tipo “Robot”, sino que tendrán que ser de tipo “alguna subclase de Robot”.
    - Debe tener dos atributos protegidos:
        - `nombre`: El nombre del robot (de tipo `String`).
        - `tiempoOperacion`: El tiempo que el robot tarda en realizar su tarea (de tipo `int`), en segundos.
    - Debe tener un constructor que reciba el nombre del robot y el tiempo de operación.
    - Debe contener un método abstracto `void operar()`, que será implementado por las clases que la hereden/implementen.
    - Es tu decisión cómo implementar las restricciones que se imponen para esta clase
2. **Crear la clase `RobotExplorador`**:
    - Esta clase debe heredar de`Robot`.
    - Debe sobrescribir el método `operar()` para implementar la lógica de exploración:
        - Imprimir un mensaje indicando que el robot ha comenzado a explorar su área asignada.
        - Hacer que el hilo "duerma" (es decir, que espere) por el tiempo especificado en segundos.
        - Imprimir un mensaje indicando que el robot ha terminado de explorar su área.
3. **Crear la clase `RobotConstructor`**:
    - Esta clase también debe heredar de`Robot`.
    - Debe tener un contador compartido entre todos los robots constructores que mantenga el número de estructuras construidas. (Esto puedes implementarlo con `static`)
    - Deberá implementar un método `construir()` y otro `destruir()`que incrementen o decrementan la cantidad de estructuras construidas.
    - Debe sobrescribir el método `operar()` para implementar la lógica de construcción y destrucción:
        - Si hay un número par de estructuras, deberá construir 3. Si hay un número impar, deberá destruir 1. (El 0 es par)
        - Simular una pausa durante la operación usando `Thread.sleep()`.
4. **Simulación en el método `main`**:
    - Crea una clase que contenga el método `main`.
    - Dentro del `main`, crea 4 instancias de `RobotExplorador` que exploran en paralelo y 3 `RobotConstructor` con diferentes nombres y tiempos de operación.
    - Lanza a explorar los 4 robots en paralelo mientras cada robot constructor construye 10  estructuras secuencialmente, es decir, sin paralelismo.
5. **Contesta las siguientes preguntas:**
    1. ¿Dónde has situado el main? Justifica tu respuesta.
    2. ¿Cuántas estructuras deberían existir creadas? Comprueba, añadiendo un `printf` que realmente el número de estructuras es el que tiene que ser. (Muestra el valor de “estructurasConstruidas” de cada instancia de la clase “RobotConstructor”)
    3. ¿Cómo has decidido implementar el paralelismo del RobotExplorador? Runnable, Thread, etc. Justifica tu respuesta.

## Ejercicio 2.2

Versiona el código anterior para que se ejecuten paralelamente las construcciones, manteniendo el mismo main y contesta las siguientes preguntas:

- ¿Cuántas estructuras deberían existir creadas? Comprueba, añadiendo un `println` que realmente el número de estructuras es el que tiene que ser. (Muestra el valor de “estructurasConstruidas” de cada instancia de la clase “RobotConstructor”)
- ¿Qué mecanismo de sincronización has utilizado?


