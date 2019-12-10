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
                        <div class="col-sm-10" >
                            <input type="checkbox" id="existeDescricao" name="existeDescricao" value="true"  onclick="mostrar()">
                        </div><br><br>
                        
                        <label class="control-label col-sm-2" for="descricaoPergunta">Descricao:</label>
                        <div class="col-sm-10">
                            <textarea rows="4" cols="50" id= "descricaoPergunta" name="descricaoPergunta" placeholder="Digite a descricao"></textarea>
                        </div><br><br>
                        <!--
                           só ativar o campo "Descrição" se o checkbox estiver ativado
                        -->
                        <label class="control-label col-sm-2" for="tipo">Tipo:</label>
                        <div class="col-sm-10">
                            <input type="radio" id="ordinal" name="tipo" value="0" checked onclick="mostrarQTD()">Ordinal<br>
                            <input type="radio" id ="continuo" name="tipo" value="1" onclick="mostrarQTD()" >Continuo<br>
                        </div>
                        <!--
                            só ativar o campo "quantidade de alternativas" se a opção "ordinal for escolhida
                        -->

                        <label class="control-label col-sm-2" for="qtd-alternativas">Alternativas:</label>
                        <div class="col-sm-10">
                            <input type="number" id="qtd-alternativas" name="qtd-alternativas" value="5" min="5" max="6"><br><br>
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
</body>
<script type="text/javascript">
    function mostrar() {
            $("#existeDescricao").click(function () {
                if ($(this).is(":checked")) {
                    $("#descricaoPergunta").show();
                } else {
                    $("#descricaoPergunta").hide();
                }
            });
    };
    function mostrarQTD() {
     if(document.getElementById('ordinal').checked) {
        $("#qtd-alternativas").show();
    }else if(document.getElementById('continuo').checked) {
      $("#qtd-alternativas").hide();
    }
    };
</script>
</html>
<%@include file="tail.jsp" %>
