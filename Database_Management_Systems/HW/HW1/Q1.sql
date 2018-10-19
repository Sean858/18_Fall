CREATE TABLE IF NOT EXISTS city (
	id int NOT NULL,
    city varchar(255),
    state varchar(255),
    country varchar(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS planes (
	plane_number int NOT NULL,
    model varchar(255),
    capacity int,
    create_year int,
    PRIMARY KEY (plane_number)
);

CREATE TABLE IF NOT EXISTS pilot (
	ssn int NOT NULL,
    home_city int,
    fullname varchar(255),
    day_of_birth int,
    month_of_birth int,
    year_of_birth int,
    salary int,
    PRIMARY KEY (ssn),
    FOREIGN KEY (home_city) REFERENCES city(id)
);

CREATE TABLE IF NOT EXISTS flight (
	time_takeoff int,
    time_landing int,
    flight_number int NOT NULL,
    captain_ssn int,
    plane_number int,
    takeoff_city int,
    landing_city int,
    PRIMARY KEY (flight_number),
    FOREIGN KEY (captain_ssn) REFERENCES pilot(ssn),
    FOREIGN KEY (plane_number) REFERENCES planes(plane_number),
    FOREIGN KEY (takeoff_city) REFERENCES city(id),
    FOREIGN KEY (landing_city) REFERENCES city(id)
);






