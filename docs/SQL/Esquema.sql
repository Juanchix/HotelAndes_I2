
CREATE TABLE alojamientos (
    activa        VARCHAR2(2) NOT NULL,
    checkin       DATE NOT NULL,
    checkout      DATE NOT NULL,
    acompanantes  NUMBER NOT NULL,
    idplan        NUMBER NOT NULL,
    idalojamiento NUMBER NOT NULL
);

ALTER TABLE alojamientos
    ADD CHECK ( activa IN ( 'NO', 'SI' ) );

ALTER TABLE alojamientos ADD CONSTRAINT alojamientos_pk PRIMARY KEY ( idalojamiento );

CREATE TABLE bares (
    estilo     VARCHAR2(50) NOT NULL,
    idservicio NUMBER NOT NULL,
    idbar      NUMBER NOT NULL
);

ALTER TABLE bares
    ADD CHECK ( estilo IN ( 'Frances', 'Italiano', 'Mediterraneo', 'Mexicano', 'Oriental' ) );

ALTER TABLE bares ADD CONSTRAINT bares_pk PRIMARY KEY ( idbar );

CREATE TABLE conferencias (
    capacidad     NUMBER NOT NULL,
    fecha         DATE,
    hora          DATE NOT NULL,
    duracion      NUMBER NOT NULL,
    idservicio    NUMBER NOT NULL,
    idconferencia NUMBER NOT NULL
);

ALTER TABLE conferencias ADD CONSTRAINT conferencias_pk PRIMARY KEY ( idconferencia );

CREATE TABLE cuentas (
    netocuenta    NUMBER NOT NULL,
    idcuenta      NUMBER NOT NULL,
    idalojamiento NUMBER NOT NULL
);

ALTER TABLE cuentas ADD CONSTRAINT cuentas_pk PRIMARY KEY ( idcuenta );

CREATE TABLE gimnasios (
    capacidad  NUMBER NOT NULL,
    maquinas   VARCHAR2(255) NOT NULL,
    idservicio NUMBER NOT NULL,
    idgimnasio NUMBER NOT NULL
);

ALTER TABLE gimnasios ADD CONSTRAINT gimnasios_pk PRIMARY KEY ( idgimnasio );

CREATE TABLE habitaciones (
    numhabitacion NUMBER NOT NULL,
    disponible    VARCHAR2(2) NOT NULL,
    precionoche   NUMBER NOT NULL,
    idhotel       NUMBER NOT NULL,
    idtipo        NUMBER NOT NULL,
    idalojamiento NUMBER NOT NULL,
    idhabitacion  NUMBER NOT NULL
);

ALTER TABLE habitaciones
    ADD CHECK ( disponible IN ( 'NO', 'SI' ) );

ALTER TABLE habitaciones ADD CONSTRAINT habitaciones_pk PRIMARY KEY ( idhabitacion );

CREATE TABLE hoteles (
    nombrehotel VARCHAR2(64) NOT NULL,
    nithotel    NUMBER NOT NULL,
    idhotel     NUMBER NOT NULL
);

ALTER TABLE hoteles ADD CONSTRAINT hoteles_pk PRIMARY KEY ( idhotel );

CREATE TABLE lavados (
    prendas    NUMBER NOT NULL,
    tipo       VARCHAR2(32) NOT NULL,
    zapatos    NUMBER NOT NULL,
    idservicio NUMBER NOT NULL,
    idlavado   NUMBER NOT NULL
);


ALTER TABLE lavados ADD CONSTRAINT lavados_pk PRIMARY KEY ( idlavado );

CREATE TABLE piscinas (
    capacidad   NUMBER NOT NULL,
    profundidad FLOAT NOT NULL,
    idservicio  NUMBER NOT NULL,
    idpiscina   NUMBER NOT NULL
);

ALTER TABLE piscinas ADD CONSTRAINT piscinas_pk PRIMARY KEY ( idpiscina );

CREATE TABLE planes (
    tipoplan  VARCHAR2(64) NOT NULL,
    descuento FLOAT NOT NULL,
    idplan    NUMBER NOT NULL
);

ALTER TABLE planes
    ADD CHECK ( tipoplan IN ( 'Larga_Estadia', 'Promo_Particular', 'Tiempo_Compartido', 'Todo_Incluido' ) );

ALTER TABLE planes ADD CONSTRAINT planes_pk PRIMARY KEY ( idplan );

CREATE TABLE productos (
    nombre        VARCHAR2(100) NOT NULL,
    precio        NUMBER NOT NULL,
    idproducto    NUMBER NOT NULL,
    idrestaurante NUMBER NOT NULL,
    idbar         NUMBER NOT NULL,
    idtienda      NUMBER NOT NULL
);

