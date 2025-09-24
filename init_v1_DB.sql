CREATE DATABASE CPM_app;

CREATE TABLE Manager (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    course VARCHAR(100),
    email VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE Student (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    course VARCHAR(100),
    phone VARCHAR(15),
    fee NUMERIC(10,2),
    status VARCHAR(10) CHECK (status IN ('PAID','UNPAID'))
);
