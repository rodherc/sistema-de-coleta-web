ALTER TABLE Respostas 
	add constraint fk_id_usuario foreign key(idUsuario)
			references Participante(idUsuario)
			on delete cascade,
	add constraint fk_cod_teste foreign key(codTeste)
			references Teste(codTeste)
			on delete cascade;

ALTER TABLE Teste
	 add constraint fk_cod_pesquisador foreign key(id)
		references Pesquisador(id)
		on delete restrict;
            
ALTER TABLE Pergunta 
	add constraint fk_codigo_teste foreign key(codTeste)
		references Teste(codTeste)
		on delete cascade;

ALTER TABLE Pergunta_imagem
	add constraint fk_codigo_pergunta foreign key(codPergunta)
		references Pergunta(codPergunta)
		on delete cascade,
	add constraint fk_codig_teste foreign key(codTeste)
		references Teste(codTeste)
		on delete cascade,
	add constraint fk_codigo_imagem foreign key(codImagem)
		references Imagem(codImagem)
		on delete cascade;

