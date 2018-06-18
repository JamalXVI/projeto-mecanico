   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:import url="/WEB-INF/jsp/header.jsp"/>
    <style>
		.pesquisar_form{
			clear:both;
		}
	</style>
	<div class="col-xs-12 col-md-12 text-center">
	 <h2><label><span class="glyphicon glyphicon-user"></span> Lista Cliente</label></h2>
 </div>
    <form role="form" id="pesquisar_form" action="${linkTo[ClienteController].listar_informacao(pesquisa)}" method="get" class="">
        <div class="input-group col-xs-8 col-md-10" > 
            <input type="text" class="form-control" placeholder="Filtrar pesquisa por nome" name="pesquisa"/>
            <span class="input-group-addon">
               <i class="glyphicon glyphicon-search"></i>
            </span>
        </div>
    </form>
    <table class="table table-hover table-bordered table-striped tablesorter" id="tabClientes">
        <thead>
            <tr>
            	<th>Foto</th>
                <th>Identidade</th>
                <th>Nome</th>
                <th>Informação</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody> 
          <c:forEach items="${clientes}" var="c">
                <tr>
               		<td>
                		<img src="<c:url value="/Imagem/Cliente/${c.id }/" />" width="90" height="90"/>
                	</td>
                    <td>
                    <c:if test="${c.identificacao.tipo == 'CPF' }">
                    CPF: ${c.identificacao.cpf}
                    </c:if>
                    <c:if test="${c.identificacao.tipo != 'CPF' }">
                    CNPJ: ${c.identificacao.cnpj} / IE: ${c.identificacao.inscricao_estadual }
                    </c:if>
                    </td>
                    <td>${c.nome}</td>
                    <td>${c.informacao }</td>
                    <td><a href="#" onclick="iniciar_modal(${c.id})">
                    <span class="glyphicon glyphicon-trash"></span></a>
                    <a href="${linkTo[ClienteController].editar(c.id)}">
                    <span class="glyphicon glyphicon-pencil"></span></a>
                    <a href="${linkTo[ClienteController].visualizar(c.id)}">
                    <span class="glyphicon glyphicon-search"></span></a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <c:import url="/WEB-INF/jsp/deletar/modal_deletar_cliente.jsp" />
<script>
	function iniciar_modal(id){
		$("#id_esc_deletar").val(id);
		$("#deletarModal").modal("show");
	}
 </script>
    <c:import url="/WEB-INF/jsp/footer.jsp"/>