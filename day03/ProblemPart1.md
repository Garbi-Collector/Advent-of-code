Bajas por una corta escalera, entras en un vestíbulo sorprendentemente amplio y pasas rápidamente por el control de seguridad. Sin embargo, al llegar a los ascensores principales, descubres que cada uno tiene una luz roja encima: todos están desconectados.

"Lo siento", se disculpa una elfa mientras trastea con un panel de control cercano. "Parece que una sobrecarga eléctrica los ha quemado. Intentaré conectarlos pronto".

Explicas tu necesidad de ir más allá. "Bueno, al menos podrías bajar por la escalera mecánica hasta el departamento de impresión, aunque no llegarías mucho más lejos sin los ascensores funcionando. Es decir, podrías si la escalera mecánica no estuviera también desconectada".

"¡Pero no te preocupes! No está quemado; solo necesita energía. Quizás puedas ponerlo en marcha mientras sigo trabajando en los ascensores".

Hay baterías cerca que pueden suministrar energía de emergencia a la escalera mecánica para una ocasión como esta. Cada batería está etiquetada con su índice de sacudidas, un valor del 1 al 9. Anota sus índices de sacudidas (tu entrada del rompecabezas). Por ejemplo:

> 987654321111111

> 811111111111119

> 234234234234278

> 818181911112111

Las baterías están organizadas en bancos; cada línea de dígitos en tu entrada corresponde a un solo banco de baterías. Dentro de cada banco, necesitas encender exactamente dos baterías; la sacudida que produce el banco es igual al número formado por los dígitos de las baterías que has encendido. Por ejemplo, si tienes un banco como el 12345 y enciendes las baterías 2 y 4, el banco produciría 24 sacudidas. (No puedes reorganizar las baterías).

Necesitarás encontrar la sacudida máxima que cada banco puede producir. En el ejemplo anterior:

- En `987654321111111`, se puede generar la mayor sacudida posible, 98, activando las dos primeras baterías.
- En `81111111111119`, se puede generar la mayor sacudida posible activando las baterías 8 y 9, lo que produce 89 sacudidas.
- En 234234234234278, se puede generar 78 activando las dos últimas baterías (7 y 8).
En `818181911112111`, la mayor sacudida que se puede producir es 92.


- La sacudida total de salida es la suma de las sacudidas máximas de cada banco; por lo tanto, en este ejemplo, la sacudida total de salida es 98 + 89 + 78 + 92 = 357.

Hay muchas baterías frente a usted. Encuentre la sacudida máxima posible de cada banco; ¿cuál es la sacudida de salida total?