package Administrativo.Login.Controller;

import Administrativo.Login.Elementos.Componente;
import Administrativo.Pesquisador.Entity.Pesquisador;
import Administrativo.Login.Elementos.Permissoes;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/menu.do"})
public class MenuController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Pesquisador pesquisador = (Pesquisador) request.getSession().getAttribute("login");
        Permissoes permissoes = (Permissoes) request.getSession().getAttribute("permissoes");
        
        if (pesquisador != null && permissoes != null ) {
            int componenteCode = Integer.valueOf(request.getParameter("componente"));
            if (Componente.getComponente(componenteCode).equals(Componente.PESQUISADOR)) {
                request.getSession().setAttribute("moduloAtual",  permissoes.getModuloFromComponente(Componente.PESQUISADOR));
                RequestDispatcher dispatcher = request.getRequestDispatcher("listagemPesquisador.do");
                dispatcher.forward(request, response);
                
            } 
            if (Componente.getComponente(componenteCode).equals(Componente.ATRIBUICAO)) {
                request.getSession().setAttribute("moduloAtual",  permissoes.getModuloFromComponente(Componente.ATRIBUICAO));
                RequestDispatcher dispatcher = request.getRequestDispatcher("listar.do");
                dispatcher.forward(request, response);
                
            }else {
                request.setAttribute("erro", new Exception("Componente Inacessível"));
                RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            request.getSession().setAttribute("moduloAtual",  null);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
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