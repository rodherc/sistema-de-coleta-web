/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrativo.Imagem.Controller;

import Administrativo.Imagem.Entity.Imagem;
import Administrativo.Imagem.Services.ImagemService;
import Administrativo.Login.Controller.LoginController;
import Administrativo.Login.Elementos.Componente;
import Administrativo.Login.Elementos.Permissoes;
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
  
@WebServlet(name = "ImagemController", urlPatterns = {"/listagemImagem.do"})
public class ListagemImagemController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Permissoes permissoes = (Permissoes) request.getSession().getAttribute("permissoes");

        if (permissoes != null && permissoes.hasComponente(Componente.USUARIO)) {
            
            try{
                String nome = request.getParameter("nome");
                Integer pag = 1;

                if (nome != null) {
                    ImagemService service = new ImagemService();
                    List<Imagem> imagens = service.getListagemImagens(nome, pag);
                    request.setAttribute("imagem", imagens);
                    Integer qtdPag = service.getQuantidadeImagens(nome);
                    request.setAttribute("qtdPag", qtdPag);
                    request.setAttribute("curPag", pag);
                    request.setAttribute("nome", nome);
                }
                if (request.getParameter("idDelete") != null) {
                    
                    ImagemService service = new ImagemService();
                    String nomeImagem = service.excluirImagem(Integer.parseInt(request.getParameter("idDelete")));
                    request.setAttribute("operacao", "Imagem " + nomeImagem + " deletado com sucesso.");
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("listagemImagens.jsp");
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