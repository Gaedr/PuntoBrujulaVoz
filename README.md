## Práctica 3
##ANDROID-GYMKANA
### Aplicación Punto BrujulaVoz
### Autores
* Samuel Peregrina Morillas
* Nieves Victoria Velásquez Díaz

### Duración de la práctica.
Desde 12-Ene-2016 hasta 15-Feb-2016

### Breve descripción de la práctica.
Para la realización de esta práctica, se programarán cinco aplicaciónes android diferentes de forma que, cada una hace uso de los distintos sensores que posee el dispositivo android.

### Descripción del problema.
Esta aplicación consiste en, mediante el uso del reconocedor de voz de Google, pasarle un punto cardinal y un margen de error y que la aplicación muestre una brújula con la que el usuario se orientará hasta que apunte a dicho punto cardinal.

###Clases
Las clases usadas durante la realización de esta práctica han sido:
* **CardinalPoint:** esta clase contiene un enum con los puntos cardinales y sus grados correspondientes. Tambien tiene los métodos necesarios para poder acceder a dichos datos.

* **CompassFragment:** en este fragment se ha implementado la funcionaidad de la brújula asi como la misma. También se trata con los datos recibidos por el acelerómetro y el magnetómetro dependiendo del movimiento del dispositivo.

* **VoiceRecognitionFragment:** en este fragment nos encargamos de lo relacionado con el reconociento del habla. Desde este fragment también nos encargamos de llamar al fragment anterior, es decir a la brújula.

* **MainActivity:** esta clase contiene los dos fragments anteriores y se encarga de inicializarlos para dar comienzo a la aplicación.

###Metodos
* Los métodos a destacar en la clase **CompassFragment** son:
	* **transformDegrees:** método que transforma las coordenadas dadas por los sensores a las coordenadas habituales usadas de 0 a 360.
	* **correctDirection:** método que comprueba si el grado actual de dirección en el que nos encontramos pertenece al que estamos buscando usando el margen de error dado
	* **onSensorChanged:** método que se lanzará cada vez que se detecte un cambio en los sensores y actualizará el angulo actual para poder compararlo
* Los métodos a destacar en la clase **VoiceRecognitionFratgment** son:
	* **listen:** este método es que lanzará el activity encargado de manejar el reconocedor de voz de *Google*
	* **showCompass:**  este método es sustituye la pantalla actual por la brújula
	* **onActivityResult:** método que captura los eventos de los activities lanzados por el *activityOnResult* y que se encargará en este caso de recibir los resultados del reconocedor de voz.

##Bibliografia
* [iconmonstr](http://iconmonstr.com/): librería de imagenes con licencia GNU
* [CompassApp](https://github.com/agamboadev/CompassApp/blob/master/CompassApp/src/com/example/compassapp/MainActivity.java): Ejemplo de brújula que utiliza varios sensores para la posición
* [Tutorial: Simple Reconocimiento de voz en Android (speech recognition)](http://www.tutorialeshtml5.com/2013/03/tutorial-simple-reconocimiento-de-voz.html)