ALTER TABLE productos ADD CONSTRAINT productos_pk PRIMARY KEY ( idproducto );

CREATE TABLE reservas (
    horareserva DATE,
    idreserva   NUMBER NOT NULL,
    idcuenta    NUMBER NOT NULL
);

ALTER TABLE reservas ADD CONSTRAINT reservas_pk PRIMARY KEY ( idreserva );

CREATE TABLE restaurantes (
    estilo        VARCHAR2(50) NOT NULL,
    idservicio    NUMBER NOT NULL,
    idrestaurante NUMBER NOT NULL
);

ALTER TABLE restaurantes ADD CONSTRAINT restaurantes_pk PRIMARY KEY ( idrestaurante );

CREATE TABLE reuniones (
    capacidad      NUMBER NOT NULL,
    costoadicional NUMBER NOT NULL,
    fecha          DATE,
    hora           DATE NOT NULL,
    duracion       NUMBER NOT NULL,
    idservicio     NUMBER NOT NULL,
    idreunion      NUMBER NOT NULL
);

ALTER TABLE reuniones ADD CONSTRAINT reuniones_pk PRIMARY KEY ( idreunion );

CREATE TABLE servicios (
    horarioinicial DATE,
    horariofinal   DATE,
    costo          NUMBER NOT NULL,
    cargado        VARCHAR2(2) NOT NULL,
    existe         VARCHAR2(2) NOT NULL,
    idservicio     NUMBER NOT NULL,
    idreserva      NUMBER NOT NULL
);

ALTER TABLE servicios
    ADD CHECK ( cargado IN ( 'NO', 'SI' ) );

ALTER TABLE servicios
    ADD CHECK ( existe IN ( 'NO', 'SI' ) );

ALTER TABLE servicios ADD CONSTRAINT servicios_pk PRIMARY KEY ( idservicio );

CREATE TABLE servispas (
    duracion    NUMBER NOT NULL,
    costo       NUMBER NOT NULL,
    fecha       DATE NOT NULL,
    idservispas NUMBER NOT NULL,
    idspa       NUMBER NOT NULL
);

ALTER TABLE servispas ADD CONSTRAINT servispas_pk PRIMARY KEY ( idservispas );

CREATE TABLE spas (
    capacidad  NUMBER NOT NULL,
    idservicio NUMBER NOT NULL,
    idspa      NUMBER NOT NULL
);

ALTER TABLE spas ADD CONSTRAINT spas_pk PRIMARY KEY ( idspa );

CREATE TABLE tiendas (
    tipo       VARCHAR2(40) NOT NULL,
    idservicio NUMBER NOT NULL,
    idtienda   NUMBER NOT NULL
);

ALTER TABLE tiendas
    ADD CHECK ( tipo IN ( 'Joyeria', 'Moda', 'Souvenirs', 'Supermercado' ) );

ALTER TABLE tiendas ADD CONSTRAINT tiendas_pk PRIMARY KEY ( idtienda );

CREATE TABLE tipos (
    tipo      VARCHAR2(64) NOT NULL,
    capacidad NUMBER NOT NULL,
    dotacion  VARCHAR2(512) NOT NULL,
    idtipo    NUMBER NOT NULL
);

ALTER TABLE tipos
    ADD CHECK ( tipo IN ( 'Doble', 'Familiar', 'Sencilla', 'Suite', 'Suite Presidencial' ) );

ALTER TABLE tipos ADD CONSTRAINT tipos_pk PRIMARY KEY ( idtipo );

CREATE TABLE usuarios (
    nombreuser    VARCHAR2(64) NOT NULL,
    tipodocuser   VARCHAR2(32) NOT NULL,
    numdocuser    NUMBER NOT NULL,
    correouser    VARCHAR2(64) NOT NULL,
    iduser        NUMBER NOT NULL,
    idalojamiento NUMBER NOT NULL
);

ALTER TABLE usuarios
    ADD CHECK ( tipodocuser IN ( 'CC', 'CE', 'NIT', 'PASAPORTE', 'TI' ) );

ALTER TABLE usuarios ADD CONSTRAINT usuarios_pk PRIMARY KEY ( iduser );

CREATE TABLE utensilios (
    devuelto    VARCHAR2(2) NOT NULL,
    estado      VARCHAR2(255) NOT NULL,
    idservicio  NUMBER NOT NULL,
    idutensilio NUMBER NOT NULL
);

ALTER TABLE utensilios
    ADD CHECK ( devuelto IN ( 'NO', 'SI' ) );

ALTER TABLE utensilios ADD CONSTRAINT utensilios_pk PRIMARY KEY ( idutensilio );

CREATE TABLE wifi (
    anchobanda FLOAT NOT NULL,
    idservicio NUMBER NOT NULL,
    idwifi     NUMBER NOT NULL
);

