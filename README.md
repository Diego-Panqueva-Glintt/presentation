# PRESENTATION

Servicio para la gestión de presentaciones para ofrecer servicios tecnologicos a otras compañias.

## Índice

* [Características](#características)
* [Requisitos Previos](#requisitos-previos)
* [Instalación](#instalación)
* [Configuración del Entorno](#configuración-del-entorno)
* [Ejecución](#ejecución)
* [Endpoints Disponibles](#endpoints-disponibles)
* [Documentación de la API](#documentación-de-la-api)
* [Notas de Lanzamiento](#notas-de-lanzamiento)

## Características

* Spring Boot 3.4.4
* Java 22
* Maven como gestor de dependencias


## Requisitos Previos

* Java 22 -> https://www.youtube.com/watch?v=PQk9O03cukQ
* Maven 3.9.9 -> https://www.youtube.com/watch?v=Ig87GMiGKn4
* Docker Descktop -> https://www.youtube.com/watch?v=uDkf1fSPlYQ
* Dbeaver -> https://dbeaver.io/download/

## Instalación

### Usando Maven

```bash
git clone https://github.com/Diego-Panqueva-Glintt/presentation.git
cd presentation
mvn clean install
```


## Configuración del Entorno

En Docker, ejecutamos la imagen de postgressql:

```plaintext
docker run -d \
  --name postgres-upgrade \
  -e POSTGRES_DB=upgrade_presentation \
  -e POSTGRES_USER=admin \
  -e POSTGRES_PASSWORD=admin123 \
  -p 5432:5432 \
  postgres:15
```
De esta manera tendremos la imagen creada de la base de datos.

## Ejecución

### Modo Producción
Luego de descargar el proyecto y de estar en la carpeta:
```bash
cd presentation
java -jar target/presentation-0.0.1-SNAPSHOT.jar

```

## Endpoints Disponibles

La aplicación expone los siguientes endpoints en el puerto 8080:
La base url con la que se debe poner en el navegador de gusto
http://localhost:8080/


| Endpoint        | Método | Descripción                                                                                                                    |
|-----------------|-------|--------------------------------------------------------------------------------------------------------------------------------|
| `/api/load-csv-all`     | GET | Realiza el cargue de la información que se encuentra en los archivos csv|
| `/api/generate-pdf-report?titulo=MásMóvil&compania=MasOrange&autor=Juan%20Anguita&diasPago=5&referenciaDocumento=OtosiText.docx&referenciaDocVersion=V2.1`      | GET | Es el endpoint que descarga el reporte de presentación|

El endpoint que hace la descarga tiene unas opciones de entrada que son necesarias y obligatorias:

| Campo        | Tipo de dato | Descripción                                                                                                                    |
|-----------------|-------|--------------------------------------------------------------------------------------------------------------------------------|
| `titulo`     | TEXTO | Es el titulo para el que va relacionada la presentación, el titulo dummy |
| `compania`     | TEXTO | Es el nombre de la compañia a la quién va dirigida la presentación |
| `autor`     | TEXTO | Está relacionado con la diapositiva 2, donde relaciona el autor para los cuadros referencias a otros documentos y registro de versiones  |
| `diasPago`     | NÚMERO | Diapositiva 11 relacionada con facturación y pago, se refiere al plazo en el que la compañía contratante tiene para hacer el pago del servicio |
| `referenciaDocumento`     | TEXTO | Es para la diapositiva 2, donde referencia a otro documento como anexo a la presentación, en el va el nombre del documento |
| `referenciaDocVersion`     | TEXTO | Se refiere a la versión presentada del documento anexo, ejemplo V1, V2, V3 |


## Documentación de la API

La documentación de la base de datos:
* POSTGRESQL - IMAGEN DOCKER
* Para crear la base de datos es necesario conectarse con un cliente como DBEaver, para hacer la ejecución de cada sentencia SQL
* Tablas:

```plaintext
/**
-- DDL tabla relacionada con la diapositiva de resumen ejecutivo
-- CREATE TABLE squat_profile
*/
create table squat_profile(
	id SERIAL primary key, -- identificador único
	squat varchar(20), -- nombre del squat
	profile varchar(50), -- nombre del perfil
	num_profile integer, -- cantidad de perfiles
	name_contact varchar(50), -- nombres de los propuestos
	full_time varchar(10), -- dedicación de los propuestos
	location varchar(20), -- ubicación de trabajo
	duration varchar(30), -- duración del proyecto
	company varchar(30), -- empresa a la que va dirigida la propuesta
	team_assign varchar(30), -- nombre del equipo asignado
	total_investment numeric(10,2), -- inversión total, pero no es necesario su valor, se calcula en otra tabla
	type_amount varchar(5), -- tipo de moneda, por su hay una variación de país
	destination_q varchar (30) -- se refiere al Q para la cual se propone el squat
);


/**
-- DDL tabla para la diapositiva de precios y valor de impresión
-- CREATE TABLE price_total_day
*/
create table price_total_day(
	id serial primary key, -- identificador único
	month_price varchar(30), -- mes de la cotización
	name_contact varchar(50), -- nombres de los propuestos
	profile varchar(30), -- nombre del perfil de los propuestos
	day_price numeric (10,2), -- precio por día
	num_days integer, -- cantidad de días laborables al mes
	q_year varchar(20) -- Q al que va dirigido el precio
);
/**
-- DDL tabla para la diapositiva alcance y objetivos, también para diapositiva factores
-- diferenciadores y la diapositiva de otras condiciones
-- CREATE TABLE scope_objective
*/
CREATE TABLE scope_objective (
	id SERIAL PRIMARY KEY, -- identificador único
	project_scope TEXT, -- define el encabezado para el alcance del proyecto, los factores diferenciadores y/o otras condiciones
	proffesion VARCHAR(50), -- se usa para diferenciar si es alcance, factores o condiciones
	q_time VARCHAR(20), -- Q al que va dirigido el proyecto
	items_code varchar(10) -- Parte importante ** este campo es el código de referencia para los items asociados al alcance, factores o condiciones
);
/**
-- DDL tabla que referencia los items asociados al alcance, factores o condiciones
-- CREATE TABLE items
*/
CREATE TABLE items (
	id SERIAL PRIMARY KEY, -- identificador único
	code varchar(10), -- código de referencia para los items asociados al alcance, factores o condiciones (solamente esta parametrizado para CONDIT1,FACTOR1,SCOPE1)
	description TEXT -- descripción del item asociado al alcance, factores o condiciones
);
/**
-- DDL tabla para configuración interna del reporte
**/
create table dual(
id serial primary key,
dual varchar(20)
);

-- INSERT tabla de configuracion para los reportes
insert into dual(dual) values('dual');
```

**Importante: 
Los archivos de cargue se encuentran en la carpeta del proyecto (abrir con numbers(mac)  y/o con excel (windows) para luego exportar en csv SEPARADO POR ';' punto y coma):
```bash
cd presentation/src/main/resources/static/csv

```


Los reportes se encuentran en la ruta (Para hacer modificaciones es necesario instalar JaspertStudio):
```bash
cd presentation/src/main/resources/templates

```

## Notas de Lanzamiento

### Versión 0.1.0

