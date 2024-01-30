DROP DATABASE IF exists esquemaCreado;

CREATE DATABASE esquemaCreado;

USE esquemaCreado;

CREATE TABLE author (
                        id INT NOT NULL AUTO_INCREMENT,
                        name VARCHAR(255) NOT NULL,
                        PRIMARY KEY (id)
);

CREATE TABLE book (
                      id INT NOT NULL AUTO_INCREMENT,
                      title VARCHAR(255) NOT NULL,
                      author_id INT NOT NULL,
                      PRIMARY KEY (id)
);

INSERT INTO author (name) VALUES ('author1');
INSERT INTO author (name) VALUES ('author2');

INSERT INTO book (title, author_id) VALUES ('book1', 1);
INSERT INTO book (title, author_id) VALUES ('book2', 1);

DELETE FROM author WHERE id = 1;
