
# Creando la base de datos

<property name="hibernate.hbm2ddl.auto">create</property>


*validate: valida si la base de datos esta creada correctamente. Comprueba que la base de datos que hay creada
        cumpla con lo establecido en las entidades


*create-drop: primero crea y despues borra. Para que funcione hay que irse a la clase HibernateUtil y comprobar
    que el metodo shutdown() incluya:
            "   if(sessionFactory !=null){
                        sessionFactory.close();
                }"
    Asegurarse de que en los metodos en los que se cree una instancia session se inserte la siguiente linea de
codigo: 
        "HibernateUtil.shutdown()"



#Creacion de datos e insercion automática:

1- crear archivo sql dentro de la carpeta resources
2- conectarlo a base de datos: clickar en opcion: configure data source, darle a + y elegir mysql
3- introducir usuario, contraseña y nombre de la base de datos
4- En el archivo de configuracion crear la propiedad:
            <property name="hibernate.hbm2ddl.import_files">nombreArchivo.sql</property>