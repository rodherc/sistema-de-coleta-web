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
                <h4 class="modal-title" id="modal-title" id="gridSystemModalLabel">Título</h4>
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
            <h3 class="panel-title">Listagem de Perguntas</h3>
        </div>
        <p></p>
        <p></p>
        <div class="panel-body">
            <!-- Main component for a primary marketing message or call to action -->



            <div class="row" >

            </div><!-- /.row -->

            <p></p>

                
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
                            <th>codPergunta</th>
                            <th>Tipo</th>
                            <th>Descricao Pergunta</th>
                            <th width="50px"></th>
                            <th width="10px"></th>
                            <th width="10px"></th>
                            <th width="10px"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                        <c:forEach items="${pergunta}" var="pergunta">
                            <tr>
                                <td><c:out value="${pergunta.codPergunta}"/></td>
                                <td><c:out value="${pergunta.tipo}"/></td>
                                <td><c:out value="${pergunta.descricaoPergunta}"/></td>
                                
                             <td width="10px"><a href="javascript:confirmarExclusao(<c:out value="${pergunta.codPergunta}"/>);"><span class="glyphicon glyphicon-trash" style="color:red" title="Excluir"  aria-hidden="true"></span></a></td>
                            
                            </tr>
                        </c:forEach>
                    
                    <!--<td><a href="criar_pergunta.jsp?idLoad=0" label= "Cadastrar"  class="btn btn-default btn pull-left"><span title="Adicionar" /> Adicionar</a></td>
                        -->
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
                    Integer codTeste = (Integer)request.getAttribute("codTeste");
                    if (qtdPag != null) {
                %>
                <ul class="pagination">


                    <%
                        String prevClass = "";
                        String prevLink = "#";
                        if (curPag == 1) {
                            prevClass = "class = 'disabled'";
                        } else {
                            prevLink = "listagemPergunta.do?curPag=";
                            prevLink += curPag - 1;
                            prevLink += "&codTeste=";
                            prevLink += codTeste;
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
                    %><li <%=active%> ><a href="listagemPergunta.do?curPag=<%=i%>&codTeste=<%=codTeste%>"><%=i%></a></li><%
                        }
                        %>


                    <%
                        String nextClass = "";
                        String nextLink = "#";
                        if (curPag == qtdPag) {
                            nextClass = "class = 'disabled'";
                        } else {
                            nextLink = "listagemPergunta.do?curPag=";
                            nextLink += curPag + 1;
                            nextLink += "&codTeste=";
                            nextLink += codTeste;
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
        $('#modal-title').html("Confirmação de Mudança de Senha");
        $('#modal-text').html("Deseja mudar a senha para o usuário " + nome + "?");
        $('#modal-buttons').html("<button type='button' class='btn btn-default' data-dismiss='modal'>Cancelar</button><button type= 'button' onclick='mudarSenha(" + id + ")' class='btn btn-primary'>Mudar e Enviar Senha</button>");
        $('#myModal').modal('show');
    }
    
    function mudarSenha(id) {
        $(location).attr('href', "listagemUsuario.do?idMudarSenha=" + id);
    }
    
    function confirmarExclusao(id, nome) {
        $('#modal-title').html("Confirmação de Exclusão");
        $('#modal-text').html("Deseja excluir " + nome + "?");
        $('#modal-buttons').html("<button type='button' class='btn btn-default' data-dismiss='modal'>Cancelar</button><button type= 'button' onclick='excluir(" + id + ")' class='btn btn-primary'>Excluir</button>");
        $('#myModal').modal('show');
    }

    function excluir(id) {
        $(location).attr('href', "listagemPergunta.do?idDelete=" + id);
    }
</script>

<%@include file="../../tail.jsp" %>

