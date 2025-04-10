/**
-- DDL tabla relacionada con la diapositiva de resumen ejecutivo
-- CREATE TABLE squat_profile
*/
create table squat_profile(
	id SERIAL primary key, // identificador único
	squat varchar(20), // nombre del squat
	profile varchar(50), // nombre del perfil
	num_profile integer, // cantidad de perfiles
	name_contact varchar(50), // nombres de los propuestos
	full_time varchar(10), // dedicación de los propuestos
	location varchar(20), // ubicación de trabajo
	duration varchar(30), // duración del proyecto
	company varchar(30), // empresa a la que va dirigida la propuesta
	team_assign varchar(30), // nombre del equipo asignado
	total_investment numeric(10,2), // inversión total, pero no es necesario su valor, se calcula en otra tabla
	type_amount varchar(5), // tipo de moneda, por su hay una variación de país
	destination_q varchar (30) // se refiere al Q para la cual se propone el squat
);

/**
-- DDL tabla para la diapositiva de precios y valor de impresión
-- CREATE TABLE price_total_day
*/
create table price_total_day(
	id serial primary key, // identificador único
	month_price varchar(30), // mes de la cotización
	name_contact varchar(50), // nombres de los propuestos
	profile varchar(30), // nombre del perfil de los propuestos
	day_price numeric (10,2), // precio por día
	num_days integer, // cantidad de días laborables al mes
	q_year varchar(20) // Q al que va dirigido el precio
);
/**
-- DDL tabla para la diapositiva alcance y objetivos, también para diapositiva factores
-- diferenciadores y la diapositiva de otras condiciones
-- CREATE TABLE scope_objective
*/
CREATE TABLE scope_objective (
	id SERIAL PRIMARY KEY, // identificador único
	project_scope TEXT, // define el encabezado para el alcance del proyecto, los factores diferenciadores y/o otras condiciones
	proffesion VARCHAR(50), // se usa para diferenciar si es alcance, factores o condiciones
	q_time VARCHAR(20), // Q al que va dirigido el proyecto
	items_code varchar(10) // Parte importante ** este campo es el código de referencia para los items asociados al alcance, factores o condiciones
);
/**
-- DDL tabla que referencia los items asociados al alcance, factores o condiciones
-- CREATE TABLE items
*/
CREATE TABLE items (
	id SERIAL PRIMARY KEY, // identificador único
	code varchar(10), // código de referencia para los items asociados al alcance, factores o condiciones (solamente esta parametrizado para CONDIT1,FACTOR1,SCOPE1)
	description TEXT // descripción del item asociado al alcance, factores o condiciones
);
/**
-- DDL tabla para configuración interna del reporte
**/
create table dual(
id serial primary key,
dual varchar(20)
)