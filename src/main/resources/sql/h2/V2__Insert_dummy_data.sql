-- Insert dummy data into the INSTITUTION table
INSERT INTO INSTITUTION (institution_code, institution_name, institution_status)
VALUES 
('12345', 'Acme University', 1),
('67890', 'Global Tech', 1),
('54321', 'Innovate Academy', 0)
ON DUPLICATE KEY UPDATE 
institution_code = VALUES(institution_code),
institution_name = VALUES(institution_name),
institution_status = VALUES(institution_status);

-- Insert dummy data into the app_user table with aliasing for ON DUPLICATE KEY UPDATE
INSERT INTO app_user (username, password, enabled) VALUES 
('admin', '$2a$12$pTFshS5ZASotbTEX0eM18OsMAv15D45qIuTcYcxviP6/zYEMiDrw.', TRUE),
('user', '$2a$12$pTFshS5ZASotbTEX0eM18OsMAv15D45qIuTcYcxviP6/zYEMiDrw.', TRUE)
AS new_values(username, password, enabled)
ON DUPLICATE KEY UPDATE 
username = new_values.username,
password = new_values.password,
enabled = new_values.enabled;
