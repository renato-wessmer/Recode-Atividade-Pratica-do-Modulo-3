CREATE SCHEMA AgenciaDeViagensNyssa;
USE AgenciaDeViagensNyssa;

CREATE TABLE IF NOT EXISTS AgenciaDeViagensNyssa.Cliente (
    idCLIENTE INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nomeCLIENTE VARCHAR(50) NOT NULL,
    numeroRG VARCHAR(50) NOT NULL UNIQUE,
    numeroCPF VARCHAR(50) NOT NULL UNIQUE,
    numeroTELEFONE VARCHAR(50) NOT NULL,
    enderecoEMAIL VARCHAR(50) NOT NULL,
    fk_Promocao_idPromocao INT UNSIGNED NOT NULL 
);

CREATE TABLE IF NOT EXISTS AgenciaDeViagensNyssa.Promocao (
    idPromocao INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    dataChegada VARCHAR(100) NOT NULL,
    dataSaida VARCHAR(100) NOT NULL,
    nomeHospedagem VARCHAR(100) NOT NULL,
    tipoDeVoo VARCHAR(100) NOT NULL,
    valor FLOAT NOT NULL,
    quantidadePessoas VARCHAR(100) NOT NULL
);
 
ALTER TABLE Cliente ADD CONSTRAINT FK_Cliente_2
    FOREIGN KEY (fk_Promocao_idPromocao)
    REFERENCES Promocao (idPromocao)
    ON DELETE RESTRICT;
