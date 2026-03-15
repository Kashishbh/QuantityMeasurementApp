CREATE DATABASE IF NOT EXISTS quantitydb;

USE quantitydb;

CREATE TABLE IF NOT EXISTS quantity_measurement (
    id INT AUTO_INCREMENT PRIMARY KEY,
    value1 DOUBLE,
    value2 DOUBLE,
    operation VARCHAR(50),
    result DOUBLE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);