/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  edgarpopos
 * Created: May 1, 2021
 */
INSERT INTO USUARIO VALUES ('11111111A','Alfredo Martínez','34001','alfredo@gmail.com','666666661');
INSERT INTO USUARIO VALUES ('11111111B','Juan Juanez','34002','juan@gmail.com','666666662');
INSERT INTO USUARIO VALUES ('11111111C','Felipe Diez','34003','felipe@gmail.com','666666663');
INSERT INTO USUARIO VALUES ('11111111D','Rodrigo Perez','47001','rodrigo@gmail.com','666666664');
INSERT INTO USUARIO VALUES ('11111111E','Alberto Rodríguez','47002','alberto@gmail.com','666666665');
INSERT INTO USUARIO VALUES ('22222222A','Pedro Rodríguez','47022','pedrorojo@gmail.com','610963218');
INSERT INTO USUARIO VALUES ('22222222B','Alvaro Alonso','47007','alvaro@gmail.com','606126772');
INSERT INTO USUARIO VALUES ('22222222C','Maikel Silva','47014','silva@gmail.com','663254159');
INSERT INTO USUARIO VALUES ('22222222D','Alfonso Gonzalez','47007','alphonso@gmail.com','663294159');
INSERT INTO USUARIO VALUES ('22222222E','Sara Rodriguez','47002','sara@gmail.com','663874159');


INSERT INTO EMPLEADO VALUES ('11111111A','empleado1','2010-11-30');
INSERT INTO EMPLEADO VALUES ('11111111B','empleado2','2011-11-30');
INSERT INTO EMPLEADO VALUES ('11111111C','empleado3','2012-11-30');
INSERT INTO EMPLEADO VALUES ('11111111D','empleado4','2013-11-30');
INSERT INTO EMPLEADO VALUES ('11111111E','empleado5','2014-11-30');

INSERT INTO EMPRESA VALUES ('22222222A',true,false);
INSERT INTO EMPRESA VALUES ('22222222B',true,false);
INSERT INTO EMPRESA VALUES ('22222222C',false,true);
INSERT INTO EMPRESA VALUES ('22222222D',true,false);
INSERT INTO EMPRESA VALUES ('22222222E',false,true);



INSERT INTO ROLESENEMPRESA VALUES ('2015-01-25','11111111A',1);
INSERT INTO ROLESENEMPRESA VALUES ('2016-01-25','11111111B',2);
INSERT INTO ROLESENEMPRESA VALUES ('2017-01-25','11111111C',3);
INSERT INTO ROLESENEMPRESA VALUES ('2018-01-25','11111111D',1);
INSERT INTO ROLESENEMPRESA VALUES ('2019-01-25','11111111E',2);

INSERT INTO VINCULACIONCONLAEMPRESA VALUES ('2015-02-25','11111111A',1);
INSERT INTO VINCULACIONCONLAEMPRESA VALUES ('2016-03-25','11111111B',1);
INSERT INTO VINCULACIONCONLAEMPRESA VALUES ('2017-02-25','11111111C',1);
INSERT INTO VINCULACIONCONLAEMPRESA VALUES ('2018-04-25','11111111D',2);
INSERT INTO VINCULACIONCONLAEMPRESA VALUES ('2019-05-25','11111111E',3);

INSERT INTO DISPONIBILIDADEMPLEADO VALUES ('2015-02-23',null,'11111111A',3);
INSERT INTO DISPONIBILIDADEMPLEADO VALUES ('2016-03-23',null,'11111111B',3);
INSERT INTO DISPONIBILIDADEMPLEADO VALUES ('2017-02-23',null,'11111111C',3);
INSERT INTO DISPONIBILIDADEMPLEADO VALUES ('2018-04-23',null,'11111111D',2);
INSERT INTO DISPONIBILIDADEMPLEADO VALUES ('2019-05-23',null,'11111111E',1);

INSERT INTO CONFIGURACIONPC VALUES (1, 1, 1, 16, 1000, 12, 16);
INSERT INTO CONFIGURACIONPC VALUES (2, 2, 2.4, 12, 500, 12, 12);
INSERT INTO CONFIGURACIONPC VALUES (3, 2, 2.7, 8, 250, 15, 6);
INSERT INTO CONFIGURACIONPC VALUES (4, 1, 1.6, 8, 725, 13, 8);
INSERT INTO CONFIGURACIONPC VALUES (5, 2, 1.8, 12, 1000, 15, 12);
INSERT INTO CONFIGURACIONPC VALUES (6, 2, 2.4, 8, 1000, 14, 16);



