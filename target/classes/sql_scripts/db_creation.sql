DROP TABLE IF EXISTS book_to_category CASCADE;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS book_categories;

CREATE TABLE clients
(
	id         bigserial PRIMARY KEY,
	first_name varchar(50) NOT NULL,
	surname    varchar(50) NOT NULL,
	age        int         NOT NULL
);

CREATE TABLE books
(
	id               bigserial PRIMARY KEY,
	title            varchar(50) NOT NULL,
	author           varchar(50) NOT NULL,
	publication_date DATE        NOT NULL
		CONSTRAINT max_publication_date CHECK ( publication_date < NOW() ),
	publication      int         NOT NULL DEFAULT 1
		CONSTRAINT positive_publications_only CHECK ( publication >= 1 ),
	age_restriction  int         NOT NULL DEFAULT 0
		CONSTRAINT min_age_restriction CHECK ( books.age_restriction >= 0 ),
	client_id        int REFERENCES clients ( id )
);

CREATE TABLE book_categories
(
	id   bigserial PRIMARY KEY,
	name varchar(20) NOT NULL
);

CREATE TABLE book_to_category
(
	book_id     int REFERENCES books ( id ),
	category_id int REFERENCES book_categories ( id )
);
