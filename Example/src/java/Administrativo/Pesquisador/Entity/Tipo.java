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
public enum Tipo {
    
    ADMINISTRADOR, PESQUISADOR;
    
    public static Tipo getTipo(int tipo) {
        if (tipo == 0) {
            return ADMINISTRADOR;
        } else {
            return PESQUISADOR;
        }
    }

    public int getValue() {
        if (ADMINISTRADOR.equals(this)) {
            return 0;
        } else {
            return 1;
        }
    }
    
    public String getDescricao() {
        if (ADMINISTRADOR.equals(this)) {
            return "Administrador";
        } else {
            return "Pesquisador";
        }
    }
}
