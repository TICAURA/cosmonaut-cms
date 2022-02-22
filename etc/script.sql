Hibernate: create table ARCHIVO (ID_ARCHIVO int8 not null, ARCHIVO oid, COMENTARIO text, FECHA_MODIFICACION timestamp, FECHA_REGISTRO timestamp not null, HASH_MD5 varchar(40) not null, NOMBRE varchar(120) not null, NUMERO_VERSION int4, IND_OBSOLETO boolean, PATH varchar(500), SIZE_KB float4 not null, ID_EXPEDIENTE int8 not null, ID_ORIGINAL int8, ID_TIPO_MULTIMEDIA int8 not null, ID_TIPO_DOCUMENTO int8 not null, primary key (ID_ARCHIVO))
Hibernate: create table DOCUMENTO_UNIDAD (ID_DOCUMENTO_UNIDAD int8 not null, IND_ACTIVO boolean not null, FECHA_REGISTRO timestamp, IND_REQUERIDO boolean not null, ID_TIPO_DOCUMENTO int8 not null, ID_UNIDAD int8 not null, primary key (ID_DOCUMENTO_UNIDAD))
Hibernate: create table ESTADO_PROCESO (ID_ESTADO_PROCESO int8 not null, IND_ACTIVO boolean, CLIENTE varchar(40) not null, DESCRIPCION varchar(200) not null, IND_PERMITE_ESCANEAR boolean not null, IND_MODIFICA_HOSPITAL boolean, NEGOCIO varchar(40) not null, PRIORIDAD int4, primary key (ID_ESTADO_PROCESO))
Hibernate: create table EVENTO (ID_EVENTO int8 not null, CLAVE varchar(10) not null, FECHA_APLICACION timestamp, FECHA_ATENCION timestamp, FECHA_REGISTRO timestamp not null, primary key (ID_EVENTO))
Hibernate: create table EXPEDIENTE (ID_EXPEDIENTE int8 not null, CLAVE varchar(10) not null, FECHA_MODIFICACION timestamp, FECHA_REGISTRO timestamp not null, IND_ACTIVO boolean, ID_EVENTO int8 not null, ID_UNIDAD int8 not null, primary key (ID_EXPEDIENTE))
Hibernate: create table TIPO_DOCUMENTO (ID_TIPO_DOCUMENTO int8 not null, ACRONIMO varchar(15) not null, IND_ACTIVO boolean not null, IND_ADICIONAL boolean not null, IND_CARGABLE boolean, DESCRIPCION varchar(120) not null, IND_ESCANEO boolean not null, NOMBRE varchar(80) not null, ORDEN int4, IND_REPETIBLE boolean not null, primary key (ID_TIPO_DOCUMENTO))
Hibernate: create table TIPO_MULTIMEDIA (ID_TIPO_MULTIMEDIA int8 not null, EXTENSION varchar(255), MIME_TYPE varchar(255), primary key (ID_TIPO_MULTIMEDIA))
Hibernate: create table UNIDAD (ID_UNIDAD int8 not null, CLAVE varchar(10) not null, IND_ACTIVO boolean, NOMBRE varchar(150) not null, PREFIJO_FOLIO varchar(25), SIGLAS varchar(12) not null, primary key (ID_UNIDAD))
Hibernate: alter table if exists EVENTO drop constraint if exists UK_ipn5b4u22yj88xokyxleu0gjr
←[36m02:06:05.898←[0;39m ←[1;30m[main]←[0;39m ←[31mWARN ←[0;39m ←[35mo.h.e.jdbc.spi.SqlExceptionHelper←[0;39m - SQL Warning Code: 0, SQLState: 00000
←[36m02:06:05.898←[0;39m ←[1;30m[main]←[0;39m ←[31mWARN ←[0;39m ←[35mo.h.e.jdbc.spi.SqlExceptionHelper←[0;39m - la restricci¾n ½uk_ipn5b4u22yj88xokyxleu0gjr╗ en la relaci¾n ½evento╗ no existe, ignorando
Hibernate: alter table if exists EVENTO add constraint UK_ipn5b4u22yj88xokyxleu0gjr unique (CLAVE)
Hibernate: alter table if exists EXPEDIENTE drop constraint if exists UK_32tv51hphoqgpo9efasxcbnx2
←[36m02:06:05.909←[0;39m ←[1;30m[main]←[0;39m ←[31mWARN ←[0;39m ←[35mo.h.e.jdbc.spi.SqlExceptionHelper←[0;39m - SQL Warning Code: 0, SQLState: 00000
←[36m02:06:05.912←[0;39m ←[1;30m[main]←[0;39m ←[31mWARN ←[0;39m ←[35mo.h.e.jdbc.spi.SqlExceptionHelper←[0;39m - la restricci¾n ½uk_32tv51hphoqgpo9efasxcbnx2╗ en la relaci¾n ½expediente╗ no existe, ignorando
Hibernate: alter table if exists EXPEDIENTE add constraint UK_32tv51hphoqgpo9efasxcbnx2 unique (CLAVE)
Hibernate: alter table if exists UNIDAD drop constraint if exists UK_4n58wx8xlklareccppvhph0as
←[36m02:06:05.919←[0;39m ←[1;30m[main]←[0;39m ←[31mWARN ←[0;39m ←[35mo.h.e.jdbc.spi.SqlExceptionHelper←[0;39m - SQL Warning Code: 0, SQLState: 00000
←[36m02:06:05.921←[0;39m ←[1;30m[main]←[0;39m ←[31mWARN ←[0;39m ←[35mo.h.e.jdbc.spi.SqlExceptionHelper←[0;39m - la restricci¾n ½uk_4n58wx8xlklareccppvhph0as╗ en la relaci¾n ½unidad╗ no existe, ignorando
Hibernate: alter table if exists UNIDAD add constraint UK_4n58wx8xlklareccppvhph0as unique (CLAVE)
Hibernate: create sequence SEQ_ARCHIVO start 1 increment 1
Hibernate: create sequence SEQ_DOCUMENTO_UNIDAD start 1 increment 1
Hibernate: create sequence SEQ_ESTADO_PROCESO start 1 increment 1
Hibernate: create sequence SEQ_EVENTO start 1 increment 1
Hibernate: create sequence SEQ_EXPEDIENTE start 1 increment 1
Hibernate: create sequence SEQ_ID_TIPO_MULTIMEDIA start 1 increment 1
Hibernate: create sequence SEQ_TIPO_DOCUMENTO start 1 increment 1
Hibernate: create sequence UNIDAD_SEQ start 1 increment 1
Hibernate: alter table if exists ARCHIVO add constraint FK6se63rlv3jwk2vm4egop08gax foreign key (ID_EXPEDIENTE) references EXPEDIENTE
Hibernate: alter table if exists ARCHIVO add constraint FKhwhxp1bx3ren60fauaxio6yl2 foreign key (ID_ORIGINAL) references ARCHIVO
Hibernate: alter table if exists ARCHIVO add constraint FKd8dtr8qxhh0sqo4749epg93g9 foreign key (ID_TIPO_MULTIMEDIA) references TIPO_MULTIMEDIA
Hibernate: alter table if exists ARCHIVO add constraint FK6yl8e1jj0ywwfdurqewgxo6un foreign key (ID_TIPO_DOCUMENTO) references TIPO_DOCUMENTO
Hibernate: alter table if exists DOCUMENTO_UNIDAD add constraint FKp62rcqnxxto9fws1s26vtvp0 foreign key (ID_TIPO_DOCUMENTO) references TIPO_DOCUMENTO
Hibernate: alter table if exists DOCUMENTO_UNIDAD add constraint FKhmcvyjm09u16l4asomgiyn5ef foreign key (ID_UNIDAD) references UNIDAD
Hibernate: alter table if exists EXPEDIENTE add constraint FKjw24ac5jtpo8mg621uxt7yt6b foreign key (ID_EVENTO) references EVENTO
Hibernate: alter table if exists EXPEDIENTE add constraint FKnlob9tcajk7bi849h0roe7t30 foreign key (ID_UNIDAD) references UNIDAD


Hibernate: create table CFG_TIPO_METADATO (ID_TIPO_METADATO int8 not null, DESCRIPCION varchar(120) not null, NOMBRE varchar(80) not null, primary key (ID_TIPO_METADATO))
Hibernate: create table METADATO_VALOR (ID_METADATO_VALOR int8 not null, VALOR varchar(400) not null, ID_EXPEDIENTE int8 not null, ID_TIPO_METADATO int8 not null, primary key (ID_METADATO_VALOR))
Hibernate: create sequence CFG_TIPO_METADATO_SEQ start 1 increment 1
Hibernate: create sequence METADATO_VALOR_SEQ start 1 increment 1
Hibernate: alter table if exists METADATO_VALOR add constraint FKswhq195tn66vkp1hs8hh751hb foreign key (ID_EXPEDIENTE) references EXPEDIENTE
Hibernate: alter table if exists METADATO_VALOR add constraint FK9iv4fuuo9r63iiesjc36lylvj foreign key (ID_TIPO_METADATO) references CFG_TIPO_METADATO