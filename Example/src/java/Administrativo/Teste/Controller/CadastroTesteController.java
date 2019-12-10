/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrativo.Teste.Controller;

import Administrativo.Login.Controller.LoginController;
import Administrativo.Login.Elementos.Componente;
import Administrativo.Login.Elementos.Permissoes;
import Administrativo.Teste.Entity.Teste;
import Administrativo.Teste.Services.TesteService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aluno
 */
@WebServlet(name = "CadastroTesteController", urlPatterns = {"/cadastroTeste.do"})
public class CadastroTesteController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, Exception {
            Permissoes permissoes = (Permissoes) request.getSession().getAttribute("permissoes");
            
            Teste teste = null;
            
            if (permissoes != null && permissoes.hasComponente(Componente.USUARIO)) {
                if (request.getParameter("nome") != "") {
                    teste = new Teste();
                    teste.setNome(request.getParameter("nome"));
                    teste.setDescricao(request.getParameter("descricao"));
                    teste.setChave(teste.criarChave());
                    

                    TesteService service = new TesteService();
                    try{
                        teste.setId(service.gravarTeste(teste));
                        request.getSession().setAttribute("codTeste",teste.getId());
                    }catch(Exception ex){
                        request.setAttribute("erro", ex);
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
                        dispatcher.forward(request, response);
                    }
                      RequestDispatcher dispatcher = request.getRequestDispatcher("criar_pergunta.jsp");
                      dispatcher.forward(request, response);
                }
            else{
                request.setAttribute("erro", new Exception("Teste Inválido"));
                RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
                dispatcher.forward(request, response);
            }
           
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CadastroTesteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CadastroTesteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
