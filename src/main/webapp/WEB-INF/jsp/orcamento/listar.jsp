   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:import url="/WEB-INF/jsp/header.jsp"/>
    <style>
		.pesquisar_form{
			clear:both;
		}
	</style>
	<div class="col-xs-12 col-md-12 text-center">
	 <h2><label><span class="glyphicon glyphicon-barcode"></span> Lista Orçamento</label></h2>
 </div>
    <form role="form" id="pesquisar_form" action="${linkTo[OrcamentoController].listar(pesquisa)}" method="get" class="">
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
            	<td>Código</td>
            	<th>Solicitacao</th>
                <th>Veiculo</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody> 
          <c:forEach items="${orcamentos}" var="orcamento">
                <tr>
                	<td>${orcamento.id }</td>
               		<td>
               		${orcamento.solicitacao.solicitacoes }
                	</td>
                    <td>${orcamento.solicitacao.veiculo.modelo.nome} - ${orcamento.solicitacao.veiculo.placa }</td>
                    <td><a href="#" onclick="iniciar_modal(${orcamento.id})">
                    <span class="glyphicon glyphicon-trash"></span></a>
                    <a href="${linkTo[OrcamentoController].visualizar(orcamento.id)}">
                    <span class="glyphicon glyphicon-search"></span></a>
                    <c:if test="${!orcamento.atendido }">
                    <a href="${linkTo[ExecucaoController].cadastro_orcamento(orcamento.id) }">
                    <span class="glyphicon glyphicon-cog"></span></a>
                    </c:if>
                    <a href="${linkTo[OrcamentoController].editar(orcamento.id)}">
                    <span class="glyphicon glyphicon-pencil"></span></a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <c:import url="/WEB-INF/jsp/deletar/modal_deletar_orcamento.jsp" />
<script>
	function iniciar_modal(id){
		$("#id_esc_deletar").val(id);
		$("#deletarModal").modal("show");
	}
 </script>
    <c:import url="/WEB-INF/jsp/footer.jsp"/>