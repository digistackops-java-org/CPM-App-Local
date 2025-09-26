-- 1. Create the database
CREATE DATABASE "cpm_app";

-- 2. Create the low-privilege user for the application to connect as
CREATE USER appuser WITH PASSWORD 'P@55Word';

-- 3. Grant connection permission to the application user
-- This allows 'appuser' to connect to the new database.
GRANT CONNECT ON DATABASE "cpm_app" TO appuser;
