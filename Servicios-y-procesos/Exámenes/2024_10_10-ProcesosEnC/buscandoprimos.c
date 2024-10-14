#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

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

#define prefix_file_name "/tmp/examen_c_"

void escribirEnArchivo(pid_t pid, int valor_a_escribir){
	char fileName[256];
	snprintf(fileName,sizeof(fileName),"/tmp/examen_c_%d.tmp",pid);
	FILE *archivo = fopen(fileName,"w");
	fprintf(archivo,"%d",valor_a_escribir);
	fclose(archivo);
}

int leerDeArchivo(pid_t pid){
	char fileName[256];
	int valor_leido = 0;
	snprintf(fileName,sizeof(fileName),"/tmp/examen_c_%d.tmp",pid);
	FILE *archivo = fopen(fileName,"r");
	fscanf(archivo,"%d",&valor_leido);
	fclose(archivo);
	return valor_leido;
}

int buscarPrimos(pid_t pid,int ini, int fin){
	printf("\nProceso %d buscando primos entre %d y %d: ",pid,ini,fin);
	int contador = 0;
	for (int i = ini; i<fin;i++){
		if (checkPrime(i))
			contador++;
	}
	printf("\t%d ha encontrado %d primos entre %d y %d\n",pid,contador,ini,fin);
	escribirEnArchivo(pid,contador);
	return 0;
}

int main(int argc, char* argv[]){

	if (argc != 3){
		printf("Uso: %s max numProc\n\t Donde max indica el límite de la búsqueda\n\tnumProc indica el número de procesos a crear",argv[0]);
		exit(1);
	}

	int max = atoi(argv[1]);
	int numProc = atoi(argv[2]);


	int inicio=1,fin=max; // Variables que iremos actualizando.
	int size = max / numProc; // Calculamos cuál es el tamaño del intervalo de búsqueda.

	printf("Vamos a calcular en los siguientes intervalos: [%d,%d)\n",inicio,size);

	for (int i = 0; i < max; i+=size){
		pid_t pid = fork();
		if (pid == 0)
			return (i+size>max) ? buscarPrimos(getpid(),i,max) : buscarPrimos(getpid(),i,i+size);
	}

	int contador_primos_total=0,status;
	pid_t pid_esperado;
	for (int i = 0; i < max; i+=size){
		pid_esperado = wait(&status);
		printf("Terminado proceso %d\n",pid_esperado);
		if (WIFEXITED(status)){
			contador_primos_total += leerDeArchivo(pid_esperado);
		}
	}

	printf("En total se han encontrado %d primos\n",contador_primos_total);

	exit(0);

}
