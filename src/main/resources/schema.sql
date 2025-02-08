CREATE TABLE IF NOT EXISTS locations (
    id UUID NOT NULL DEFAULT random_uuid() PRIMARY KEY,
    city VARCHAR(255) NOT NULL,
    country VARCHAR(2) NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
    id UUID NOT NULL DEFAULT random_uuid() PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    location_id UUID,
    FOREIGN KEY (location_id) REFERENCES locations(id)
);

INSERT INTO locations (city, country) VALUES ('Paris', 'FR');
INSERT INTO locations (city, country) VALUES ('London', 'UK');

--INSERT INTO users (first_name, last_name, location_id) VALUES ('John', 'Doe', 1);
--INSERT INTO users (first_name, last_name, location_id) VALUES ('Jane', 'Smith', 2);

INSERT INTO users (first_name, last_name, location_id)
VALUES ('John', 'Doe', (SELECT id FROM locations WHERE city = 'Paris'));

INSERT INTO users (first_name, last_name, location_id)
VALUES ('Jane', 'Smith', (SELECT id FROM locations WHERE city = 'London'));

