
# Diferencias entre herencias:


* mappedsuperclass: 
    - Tenemos clase abstracta (herencia)
    - La clase abstracta no tiene tabla
* tableperclass:
    - Tenemos herencia pero la clase NO es abstracta
    - Todas las clases tienen tabla
    - Los campos de la clase padre APARECEN en los hijos
*joined:
  - Tenemos herencia pero la clase NO es abstracta
  - Todas las clases tienen tabla
  - Los campos de la clase padre NO APARECEN en los hijos
*singleTable:
  - Dos unicas tablas: la del dueño, y account, la cual recoge todas las propiedades de las otras dos clases