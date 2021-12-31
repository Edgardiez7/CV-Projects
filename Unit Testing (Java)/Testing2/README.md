This project will show how to follow Test Driven Development (TDD) to design and develop a Java project with Maven and Ant. 
JUnit 5 and Mock Objects are used to perform WhiteBox, BlackBox and Isolation tests and provide a 100% code coverage.
More info in the pdf file.


TDS 2020-2021 PRÁCTICA 4 ÉDGAR DÍEZ ALONSO

-Tiempo Total Horas/Hombre:

Edgar Diez Alonso -> 21h 10min

-Clases que forman parte de la solucion:

                                SLOC              LLOC
    Coordenadas                  75                18
    Estacion                    102                24
    Estado                       12                 6
    PuntoRecogida                43                13
    Sistema                     484               282   TOTAL SISTEMA :       484 SLOC

-Clases de Test de las clases diseñadas:
                                SLOC             LLOC
    PruebasSecuenciaTest        147               142
    SistemaTest                 717               704   TOTAL TESTS SISTEMA : 864 SLOC

-Ratio code/test ->  1 : 1.78

-Statistics
   |      extension|     total code|  total comment|    total blank|percent|
   -------------------------------------------------------------------------
   |               |             85|              1|              6|    5.4|
   |           .xml|            202|             18|             26|     13|
   |            .md|             54|              0|             16|    3.4|
   |         .prefs|             31|              2|              0|    2.0|
   |         .index|              1|              0|              0|  0.064|
   |       .history|              4|              0|              0|   0.26|
   |          .java|           1189|            401|            147|     76|
   -------------------------------------------------------------------------

-Explicación proceso TDD:

Lo primero que hice tras crear el proyecto y sincronizar mi repositorio local con Git fue
reflexionar sobre que requisitos deberia cumplir un sistema y todos sus componentes segun
el guion de la practica. Realice un esquema "borrador" sobre que clases deberia implementar,
cual seria la relacion entre ellas y sus atributos.

Posteriormente realice los tests de fase RED de TODAS las clases mencionadas en el guion, 
ya que no habia entendido que solo hacia falta realizar los tests en la fase RED de sistema
(me toco borrar todos los tests posteriormente).

Tras escribir los tests comprobe que fallaban, y arregle aquellos mal implementados que, o bien,
daban error o pasaban en verde.

A partir de la funcionalidad que especifique en los tests, cree el stub de Sistema e importe los 
stubs de las demas clases que ya se nos daban.

Posteriormente, ya en la fase red, realice las pruebas de secuencia y las de caja blanca con mocks de
las clases no implementadas, tras implementar Sistema. Tuve varios problemas en los tests que no
pasaban pero al final logre solucionarlos todos, y logre un coverage del 100%.

Por ultimo, implemente los metodos para manejar xml, tanto importar como exportar.

PROBLEMAS QUE ME HAN SURGIDO: 
- El coverage es del 99,5%, ya que si creo un xml de prueba con varias estaciones para 
comprobar la ramificacion de la funcion privada que asigna el estado de los PR me salta excepcion al no poder construir
objetos del xml a la clase Sistema al no estar implementadas las demas clases. Necesitaría implementar los constructores 
de las clases auxiliares para poder tener un 100% de coverage.
- No sé porque no se han registrado los cambios que he realizado tras usar el plugin sonarlint de eclipse. Se han guardado
en el historial como un único cambio aunque he realizado muchos. ¿Puede ser porque los he corregido al finalizar la practica
todos simultaneamente? No he corregido todas las sugerencias del plugin, ya que algunas como que no devuelvan arraylists sino
interfaces, nombres de paquetes, etc van en contra de lo estipulado en el guion de la practica.

Como principal VENTAJA del proceso TDD destaco el hecho de que te obliga a pararte a pensar antes
de ponerte a programar, ya que diseñar los tests antes que la clase te fuerza a "diseñar" el esqueleto
de las clases antes de programarlas.

Como principal DESVENTAJA del proceso TDD, opino que es el tiempo de desarrollo, ya que aunque asegura
un proyecto muy bien testado y refinado, el proceso TDD lleva mucho tiempo entre diseñar los numerosos
tests, comprobar coberturas, etc ... 
