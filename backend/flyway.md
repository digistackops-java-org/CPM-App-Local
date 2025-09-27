# How Flyway Works
## Why we need flyway
You need Flyway because, in modern software development, databases are just as much a part of your application as your code is. You use Git for code version control; Flyway is version control for your database schema.
## What happen without Flyway Migration
you revert to old, error-prone manual processes, which are incredibly painful as your team and application scale.

<img width="695" height="274" alt="image" src="https://github.com/user-attachments/assets/6f70d569-ed87-4df1-837f-426d48a07ef0" />

### Problem
We Manually create DB and Tables that lead human error like forget to create Tables so that you app is down


Your development database, staging database, and production database gradually get out of sync. A developer might manually run a "CREATE TABLE" command on their local machine, forget to save it, or skip running it on the test server.
so that You deploy new code that relies on a column (V3) that only exists in development. The application starts, hits the staging server, and immediately fails because the column is missing. This is called Schema Drift .

# How we use Flyway

We write our DB Migration code in a particular Naming convention {Naming Convention (The Primary Ordering Key)}
```
V⟨Version⟩__⟨Description⟩.sql
```
<img width="700" height="249" alt="image" src="https://github.com/user-attachments/assets/4e150c0e-89f0-406a-81e2-a5bad140e3e7" />

Example Order:
```
V1__initial_schema.sql

V2__add_foreign_keys.sql

V2_1__add_indexes.sql (Runs after V2, because 2.1>2)

V3__update_data.sql (Runs after V2.1, because 3>2.1)
```
The key is the Version number. Flyway treats it as a series of numeric components (separated by dots or underscores) and orders them numerically.

### What happen backend when we execute the flyway command

When you run the migrate command, Flyway performs the following steps:
```
1. Scan: It scans the filesystem (or classpath) for all available migration scripts (like your V1__... files).

2. Compare: It queries the flyway_schema_history table to see which scripts have already been applied to the database.

3. Filter: It ignores any script whose version is already recorded in the history table.

4. Order & Execute: It takes the remaining pending scripts and sorts them numerically by their version number. It then executes them one-by-one, in a single transaction (for databases that support transactional DDL, like PostgreSQL).

5. Record: After a script successfully runs, Flyway inserts a new row into the flyway_schema_history table, recording the script's version, description, and a checksum to ensure the script's contents are never changed later.
```
### Problem HERE
As on now we see ontime Run .sql Scripts => so that flayway can run that scripts in orderwise


Sometime we run the Repetable .sql Scripts => How flyway handle that repeatable .sql scripts

#### Solution
Flyway has a second type of script called a "Repeatable Migration", which is used for objects that might need to be re-run and updated frequently, like views, stored procedures, or functions.

Standard Versioned Migrations (V) ===>  are run once and only once. They are ideal for destructive or additive operations like creating tables (CREATE TABLE) or adding columns (ALTER TABLE).
Repeatable Migrations (R) ==> are re-run every time their checksum changes.

They are ideal for operations that need to be re-applied whenever their definition changes, such as:
```
Creating or modifying stored procedures, functions, and views.

Inserting lookup data that should always match a definition file.

Managing application-level permissions (like the GRANT statements we discussed earlier).
```
<img width="695" height="148" alt="image" src="https://github.com/user-attachments/assets/e967428c-181c-4742-9cd4-a16a14537aae" />

```
Naming: They start with the prefix R (e.g., R__refresh_all_views.sql).

Execution: Repeatable migrations are always executed after all pending Versioned Migrations have run.

Re-run Logic: Flyway will re-run a repeatable script if its checksum (the hash of its content) has changed since the last execution. If the script is run, Flyway updates the checksum in the schema history table.
```
This ensures that schema-changing scripts (V) run first, followed by the logical objects (R) that might depend on the new schema structure.

### Real-Time scenario for Repeatable Migrations scripts

#### Scenario
```
Imagine you have a reporting application that pulls data from multiple tables (student, manager, and a course_enrollment table). To simplify reporting queries, you use a complex Database View.
```
### The Problem Without Repeatable Migrations
You create a Versioned Migration (V6__create_reporting_view.sql) to define the view:
```
-- V6__create_reporting_view.sql
CREATE VIEW active_users_report AS 
SELECT s.name, m.email FROM student s JOIN manager m ON ...;
```
Later, a bug is found: the view is slow. You realize you need to add an extra join and a filter to the view definition.

You cannot modify V6! If you change the file, the next time Flyway runs, it will validate the checksum for V6 against the history table, see that it changed, and fail the migration run completely.

our only option is to create a new, empty versioned migration (V7__fix_reporting_view.sql) containing the statement: CREATE OR REPLACE VIEW active_users_report AS ...

This quickly leads to a long, messy chain of corrective migrations (V7, V9, V12) just to manage a single database object (the view).

### TThe Solution With Repeatable Migrations

Instead of a Versioned Migration, you create a Repeatable Migration file: R__active_users_report.sql
```

CREATE OR REPLACE VIEW active_users_report AS
SELECT s.name, m.email
FROM student s
JOIN course_enrollment ce ON s.id = ce.student_id
WHERE ce.status = 'active';
```
You run mvn flyway:migrate. Flyway applies the script and records its checksum in the history table.
The Real-Time Change (Fixing the Bug)
```
1. A few weeks later, you realize you need to add a new column, course_name, to the view.

2. You directly modify the contents of the same file: R__active_users_report.sql

3. You run mvn flyway:migrate again.
```
What flyway do here is
```
1. Flyway compares the new checksum of R__active_users_report.sql to the checksum in the history table.

2. It sees the checksum has changed.

3. It re-runs the entire R__active_users_report.sql script (which contains CREATE OR REPLACE VIEW...).

4. The view is updated in the database, and Flyway updates the history table with the new checksum.
```
