-- Create INSTITUTION table
CREATE TABLE IF NOT EXISTS INSTITUTION (
    institution_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    institution_code VARCHAR(5) NOT NULL,
    institution_name VARCHAR(50) NOT NULL,
    institution_status TINYINT NOT NULL CHECK (institution_status IN (0, 1))
);

-- Create app_user table
CREATE TABLE IF NOT EXISTS app_user (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL
);

-- Create REFRESH_TOKEN table
CREATE TABLE IF NOT EXISTS refresh_token (
    refresh_token_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    token VARCHAR(255) NOT NULL,
    expiry_date TIMESTAMP NOT NULL,
    CONSTRAINT fk_refresh_token_user FOREIGN KEY (user_id) REFERENCES app_user(user_id)
);


