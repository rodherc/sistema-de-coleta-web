<%-- 
    Document   : listagemTestes
    Created on : 18/11/2019, 14:31:38
    Author     : aluno
--%>
<%@include file="../../head.jsp" %>


<!-- MODAL -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="modal-title" id="gridSystemModalLabel">T�tulo</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div id="modal-text" class="col-md-10">Mensagem</div>
                </div>
            </div>
            <div class="modal-footer" id="modal-buttons">

            </div>
        </div>
    </div>
</div>
<!-- END MODAL -->

<div class="panel-group ">
    <div class="panel panel-default ">
        <div class="panel-heading" >
            <h3 class="panel-title">Listagem de Testes</h3>
        </div>
        <p></p>
        <p></p>
        <div class="panel-body">
            <!-- Main component for a primary marketing message or call to action -->



            <div class="row" >

                <form class="navbar-form navbar-left" role="search" method="POST" action="listagemTeste.do">
                    <div class="form-group">
                        <input type="text" name="nome" class="form-control" placeholder="Digite um nome"> 
                    </div>
                    <button type="submit" class="btn btn-default">Buscar</button>
                </form>

            </div><!-- /.row -->

            <p></p>

            <c:if test="${testes == null}">
                <div class="alert alert-warning" role="alert">Nenhuma busca foi executada.</div>
            </c:if>    
                
            <c:if test="${filtro != null}">
                <div class="alert alert-info" role="alert"><c:out value="${filtro}"/></div>
            </c:if>
                
            <c:if test="${operacao != null}">
                <div class="alert alert-success" role="alert"><c:out value="${operacao}"/></div>
            </c:if>
                
            <div class="row" >

                <ul class="nav nav-pills" role="tablist">

                </ul>

            </div><!-- /.row -->

            <p></p>

            <div class="panel panel-default">
                <!-- Default panel contents -->

                <!-- Table -->
                <table class="table table-hover">
                    <thead class="thead-inverse">
                        <tr>
                            <th>C�digo</th>
                            <th>Nome</th>
                            <th width="50px"></th>
                            <th width="10px"></th>
                            <th width="10px"></th>
                            <th width="10px"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                        <c:forEach items="${teste}" var="teste">
                            <tr>
                                <td><c:out value="${teste.codTeste}"/></td>
                                <td><c:out value="${teste.nome}"/></td>
                                <td></td>
                                
                             <td width="10px"><a href="javascript:confirmarExclusao(<c:out value="${teste.codTeste}"/>,'<c:out value="${teste.nome}"/>');"><span class="glyphicon glyphicon-trash" style="color:red" title="Excluir"  aria-hidden="true"></span></a></td>
                            
                            </tr>
                        </c:forEach>
                    
                        <td><a href="criar_teste.jsp?idLoad=0" label= "Cadastrar"  class="btn btn-default btn pull-left"><span title="Adicionar" /> Adicionar</a></td>
                        <td></td>
                        <td></td>
                        <td></td> 

                        </tr>
                    </tbody>
                </table>
            </div>



            <nav>

                <%
                    Integer curPag = (Integer) request.getAttribute("curPag");
                    Integer qtdPag = (Integer) request.getAttribute("qtdPag");
                    String nome = (String) request.getAttribute("nome");
                    if (qtdPag != null) {
                %>
                <ul class="pagination">


                    <%
                        String prevClass = "";
                        String prevLink = "#";
                        if (curPag == 1) {
                            prevClass = "class = 'disabled'";
                        } else {
                            prevLink = "listagemUsuario.do?curPag=";
                            prevLink += curPag - 1;
                            prevLink += "&nome=";
                            prevLink += nome;
                        }
                    %>
                    <li <%=prevClass%>>
                        <a href="<%=prevLink%>" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>


                    <%
                        for (int i = 1; i <= qtdPag; i++) {
                            String active = "";
                            if (i == curPag.intValue()) {
                                active = "class='active'";
                            }
                    %><li <%=active%> ><a href="listagemUsuario.do?curPag=<%=i%>&nome=<%=nome%>"><%=i%></a></li><%
                        }
                        %>


                    <%
                        String nextClass = "";
                        String nextLink = "#";
                        if (curPag == qtdPag) {
                            nextClass = "class = 'disabled'";
                        } else {
                            nextLink = "listagemUsuario.do?curPag=";
                            nextLink += curPag + 1;
                            nextLink += "&nome=";
                            nextLink += nome;
                        }
                    %>
                    <li <%=nextClass%>>
                        <a href="<%=nextLink%>" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
                <%}%>
            </nav>
        </div>
    </div>
</div>

<script>
    function confirmarMudancaSenha(id, nome) {
        $('#modal-title').html("Confirma��o de Mudan�a de Senha");
        $('#modal-text').html("Deseja mudar a senha para o usu�rio " + nome + "?");
        $('#modal-buttons').html("<button type='button' class='btn btn-default' data-dismiss='modal'>Cancelar</button><button type= 'button' onclick='mudarSenha(" + id + ")' class='btn btn-primary'>Mudar e Enviar Senha</button>");
        $('#myModal').modal('show');
    }
    
    function mudarSenha(id) {
        $(location).attr('href', "listagemUsuario.do?idMudarSenha=" + id);
    }
    
    function confirmarExclusao(id, nome) {
        $('#modal-title').html("Confirma��o de Exclus�o");
        $('#modal-text').html("Deseja excluir " + nome + "?");
        $('#modal-buttons').html("<button type='button' class='btn btn-default' data-dismiss='modal'>Cancelar</button><button type= 'button' onclick='excluir(" + id + ")' class='btn btn-primary'>Excluir</button>");
        $('#myModal').modal('show');
    }

    function excluir(id) {
        $(location).attr('href', "listagemTeste.do?idDelete=" + id);
    }
</script>

<%@include file="../../tail.jsp" %>

