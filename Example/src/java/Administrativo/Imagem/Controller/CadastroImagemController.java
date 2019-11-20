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

//<img src="imagens/ufla.jpeg" alt="some text" width=100 height=60>

    
@WebServlet(name = "CadastroImagemController", urlPatterns = {"/cadastroImagem.do"})
public class CadastroImagemController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, Exception {
            Permissoes permissoes = (Permissoes) request.getSession().getAttribute("permissoes");
            
            Imagem imagem = null;
            
            if (permissoes != null && permissoes.hasComponente(Componente.USUARIO)) {
                if (request.getParameter("nome") != "") {
                    imagem = new Imagem();
    //                Usuario usuario = null;
                 //  usuario = ServiceFactory.getUsuarioService().getUsuario(id);
                    //teste.setId(Integer.parseInt(request.getParameter("id")));
                    imagem.setNome(request.getParameter("nome"));
                    imagem.setLocalizacao(request.getParameter("localizacao"));


                    ImagemService service = new ImagemService();
                    try{
                        service.gravarImagem(imagem);

                    }catch(Exception ex){
                        request.setAttribute("erro", ex);
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
                        dispatcher.forward(request, response);
                    }
                      RequestDispatcher dispatcher = request.getRequestDispatcher("listagemImagens.jsp");
                      dispatcher.forward(request, response);
                }
            else{
           
                request.setAttribute("erro", new Exception("Imagem Inválida"));
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
            Logger.getLogger(CadastroImagemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CadastroImagemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    }