INSERT INTO ESPACIOALMACENAMIENTO VALUES (1,5,4,3,false);
INSERT INTO ESPACIOALMACENAMIENTO VALUES (2,6,9,3,false);
INSERT INTO ESPACIOALMACENAMIENTO VALUES (3,2,6,8,false);
INSERT INTO ESPACIOALMACENAMIENTO VALUES (4,9,3,1,false);
INSERT INTO ESPACIOALMACENAMIENTO VALUES (5,3,4,4,false);
INSERT INTO ESPACIOALMACENAMIENTO VALUES (6,1,4,1,true);
INSERT INTO ESPACIOALMACENAMIENTO VALUES (7,4,8,2,true);
INSERT INTO ESPACIOALMACENAMIENTO VALUES (8,7,9,1,true);

INSERT INTO ESPACIOALMACENAMIENTO VALUES (9,8,9,1,true);
INSERT INTO ESPACIOALMACENAMIENTO VALUES (10,7,2,2,true);
INSERT INTO ESPACIOALMACENAMIENTO VALUES (11,4,5,5,true);
INSERT INTO ESPACIOALMACENAMIENTO VALUES (12,6,1,3,true);
INSERT INTO ESPACIOALMACENAMIENTO VALUES (13,5,2,2,true);
INSERT INTO ESPACIOALMACENAMIENTO VALUES (14,3,3,3,true);

INSERT INTO PEDIDOPC VALUES (1, 4,'2021-05-09', 5, 1, '22222222A');
INSERT INTO PEDIDOPC VALUES (2, 5, '2021-05-10', 5, 2, '22222222B');
INSERT INTO PEDIDOPC VALUES (3, 3, '2021-05-11', 1, 2, '22222222B');
INSERT INTO PEDIDOPC VALUES (4, 2, '2021-05-12', 5, 1, '22222222B');
INSERT INTO PEDIDOPC VALUES (5, 1, '2021-05-13', 3, 2, '22222222B');
INSERT INTO PEDIDOPC VALUES (6, 4, '2021-05-14', 4, 1, '22222222B');
INSERT INTO PEDIDOPC VALUES (7, 1, '2021-05-15', 5, 2, '22222222B');
INSERT INTO PEDIDOPC VALUES (8, 7, '2021-05-16', 1, 1, '22222222A');
INSERT INTO PEDIDOPC VALUES (9, 1, '2021-05-17', 1, 2, '22222222A');
INSERT INTO PEDIDOPC VALUES (10,2, '2021-05-18', 1, 1, '22222222B');
INSERT INTO PEDIDOPC VALUES (11, 1, '2021-05-19', 1, 2, '22222222B');
INSERT INTO PEDIDOPC VALUES (12, 1, '2021-05-20', 1, 1, '22222222A');



INSERT INTO PC VALUES (1,false,'2021-05-02',1,'11111111C',1,null);
INSERT INTO PC VALUES (2,false,'2019-04-25',2,'11111111A',2,null);
INSERT INTO PC VALUES (3,false,'2019-04-25',1,'11111111B',3,null);
INSERT INTO PC VALUES (4,false,'2019-04-25',2,'11111111A',4,null);
INSERT INTO PC VALUES (5,false,'2019-04-25',1,'11111111C',5,null);
INSERT INTO PC VALUES (6,false,'2019-04-25',1,'11111111C',6,null);
INSERT INTO PC VALUES (7,false,'2019-04-25',1,'11111111C',7,null);
INSERT INTO PC VALUES (8,false,'2019-04-25',1,'11111111C',8,null);

