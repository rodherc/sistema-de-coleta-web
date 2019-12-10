/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrativo.Pergunta.Entity;

/**
 *
 * @author aluno
 */
public class Pergunta {
    private int codPergunta;
    private Tipo tipo;
    private String descricaoPergunta;
    private int codTeste;
    private boolean existeDescricao;
    private String[] imagens;

    public Pergunta(){}

    public Pergunta(int codPergunta, Tipo tipo, String descricaoPergunta, int codTeste, boolean existeDescricao,String[] imagens) {
        this.codPergunta = codPergunta;
        this.tipo = tipo;
        this.descricaoPergunta = descricaoPergunta;
        this.codTeste = codTeste;
        this.existeDescricao = existeDescricao;
        this.imagens = imagens;
    }

    public int getCodPergunta() {
        return codPergunta;
    }

    public void setCodPergunta(int codPergunta) {
        this.codPergunta = codPergunta;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getDescricaoPergunta() {
        return descricaoPergunta;
    }

    public void setDescricaoPergunta(String descricaoPergunta) {
        this.descricaoPergunta = descricaoPergunta;
    }

    public int getCodTeste() {
        return codTeste;
    }

    public void setCodTeste(int codTeste) {
        this.codTeste = codTeste;
    }

    public boolean getExisteDescricao() {
        return existeDescricao;
    }

    public void setExisteDescricao(boolean existeDescricao) {
        this.existeDescricao = existeDescricao;
    }

    public String[] getImagens() {
        return imagens;
    }

    public void setImagens(String[] imagens) {
        this.imagens = imagens;
    }
    
    
}
