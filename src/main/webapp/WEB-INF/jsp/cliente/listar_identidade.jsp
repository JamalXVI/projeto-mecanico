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
    <form role="form" id="pesquisar_form" action="${linkTo[ClienteController].listar_identidade(cpf, cnpj, tipo)}" method="get" class="">
        <div class="col-sm-12 col-md-4 col-lg-2">
	<label for="tipo_documento">Tipo de Documento:</label>
		 <select class="input-large form-control" id="tipo_documento" name="tipo">
		 	<option  value="CPF">CPF</option>
		 	<option  value="CNPJ">CPNJ</option>
		 </select>
	</div>
	<div class="col-sm-12 col-md-12 col-lg-6 div_fisica">
		<div class="tipo_doc doc_CPF">
			<div class="col-sm-6">
			<label for="cpf">CPF:</label>
			<input type="text" name="cpf"
				id="cpf" class="form-control cpf" />
			</div>
			</div>
		<div class="tipo_doc doc_CNPJ">
		<div class="col-sm-6">
		<label for="cnpj">CNPJ:</label>
		<input type="text" name="cnpj"
			id="cnpj" class="form-control cnpj"/>
		</div>
	</div>
		<div class="col-sm-4">  
        	<label>Pesquisar:</label>
        	<input type="submit" value="Pesquisar" id="btn_finalizar"
        	 class="btn btn-primary" />
        </div>
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

<c:import url="/WEB-INF/jsp/formatacao.jsp" />
<script>
	function iniciar_modal(id){
		$("#id_esc_deletar").val(id);
		$("#deletarModal").modal("show");
	}
 </script>
 <script type="text/javascript">
var mudar_documento = function(){
	$(".tipo_doc").each(function(i, e){
		$(this).hide();
		
	}
	)
	$(".doc_"+$("#tipo_documento").val()).show();
}
$(mudar_documento());
$("#tipo_documento").on("change", function(){
	mudar_documento();
});

</script>
    <c:import url="/WEB-INF/jsp/footer.jsp"/>