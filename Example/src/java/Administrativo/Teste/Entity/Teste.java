/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrativo.Teste.Entity;

import java.util.UUID;

/**
 *
 * @author aluno
 */
public class Teste {
    
    private int codTeste;
    private String descricao;
    private int id;
    private String nome;
    private String chave;
    private boolean ativo; 

    public Teste(){};
    
    public Teste(int codTeste, String descricao, int id, String nome, String chave, boolean ativo) {
        this.codTeste = codTeste;
        this.descricao = descricao;
        this.id = id;
        this.nome = nome;
        this.chave = chave;
        this.ativo = ativo;
    }

    public int getCodTeste() {
        return codTeste;
    }

    public void setCodTeste(int codTeste) {
        this.codTeste = codTeste;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }
    
    public String criarChave(){
        String chave = UUID.randomUUID().toString();
        chave = chave.substring(0, chave.indexOf("-"));
        return chave;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
