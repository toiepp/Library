DROP TABLE IF EXISTS book_genres CASCADE;
DROP TABLE IF EXISTS books CASCADE;
DROP TABLE IF EXISTS clients CASCADE;
DROP TABLE IF EXISTS libraries CASCADE;
DROP TABLE IF EXISTS genres CASCADE;

CREATE TABLE libraries
(
	id             bigserial PRIMARY KEY,
	phone_number   varchar(20) NOT NULL,
	email          varchar(50) NOT NULL,
	address_street varchar(50) NOT NULL,
	address_house  varchar(10) NOT NULL,
	city           varchar(20) NOT NULL
);

CREATE TABLE clients
(
	id            bigserial PRIMARY KEY,
	first_name    varchar(50)  NOT NULL,
	last_name     varchar(50)  NOT NULL,
	password      varchar(100) NOT NULL,
	date_of_birth DATE         NOT NULL
);

CREATE TABLE genres
(
	id    bigserial PRIMARY KEY,
	genre varchar(50) NOT NULL
);

CREATE TABLE books
(
	id               bigserial PRIMARY KEY,
	title            varchar(50)  NOT NULL,
	author           varchar(50)  NOT NULL,
	description      varchar(500) NOT NULL DEFAULT 'No description',
	edition          int          NOT NULL DEFAULT 1,
	publishing_house varchar(50)  NOT NULL,
-- 	cover_image BOOK'S COVER IMAGE
	library          int REFERENCES libraries ( id ),
	tenant           int REFERENCES clients ( id )
);

CREATE TABLE book_genres
(
	book_id  int REFERENCES books ( id ),
	genre_id int REFERENCES genres ( id )
)