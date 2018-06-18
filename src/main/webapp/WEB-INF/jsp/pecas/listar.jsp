   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:import url="/WEB-INF/jsp/header.jsp"/>
    <style>
		.pesquisar_form{
			clear:both;
		}
	</style>
	<div class="col-xs-12">
		<h2 class="text-center"><label><span class="glyphicon glyphicon-oil"></span> Lista Peça</label></h2>
	</div>
    <form role="form" id="pesquisar_form" action="${linkTo[PecasController].listar(pesquisa)}" method="get" class="">
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
            	<th>Nome</th>
                <th>Fabricante</th>
                <th>Código Fiscal</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody> 
          <c:forEach items="${pecas}" var="peca">
                <tr>
               		<td>
               		${peca.nome }
                	</td>
                    <td>${peca.fornecedor.nome }</td>
                    <td>${peca.cod_fiscal} </td>
                    <td><a href="#" onclick="iniciar_modal(${peca.id})">
                    <span class="glyphicon glyphicon-trash"></span></a>
                    <a href="${linkTo[PecasController].editar(peca.id)}">
                    <span class="glyphicon glyphicon-pencil"></span></a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <c:import url="/WEB-INF/jsp/deletar/modal_deletar_peca.jsp" />
<script>
	function iniciar_modal(id){
		$("#id_esc_deletar").val(id);
		$("#deletarModal").modal("show");
	}
 </script>
    <c:import url="/WEB-INF/jsp/footer.jsp"/>