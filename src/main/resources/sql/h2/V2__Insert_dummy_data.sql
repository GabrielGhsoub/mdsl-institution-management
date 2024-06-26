-- Insert dummy data into the INSTITUTION table
MERGE INTO INSTITUTION KEY(institution_id)
VALUES 
(1, '12345', 'Acme University', 1),
(2, '67890', 'Global Tech', 1),
(3, '54321', 'Innovate Academy', 0);

-- Insert dummy data into the app_user table
MERGE INTO app_user KEY(user_id)
VALUES 
(1, 'admin', '$2a$12$pTFshS5ZASotbTEX0eM18OsMAv15D45qIuTcYcxviP6/zYEMiDrw.', TRUE),  -- Note: Password is password123
(2, 'user', '$2a$12$pTFshS5ZASotbTEX0eM18OsMAv15D45qIuTcYcxviP6/zYEMiDrw.', TRUE); -- Note: Password is password123
