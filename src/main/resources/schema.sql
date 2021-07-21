DROP TABLE IF EXISTS regions;
CREATE TABLE regions (
    id int auto_increment primary key,
    name VARCHAR(100) NOT NULL,
    country_code VARCHAR(2)
);

DROP TABLE IF EXISTS restaurants;
CREATE TABLE restaurants (
    id int auto_increment primary key,
    region_id int,
    name VARCHAR(100) NOT NULL
);