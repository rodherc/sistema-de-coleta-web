/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrativo.Login.Elementos;

/**
 *
 * @author raphaelwb
 */
public enum Componente {
    
    PESQUISADOR, WEB_LISTA_PESQUISADORES,ATRIBUICAO;
    
    public int getId() {
        if ( this.equals(PESQUISADOR) )
            return 1;
        if ( this.equals(WEB_LISTA_PESQUISADORES) )
            return 2;
        if ( this.equals(ATRIBUICAO) )
            return 3;
        return 0;
    }
    
    public static Componente getComponente(int code) {
        if ( code == 1 )
            return PESQUISADOR;
        if ( code == 2 )
            return WEB_LISTA_PESQUISADORES;
        if ( code == 3 )
            return ATRIBUICAO;
        return null;
    }
}