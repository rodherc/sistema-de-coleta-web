/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrativo.Participante.Entity;

/**
 *
 * @author aluno
 */
public class Participante {
    private int codParticipante;
    private String emailParticipante;
    private String telefone;
    private int idade;
    private String sexo;
    private String cep;
    private String cor;
    private String descricaoParticipante;

    public Participante() {
    }

    public int getCodParticipante() {
        return codParticipante;
    }

    public void setCodParticipante(int codParticipante) {
        this.codParticipante = codParticipante;
    }

    public String getEmailParticipante() {
        return emailParticipante;
    }

    public void setEmailParticipante(String emaiParticipante) {
        this.emailParticipante = emaiParticipante;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getDescricaoParticipante() {
        return descricaoParticipante;
    }

    public void setDescricaoParticipante(String descricaoParticipante) {
        this.descricaoParticipante = descricaoParticipante;
    }
    
    
}
