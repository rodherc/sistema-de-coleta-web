<%-- 
    Document   : criar_pergunta
    Created on : 11/11/2019, 21:11:51
    Author     : aluno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="head.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
                    <form class="form-horizontal" role="form" action="cadastroPergunta.do" method="POST">
                        
                        <label class="control-label col-sm-2" for="existeDescricao">Inserir descricao:</label>
                        <div class="col-sm-10">
                            <input type="checkbox" name="existeDescricao" checked value="true">
                        </div><br><br>
                        
                        <label class="control-label col-sm-2" for="descricaoPergunta">Descricao:</label>
                        <div class="col-sm-10">
                            <textarea rows="4" cols="50" name="descricaoPergunta" placeholder="Digite a descricao"></textarea>
                        </div><br><br>
                        <!--
                           só ativar o campo "Descrição" se o checkbox estiver ativado
                        -->
                        <label class="control-label col-sm-2" for="tipo">Tipo:</label>
                        <div class="col-sm-10">
                            <input type="radio" name="tipo" value="0">Ordinal<br>
                            <input type="radio" name="tipo" value="1">Continuo<br>
                        </div>
                        <!--
                            só ativar o campo "quantidade de alternativas" se a opção "ordinal for escolhida
                        -->

                        <label class="control-label col-sm-2" for="qtd-alternativas">Alternativas:</label>
                        <div class="col-sm-10">
                            <input type="number" name="qtd-alternativas" value="5" min="5" max="6"><br><br>
                        </div>
                        <label class="control-label col-sm-2" for="qtd-alternativas">Adicionar imagens:</label>
                        <div class="col-sm-10">
                            <br><input type="file" name="img" multiple><br><br>
                        </div>
                         <div class="form-group" >
                            <div class="col-sm-offset-2 col-sm-10">
                                <div class="btn-toolbar">
                                    <button type="submit" class="btn btn-default btn pull-right">Gravar</button>
                                </div>
                            </div>
                        </div>
                    </form><br>
                </div>
            </div>
     </div>
  <!--  <script>function myFunction(){confirm("oi");}</script>
     <button onclick="myFunction()">Concluir</button> 
    -->
    <!--
    <script>

    function doSomething(){
        document.getElementById('id_confrmdiv').style.display="block"; //this is the replace of this line


        document.getElementById('id_truebtn').onclick = function(){
           //do your delete operation
            alert('true');
        };

        document.getElementById('id_falsebtn').onclick = function(){
             alert('false');
           return false;
        };
    }
</script>
<div id="id_confrmdiv">confirmation<br>
    <button id="id_truebtn">Yes</button>
    <button id="id_falsebtn">No</button>
    </div>

<button onclick="doSomething()">Confirmar</button>
-->
    </body>
</html>
<%@include file="tail.jsp" %>
