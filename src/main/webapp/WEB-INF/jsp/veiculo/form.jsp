
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="alura"%>
<c:import url="/WEB-INF/jsp/header.jsp" />
<div class="col-xs-12 col-md-12 text-center">
	 <h2><label><span class="glyphicon glyphicon-bed"></span> Novo Veículo</label></h2>
 </div>
<form action="${linkTo[VeiculoController].cadastrar(null, null, null) }" method="post" role="form">
<div class="col-sm-6 col-md-4 col-lg-2">
<label for="tipo_documento">Marca:</label>
<select class="input-large form-control" id="id_marca" name="id">
<c:forEach var="marca" items="${marcas }">
<c:if test="${modelo_a.marca.id == marca.id }">
<option value="${marca.id }" selected="selected">${marca.nome }</option>
</c:if>
<c:if test="${modelo_a.marca.id != marca.id }">
<option value="${marca.id }">${marca.nome }</option>
</c:if>
</c:forEach>
</select>
</div>
<div class="col-sm-6 col-md-4 col-lg-2">
<input type="hidden" name="id_modelo" value="${modelo_a.id }"  id="modelo_veiculo"/>
<label for="tipo_documento">Modelo:</label>
<c:forEach var="marca" items="${marcas }">
<div class="marca" id="div_marca_${marca.id }">
<select class="input-large form-control modelos_marca" id="marca_${marca.id }">
<c:forEach var="modelo" items="${marca.modelo }">
<c:if test="${modelo.ativo }">
<c:if test="${modelo_a.id == modelo.id }">
<option value="${modelo.id }" selected="selected">${modelo.nome }</option>
</c:if>
<c:if test="${modelo_a.id != modelo.id }">
<option value="${modelo.id }">${modelo.nome }</option>
</c:if>
</c:if>
</c:forEach>
</select>
</div>
</c:forEach>
</div>
<div class="col-sm-6 col-md-4 col-lg-2">
<label>Tipo de Veículo</label>
<select class="input-large form-control" id="veiculo_tipo" name="veiculo.tipo">
<option value="automóvel">Automóvel</option>
<option value="caminhão">Caminhão</option>
<option value="caminhonete">Caminhonete</option>
<option value="ônibus">Ônibus</option>
<option value="micro ônibus">Micro Ônibus</option>
<option value="van">Van</option>
<option value="moto">Moto</option>
<option value="outros">Outros</option>
</select>
</div>
<div class="col-sm-6 col-md-4 col-lg-2">
<label>Placa do Veículo</label>
<input type="text" class="form-control veiculo" value="${veiculo.placa }"
name="veiculo.placa" />
</div>
<div class="col-sm-6 col-md-4 col-lg-2">
<label>Ano de Fabricação:</label>
<input type="text" class="form-control ano" value="${veiculo.ano }"
name="veiculo.ano" />
</div>
<div class="col-sm-6 col-md-4 col-lg-2">
<label>Cor do Veículo:</label>
<input type="text" class="form-control" value="${veiculo.cor }"
name="veiculo.cor" />
</div>
<div class="col-sm-6 col-md-4 col-lg-2">
<label>Buscar Cliente:</label>
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#clienteModal">
  Buscar Cliente
</button>
</div>
<div class="col-sm-8 col-md-6 col-lg-3">
<label>Cliente:</label>
<input type="hidden" id="cliente_selecionado_id" value="${cliente.id }" name="id_cliente" />
<input type="text" class="form-control" readonly="readonly" id="cliente_selecionado"
 value="${cliente.nome }" />
</div>
<div class="col-sm-6 col-md-8">  
      	<input type="submit" value="Cadastrar" id="btn_finalizar"
        	 class="btn btn-primary botao_confirmar btn-lg" />
</div>
<c:import url="/WEB-INF/jsp/modal_cliente.jsp" />
</form>
<c:import url="/WEB-INF/jsp/formatacao.jsp" />
<script type="text/javascript"
	src="<c:url value="/js/pegar_cliente.js"/>"></script>
<script type="text/javascript">

var atualizar_valor_marca = function()
{
	marca = $("#id_marca").val();
	$("#div_marca_"+marca).show();
	$("#modelo_veiculo").val($("#marca_"+marca).val());
};
var marca_selecionada = function(){
	$(".marca").each(function(i,e){
		$(this).hide();
	});
	atualizar_valor_marca();
}
$(marca_selecionada());
$("#id_marca").on("change", function()
		{
			marca_selecionada();
		});
$(".modelos_marca").on("change", function(){
	atualizar_valor_marca();
});
</script>
<script>
var clientes = null;
var cliente_escolhido = null;
var dados = function(){
	$.ajax({
		type:"GET",
		url: '<c:url value= "/Json/Lista/Cliente" />',
		dataType:"json",
		success: function(data)
		{
			clientes = data;
		},
		beforeSend: function(jqXHR, settings) {
	        jqXHR.url = settings.url;
	    },
		error: function(jqXHR, ajaxOptions, thrownError) {
	        alert(jqXHR.status);
	        alert(jqXHR.url);
	        alert(thrownError);
	 	}	
	});
}
$(dados());

var cliente_selecionado = function()
{
	id_cliente = $("#selecionar_cliente").val();
	$(clientes).each( function(indice, cliente){
		if(id_cliente == cliente.id)
		{
			cliente_escolhido = cliente;
			$("#nome_cliente").val(cliente.nome);
			$("#email_cliente").val(cliente.email);
			$(".identificacao_cliente").each(function(i,e){
				$(this).hide();
			});
			//$("#perfil_cliente").attr("src", imagem_perfil(cliente.id));
			var identidade = clientes[indice].identificacao;
			if(identidade.tipo == "CPF")
			{
				$("#cpf_cliente").show();
				$("#vlr_cpf_cliente").val(identidade.cpf);
			}else{
				$("#cnpj_cliente").show();
				$("#vlr_cnpj_cliente").val(identidade.cnpj);
				$("#vlr_IE_cliente").val(identidade.inscricao_estadual);
			}
			
		}
	});
};
$(cliente_selecionado());
$("#selecionar_cliente").on("change", function(){
	cliente_selecionado();
});
var selecionado =  function(){
		if (cliente_selecionado != null) {
			$("#cliente_selecionado").val(cliente_escolhido.nome);
			$("#cliente_selecionado_id").val(cliente_escolhido.id);
		}
}
</script>
<c:import url="/WEB-INF/jsp/footer.jsp" />