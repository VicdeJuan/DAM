#include <stdio.h>
#include <stdlib.h>

/**
*
*	Autor: Víctor de Juan
*/

#define MAX_ITEMS 100

typedef struct{
	char nombre[100];
	char direccion[100];
	char telefono[13]; // +34611222333\0
} Proveedor;

typedef struct{
	char nombre[100];
	float precio;
	Proveedor proveedor;
} Articulo;

typedef struct{
	Articulo articulo;
	int cantidad;
} ArticuloDelInventario;

typedef struct{	
	ArticuloDelInventario articulos[MAX_ITEMS];
	int totalArticulos;
} Inventario;

int main(){
	Inventario miInventario; // DECLARACIÓN

	// Esta forma de inicializar un struct solo es 
	// 	válida en la declaración.
	Articulo articulo = {"Nombre",10,{"NomPro","DirPro","telPro"}}; //DECLARACIÓN E INICIALIZACIÓN

	// Aquí, que miInventario ya está declarada,
	// 	no puedo inicializarla así: miInventario = {}

	miInventario.articulos[0].articulo  = articulo; // Asigno como primer artículo del array, el artículo creado a mano.
	miInventario.articulos[0].cantidad = 10; // INICIALIZACIÓN

	printf("Nombre articulo: %s\n",
		miInventario.articulos[0].articulo.nombre
	);

	printf("Cantidad: %d\n",
		miInventario.articulos[0].cantidad
	);

	printf("Nombre del proveedor: %s\n",
		miInventario.articulos[0].articulo.proveedor.nombre
	);

		
	return EXIT_SUCCESS;
}
