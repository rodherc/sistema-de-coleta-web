CREATE SCHEMA Trabalho;

use Trabalho;

CREATE TABLE Participante (
    idUsuario int not null auto_increment,
    emailParticipante varchar(45) not null,
    telefone varchar(11) not null,
    idade int not null,
    sexo char not null,
    cep varchar(8) not null,
    cor varchar(15) not null,
    descricaoParticipante varchar(200),
    
    
    constraint pk_id_usuario primary key(idUsuario)
);

CREATE TABLE Teste (
	codTeste int not null auto_increment,
    descricao varchar(500),
    codPesquisador int not null,
    
    constraint pḱ_cod_teste primary key(codTeste)
);

CREATE TABLE Respostas (
    idUsuario int not null,
	codTeste int not null,
    resposta varchar(50),
    
	constraint pḱ_respostas primary key(idUsuario, codTeste)
);

CREATE TABLE Pesquisador(
	codPesquisador int not null auto_increment,
	email varchar(45) not null,
    senha varchar(45) not null,
    tipo int(20) unsigned DEFAULT NULL,
	nome varchar(50) NOT NULL,
    
	constraint pk_cod_pesquisador primary key(codPesquisador),
	constraint uk_senha_pesquisador unique(senha)
);

CREATE TABLE Pergunta(
	codPergunta int not null,
    tipo char not null,
    descricaoPergunta varchar(200) not null,
    codTeste int not null ,
    
	constraint pḱ_cod_pergunta primary key(codPergunta, codTeste)
);

CREATE TABLE Imagem(
	codImagem int not null,
    nome varchar(45) not null,
    img longblob,
    
	constraint pḱ_cod_imagem primary key(codImagem)
);

CREATE TABLE Pergunta_imagem(
	codPergunta int not null,
    codTeste int not null,
    codImagem int not null,
    
    constraint pḱ_pergunta_imagem primary key(codPergunta, codTeste,codImagem)
);