INSERT INTO DESCRIPCIONCOMPONENTE VALUES (1, 1, 'NVIDIA', '1070', 600, 'Tarjeta grafica de ultima generacion');
INSERT INTO DESCRIPCIONCOMPONENTE VALUES (2, 1, 'AMD', 'Radeon RX 6000', 899, 'Ultimo modelo de tarjeta grafica reconocido mundialmente');
INSERT INTO DESCRIPCIONCOMPONENTE VALUES (3, 2, 'Seagate', 'Barracuda ', 37.11, '2TB Disco duro mas utilizado en el año 2020');
INSERT INTO DESCRIPCIONCOMPONENTE VALUES (4, 2, 'Kingston', 'A400', 29.50, 'SDD Producto del año en amazon en categoria "componentes electronicos"');
INSERT INTO DESCRIPCIONCOMPONENTE VALUES (5, 3, 'MSI', 'A320-M', 47.22, 'Placa base preparada para procesadores AMD Ryzen');
INSERT INTO DESCRIPCIONCOMPONENTE VALUES (6, 3, 'ASUS', 'ROG Strix', 129.9, 'Serie gaming con socket de tipo ATX');
INSERT INTO DESCRIPCIONCOMPONENTE VALUES (7, 4, 'Cooler Master', 'MasterBox Q300L', 39.95, 'Caja amplia para una mejor ventilzacion');
INSERT INTO DESCRIPCIONCOMPONENTE VALUES (8, 4, 'NOX', 'Infinity Alfa', 9.99, 'Extra pequeña para los componentes de más esenciales');
INSERT INTO DESCRIPCIONCOMPONENTE VALUES (9, 5, 'AMD', 'Ryzen 5 3500X', 179.90, 'Fabricado en 12nm con arquitectura Zen+');
INSERT INTO DESCRIPCIONCOMPONENTE VALUES (10, 5, 'IntelCore', 'i9-990K', 344.29, 'Chipset de la serie 300, con 8 nucleos');
INSERT INTO DESCRIPCIONCOMPONENTE VALUES (11, 6, 'Corsair Vengeance', 'RGB Pro', 47.61, '16GB Especializada para gaming, para una mejor sensacion de fluidez');
INSERT INTO DESCRIPCIONCOMPONENTE VALUES (12, 6, 'HiperX', 'Fury', 49, '8GB Preparada para seguirle el ritmo a los nuevos procesadores de ultima generacion');


INSERT INTO PEDIDOCOMPONENTES VALUES (1, 1, 5, '2021-05-02', '2021-07-02', 1, '22222222C');
INSERT INTO PEDIDOCOMPONENTES VALUES (2, 2, 8, '2021-05-03', '2021-07-03', 1, '22222222C');
INSERT INTO PEDIDOCOMPONENTES VALUES (3, 3, 7, '2021-05-04', '2021-07-04', 1, '22222222E');
INSERT INTO PEDIDOCOMPONENTES VALUES (4, 4, 3, '2021-05-05', '2021-07-05', 1, '22222222C');
INSERT INTO PEDIDOCOMPONENTES VALUES (5, 5, 12, '2021-05-06', '2021-07-06', 1, '22222222C');
INSERT INTO PEDIDOCOMPONENTES VALUES (6, 6, 5, '2021-05-07', '2021-07-07', 1, '22222222E');
INSERT INTO PEDIDOCOMPONENTES VALUES (7, 7, 5, '2021-05-02', '2021-07-02', 1, '22222222C');
INSERT INTO PEDIDOCOMPONENTES VALUES (8, 8, 8, '2021-05-03', '2021-07-03', 1, '22222222C');
INSERT INTO PEDIDOCOMPONENTES VALUES (9, 9, 7, '2021-05-04', '2021-07-04', 1, '22222222E');
INSERT INTO PEDIDOCOMPONENTES VALUES (10, 10, 3, '2021-05-05', '2021-07-05', 1, '22222222C');
INSERT INTO PEDIDOCOMPONENTES VALUES (11, 11, 12, '2021-05-06', '2021-07-06', 1, '22222222C');
INSERT INTO PEDIDOCOMPONENTES VALUES (12, 12, 5, '2021-05-07', '2021-07-07', 1, '22222222E');

INSERT INTO COMPONENTESENCONFIGURACION VALUES (1, 1);
INSERT INTO COMPONENTESENCONFIGURACION VALUES (2, 2);
INSERT INTO COMPONENTESENCONFIGURACION VALUES (3, 1);
INSERT INTO COMPONENTESENCONFIGURACION VALUES (4, 2);
INSERT INTO COMPONENTESENCONFIGURACION VALUES (5, 1);
INSERT INTO COMPONENTESENCONFIGURACION VALUES (6, 2);
INSERT INTO COMPONENTESENCONFIGURACION VALUES (7, 3);
INSERT INTO COMPONENTESENCONFIGURACION VALUES (7, 4);
INSERT INTO COMPONENTESENCONFIGURACION VALUES (8, 5);
INSERT INTO COMPONENTESENCONFIGURACION VALUES (9, 6);
INSERT INTO COMPONENTESENCONFIGURACION VALUES (10, 5);
INSERT INTO COMPONENTESENCONFIGURACION VALUES (11, 4);
INSERT INTO COMPONENTESENCONFIGURACION VALUES (12, 3);

