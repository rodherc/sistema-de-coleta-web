/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrativo.Pesquisador.Entity;

/**
 *
 * @author iagho
 */
public class Pesquisador {
    private int codPesquisador;
    private String nome;
    private String email;
    private String senha;
    private Tipo tipo;

    public Pesquisador() {
    }

    public Pesquisador(int codPesquisador, String nome, String email, String senha, Tipo tipo) {
        this.codPesquisador = codPesquisador;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }

    
    public int getCodPesquisador() {
        return codPesquisador;
    }

    public void setCodPesquisador(int codPesquisador) {
        this.codPesquisador = codPesquisador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    
}
