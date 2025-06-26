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
    
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    valor_bolsa DECIMAL(10,2) DEFAULT 0.00,
    cargo VARCHAR(50) NOT NULL,
    data_nascimento DATE NOT NULL,
    valor_bolsa DOUBLE NOT NULL,
    escala VARCHAR(50),
    horario VARCHAR(50),
    matricula VARCHAR(15)
);
CREATE TABLE materiais (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    tipo ENUM('Campo', 'Laboratório') NOT NULL
);
CREATE TABLE retiradas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    matricula VARCHAR(20) NOT NULL,
    material_id INT NOT NULL,
    data_retirada DATE NOT NULL,
    FOREIGN KEY (material_id) REFERENCES materiais(id)
);
CREATE TABLE devolucoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    material_id INT NOT NULL,
    data_devolucao DATE NOT NULL,
    FOREIGN KEY (material_id) REFERENCES materiais(id)
);

CREATE TABLE movimentacoes_materiais (
    id_movimentacao INT AUTO_INCREMENT PRIMARY KEY,
    id_material INT NOT NULL,
    id_usuario INT NOT NULL,
    matricula_usuario VARCHAR(20) NOT NULL,
    tipo_movimentacao ENUM('Retirada', 'Devolução') NOT NULL,
    data_movimentacao DATETIME NOT NULL,
    data_prevista_devolucao DATE,
    data_devolucao_efetiva DATE,
    observacoes TEXT,
    FOREIGN KEY (id_material) REFERENCES materiais(id),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

CREATE TABLE amostras (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo ENUM('Capim Tamani', 'Amendoim') NOT NULL,
    peso_natural DECIMAL(10,2) NOT NULL,
    peso_seco DECIMAL(10,2) NOT NULL,
    data_corte DATE NOT NULL,
    data_pesagem DATE NOT NULL,
    parcela INT NOT NULL,
    tratamento VARCHAR(100) NOT NULL,
    peso_total DECIMAL(10,2) NOT NULL
);

