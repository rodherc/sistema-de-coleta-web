/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrativo.Participante.Controller;

import Administrativo.Login.Controller.LoginController;
import Administrativo.Login.Elementos.Componente;
import Administrativo.Login.Elementos.Permissoes;
import Administrativo.Participante.Entity.Participante;
import Administrativo.Participante.Services.ParticipanteService;
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
@WebServlet(name = "CadastroParticipanteController", urlPatterns = {"/cadastroParticipante.do"})
public class CadastroParticipanteController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, Exception {
            Permissoes permissoes = (Permissoes) request.getSession().getAttribute("permissoes");
            
            Participante participante = null;
            
            if (permissoes != null && permissoes.hasComponente(Componente.USUARIO)) {
                if (request.getParameter("emailParticipante") != "") {
                    
                    participante = new Participante();                                                       
                    participante.setEmailParticipante(request.getParameter("emailParticipante"));
                    participante.setTelefone(request.getParameter("telefone"));
                    participante.setIdade(Integer.parseInt(request.getParameter("idade")));
                    participante.setSexo(request.getParameter("sexo"));
                    participante.setCep(request.getParameter("cep"));
                    participante.setCor(request.getParameter("cor"));
                    participante.setDescricaoParticipante(request.getParameter("descricaoParticipante"));

                    ParticipanteService service = new ParticipanteService();
                    try{
                        service.gravarParticipante(participante);
                        System.out.println("---------Gravou Participante-------------");
                    }catch(Exception ex){
                        request.setAttribute("erro", ex);
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
                        dispatcher.forward(request, response);
                    }
                      RequestDispatcher dispatcher = request.getRequestDispatcher("responder_perguntas.jsp");
                      dispatcher.forward(request, response);
                }
            else{
                System.out.println("-----------Participante invalido-----------");
                request.setAttribute("erro", new Exception("Participante Inválido"));
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
            Logger.getLogger(CadastroParticipanteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CadastroParticipanteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
