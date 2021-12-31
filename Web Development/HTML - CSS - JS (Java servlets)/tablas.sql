DROP TABLE historico_Anuncios;
DROP TABLE historico_Compras;
DROP TABLE anuncio;
DROP TABLE cliente;
DROP TABLE gestor;
DROP TABLE TipoAnuncio;


-- Enum
create table TipoAnuncio
(
	IdEstado SMALLINT not null,
	NombreEstado VARCHAR(20) not null unique,
		PRIMARY KEY(IdEstado)
);

INSERT INTO TipoAnuncio
VALUES  (1,'Transporte'),
        (2,'Alojamiento'),
	(3,'Turismo');

CREATE TABLE cliente (
  DNI VARCHAR(9) not null primary key,
  password VARCHAR(20) not null,
  nombre VARCHAR(30) not null,
  apellidos VARCHAR(30) not null,
  correo VARCHAR(30) not null
  
);

CREATE TABLE gestor(
  CIF VARCHAR(9) not null primary key,
  codCuenta VARCHAR(30) not null,
  password VARCHAR(30) not null,
  correo VARCHAR(30) not null
);

CREATE TABLE anuncio (
  ID INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) primary key,
  titulo VARCHAR(100) not null,
  descripcion VARCHAR(100) not null,
  precio INTEGER not null,
  fechaIni DATE not null,
  fechaFin DATE not null,
  maps VARCHAR(200),
  destino VARCHAR(30) not null,
  tipo SMALLINT not null,
    FOREIGN KEY(tipo) REFERENCES TipoAnuncio(IdEstado)
);

CREATE TABLE historico_Anuncios(
  ID INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) primary key,
  IDAnuncio INTEGER,
  fechaPublicacion DATE not null,
  CIF VARCHAR(9) not null,
    FOREIGN KEY (IDAnuncio) REFERENCES anuncio (ID),
    FOREIGN KEY (CIF) REFERENCES gestor (CIF)
);

CREATE TABLE historico_Compras(
  ID INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) primary key,
  IDAnuncio INTEGER,
  fechaCompra DATE not null,
  DNI VARCHAR(9) not null,
    FOREIGN KEY (IDAnuncio) REFERENCES anuncio (ID),
    FOREIGN KEY (DNI) REFERENCES cliente (DNI)
);



