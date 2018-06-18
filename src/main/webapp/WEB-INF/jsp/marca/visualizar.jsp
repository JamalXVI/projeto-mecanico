   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:import url="/WEB-INF/jsp/header.jsp"/>
    <style>
		.pesquisar_form{
			clear:both;
		}
	</style>
	<div class="col-xs-12">
	<h2 class="text-center"><label><span class="glyphicon glyphicon-plane"></span> Visualizar Marca</label></h2>
	</div>
	<div class="col-xs-12 text-center">
	<h4>Marca ${marca.nome }. Modelos:</h4>
	
	</div>
    <table class="table table-hover table-bordered table-striped tablesorter" id="tabClientes">
        <thead>
            <tr>
                <th>Nome</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody> 
          <c:forEach items="${marca.modelo}" var="c">
                <tr>
                    <td>${c.nome}</td>
                    <td><a href="#" onclick='iniciar_modal(${c.id})'>
                    <span class="glyphicon glyphicon-trash"></span></a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
<c:import url="/WEB-INF/jsp/deletar/modal_deletar_modelo.jsp" />
<script>
	function iniciar_modal(id){
		$("#id_esc_deletar").val(id);
		$("#deletarModal").modal("show");
	}
 </script>
    <c:import url="/WEB-INF/jsp/footer.jsp"/>