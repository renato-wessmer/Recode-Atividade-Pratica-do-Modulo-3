CREATE DATABASE IF NOT exists AgenciaDeViagensNysaa;
use AgenciaDeViagensNysaa;

CREATE TABLE destino (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    estado VARCHAR(40),
    data_ida DATE DEFAULT NULL,
    data_volta DATE DEFAULT NULL,
    valor_integral FLOAT NOT NULL,
    fk_cliente_id INT NOT NULL
);

CREATE TABLE hospedagem (
    id INT NOT NULL AUTO_INCREMENT KEY,
    nome_hotel VARCHAR(50) NOT NULL,
    endereco VARCHAR(80) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    valor_integral FLOAT NOT NULL,
    check_in DATE DEFAULT NULL,
    check_out DATE DEFAULT NULL,
    numero_hotel VARCHAR(15) NOT NULL,
    fk_cliente_id INT NOT NULL
);

CREATE TABLE cliente (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cpf VARCHAR(15) NOT NULL UNIQUE,
    nome_completo VARCHAR(80),
    rua VARCHAR(50) NOT NULL,
    bairro VARCHAR(50) NOT NULL,
    cep VARCHAR(10) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    estado VARCHAR(30) NOT NULL,
    email VARCHAR(50) NOT NULL,
    numero VARCHAR(15) NOT NULL
);
 
ALTER TABLE destino ADD CONSTRAINT FK_destino_2
    FOREIGN KEY (fk_cliente_id)
    REFERENCES cliente (id)
    ON DELETE cascade
    ON UPDATE CASCADE;
 
ALTER TABLE hospedagem ADD CONSTRAINT FK_hospedagem_2
    FOREIGN KEY (fk_cliente_id)
    REFERENCES cliente (id)
    ON DELETE cascade
    ON UPDATE CASCADE;