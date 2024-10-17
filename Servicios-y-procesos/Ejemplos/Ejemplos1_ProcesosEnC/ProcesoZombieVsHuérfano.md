#Proceso Zombie vs Huérfano

Los términos "proceso zombie" y "proceso huérfano" se refieren a diferentes estados de un proceso en un sistema operativo. A continuación, se explica cada uno de ellos y sus diferencias.

## Proceso Zombie

- **Definición**: Un proceso zombie es un proceso que ha terminado su ejecución pero todavía tiene una entrada en la tabla de procesos. Esto ocurre porque el proceso padre no ha llamado a `wait()` o `waitpid()` para recoger el estado de terminación del hijo.
  
- **Características**:
  - **Estado**: El proceso ha terminado (ha llamado a `exit()` o ha terminado por una señal), pero su información de estado aún está disponible para el proceso padre.
  - **Recursos**: Consume poca memoria, ya que ha terminado, pero ocupa una entrada en la tabla de procesos. Una cantidad desorbitada de procesos zombie podrían copar la tabla de procesos impiendo la creación de nuevos procesos.
  - **Causas**: La creación de procesos zombies ocurre cuando el padre no espera a que el hijo termine.

- **Ejemplo**: Si un proceso hijo se termina y el padre no lo espera, el hijo se convierte en un zombie. El sistema operativo mantiene su estado hasta que el padre lo recoge, evitando que se liberen completamente los recursos del hijo. 

## Proceso Huérfano

- **Definición**: Un proceso huérfano es un proceso que sigue en ejecución, pero cuyo proceso padre ha terminado antes que él. El sistema operativo asigna un nuevo padre al proceso huérfano, generalmente el proceso `init` (PID 1).

- **Características**:
  - **Estado**: El proceso aún está en ejecución y no ha terminado.
  - **Recursos**: Ocupa recursos del sistema como cualquier proceso en ejecución.
  - **Causas**: Sucede cuando el padre termina antes que el hijo, sin necesidad de que el hijo termine.

- **Ejemplo**: Si un proceso padre termina antes que su hijo, el hijo se convierte en huérfano. El sistema operativo lo reubica bajo el proceso `init`, que se encargará de limpiar su estado cuando termine.

## Resumen de diferencias

| Característica     | Proceso Zombie                  | Proceso Huérfano               |
|--------------------|---------------------------------|---------------------------------|
| **Estado**         | Terminado                       | En ejecución                   |
| **Información**    | Preservada (en tabla de procesos) | Reasignada a `init`           |
| **Recursos**       | Consume pocos recursos          | Consume recursos como cualquier proceso en ejecución |
| **Causa**          | Padre no llama a `wait()`      | Padre termina antes que el hijo |
| **Limpieza**       | Requiere que el padre recoja su estado | `init` se encarga de limpiar al hijo cuando termine |

## Conclusión

Ambos tipos de procesos son importantes para la gestión de procesos en sistemas operativos. Los zombies son un problema que puede resultar en fugas de recursos si no se manejan adecuadamente, mientras que los huérfanos son una parte normal del ciclo de vida de los procesos en un sistema operativo.

