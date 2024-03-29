/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrativo.Pergunta.Controller;

import Administrativo.Login.Controller.LoginController;
import Administrativo.Login.Elementos.Componente;
import Administrativo.Login.Elementos.Permissoes;
import Administrativo.Pergunta.Entity.Pergunta;
import Administrativo.Pergunta.Entity.Tipo;
import Administrativo.Pergunta.Services.PerguntaService;
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
@WebServlet(name = "CadastroPerguntaController", urlPatterns = {"/cadastroPergunta.do"})
public class CadastroPerguntaController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, Exception {
            Permissoes permissoes = (Permissoes) request.getSession().getAttribute("permissoes");
            
            Pergunta pergunta = null;
            
            if (permissoes != null && permissoes.hasComponente(Componente.USUARIO)) {

                pergunta = new Pergunta();
                pergunta.setDescricaoPergunta(request.getParameter("descricaoPergunta"));
                pergunta.setCodTeste((int)request.getSession().getAttribute("codTeste"));
                pergunta.setExisteDescricao(Boolean.parseBoolean(request.getParameter("existeDescricao")));
                pergunta.setTipo(Tipo.getTipo(Integer.parseInt(request.getParameter("tipo"))));
                PerguntaService service = new PerguntaService();
                try{
                    service.gravarPergunta(pergunta);
                }catch(Exception ex){
                    request.setAttribute("erro", ex);
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
                    dispatcher.forward(request, response);
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("criar_pergunta.jsp");
                dispatcher.forward(request, response);
            }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CadastroPerguntaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CadastroPerguntaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
