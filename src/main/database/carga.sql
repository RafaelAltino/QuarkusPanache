USE db;

CREATE TABLE USERS(
	id bigint NOT NULL AUTO_INCREMENT,
	name varchar(255)NOT NULL,
	age INTEGER NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE POSTS(
	id bigint NOT NULL AUTO_INCREMENT,
	post_text text NOT NULL, 
	dateTime timestamp NOT NULL,
	user_id bigint NOT NULL REFERENCES USERS(id),
	PRIMARY KEY(id)
);