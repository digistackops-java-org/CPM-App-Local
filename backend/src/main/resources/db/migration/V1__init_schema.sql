-- V1: initial schema for manager and student
CREATE TABLE IF NOT EXISTS manager (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  course VARCHAR(255),
  email VARCHAR(255) UNIQUE
);

CREATE TABLE IF NOT EXISTS student (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  course VARCHAR(255),
  email VARCHAR(255),
  phone VARCHAR(30),
  fee NUMERIC(10,2) DEFAULT 0,
  status VARCHAR(10) CHECK (status IN ('PAID','UNPAID')) DEFAULT 'UNPAID'
);

CREATE INDEX IF NOT EXISTS idx_student_email ON student(email);
CREATE INDEX IF NOT EXISTS idx_manager_email ON manager(email);
