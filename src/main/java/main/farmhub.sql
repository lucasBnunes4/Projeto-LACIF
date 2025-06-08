CREATE DATABASE farmhub;
USE farmhub;

CREATE TABLE atividades (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    inicio DATE,
    termino DATE,
    participantes TEXT,
    status VARCHAR(50)
    );