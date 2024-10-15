#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>


// Función para comprobar si él número dado como argumento es primo o no.
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

#define NOMBRE_ARCHIVO_PID "/tmp/examen_c_%d.tmp"

/**
 * 	Función para escribir en un archivo temporal cuyo nombre contiene el PID dado como argumento.
 * En el fichero solamente se escribirá un número entero. 
 **/
void escribirEnArchivo(pid_t pid, int valor_a_escribir){
	char fileName[256];
	snprintf(fileName,sizeof(fileName),NOMBRE_ARCHIVO_PID,pid); // Generamos la cadena del nombre del fichero
	FILE *archivo = fopen(fileName,"w");
	
	if (archivo == NULL){ // Comprobación habitual.
		printf("Error abriendo el archvio %s",fileName);
		exit(-1);
	}
	
	fprintf(archivo,"%d",valor_a_escribir);
	fclose(archivo);											// Cerramos el fichero. Importante.
}

/**
 * Función para leer de un archivo temporal cuyo nombre contiene el PID dado como argumento.
 * Devuelve el número entero leído del fichero.
 **/
int leerDeArchivo(pid_t pid){
	char fileName[256];
	int valor_leido = 0;
	snprintf(fileName,sizeof(fileName),NOMBRE_ARCHIVO_PID,pid); // Generamos la cadena del nombre del fichero
	FILE *archivo = fopen(fileName,"r"); 						// Abrimos el archivo en modo lectura.
	
	if (archivo == NULL){ // Comprobación habitual.
		printf("Error abriendo el archvio %s",fileName);
		exit(-1);
	}
	
	fscanf(archivo,"%d",&valor_leido);
	fclose(archivo);											// Cerramos el fichero. Importante.
	return valor_leido;
}


/**
 * Función core del programa. Busca los primos comprendidos en el intervalo [ini,fin) o [ini,fin] según si hay que inlcuir el último o no.
 * Esta separación de incluir el último o no es importante por si el intervalo acaba en un número primo. Si no se incluyera, en ningún 
 * en ningún momento se comprobaría ese caso.
 * 
 * Una vez contados cuántos primos hay, se escribe en un fichero
 */
int buscarPrimos(pid_t pid,int ini, int fin, int incluirUltimo){
	printf("\nProceso %d buscando primos entre %d y %d: ",pid,ini,fin);
	int contador = 0;
	if (incluirUltimo){
		for (int i = ini; i<=fin;i++){
			if (checkPrime(i))
				contador++;
			}	
	}else {
		for (int i = ini; i<fin;i++){
			if (checkPrime(i))
				contador++;
		}
	}
	
	printf("\t%d ha encontrado %d primos entre %d y %d\n",pid,contador,ini,fin);
	escribirEnArchivo(pid,contador);
	return 0;
}


int main(int argc, char* argv[]){

	// Comprobación del número de argumentos recibidos.
	if (argc != 3){
		printf("Uso: %s max numProc\n\t Donde max indica el límite de la búsqueda\n\tnumProc indica el número de procesos a crear",argv[0]);
		exit(1);
	}

	int max = atoi(argv[1]);
	int numProc = atoi(argv[2]);


	int inicio=1,fin=max; 		// Variables que iremos actualizando.
	int size = max / numProc; 	// Calculamos cuál es el tamaño del intervalo de búsqueda. Al realizar una divisón entre enteros, el resultados es un número entero.

	printf("Vamos a calcular entre %d y %d\n",inicio,size);

	for (int i = 0; i < max; i+=size){
		pid_t pid = fork();
		if (pid == -1){
			printf("Error: no se ha podido crear el proceso número %d",i%size);
			exit(-1);
		}
		if (pid == 0)
			// Llamamos a la función buscar primos con el intervalo [ini,fin), salvo que estemos en la última vuelta del bucle.
			// En ese caso, buscar primos será [ini,max], incluyendo el último valor. 
			// El hijo termina con el valor de retorno de la función "buscarPrimos".
			return (i+size>=max) ? buscarPrimos(getpid(),i,max,1) : buscarPrimos(getpid(),i,i+size,0); 
	}


	int contador_primos_total=0,status;
	pid_t pid_esperado;
	for (int i = 0; i < max; i+=size){
		pid_esperado = wait(&status); 	// Recogemos el PID del proceso esperado con el valor de retorno de la función wait.
										// Esto es fundamental para poder abrir el archivo que ha creado ese proceso.
		
		printf("Terminado proceso %d\n",pid_esperado);
		if (WIFEXITED(status)){ // Si el proceso ha terminado correctamente, sumamos el valor leído.
			contador_primos_total += leerDeArchivo(pid_esperado);
		} else{
			printf("Error inesperado con el proceso %d",pid_esperado);
			exit(-1);
		}
	}

	printf("En total se han encontrado %d primos\n",contador_primos_total);

	exit(0);

}
