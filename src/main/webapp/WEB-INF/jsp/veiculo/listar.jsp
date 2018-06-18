   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:import url="/WEB-INF/jsp/header.jsp"/>
    <style>
		.pesquisar_form{
			clear:both;
		}
	</style>
	<div class="col-xs-12 col-md-12 text-center">
	 <h2><label><span class="glyphicon glyphicon-bed"></span> Novo Veículo</label></h2>
 </div>
    <form role="form" id="pesquisar_form" action="${linkTo[VeiculoController].listar(pesquisa)}" method="get" class="">
        <div class="input-group col-xs-8 col-md-10" > 
            <input type="text" class="form-control" placeholder="Filtrar pesquisa por placa" name="pesquisa"/>
            <span class="input-group-addon">
               <i class="glyphicon glyphicon-search"></i>
            </span>
        </div>
    </form>
   <div class="col-xs-12">
        <table class="table table-hover table-bordered table-striped tablesorter" id="tabClientes">
        <thead>
            <tr>
            	<th>Dono</th>
                <th>Marca</th>
                <th>Modelo</th>
                <th>Placa</th>
                <th>Ano</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody> 
        <c:forEach items="${veiculos }" var="veiculo">
        	<c:if test="${veiculo.ativo }">
        	<tr><td>${veiculo.cliente.nome }</td>
        	<td>${veiculo.modelo.marca.nome }</td><td>${veiculo.modelo.nome }</td>
        	<td>${veiculo.placa }</td><td>${veiculo.ano }</td><td>
        	<a href="#" onclick='iniciar_modal(${veiculo.id})'>
                    <span class="glyphicon glyphicon-trash"></span></a>
                    <a href="${linkTo[VeiculoController].visualizar(veiculo.id)}">
                    <span class="glyphicon glyphicon-search"></span></a></td></tr>
        	</c:if>
        </c:forEach>
        </tbody>
        </table>
        </div>
    <c:import url="/WEB-INF/jsp/deletar/modal_deletar_veiculo.jsp" />
<script>
	function iniciar_modal(id){
		$("#id_esc_deletar").val(id);
		$("#deletarModal").modal("show");
	}
 </script>
    <c:import url="/WEB-INF/jsp/footer.jsp"/>