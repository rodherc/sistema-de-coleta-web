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
public enum Tipo {

    ORDINAL, CONTINUO;

    public static Tipo getTipo(int tipo) {
        if (tipo == 0) {
            return ORDINAL;
        } else {
            return CONTINUO;
        }
    }

    public int getValue() {
        if (ORDINAL.equals(this)) {
            return 0;
        } else {
            return 1;
        }
    }
    
    public String getDescricao() {
        if (ORDINAL.equals(this)) {
            return "Ordinal";
        } else {
            return "Continuo";
        }
    }

}
