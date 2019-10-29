package Util;

import Administrativo.Login.Services.LoginService;
import Administrativo.Login.Services.SegurancaService;
import Administrativo.Pesquisador.Services.PesquisadorService;

public class ServiceFactory {
    
    private static LoginService loginService;
    private static PesquisadorService pesquisadorService;
    private static SegurancaService segurancaService;
    private static EmailService emailService;
    
    public static LoginService getLoginService() {
        
        if ( loginService == null ) {
            loginService = new LoginService();
        }
        
        return loginService;
    }
    
    public static PesquisadorService getPesquisadorService() {
        
        if ( pesquisadorService == null ) {
            pesquisadorService = new PesquisadorService();
        }
        
        return pesquisadorService;
    }
    
    public static SegurancaService getSegurancaService() {
        
        if ( segurancaService == null ) {
            segurancaService = new SegurancaService();
        }
        
        return segurancaService;
    }
    
    public static EmailService getEmailService() {
        
        if ( emailService == null ) {
            emailService = new EmailService();
        }
        
        return emailService;
    }
}
