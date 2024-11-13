#include <stdio.h>
int main()
{
  int arr[5] = {10, 20, 30, 40, 50};
  // La variable p tiene que apuntar "al final"
  int *p;
  //p = arr +4; // Arnau al principio.
  p = arr+5; // Arnau probando
//  p = &arr[5]; // Pablo. Dirección del sexto elemento.
  p = (&arr[4])+1

  for (int i = 5; i >0; i--) {
    printf("Elemento %d: %d\n", i, *(p - i));
  }
}
