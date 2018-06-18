   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:import url="/WEB-INF/jsp/header.jsp"/>
    <style>
		.pesquisar_form{
			clear:both;
		}
	</style>
	<div class="col-xs-12">
	<h2 class="text-center"><label><span class="glyphicon glyphicon-plane"></span> Lista Marca</label></h2>
	</div>
    <form role="form" id="pesquisar_form" action="${linkTo[MarcaController].listar(pesquisa)}" method="get" class="">
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
                <th>Ações</th>
            </tr>
        </thead>
        <tbody> 
          <c:forEach items="${marcas}" var="c">
                <tr>
                    <td>${c.nome}</td>
                    <td><a href="#" onclick='iniciar_modal(${c.id})'>
                    <span class="glyphicon glyphicon-trash"></span></a>
                    <a href="${linkTo[MarcaController].visualizar(c.id)}">
                    <span class="glyphicon glyphicon-search"></span></a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
<c:import url="/WEB-INF/jsp/deletar/modal_deletar_marca.jsp" />
<script>
	function iniciar_modal(id){
		$("#id_esc_deletar").val(id);
		$("#deletarModal").modal("show");
	}
 </script>
    <c:import url="/WEB-INF/jsp/footer.jsp"/>