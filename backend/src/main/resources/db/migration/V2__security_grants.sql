-- 1. Grant DML permissions on existing tables (manager and student)
GRANT SELECT, INSERT, UPDATE, DELETE ON manager TO appuser;
GRANT SELECT, INSERT, UPDATE, DELETE ON student TO appuser;

-- 2. Grant USAGE permission on the schema
GRANT USAGE ON SCHEMA public TO appuser;

-- 3. Grant ALL privileges on sequences (for auto-incrementing IDs)
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO appuser;

-- IMPORTANT: You will also need to grant permissions on any *new* tables/sequences
-- that are created *after* this migration, otherwise the appuser won't be able to access them.
-- For new objects, you can use the DEFAULT PRIVILEGES setting (see note below).
