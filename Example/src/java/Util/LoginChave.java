/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Administrativo.LoginChave.Services.LoginChaveService;

/**
 *
 * @author aluno
 */
public class LoginChave {
    private static LoginChaveService loginChaveService;

    public static LoginChaveService getLoginChaveService() {
         if ( loginChaveService == null ) {
            loginChaveService = new LoginChaveService();
        }
        
        return loginChaveService;
    }
    
    
}
