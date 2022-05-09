Introducción Durante el taller desarrollaremos un juego de carreras multijugador, con mecánicas similares al Road Fighter

En las clases del taller se les proveerá de herramientas para poder realizarlo en Java, con las buenas prácticas que irán adquiriendo.

Metodología de trabajo

La realización del juego, tendrá tres etapas con sus respectivas entregas:

Fecha	Contenido de la entrega
14/05	Diagrama de clases del modelo del juego, implementado y testeado (código)
04/06	Posibilidad de interactuar con los elementos del juego en tiempo real en un entorno simulado, de forma gráfica
02/07	Capacidad de ser jugado a través de cliente-servidor
Se espera que las prácticas de programación sean buenas, y adecuadas al conocimiento adquirido en la materia. Esto incluye evitar el código aglomerado, tener una buena interacción de clases, no duplicar funcionalidades, tener el código indentado y prolijo, entre otras.

Mecánica del juego

La mecánica del juego consiste en una carrera entre múltiples automóviles en una ruta. A diferencia del Road Fighter, además de existir los automóviles circulando por la ruta, se contará con otros oponentes controlados por otras personas.

Una partida debe contar como mínimo con 2 jugadores (y soportar más de 4)
Una partida debe contar con una única ronda donde se determinaran las posiciones de los jugadores
La ronda puede finalizar cuando al menos un jugador haya llegado a la meta. Se puede esperar a todos los jugadores, esperar un cierto tiempo, o alguna otra forma
El mapa se compone de una ruta que será recorrida por los automóviles, y los margenes del mismo
El mapa contiene automóviles circulando que si son chocados por los automóviles de los jugadores, les harán perder el control momentáneamente
El mapa puede haberse generado previamente o generarlo aleatoriamente
Si un automóvil toca alguno de los dos márgenes de la ruta, explotará y si le pertenece a algún jugador volverá a aparecer cerca de ese margen unos instantes después
El movimiento de los vehículos será de forma horizontal o libre en la pantalla. El movimiento se tiene que sentir fluido
La posición inicial de los vehículos no debe darle ningún tipo de ventaja a algún jugador
Los vehículos podrán acelerar, en un principio a una velocidad constante
Los vehículos de los jugadores también tienen que tener la capacidad de frenar, lo cual disminuye su velocidad
Al finalizar la partida se debe indicar de forma clara la posición de cada jugador
Requisitos generales

Como usuario quiero ingresar al juego con mi nombre de usuario y contraseña, para poder jugar contra otros jugadores en una sala
Como usuario quiero seleccionar una sala de las existentes, para poder ingresar y jugar con los participantes (*1)
Como usuario quiero crear nuevas salas para que ingresen otros jugadores (*2)
Como usuario quiero poder salir de una sala, incluso si en la misma el juego se encuentra en ejecución
Como usuario quiero poder identificar cada jugador (saber quién lo maneja, y ver su puntuación)
Notas: (*1) Una sala puede estar activa para que ingresen jugadores mientras un juego no esté activo en la misma. Aún así, debe aparecer en la lista de juegos como que el juego ya inició (*2) La partida podrá ser iniciada por el creador de la sala, o cuando todos los jugadores estén listos, o cualquier otra condición que consideren

Requisitos extras

Además de las mecánicas y requisitos antes mencionados, cada grupo debe elegir al menos 5 puntos de requisitos extras de los siguientes

(3) Agregar cambios para acelerar más rápido
(2) Agregar obstáculos fijos en el camino
(2) Agregar vehículos que tengan algún patrón de movimiento extra
(1) Incluir efectos de sonido con configuración de ajuste de volumen
(+1) Agregar música de fondo de manera correcta en el menú y en la carrera. Deberá tener un ajuste de volumen independiente a los efectos de sonido
(2) Si mapa generado previamente: Permitir elegir entre varios previo a la carrera
(2) Si mapa generado aleatoriamente: Permitir elegir configuración de que cosas generar y con cuanta frecuencia
(3) Crear un modo de 1 jugador donde se aplique algún motivo distinto para competir contra mí mismo (mecánica de puntuación, combustible, tiempo). El mismo podrá ser accedido desde un nuevo menú intermedio donde pregunte si va a jugar solo o por red
(2) Agregar algún power-up en el mapa que al ser agarrado genere algún efecto (positivo o negativo)
(3) Agregar alguna habilidad activa (que tenga un límite de usos o que se pueda utilizar cada cierto tiempo)
(1) Permitir configuración de controles por un archivo (y que a nivel código haya una interfaz entre la configuración y los comandos)
(2) Modo sala simplificada. La pantalla de ingreso solo tendrá la posibilidad de crear una sala o unirse con un código (no habrá listado de salas). Mientras se esta esperando a que se inicie la partida, los jugadores podrán interactuar con su vehículo de alguna manera