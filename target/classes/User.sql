DROP TABLE IF EXISTS users;
CREATE TABLE users (
        id INTEGER NOT NULL AUTO_INCREMENT,
        LastName varchar(255) NOT NULL,
        FirstName varchar(255) NOT NULL,
        Email varchar(255) NOT NULL,
        Age int NOT NULL,
        PRIMARY KEY (id)
)