## Pactica 3.
##ANDROID-GYMKANA
### Aplicacion Punto NFC.
### Autores
* Samuel Peregrina Morillas
* Nieves Victoria Velásquez Díaz

### Duración de la práctica.
Desde 12-Ene-2016 hasta 15-Feb-2016

### Breve descripción de la práctica.
Para la realizacion de esta práctica, se programarán cinco aplicaciones android diferentes de forma que, cada una hace uso de los distintos sensores que posee el dispositivo android.

### Descripción del problema.
Esta aplicación consiste en, mediante el uso del reconocedor de voz de Google, pasarle un punto cardinal y un margen de error y que la aplicacion mueste una brujula con la que el usuario se orientara hasta que apunte a dicho punto cardinal.

###Clases
Las clases usadas durante la realización de esta práctica han sido:
* **CardinalPoint:** esta clase contiene un enum con los puntos cardinales y sus grados correspondientes. Tambien tiene los métodos necesarios para poder acceder a dichos datos.

* **CompassFragment:** en este fragmet se ha implementado la funcionaidad de la brújula asi como la misma. También se trata con los datos recibidos por el acelerómetro y el magnetómetro dependiendo del movimiento del dispositivo.

* **VoiceRecognitionFragment:** en este fragment nos encargamos de lo relacionado con el reconociento del habla. Desde este fragment también nos encargamos de llamar al fragment anterior, es decir a la brújula.

* **MainActivity:** esta clase contiene los dos fragments anteriores y se encarga de inicializarlos para dar comienzo a la aplicación.

###Metodos.
