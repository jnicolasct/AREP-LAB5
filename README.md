# AREP-LAB5

## Insatalacion y compilacion

Para instalar el programa se debe clonar el proyecto con el comando git clone URL del proyecto o descargar el zip de este y extraer los archivos en su computador y finalamente abrirlo desde cualquier IDE

Para la compilacion, dentro del directorio del proyecto desde la cmd ejecutar el comando mvn clean install.

Debe ejecutar el comando "docker-compose up -d" con el fin de crearla tes imagenes en un contenedor de docker.

### Requisitos

  - JDK instalado
  - maven instalado
  - Docker Deskop
  
 
## Uso

Para hacer uso de la aplicacion se debe acceeder al siguiente link con puerto especifico

http://ec2-34-224-66-113.compute-1.amazonaws.com:42001

Al acceder a esta vera l siguiente pagina:

![princial](https://github.com/jnicolasct/AREP-LAB5/blob/master/resources/principal.PNG)

Al dar click en el boton submit, este agregara el texto a la base de datos, como se muestra a continucion:

![adicion](https://github.com/jnicolasct/AREP-LAB5/blob/master/resources/adicion.PNG)

Para rectificar que se agrego un dato a la base de datos alojada en aws, se verifica mediante un cliente en cual en este caso es studio 3t:

![AdicionDB](https://github.com/jnicolasct/AREP-LAB5/blob/master/resources/AdicionDB.PNG)

Para el balanceador de carga se reañizo un pequeño metodo que por turno le va asignando las request a diferentes puertos:

![roundrobin](https://github.com/jnicolasct/AREP-LAB5/blob/master/resources/roundrobin.PNG)

## Reporte

El reporte fue generado mediante latex, su nombre es Media y Desviacion estadar. Se encuentra en la raiz del proyecto

## Autor
  Johan Nicolas Cortes Torres