INSERT INTO COMPONENTE VALUES (1, '2021-05-02', false, 1, null, 1, 14);
INSERT INTO COMPONENTE VALUES (2, '2021-05-03', false, 2, 2, 2, 13);
INSERT INTO COMPONENTE VALUES (3, '2021-05-04', false, 3, null, 3, 12);
INSERT INTO COMPONENTE VALUES (4, '2021-05-05', false, 4, 2, 4, 11);
INSERT INTO COMPONENTE VALUES (5, '2021-05-06', false, 5, null, 5, 10);
INSERT INTO COMPONENTE VALUES (6, '2021-05-07', false, 6, null, 6, 9);
INSERT INTO COMPONENTE VALUES (7, '2021-05-02', false, 1, null, 1, 14);
INSERT INTO COMPONENTE VALUES (8, '2021-05-03', false, 2, null, 2, 13);
INSERT INTO COMPONENTE VALUES (9, '2021-05-04', false, 3, null, 3, 12);
INSERT INTO COMPONENTE VALUES (10, '2021-05-05', false, 4, null, 4, 11);
INSERT INTO COMPONENTE VALUES (11, '2021-05-06', false, 5, null, 5, 10);
INSERT INTO COMPONENTE VALUES (12, '2021-05-07', false, 6, null, 6, 9);
INSERT INTO COMPONENTE VALUES (13, '2021-05-02', false, 1, null, 1, 14);
INSERT INTO COMPONENTE VALUES (14, '2021-05-03', false, 2, null, 2, 13);
INSERT INTO COMPONENTE VALUES (15, '2021-05-04', false, 3, null, 3, 12);

INSERT INTO COMPONENTE VALUES (16, '2021-05-02', false, 1, null, 1, 14);
INSERT INTO COMPONENTE VALUES (17, '2021-05-03', false, 2, 2, 2, 13);
INSERT INTO COMPONENTE VALUES (18, '2021-05-04', false, 3, null, 3, 12);
INSERT INTO COMPONENTE VALUES (19, '2021-05-05', false, 4, 2, 4, 11);
INSERT INTO COMPONENTE VALUES (20, '2021-05-06', false, 5, null, 5, 10);
INSERT INTO COMPONENTE VALUES (21, '2021-05-07', false, 6, null, 6, 9);
INSERT INTO COMPONENTE VALUES (22, '2021-05-02', false, 1, null, 1, 14);
INSERT INTO COMPONENTE VALUES (23, '2021-05-03', false, 2, null, 2, 13);
INSERT INTO COMPONENTE VALUES (24, '2021-05-04', false, 3, null, 3, 12);
INSERT INTO COMPONENTE VALUES (25, '2021-05-05', false, 4, null, 4, 11);
INSERT INTO COMPONENTE VALUES (26, '2021-05-06', false, 5, null, 5, 10);
INSERT INTO COMPONENTE VALUES (27, '2021-05-07', false, 6, null, 6, 9);
INSERT INTO COMPONENTE VALUES (28, '2021-05-02', false, 1, null, 1, 14);
INSERT INTO COMPONENTE VALUES (29, '2021-05-03', false, 2, null, 2, 13);
INSERT INTO COMPONENTE VALUES (30, '2021-05-04', false, 3, null, 3, 12);

INSERT INTO COMPONENTE VALUES (31, '2021-05-02', false, 1, null, 1, 14);
INSERT INTO COMPONENTE VALUES (32, '2021-05-03', false, 2, 2, 2, 13);
INSERT INTO COMPONENTE VALUES (33, '2021-05-04', false, 3, null, 3, 12);
INSERT INTO COMPONENTE VALUES (34, '2021-05-05', false, 4, 2, 4, 11);
INSERT INTO COMPONENTE VALUES (35, '2021-05-06', false, 5, null, 5, 10);
INSERT INTO COMPONENTE VALUES (36, '2021-05-07', false, 6, null, 6, 9);
INSERT INTO COMPONENTE VALUES (37, '2021-05-02', false, 1, null, 1, 14);
INSERT INTO COMPONENTE VALUES (38, '2021-05-03', false, 2, null, 2, 13);
INSERT INTO COMPONENTE VALUES (39, '2021-05-04', false, 3, null, 3, 12);
INSERT INTO COMPONENTE VALUES (40, '2021-05-05', false, 4, null, 4, 11);
INSERT INTO COMPONENTE VALUES (41, '2021-05-06', false, 5, null, 5, 10);
INSERT INTO COMPONENTE VALUES (42, '2021-05-07', false, 6, null, 6, 9);
INSERT INTO COMPONENTE VALUES (43, '2021-05-02', false, 1, null, 1, 14);
INSERT INTO COMPONENTE VALUES (44, '2021-05-03', false, 2, null, 2, 13);
INSERT INTO COMPONENTE VALUES (45, '2021-05-04', false, 3, null, 3, 12);