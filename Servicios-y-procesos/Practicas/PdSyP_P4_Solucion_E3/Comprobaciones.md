
# Comprobación

## Secuencial con saldos negativos
Para comprobar que está bien paralelizado, ejecutamos el secuencial permitiendo llegar a dinero negativo. De esta forma, aseguramos que se ejecuten todas las transacciones.

La salida obtenida es la siguiente:


Cliente Cliente6:
	- Federico
	- Num cuenta:6789012345
	- Saldo: 5910,52
Cliente Cliente5:
	- Eusebio
	- Num cuenta:5678901234
	- Saldo: 22821,47
Cliente Cliente4:
	- Dulcinea
	- Num cuenta:4567890123
	- Saldo: 3967,82
Cliente Cliente3:
	- Carla
	- Num cuenta:3456789012
	- Saldo: -15026,62
Cliente Cliente2:
	- Bisbal
	- Num cuenta:2345678901
	- Saldo: -10600,01
Cliente Cliente1:
	- Atanasio
	- Num cuenta:1234567890
	- Saldo: 6426,82
	

## Concurrente con saldos negativos
Al permitir saldos negativos, dado que son operaciones de aritmética, el orden de los sumandos no altera el producto. La salida esperada debería ser la misma.

Efectivamente, comprobamos que sale lo mismo.


Cliente Cliente6:
	- Federico
	- Num cuenta:6789012345
	- Saldo: 5910,52
Cliente Cliente5:
	- Eusebio
	- Num cuenta:5678901234
	- Saldo: 22821,47
Cliente Cliente4:
	- Dulcinea
	- Num cuenta:4567890123
	- Saldo: 3967,82
Cliente Cliente3:
	- Carla
	- Num cuenta:3456789012
	- Saldo: -15026,62
Cliente Cliente2:
	- Bisbal
	- Num cuenta:2345678901
	- Saldo: -10600,01
Cliente Cliente1:
	- Atanasio
	- Num cuenta:1234567890
	- Saldo: 6426,82


# Ejecuciones en paralelo sin saldos negativos

Para simplificar el problema, no se ha tenido en cuenta el orden en el que se hacen las transferencias. Esta simplificación convierte el problema resuelto en un problema que no es real ya que
en la realidad siempre hay una cronología de transferencias.

##La salida secuencial sin saldos negativos:

Cliente Cliente6:
	- Federico
	- Num cuenta:6789012345
	- Saldo: 1669,61
Cliente Cliente5:
	- Eusebio
	- Num cuenta:5678901234
	- Saldo: 3711,73
Cliente Cliente4:
	- Dulcinea
	- Num cuenta:4567890123
	- Saldo: 668,14
Cliente Cliente3:
	- Carla
	- Num cuenta:3456789012
	- Saldo: 806,21
Cliente Cliente2:
	- Bisbal
	- Num cuenta:2345678901
	- Saldo: 5984,59
Cliente Cliente1:
	- Atanasio
	- Num cuenta:1234567890
	- Saldo: 659,72

## Salidas concurrentes sin saldos negativos:
A continuación se muestran distintas salidas de la paralelización:

### Salida 1:

Cliente Cliente6:
	- Federico
	- Num cuenta:6789012345
	- Saldo: 1619,43
Cliente Cliente5:
	- Eusebio
	- Num cuenta:5678901234
	- Saldo: 5033,33
Cliente Cliente4:
	- Dulcinea
	- Num cuenta:4567890123
	- Saldo: 690,17
Cliente Cliente3:
	- Carla
	- Num cuenta:3456789012
	- Saldo: 765,07
Cliente Cliente2:
	- Bisbal
	- Num cuenta:2345678901
	- Saldo: 4812,18
Cliente Cliente1:
	- Atanasio
	- Num cuenta:1234567890
	- Saldo: 579,82

### Salida 2: 

Cliente Cliente6:
	- Federico
	- Num cuenta:6789012345
	- Saldo: 1616,06
Cliente Cliente5:
	- Eusebio
	- Num cuenta:5678901234
	- Saldo: 4628,64
Cliente Cliente4:
	- Dulcinea
	- Num cuenta:4567890123
	- Saldo: 722,33
Cliente Cliente3:
	- Carla
	- Num cuenta:3456789012
	- Saldo: 964,94
Cliente Cliente2:
	- Bisbal
	- Num cuenta:2345678901
	- Saldo: 5022,85
Cliente Cliente1:
	- Atanasio
	- Num cuenta:1234567890
	- Saldo: 545,18
	
### Salida 3: 

Cliente Cliente6:
	- Federico
	- Num cuenta:6789012345
	- Saldo: 1744,71
Cliente Cliente5:
	- Eusebio
	- Num cuenta:5678901234
	- Saldo: 4644,49
Cliente Cliente4:
	- Dulcinea
	- Num cuenta:4567890123
	- Saldo: 802,13
Cliente Cliente3:
	- Carla
	- Num cuenta:3456789012
	- Saldo: 929,47
Cliente Cliente2:
	- Bisbal
	- Num cuenta:2345678901
	- Saldo: 4714,13
Cliente Cliente1:
	- Atanasio
	- Num cuenta:1234567890
	- Saldo: 665,07
