/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrativo.LoginChave.Controller;

import Administrativo.LoginChave.Model.LoginChaveModel;
import Administrativo.Teste.Entity.Teste;
import Util.MensagemErro;
import Util.LoginChave;
import Util.Validation;
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
@WebServlet(name = "LoginChave", urlPatterns = {"/loginChave.do"})
public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        if (Validation.notNullNotBlank(request.getParameter("chave")) && Validation.notNullNotBlank(request.getParameter("chave"))) {

            LoginChaveModel lm = new LoginChaveModel(request.getParameter("chave"));
            Teste teste = null;

            try {
                teste = LoginChave.getLoginChaveService().verificarLogin(lm);

                if (teste != null) {
                    request.getSession().setAttribute("login", teste);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("baseChave.do");
                    dispatcher.forward(request, response);
                } else {
                    MensagemErro erros = new MensagemErro("Chave invalida");
                    request.setAttribute("erros", erros);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("login_pergunta.jsp");
                    dispatcher.forward(request, response);
                }

            } catch (Exception ex) {
                request.setAttribute("erro", ex);
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            MensagemErro erros = new MensagemErro("Digite a Chave");
            request.setAttribute("erros", erros);
            RequestDispatcher dispatcher = request.getRequestDispatcher("login_pergunta.jsp");
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