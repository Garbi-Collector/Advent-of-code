Entras y tomas el ascensor hasta la única parada restante: la tienda de regalos. "¡Gracias por visitar el Polo Norte!", exclama alegremente un cartel cercano. No estás seguro de quién tiene permiso para visitar el Polo Norte, pero sabes que puedes acceder al vestíbulo por aquí y desde allí al resto de la base.

Mientras recorres la sorprendentemente extensa selección, uno de los dependientes te reconoce y te pide ayuda.

Resulta que uno de los Elfos más jóvenes estaba jugando con el ordenador de una tienda de regalos y ¡añadió un montón de ID de producto no válidos a la base de datos! Seguro que no te costará nada identificarlos, ¿verdad?

Ya han comprobado la mayoría de los rangos de ID de producto; solo tienen unos pocos (los que has introducido en el rompecabezas) que tendrás que comprobar. Por ejemplo:

> 11-22,95-115,998-1012,1188511880-1188511890,222220-222224,
1698522-1698528,446443-446449,38593856-38593862,565653-565659,
824824821-824824827,2121212118-2121212124


(Los rangos de ID se encierran aquí para mayor legibilidad; en tu entrada, aparecen en una sola línea larga).

Los rangos están separados por comas (,); cada rango muestra su primer y último ID, separados por un guion (-).

Como el joven elfo solo estaba creando patrones divertidos, puedes encontrar los ID no válidos buscando cualquier ID compuesto únicamente por una secuencia de dígitos repetidos dos veces. Por lo tanto, 55 (5 dos veces), 6464 (64 dos veces) y 123123 (123 dos veces) serían ID no válidos.

Ninguno de los números tiene ceros a la izquierda; 0101 no es un ID en absoluto. (101 es un ID válido que ignorarías).

Tu tarea es encontrar todos los ID no válidos que aparecen en los rangos dados. En el ejemplo anterior:

11-22 tiene dos ID no válidos: 11 y 22.
95-115 tiene un ID no válido: 99.
998-1012 tiene un ID no válido: 1010.
1188511880-1188511890 tiene un ID no válido: 1188511885.
222220-222224 tiene un ID no válido: 222222.
1698522-1698528 no contiene ningún ID no válido.
446443-446449 tiene un ID no válido: 446446.
38593856-38593862 tiene un ID no válido: 38593859.
El resto de los rangos no contiene ningún ID no válido. Al sumar todos los ID no válidos en este ejemplo, se obtiene 1227775554.

¿Qué se obtiene al sumar todos los ID no válidos?