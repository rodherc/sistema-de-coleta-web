<%-- 
    Document   : criar_teste
    Created on : 11/11/2019, 20:58:41
    Author     : aluno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="head.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CadastrarTeste</title>
    </head>
    <body>
        <div class="panel-group ">
            <div class="panel panel-default ">
                <div class="panel-heading" >
                    <h3 class="panel-title">Cadastro de Testes</h3>
                </div>
                <p></p>
                <p></p>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="cadastroTeste.do" method="POST">
                        <!--id:<br>
                        <input type="text" name="id" placeholder="Digite o id do teste">
                        <br>-->
                       <label class="control-label col-sm-2" for="nome">Nome:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="nome" id="nome" placeholder="Digite o Nome">
                        </div>
                       <label class="control-label col-sm-2" for="descricao">Descricao:</label>
                        <div class="col-sm-10">
                            <textarea rows="4" cols="50" name="descricao" placeholder="Digite a descricao"></textarea>
                        </div>
                        <br>
                        <div class="form-group" >
                            <div class="col-sm-offset-2 col-sm-10">
                                <div class="btn-toolbar">
                                    <button type="submit" class="btn btn-default btn pull-right">Gravar</button>
                                    <a href="listagemTeste.do" class="btn btn-default btn pull-right">Cancelar</a>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
<%@include file="tail.jsp" %>
