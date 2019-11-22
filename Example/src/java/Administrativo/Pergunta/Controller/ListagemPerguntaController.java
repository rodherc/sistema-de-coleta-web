/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrativo.Pergunta.Controller;

import Administrativo.Login.Controller.LoginController;
import Administrativo.Login.Elementos.Componente;
import Administrativo.Login.Elementos.Permissoes;
import Administrativo.Teste.Entity.Teste;
import Administrativo.Pergunta.Services.PerguntaService;
import Administrativo.Usuario.Entity.Usuario;
import Util.ServiceFactory;
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
import static org.apache.avalon.framework.container.ContainerUtil.service;

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
                String nome = request.getParameter("codPergunta");
                Integer pag = 1;

                if (nome != null) {
                    PerguntaService service = new PerguntaService();
                    List<Teste> testes = service.getListagemTestes(nome, pag);
                    request.setAttribute("teste", testes);
                    Integer qtdPag = service.getQuantidadeTestes(nome);
                    request.setAttribute("qtdPag", qtdPag);
                    request.setAttribute("curPag", pag);
                    request.setAttribute("nome", nome);
                }
                if (request.getParameter("idDelete") != null) {
                    
                    TesteService service = new TesteService();
                    String nomeTeste = service.excluirTeste(Integer.parseInt(request.getParameter("idDelete")));
                    request.setAttribute("operacao", "Usuário " + nomeTeste + " deletado com sucesso.");
                }
                
                RequestDispatcher dispatcher = request.getRequestDispatcher("listagemTestes.jsp");
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