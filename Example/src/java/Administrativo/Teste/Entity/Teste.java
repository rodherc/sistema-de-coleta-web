/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrativo.Teste.Entity;

/**
 *
 * @author iagho
 */
public class Teste {
    private int codTeste;
    private String descrição;
    private int codPesquisador;

    public Teste(int codTeste, String descrição, int codPesquisador) {
        this.codTeste = codTeste;
        this.descrição = descrição;
        this.codPesquisador = codPesquisador;
    }

    public int getCodTeste() {
        return codTeste;
    }

    public void setCodTeste(int codTeste) {
        this.codTeste = codTeste;
    }

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    public int getCodPesquisador() {
        return codPesquisador;
    }

    public void setCodPesquisador(int codPesquisador) {
        this.codPesquisador = codPesquisador;
    }
    
}
