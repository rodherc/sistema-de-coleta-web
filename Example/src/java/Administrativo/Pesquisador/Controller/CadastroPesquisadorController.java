package Administrativo.Pesquisador.Controller;

import Administrativo.Login.Controller.LoginController;
import Administrativo.Login.Elementos.Componente;
import Administrativo.Login.Elementos.Permissoes;
import Administrativo.Pesquisador.Entity.Tipo;
import Administrativo.Pesquisador.Entity.Pesquisador;
import Util.MensagemErro;
import Util.ServiceFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CadastroPesquisadorController", urlPatterns = {"/cadastroPesquisador.do"})
public class CadastroPesquisadorController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Permissoes permissoes = (Permissoes) request.getSession().getAttribute("permissoes");

        if (permissoes != null && permissoes.hasComponente(Componente.PESQUISADOR)) {

            Integer id = 0;
            Pesquisador pesquisador = null;

            if (request.getParameter("idLoad") != null) {
                //Carregando
                id = Integer.parseInt(request.getParameter("idLoad"));
                if (id != 0) {
                    try {
                        pesquisador = ServiceFactory.getPesquisadorService().getPesquisador(id);
                        if (pesquisador != null) {
                            request.getSession().setAttribute("pesquisador", pesquisador);
                            RequestDispatcher dispatcher = request.getRequestDispatcher("administrativo/Pesquisador/cadastroPesquisador.jsp");
                            dispatcher.forward(request, response);
                        } else {
                            request.setAttribute("erro", new Exception("Usuário Inválido"));
                            RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
                            dispatcher.forward(request, response);
                        }
                    } catch (Exception ex) {
                        request.setAttribute("erro", ex);
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
                        dispatcher.forward(request, response);
                    }
                } else {
                    //Novo Usu�rio
                    pesquisador = new Pesquisador();
                    pesquisador.setCodPesquisador(id);
                    request.getSession().setAttribute("pesquisador", pesquisador);
                    pesquisador.setTipo(Tipo.PESQUISADOR);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("administrativo/Pesquisador/cadastroPesquisador.jsp");
                    dispatcher.forward(request, response);
                }
            } else if (request.getParameter("id") != null) {
                //Gravando

                pesquisador = new Pesquisador();
                pesquisador.setCodPesquisador(Integer.parseInt(request.getParameter("id")));
                pesquisador.setNome(request.getParameter("nome"));
                pesquisador.setEmail(request.getParameter("email"));
                pesquisador.setTipo(Tipo.getTipo(Integer.parseInt(request.getParameter("tipo"))));

                try {
                    MensagemErro erros = validarPesquisador(pesquisador);
                    if (erros.existemErros()) {
                        request.getSession().setAttribute("pesquisador", pesquisador);
                        request.setAttribute("erros", erros);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("administrativo/Pesquisador/cadastroPesquisador.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        String senha = ServiceFactory.getPesquisadorService().gravarPesquisador(pesquisador);
                        if (senha != null) {
                            ServiceFactory.getEmailService().sendEmailSenha(senha, pesquisador.getEmail());
                            request.setAttribute("operacao", "O usuário " + pesquisador.getNome() + " foi incluído com sucesso e uma senha temporária foi enviada por email.");
                        } else {
                            request.setAttribute("operacao", "Os dados do usuário " + pesquisador.getNome() + " foram alterados com sucesso.");
                        }

                        RequestDispatcher dispatcher = request.getRequestDispatcher("administrativo/Pesquisador/listagemPesquisador.jsp");
                        dispatcher.forward(request, response);
                    }
                } catch (Exception ex) {
                    request.setAttribute("erro", ex);
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
                    dispatcher.forward(request, response);
                }

            } else {
                request.setAttribute("erro", new Exception("Usuário Inválido"));
                RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
                dispatcher.forward(request, response);
            }

        } else if (permissoes != null) {
            request.setAttribute("erro", "Componente Inacessível");
            RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }

    private MensagemErro validarPesquisador(Pesquisador pesquisador) throws Exception {

        MensagemErro erros = new MensagemErro();

        if (pesquisador.getNome() == null || pesquisador.getNome().length() < 10) {
            erros.add("O nome do usuário deve conter pelo menos 10 caracteres");
        }

        if (pesquisador.getEmail() == null || pesquisador.getEmail().length() < 10 || pesquisador.getEmail().contains("@") == false) {
            erros.add("O email digitado não é válido");
        } else {
            boolean existeEmail = false;
            if (pesquisador.getCodPesquisador() == 0) {
                existeEmail = ServiceFactory.getPesquisadorService().existeEmail(pesquisador, false);
            } else {
                existeEmail = ServiceFactory.getPesquisadorService().existeEmail(pesquisador, true);
            }

            if (existeEmail) {
                erros.add("Este email já está sendo utilizado");
            }
        }

        return erros;
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
