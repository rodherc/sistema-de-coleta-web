<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head lang="en>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">


        <title>Sistema de coleta de dados -UFLA</title>

        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/navbar.css" rel="stylesheet">

        <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
        <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
        <script src="js/ie-emulation-modes-warning.js"></script>

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
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
                    <form class="form-horizontal" role="form" action="cadastroParticipante.do" method="POST">
                        
                        <label class="control-label col-sm-2" for="emailParticipante">Email:</label>
                        <div class="col-sm-10"><br>
                            <input type="email" name="emailParticipante" placeholder="Digite seu email">
                        </div>
                        
                        <label class="control-label col-sm-2" for="telefone">Telefone:</label>
                        <div class="col-sm-10"><br>
                            <input type="text" name="telefone" pattern="[0-9]{11}" placeholder="Digite somente numeros" >
                        </div>
           
                        <label class="control-label col-sm-2" for="idade">Idade:</label>
                        <div class="col-sm-10"><br>
                            <input type="number" name="idade" placeholder="Digite sua idade">
                        </div>

                        <label class="control-label col-sm-2" for="sexo">Sexo:</label>
                        <div class="col-sm-10"><br>
                            <input type="radio" name="sexo" value="M" checked >Masculino<br>
                            <input type="radio" name="sexo" value="F">Feminino<br>
                        </div>
                  
                        <label class="control-label col-sm-2" for="cep">CEP:</label>
                        <div class="col-sm-10"><br>
                            <input type="text" name="cep" pattern="[0-9]{8}" placeholder="Digite somente numeros">
                        </div>
                        
                        <label class="control-label col-sm-2" for="cor">Cor (IBGE):</label>
                        <div class="col-sm-10"><br>
                             <select name="cor">
                                <option value="Branca">Branca</option>
                                <option value="Parda">Parda</option>
                                <option value="Preta">Preta</option>
                                <option value="Amarela">Amarela</option>
                                <option value="Indigena">Indigena</option>
                            </select>                   
                        </div>
                        
                        <label class="control-label col-sm-2" for="descricaoParticipante">Observacao:</label>
                        <div class="col-sm-10"><br>
                            <textarea rows="4" cols="50" name="descricaoParticipante" placeholder="Descreva algum fator pessoal que possa influenciar no resultado do teste como algum tipo de deficiência visual por exemplo"></textarea>
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
<%@include file="tail.jsp" %>