ALTER TABLE wifi ADD CONSTRAINT wifi_pk PRIMARY KEY ( idwifi );

ALTER TABLE alojamientos
    ADD CONSTRAINT alojamientos_planes_fk FOREIGN KEY ( idplan )
        REFERENCES planes ( idplan );

ALTER TABLE bares
    ADD CONSTRAINT bares_servicios_fk FOREIGN KEY ( idservicio )
        REFERENCES servicios ( idservicio );

ALTER TABLE conferencias
    ADD CONSTRAINT conferencias_servicios_fk FOREIGN KEY ( idservicio )
        REFERENCES servicios ( idservicio );

ALTER TABLE cuentas
    ADD CONSTRAINT cuentas_alojamientos_fk FOREIGN KEY ( idalojamiento )
        REFERENCES alojamientos ( idalojamiento )
            ON DELETE CASCADE;

ALTER TABLE gimnasios
    ADD CONSTRAINT gimnasios_servicios_fk FOREIGN KEY ( idservicio )
        REFERENCES servicios ( idservicio );

ALTER TABLE habitaciones
    ADD CONSTRAINT habitaciones_alojamientos_fk FOREIGN KEY ( idalojamiento )
        REFERENCES alojamientos ( idalojamiento )
            ON DELETE CASCADE;

ALTER TABLE habitaciones
    ADD CONSTRAINT habitaciones_hoteles_fk FOREIGN KEY ( idhotel )
        REFERENCES hoteles ( idhotel )
            ON DELETE CASCADE;

ALTER TABLE habitaciones
    ADD CONSTRAINT habitaciones_tipos_fk FOREIGN KEY ( idtipo )
        REFERENCES tipos ( idtipo )
            ON DELETE CASCADE;

ALTER TABLE lavados
    ADD CONSTRAINT lavados_servicios_fk FOREIGN KEY ( idservicio )
        REFERENCES servicios ( idservicio );

ALTER TABLE piscinas
    ADD CONSTRAINT piscinas_servicios_fk FOREIGN KEY ( idservicio )
        REFERENCES servicios ( idservicio );

ALTER TABLE productos
    ADD CONSTRAINT productos_bares_fk FOREIGN KEY ( idbar )
        REFERENCES bares ( idbar )
            ON DELETE CASCADE;

ALTER TABLE productos
    ADD CONSTRAINT productos_restaurantes_fk FOREIGN KEY ( idrestaurante )
        REFERENCES restaurantes ( idrestaurante )
            ON DELETE CASCADE;

ALTER TABLE productos
    ADD CONSTRAINT productos_tiendas_fk FOREIGN KEY ( idtienda )
        REFERENCES tiendas ( idtienda )
            ON DELETE CASCADE;

ALTER TABLE reservas
    ADD CONSTRAINT reservas_cuentas_fk FOREIGN KEY ( idcuenta )
        REFERENCES cuentas ( idcuenta )
            ON DELETE CASCADE;

ALTER TABLE restaurantes
    ADD CONSTRAINT restaurantes_servicios_fk FOREIGN KEY ( idservicio )
        REFERENCES servicios ( idservicio );

ALTER TABLE reuniones
    ADD CONSTRAINT reuniones_servicios_fk FOREIGN KEY ( idservicio )
        REFERENCES servicios ( idservicio );

ALTER TABLE servicios
    ADD CONSTRAINT servicios_reservas_fk FOREIGN KEY ( idreserva )
        REFERENCES reservas ( idreserva )
            ON DELETE CASCADE;

ALTER TABLE servispas
    ADD CONSTRAINT servispas_spas_fk FOREIGN KEY ( idspa )
        REFERENCES spas ( idspa )
            ON DELETE CASCADE;

ALTER TABLE spas
    ADD CONSTRAINT spas_servicios_fk FOREIGN KEY ( idservicio )
        REFERENCES servicios ( idservicio );

ALTER TABLE tiendas
    ADD CONSTRAINT tiendas_servicios_fk FOREIGN KEY ( idservicio )
        REFERENCES servicios ( idservicio );

ALTER TABLE usuarios
    ADD CONSTRAINT usuarios_alojamientos_fk FOREIGN KEY ( idalojamiento )
        REFERENCES alojamientos ( idalojamiento )
            ON DELETE CASCADE;

ALTER TABLE utensilios
    ADD CONSTRAINT utensilios_servicios_fk FOREIGN KEY ( idservicio )
        REFERENCES servicios ( idservicio );

ALTER TABLE wifi
    ADD CONSTRAINT wifi_servicios_fk FOREIGN KEY ( idservicio )
        REFERENCES servicios ( idservicio );

COMMIT;