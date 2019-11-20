<%@include file="../../head.jsp" %>

<div class="panel-group ">
    <div class="panel panel-default ">
        <div class="panel-heading" >
            <h3 class="panel-title">Cadastro de Imagem</h3>
        </div>
        <p></p>
        <p></p>
        <div class="panel-body">
            <form class="form-horizontal" role="form" enctype="multipart/form-data" method="POST" action="cadastroImagem.do">

                <input type="hidden" name="id" value="<c:out value="${imagem.codImagem}"/>"/>

                <c:if test="${erros != null}">

                    <div class="form-group">

                        <div class="col-sm-offset-2 col-sm-10">
                            <div class="alert alert-danger" id="myAlert">
                                <a href="#" class="close">&times;</a>
                                <c:forEach items="${erros.mensagens}" var="item">
                                    <p><c:out value="${item}"/></p>
                                </c:forEach>
                            </div>
                        </div>
                    </div>

                </c:if>

                <div class="form-group ">
                    <label class="control-label col-sm-2" for="nome">Nome:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="nome" id="nome" placeholder="Digite o Nome" value="<c:out value="${imagem.nome}"/>">
                    </div>
                </div>
                    
               <div class="form-group ">
               <label class="control-label col-sm-2" for="nome">Imagem:</label>
                    File to upload: <input type="file" name="upfile"><br/>
               
                </form>
                </div>
                    
                    <div class="form-group" >
                        <div class="col-sm-offset-2 col-sm-10">
                            <div class="btn-toolbar">
                                <button type="submit" class="btn btn-default btn pull-right">Gravar</button>
                                <a href="listagemImagem.do" class="btn btn-default btn pull-right">Cancelar</a>
                            </div>
                        </div>

                    </div>


                </form>
            </div>
        </div>
    </div>

    <script>
        $(document).ready(function () {
            $(".close").click(function () {
                $("#myAlert").alert("close");
            });
        });
    </script>

<%@include file="../../tail.jsp" %>