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
import java.util.List;
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
@WebServlet(name = "PerguntaController", urlPatterns = {"/listagemPergunta.do"})
public class ListagemPerguntaController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Permissoes permissoes = (Permissoes) request.getSession().getAttribute("permissoes");

        if (permissoes != null && permissoes.hasComponente(Componente.USUARIO)) {
            
            try{
                int codTeste = Integer.parseInt(request.getParameter("idVisu"));
                Integer pag = 1;
                //codTeste = 1;
                
                if (codTeste != 0) {
                    PerguntaService service = new PerguntaService();
                    System.out.println(codTeste);
                    List<Pergunta> perguntas = service.getListagemPerguntas(codTeste, pag);
                    request.setAttribute("pergunta", perguntas);
                    Integer qtdPag = service.getQuantidadePerguntas(codTeste);
                    request.setAttribute("qtdPag", qtdPag);
                    request.setAttribute("curPag", pag);
                    request.setAttribute("codTeste", codTeste);
                }

                RequestDispatcher dispatcher = request.getRequestDispatcher("listagemPerguntas.jsp");
                dispatcher.forward(request, response);
                
            }catch(Exception ex){
                request.setAttribute("erro", ex);
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
                dispatcher.forward(request, response);
            }
}
    }
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
 }