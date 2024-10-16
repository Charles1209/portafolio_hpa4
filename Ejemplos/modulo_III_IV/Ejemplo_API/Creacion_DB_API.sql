-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS prueba_api_conversiones;

-- Usar la base de datos creada
USE prueba_api_conversiones;

-- Crear la tabla con las especificaciones requeridas
CREATE TABLE conversion_temperatura (
    id INT AUTO_INCREMENT PRIMARY KEY, -- Llave primaria autoincremental
    resultado DECIMAL(10, 2) NOT NULL, -- Columna numérica con 2 decimales, sin permitir valores nulos
    tipo VARCHAR(50) NOT NULL, -- Columna de texto que no acepta nulos
    registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Columna de fecha con la fecha de inserción
);
