# JUMP2DIGITAL2023




[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

Jump2digital2023 es un proyecto realizada para la prueba de Hackathon de Jump2Digital. La tarea consiste en crear una Api que permita a los usuarios consultar, adquirir, modificar y eliminar skins por un videojuego. Usa MongoDB como base de datos. Además, en este proyecto está implementado SpringSecurity con JWT para la autentificación de los usuarios. 

## INSTALACIÓN 
Para usar la aplicación es necesario tener instalados en la computadora Java y MongoDB. No hay que crear nada en la base de datos, la aplicación instala la base de datos y las colecciones por si misma. El server.port que está puesto es 8281 y se puede cambiar en los resources>application.properties si lo desea. 

## DESCRIPCIÓN
Hay un archivo en 'resources' en el directorio 'data' con el nombre 'skins.json' que se carga en la base de datos automáticamente tras iniciar la aplicación. La implementación de esta parte está en el package 'loader' usando la interfaz CommandLineRunner.

En el package 'model-domain' tenemos tres clases:
    -clase User - para los usuarios, tienen enum Role con opción de ADMIN y USER para futuros cambios.
    -clase Skin - para los skins que están en el archivo 'skins.json' y que están disponibles para los usuarios. Tiene enum de Type para la asignación de tipo de skin.
    -clase UserSkin - para los skins que los usuarios ya han adquirido. En esta clase a parte de todos los atributos de la clase Skin, el usuario deberá indicar el color que desea ponerle al skin.
    
Las clases funcionan con sus correspondientes repositorios y services que interectuan entre si y también con los controllers.

Los controlladores están divididos en dos :
    >UserController - /user - se encarga de registración y acceso de los usuarios.
    >SkinController - /skin - hace las manipulaciones con los skins y sus endpoints son los siguentes:

        Get - /available - devuelve la lista de los skins que están disponibles 
                           para comprar
        Post - /buy - permite comprar un skin introduciendo el id del skin, el id 
                           de usuario y el color deseado
        Get  -/myskins - devuelve la lista de los skins adquiridos por el usuario.
        
        Put - /color -   permite cambiar el color del skin.
        
        Delete - /delete/{id} - elimina el skin compardo.
        
        Get - /getskin/{id} - devuelve una determinada skin
    
El programa no permite que el email este duplicado y lanza una excepción si esto ocurre.    
    