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
    nome_completo VARCHAR(100) NOT NULL,
    valor_bolsa DECIMAL(10,2) DEFAULT 0.00,
    cargo VARCHAR(50) NOT NULL,
    data_nascimento DATE NOT NULL,
    escala_segunda BOOLEAN DEFAULT FALSE,
    escala_terca BOOLEAN DEFAULT FALSE,
    escala_quarta BOOLEAN DEFAULT FALSE,
    escala_quinta BOOLEAN DEFAULT FALSE,
    escala_sexta BOOLEAN DEFAULT FALSE,
    horarios_segunda VARCHAR(50),
    horarios_terca VARCHAR(50),
    horarios_quarta VARCHAR(50),
    horarios_quinta VARCHAR(50),
    horarios_sexta VARCHAR(50),
    data_cadastro DATETIME DEFAULT CURRENT_TIMESTAMP,
    ativo BOOLEAN DEFAULT TRUE
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