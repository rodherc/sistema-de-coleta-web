<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">


        <title>Sistema de coleta de dados -UFLA</title>

        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/signin.css" rel="stylesheet">

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

        <div class="container">

            <form class="form-signin" action="loginChave.do" method="POST" >

                <div class="form-group">

                    <!---->
                    <h2 align="center" class="form-signin-heading">Responder Teste</h2>


                    <label for="inputChave" class="sr-only">Chave</label>
                    <input type="text" id="chave" name="chave" class="form-control" placeholder="Digite a chave"  autofocus>
                    
                    <!--<div class="checkbox">
                        <label>
                            <input type="checkbox" value="remember-me"> Lembrar
                        </label>
                    </div>-->

                    <c:if test="${erros != null}">
                        <div class="col-sm-offset-0 col-sm-20">
                            <div class="alert alert-danger" id="myAlert">
                                <a href="#" class="close">&times;</a>
                                <c:forEach items="${erros.mensagens}" var="item">
                                    <p><c:out value="${item}"/></p>
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                    <br>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Validar</button>

                </div>


            </form>

        </div> <!-- /container -->




        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="js/vendor/jquery.min.js"><\/script>')</script>
        <script src="js/bootstrap.min.js"></script>
        <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>

        
    </body>
</html>
