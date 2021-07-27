# jInventory

Aplicacion de manejo de inventario y ventas construida en java con JFrame. Creada como proyecto semestral para el ramo ICC264-1 de la Universidad de La Frontera

## Stack
- Java
- JFrame

## Setup Local 
Suponiendo que inicialmente se posee instalado y configurado el ambiente para ejecutar aplicaciones con Java, los pasos para poder configurar la aplicacion a nivel local son los siguientes: 

Para poder ejecutar esta aplicacion es necesario tener instalado el servidor MySQL e importar previamente la base de datos, la cual se encuentra adjunta en el directorio database como jinventory.sql. Para esto poseemos recomendaciones de instalacion tanto para Windows como Linux. 

### Linux

Primero debemos asegurarnos de tener nuestro packet manager actualizado utilizando
```sh
sudo apt-get update
```
Luego, podremos descargar el servidor MySQL con el siguiente comando:
```sh
sudo apt-get install mysql-server
```

Luego ejecutamos la configuracion de MySQL
```sh
sudo mysql_secure_installation
```
Luego de finalizar la configuracion mediante el menu, requeriremos configurar nuestros privilegios de usuario e importar nuestra base de datos. Para esto inicializaremos mysql utilizando: 
```sh
sudo mysql
```
Luego configuraremos nuestro usuario root mediante:
```sh
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';
```
Donde password es la contraseña que utilizaremos, en nuestro caso se encuentra vacia. Si se desea utilizar una contraseña diferente, es necesario modificar el archivo DBConnection y colocar la contraseña que corresponda para la conexion a la BBDD

Continuaremos haciendo el flush de los privilegios mediante:
```sh
FLUSH PRIVILEGES;
```
Saldremos de nuestra terminal de SQL utilizando:
```sh
exit
```
Por ultimo importamos nuestra base de datos escribiendo el siguiente comando:
```sh
mysql -u root -p jinventory < jinventory.sql
```
Es importante recalcar que debemos encontrarnos en el mismo directorio o especificar la ruta hacia jinventory.sql

Para iniciar y reiniciar los servicios de MySQL podemos utilizar:
```sh
sudo systemctl start/stop mysqld
```
En caso de tener complejidades con la instalacion, puede utilizar la siguiente guia de DigitalOcean, la cual es mas completa. 

https://www.digitalocean.com/community/tutorials/como-instalar-mysql-en-ubuntu-18-04-es

### Windows
Para una instalacion mas sencilla y amistosa para el usuario podemos utilizar el software Xampp, este es posible de descargarlo desde su sitio oficial: https://www.apachefriends.org/es/index.html

Este se encargara de instalar y configurar por nosotros MySQL, Apache y PHP, por lo cual podremos configurar nuestras bases de datos desde PhpMyAdmin. 

Luego de descargar e instalar xampp podemos inicializarlo. Luego de iniciar los servidores Apache y MySQL podemos dirigirnos a:
https://localhost/phpmyadmin

Donde podremos configurar nuestras bases de datos. Luego de importar nuestra base de datos podremos empezar a utilizar el programa

## Testing
Si se desea realizar la rutina de tests unitarios incluidos en el proyecto, es necesario utilizar la base de datos para tests, la cual esta nombrada como jinventorytest. Sabemos que esto no es algo comodo, pero es la mejor forma para poder comprobar todas las funcionalidades del software. 








