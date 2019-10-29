ALTER TABLE Respostas 
	add constraint fk_id_usuario foreign key(idUsuario)
			references Participante(idUsuario)
			on delete restrict,
	add constraint fk_cod_teste foreign key(codTeste)
			references Teste(codTeste)
			on delete restrict;

ALTER TABLE Teste
	add constraint fk_cod_pesquisador foreign key(codPesquisador)
			references Pesquisador(codPesquisador)
			on delete restrict;
            
ALTER TABLE Pergunta 
	add constraint fk_codigo_teste foreign key(codTeste)
		references Teste(codTeste)
		on delete restrict;

ALTER TABLE Pergunta_imagem
	add constraint fk_codigo_pergunta foreign key(codPergunta)
		references Pergunta(codPergunta)
		on delete restrict,
	add constraint fk_codig_teste foreign key(codTeste)
		references Teste(codTeste)
		on delete restrict,
	add constraint fk_codigo_imagem foreign key(codImagem)
		references Imagem(codImagem)
		on delete restrict;
