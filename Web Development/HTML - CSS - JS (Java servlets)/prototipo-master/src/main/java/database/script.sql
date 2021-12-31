INSERT INTO cliente VALUES('11111111A','cliente1','sancho','panza','sancho.panza@gmail.com');
INSERT INTO cliente VALUES('22222222B','cliente2','quijote','manchego','quijote@gmail.com');
INSERT INTO cliente VALUES('33333333C','cliente3','fernando','alonso','fernandoalonso@gmail.com');
INSERT INTO cliente VALUES('44444444D','cliente4','rafa','nadal','vamosrafa@gmail.com');
INSERT INTO cliente VALUES('55555555E','cliente5','benito','galarga','benito@gmail.com');
INSERT INTO cliente VALUES('66666666F','cliente6','tuver','camela','camela@gmail.com');
INSERT INTO cliente VALUES('77777777G','cliente7','luka','modric','lukamodric@gmail.com');
INSERT INTO cliente VALUES('88888888H','cliente8','sergio','ramos','sergioramos@gmail.com');
INSERT INTO cliente VALUES('99999999I','cliente9','manuel','mendez','manumen@gmail.com');

INSERT INTO gestor VALUES('A11111111','ES2100000000000000000000','gestor1','gestor1@gmail.com');
INSERT INTO gestor VALUES('B22222222','ES2100000000000000000001','gestor2','gestor2@gmail.com');
INSERT INTO gestor VALUES('C33333333','ES2100000000000000000002','gestor3','gestor3@gmail.com');
INSERT INTO gestor VALUES('D44444444','ES2100000000000000000003','gestor4','gestor4@gmail.com');
INSERT INTO gestor VALUES('E55555555','ES2100000000000000000004','gestor5','gestor5@gmail.com');

INSERT INTO anuncio (titulo, descripcion,precio,fechaIni,fechaFin,maps,destino,tipo) VALUES('BARCELONA MISTERIOSA','Conoce Barcelona como nunca lo has hecho',29900,'2021-05-10', '2021-05-20', 'https://goo.gl/maps/T16nNKa5WvfrHxpu5','Barcelona',3);
INSERT INTO anuncio (titulo, descripcion,precio,fechaIni,fechaFin,maps,destino,tipo) VALUES('Hotel Eurostarts Grand Marina','Hotel 5 estrellas todo incluido',60000,'2021-05-10', '2021-05-20', 'https://goo.gl/maps/R3YYuHZp7jLHoeKJ8','Barcelona',2);
INSERT INTO anuncio (titulo, descripcion,precio,fechaIni,fechaFin,maps,destino,tipo) VALUES('Tren Palencia-Barcelona','Ave Palencia-Barcelona',10000,'2021-05-10', '2021-05-20', 'https://goo.gl/maps/T16nNKa5WvfrHxpu5','Barcelona',1);
INSERT INTO anuncio (titulo, descripcion,precio,fechaIni,fechaFin,maps,destino,tipo) VALUES('Madrid de los Austrias','Conoce Madrid como nunca lo has hecho',19900,'2021-05-01', '2021-05-10', 'https://goo.gl/maps/qNmnSY7SWJZFdDiDA','Madrid',3);
INSERT INTO anuncio (titulo, descripcion,precio,fechaIni,fechaFin,maps,destino,tipo) VALUES('Hotel Riu Plaza España','Hotel 5 estreallas todo incluido',50000,'2021-05-01', '2021-05-10', 'https://goo.gl/maps/BeqfWEFR3EttwgdE7','Madrid',2);
INSERT INTO anuncio (titulo, descripcion,precio,fechaIni,fechaFin,maps,destino,tipo) VALUES('Tren Palencia-Madrid','Ave Palencia - Madrid',15000,'2021-05-01', '2021-05-10', 'https://goo.gl/maps/qNmnSY7SWJZFdDiDA','Madrid',1);
INSERT INTO anuncio (titulo, descripcion,precio,fechaIni,fechaFin,maps,destino,tipo) VALUES('Museo de la Ciencia Valladolid','Un lado divertido de la ciencia',10000,'2021-05-10', '2021-05-20', 'https://goo.gl/maps/3dBewCCSe9VtbvYt9','Valladolid',3);
INSERT INTO anuncio (titulo, descripcion,precio,fechaIni,fechaFin,maps,destino,tipo) VALUES('Catedral de Palencia','La Bella Desconocida',500,'2021-05-10', '2021-05-20', 'https://goo.gl/maps/CSz4c4dh1gW1ydxEA','Palencia',3);

INSERT INTO historico_Anuncios (IDAnuncio, fechaPublicacion, CIF) VALUES(1,'2021-01-30','A11111111');
INSERT INTO historico_Anuncios (IDAnuncio, fechaPublicacion, CIF) VALUES(2,'2021-02-15','B22222222');
INSERT INTO historico_Anuncios (IDAnuncio, fechaPublicacion, CIF) VALUES(3,'2021-03-30','C33333333');
INSERT INTO historico_Anuncios (IDAnuncio, fechaPublicacion, CIF) VALUES(4,'2021-04-22','44444444D');
INSERT INTO historico_Anuncios (IDAnuncio, fechaPublicacion, CIF) VALUES(5,'2021-04-20','E55555555');
INSERT INTO historico_Anuncios (IDAnuncio, fechaPublicacion, CIF) VALUES(6,'2020-01-30','A11111111');
INSERT INTO historico_Anuncios (IDAnuncio, fechaPublicacion, CIF) VALUES(7,'2020-03-30','B22222222');
INSERT INTO historico_Anuncios (IDAnuncio, fechaPublicacion, CIF) VALUES(8,'2020-04-30','D44444444');

INSERT INTO historico_Compras (IDAnuncio, fechaCompra, DNI) VALUES(1,'2021-04-25','11111111A');
INSERT INTO historico_Compras (IDAnuncio, fechaCompra, DNI) VALUES(2,'2021-04-25','11111111A');
INSERT INTO historico_Compras (IDAnuncio, fechaCompra, DNI) VALUES(3,'2021-04-25','11111111A');
INSERT INTO historico_Compras (IDAnuncio, fechaCompra, DNI) VALUES(4,'2021-03-25','22222222B');
