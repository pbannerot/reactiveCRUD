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

