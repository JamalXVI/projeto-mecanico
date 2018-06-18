   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:import url="/WEB-INF/jsp/header.jsp"/>
    <style>
		.pesquisar_form{
			clear:both;
		}
	</style>
	<div class="col-xs-12 col-md-12 text-center">
	 <h2><label><span class="glyphicon glyphicon-wrench"></span> Lista Solicitação</label></h2>
</div>
    <form role="form" id="pesquisar_form" action="${linkTo[SolicitacaoController].listar(pesquisa)}" method="get" class="">
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
            	<th>Código</th>
            	<th>Solicitacao</th>
                <th>Veiculo</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody> 
          <c:forEach items="${solicitacoes}" var="solicitacao">
                <tr>
                	<td>${solicitacao.id }</td>
               		<td>
               		${solicitacao.solicitacoes }
                	</td>
                    <td>${solicitacao.veiculo.modelo.nome} - ${solicitacao.veiculo.placa }</td>
                    <td><a href="#" onclick="iniciar_modal(${solicitacao.id})">
                    <span class="glyphicon glyphicon-trash"></span></a>
                    <a href="${linkTo[VeiculoController].visualizar(solicitacao.veiculo.id)}">
                    <span class="glyphicon glyphicon-search"></span></a>
                    <a href="${linkTo[OrcamentoController].cadastro_solicitacao(solicitacao.id) }">
                    <span class="glyphicon glyphicon-cog"></span></a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <c:import url="/WEB-INF/jsp/deletar/modal_deletar_solicitacao.jsp" />
<script>
	function iniciar_modal(id){
		$("#id_esc_deletar").val(id);
		$("#deletarModal").modal("show");
	}
 </script>
    <c:import url="/WEB-INF/jsp/footer.jsp"/>