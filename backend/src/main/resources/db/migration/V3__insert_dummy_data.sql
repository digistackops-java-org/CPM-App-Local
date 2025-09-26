-- V3: Insert initial dummy data for testing and development

--
-- 1. Insert dummy data into the manager table
--
INSERT INTO manager (name, course, email) VALUES
('Venkatesh', 'DevSecOps', 'venkatesh@sapsecops.com'),
('Padol', 'SAP', 'padol@sapsecops.com'),
('Ganesh', 'Bussiness', 'padol@sapsecops.com');

--
-- 2. Insert dummy data into the student table
--
INSERT INTO student (name, course, email, phone, fee, status) VALUES
('Chitu', 'DevSecOps', 'chaitu@gmail.com', '123-456-7890', 55000.00, 'PAID'),
('pandu', 'Business', 'pandu@email.com', '987-654-3210', 0.00, 'UNPAID'),
('Himu', 'sap', 'ghimu@email.com', '555-123-4567', 1200.50, 'PAID'),
('venku', 'DevOps', 'venky@email.com', '333-888-9999', 4000.00, 'UNPAID'),
('vinodh', 'Literature', NULL, NULL, 750.00, 'PAID');
