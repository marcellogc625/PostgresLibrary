CREATE DATABASE publiclibrary;

CREATE TABLE Authors (
author_id SERIAL PRIMARY KEY,
name character varying(50),
fname character varying(255)
);

CREATE TABLE Publishers(
	publisher_id serial primary key,
	name character varying(30),
	url character varying(80)
);

CREATE TABLE Books(
	title character varying(60),
	isbn character varying(13) primary key,
	publisher_id int,
	price decimal (10,2),
	foreign key (publisher_id) references Publishers (publisher_id)
);

CREATE TABLE BookAuthors(
	isbn character varying(13),
	author_id int,
	seq_no int,
	foreign key (isbn) references Books (isbn),
	foreign key (author_id) references Authors (author_id),
	primary key (isbn, author_id)
);