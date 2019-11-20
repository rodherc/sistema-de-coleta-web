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
                //teste.setId(Integer.parseInt(request.getParameter("id")));
                //pergunta.setTipo(Tipo.valueOf(request.getParameter("tipo")));//convertendo string para char
                System.out.println("-----------Criar pergunta-------------");
                pergunta.setDescricaoPergunta(request.getParameter("descricaoPergunta"));
                //pergunta.setCodTeste(request.getParameter("codTeste")); //pegar codTeste
                //pergunta.setExisteDescricao(Boolean.parseBoolean(request.getParameter("existeDescricao")));
                // "true" retorna true, "false" retorna false
                
                PerguntaService service = new PerguntaService();
                try{
                    System.out.println("----------Gravar Pergunta-----------");
                    service.gravarPergunta(pergunta);
                    System.out.println("----------Gravou Pergunta-----------");
                }catch(Exception ex){
                    System.out.println("----------Erro-----------");
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
