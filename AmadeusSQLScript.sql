CREATE database Amadeus;

USE Amadeus;

CREATE TABLE Flights (
    id INT AUTO_INCREMENT PRIMARY KEY,
	departure_airport varchar(20) NOT NULL,
    arrival_airport varchar(20) NOT NULL,
    departure_datetime DATETIME NOT NULL,
    return_datetime DATETIME,
    price DECIMAL(10, 2) NOT NULL
);

SELECT * FROM flights;

CREATE TABLE Airports(
	id varchar(20) PRIMARY KEY,
    city varchar(20) NOT NULL
);

SELECT * FROM airports;