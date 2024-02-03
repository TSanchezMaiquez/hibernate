#Cascada

*OneToOne = Un author tiene una direccion
            -Solo se persiste/elimina el objeto que tiene la asociacion: 
                        author tiene address -> solo persistimos/eliminamos author

*OneToMany = Un autor tiene muchos libros
            -Persistimos autor y despues los libros, con el autor asociado
            -Cuando eliminamos un autor los libros asociados serán eliminados tambien

*ManyToOne = muchos libros tienen muchos autores
            -Solo persistimos los libros, los cuales tendran el autor asociado
            -CUIDADO: Al poner cascada en esta asociacion y borrar un libro eliminara al autor, y como el autor
                        tambien tiene cascada lo elimina y con él se eliminan los libros asociados a dicho autor
            -Poner cascada en este lado de la asociacion NO TIENE SENTIDO




