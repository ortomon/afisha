-- 1.1. Создать скрипт генерирующий схему application
create schema application;

-- 1.2. Создать скрипт генерирующий сущность тип события (event_type): id, name. 
create table application.event_type (id serial primary key, name varchar(100));

-- 1.3. Создать скрипт добавляющий три типа событий в таблицу: museum, cinema, theather. (3 записи)
insert into application.event_type (name) values ('museum'), ('cinema'), ('theather');

-- 1.4. Создать скрипт генерирующий сущность место проведения (place): id, name, address, city
create table application.place (id serial primary key, name varchar(100), address varchar(200), city varchar(100));

-- 1.5. Создать скрипт генерирующий сущность событие (event): id, name, event_type_id, event_date (дата + время), place_id.
create table application.event (
	id serial primary key, 
	name varchar(100), 
	event_type_id int REFERENCES application.event_type(id), 
	event_date timestamp,
	place_id int REFERENCES application.place(id));

-- 1.7. Создать скрипт герерирующий сущность билет (ticket): id, event_id, client_email, price, is_selled (продан)
create table application.ticket (
	id serial primary key, 
	event_id int REFERENCES application.event(id), 
	client_email varchar(100),
	price numeric(9,2),
	is_selled boolean);