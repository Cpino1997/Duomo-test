pruebame en https://backend-duomo.azurewebsites.net/
<img src="https://i.ibb.co/2K5632D/logo.png">
<h3 align="center"> Bienvenido al manual de instrucciones de la app! lml</h3>
<h4 align="center"> Requisitos y Primeros Pasos</h4>
Para esta aplicacion se necesita un entorno con los siguentes programas: Java 17, Maven, Springboot, Angular 14, Mysql, Node. Para correr el proyecto necesitaremos clonar el repositorio completo y desde la terminal dirigirnos a nuestro frontend para ejecutar un npm install y asi instalar nuestro entorno de desarollo, para iniciar el proyecto necesitaremos escribir en nuestra consola ng serve.
``` Node
git clone 
```
Para correr el backend necesitaremos abrir la carpeta con nuestro ide preferido, yo utilice el intellij. Para la base de datos deberemos ejecutar el proyecto con springboot para la creacion de las tablas uego de esto ingresaremos los datos indispensables para arrancar el programa. Desde mysql ejecutamos los siguentes codigos: 

``` Mysql 
create database demos;
use demos;
/*arrancar el programa para crear las entidades y luego insertar los roles*/
insert into roles values(1,"ROLE_USER"),(2,"ROLE_MOD"),(3,"ROLE_ADMIN");
```

y tambien agregaremos nuestras regiones y comunas para comenzar a trabajar:

``` Mysql 
insert into regiones values(0,"Arica y Parinacota"),(0,"Tarapacá"),(0,"Antofagasta"),(0,"Atacama"),
(0,"Coquimbo"),(0,"Valparaíso"),(0,"Región del Libertador Gral. Bernardo O’Higgins"),(0,"Región del Maule"),
(0,"Región del Biobío"),(0,"Región de la Araucanía"),(0,"Región de Los Ríos"),(0,"Región de Los Lagos"),
(0,"Región Aisén del Gral. Carlos Ibáñez del Campo"),(0,"Región de Magallanes y de la AntárVca Chilena"),
(0,"Región Metropolitana de Santiago");


```


lo primero que debes saber es que esta api se protegida con JWT, por lo cual para cualquier peticion que realices al api debera incluir un header con el token. para ello necsitamos loguearnos en http://localhost:8080/api/auth/login, si no te encuentras logeado puedes hacerlo enviarndo un post a http://localhost:8080/api/registro en el cual se debera incluir un json con los siguentes atributos:
- username: String de 4 a 40 caracteres
- password: String que se encripta al guardar en la bd
- email: String que contiene un correo el cual sera validado!
Todos los usuarios nuevos creados automaticamente quedaran con un rol de usuario, si deseas crear un usuario con rol de administrador o moderador deberas hacerlo desde mysql.

Para obtener la lista de personas nos basta con realizar un get a /api/personas para obtener un listado con las personas disponibles. para obtener una sola persona debemos realizar un get a /api/personas/idPersona . si deseas eliminar una persona deberas realizar una solicitud delete a /api/personas/idPersona, si lo que buscas es agregar personas a la bd deberas realizar una solicitud post en /api/personas la cual debera ir acompañada de un archivo json con un objeto PersonaDTO. Querys avanzadas! = En esta api tambien podemos realizar la busqueda de personas segun la comuna en la cual viven, para ello debemos enviar una peticion get en /api/personas/com/idComuna.

Para obtener la lista de comunas debemos realizar una solicitud get a /api/comunas la cual nos devolvera una lista de comunas, para consultar solo por una deberemos hacer la misma solicitud pero esta vez a /api/comunas/idComuna, para realizar un delete al igual que en el controlador de personas deberemos enviar una peticion delete en /api/comunas/idComuna, para guardar registros deberemos realizar una peticion post a /api/comunas la cual debera incluir un objeto en formato json con los atributos de la comuna. Tambien podemos listar las comunas segun su reguion para ello debemos realizar un get a /api/comunas/reg/idRegion.

Para obtener una lista de Regiones debemos realizar una peticion del tipo get a /api/regiones,
para obtener un solo id deberemos enviar una peticion get a /api/regiones/idRegion. En caso de querer eliminar un registro se puede hacer enviando una peticion delete a /api/regiones, para realizar el guardado de una entidad debemos realizar una peticion del tipo post a /api/regiones la cual debera incluir un objeto del tipo RegionDTO

Para realizar cualquier guardado o eliminacion de datos de las tablas comunas y region deberas tener un rol como administrador o uno como moderador para ello puedes ejecutar el siguente comando en mysql 

``` Mysql 
/* para rol admin */
insert into usuario_roles values("username",1);
/* para rol mod */
insert into usuario_roles values("username",2);
```

Created by Pinolabs
