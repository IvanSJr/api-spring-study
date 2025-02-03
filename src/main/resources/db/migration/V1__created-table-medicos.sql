CREATE TABLE medicos
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    nome          VARCHAR(100) NOT NULL,
    email         VARCHAR(100) NOT NULL UNIQUE,
    crm           VARCHAR(24) NOT NULL,
    especialidade VARCHAR(10) NOT NULL,
    logradouro    VARCHAR(255) NOT NULL,
    bairro        VARCHAR(255) NOT NULL,
    cidade        VARCHAR(255) NOT NULL,
    complemento   VARCHAR(255) NULL,
    numero        VARCHAR(20) NULL,
    cep           VARCHAR(9) NULL,
    uf            CHAR(2) NOT NULL,
    CONSTRAINT pk_medicos PRIMARY KEY (id)
);