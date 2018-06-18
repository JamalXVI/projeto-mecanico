
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="alura"%>
<c:import url="/WEB-INF/jsp/header.jsp" />
<div class="col-xs-12 col-md-12 text-center">
	 <h2><label><span class="glyphicon glyphicon-wrench"></span> Cadastrar Solicitação</label></h2>
</div>
<form action="${linkTo[SolicitacaoController].cadastrar(null, null, null, null) }" method="post" role="form" enctype="multipart/form-data">

<div class="col-sm-12 col-md-8 col-lg-3">
<label class="text-center">Tipo de Busca:</label>
<div>
<button type="button" class="btn btn-info" data-toggle="modal" data-target="#clienteModal">
  Cliente
</button>
<button type="button" class="btn btn-info" data-toggle="modal" data-target="#placaModal">
  Placa
</button>
<button type="button" id="btn_pesq_modelo" class="btn btn-info" data-toggle="modal" data-target="#pesqmodeloModal">
  Modelo
</button>
</div>
</div>
<div class="col-sm-12 col-md-6 col-lg-3">
<label>Cliente:</label>
<input type="hidden" id="cliente_selecionado_id" value="${veiculo.cliente.id }" name="id_cliente" />
<input type="text" class="form-control" readonly="readonly" id="cliente_selecionado"
value="${veiculo.cliente.nome }" />
</div>
<div class="col-sm-12 col-md-6 col-lg-3">
<label>Veículo:</label>
<input type="hidden" id="id_veiculo" value="${veiculo.id }" name="id_veiculo" />
<input type="text" class="form-control" readonly="readonly" id="veiculo_selecionado"
value="${veiculo.placa }" />
</div>
<div class="col-sm-12 col-md-8 col-lg-3">
<label>Data Entrada:</label>
<input type="text" class="form-control horario_dia" id="data_entrada" name="horario" 
value="<fmt:formatDate value="${solicitacao.data_entrada.time}" />" />
</div>
<div class="col-sm-12 col-md-6 col-lg-4">
<label for="solicitacoes_comentario">Solicitação:</label>
<textarea class="form-control" rows="5" id="solicitacoes_comentario" name="solicitacao.solicitacoes" value="${solicitacao.solicitacoes }"></textarea>
</div>
<div class="col-sm-12 col-md-6 col-lg-4">
  <label id="lbl_upload_foto">Fotos da Solicitação:</label>
   <input type="file" id="upload_foto" name="imagem[]" class="btn" multiple="multiple" value="<c:url value="/resources/imagens/Desconhecido.png"/>"/>
 </div>
<div class="col-sm-6 col-md-8">  
      	<input type="submit" value="Cadastrar" id="btn_finalizar"
        	 class="btn btn-primary botao_confirmar btn-lg" />
</div>

<c:import url="/WEB-INF/jsp/modal_modelo.jsp" />
<c:import url="/WEB-INF/jsp/modal_cliente_veiculo.jsp" />
<c:import url="/WEB-INF/jsp/modal_placa.jsp" />
</form>
<script type="text/javascript">
var formatar = function(){
    $('.horario_dia').mask('00/00/0000 00:00');
    $(".veiculo").mask('AAA-9999');
	
};
$(formatar());
</script>
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
var veiculos = null;
var veiculo_escolhido = null;
var modelos = null;
var marcas = null;
var dados = function(){
	$.ajax({
		type:"GET",
		url: '<c:url value= "/Json/Lista/Cliente/Veiculo" />',
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
	$.ajax({
		type:"GET",
		url: '<c:url value= "/Json/Lista/Veiculo/" />',
		dataType:"json",
		success: function(data)
		{
			veiculos = data;
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
	$.ajax({
		type:"GET",
		url: '<c:url value= "/Json/Lista/Modelo/" />',
		dataType:"json",
		success: function(data)
		{
			modelos = data;
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
	$.ajax({
		type:"GET",
		url: '<c:url value= "/Json/Lista/Marca/" />',
		dataType:"json",
		success: function(data)
		{
			marcas = data;
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
</script>
<script type="text/javascript">
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
			$("#pesq_cliente_seletor").find('option').remove().end;
			$(cliente.veiculos).each(function(i_veiculo, veiculo){
				if (veiculo.ativo) {
					$(veiculos).each(function(i_veiculos, e_veiculo){
						if (e_veiculo.id == veiculo.id) {
							$("#pesq_cliente_seletor").append("<option value='"+veiculo.id+
									"'>"+e_veiculo.modelo.nome+" "+veiculo.placa+"</option>");
						};
					});
				};
			});
		}
	});
};
var mudar_veiculo_cliente = function(){
	var id_veiculo = $("#pesq_cliente_seletor").val();
	$(veiculos).each(function(indice,veiculo){
		if(veiculo.id == id_veiculo)
		{
			$("#id_veiculo").val(id_veiculo);
			$("#veiculo_selecionado").val(veiculo.modelo.nome + " - "+veiculo.placa);
		}
	});
};
$("#pesq_cliente_selector").on("change", function(){
	mudar_veiculo_cliente();
});

$(cliente_selecionado());
$("#selecionar_cliente").on("change", function(){
	cliente_selecionado();
	mudar_veiculo_cliente();
});

var selecionado =  function(){
		if (cliente_selecionado != null) {
			$("#cliente_selecionado").val(cliente_escolhido.nome);
			$("#cliente_selecionado_id").val(cliente_escolhido.id);
		}
}
</script>
<script>
var texto_pesquisa = "";
var pesquisar_veiculo_placa = function(){
	
	$(veiculos).each(function(i_veiculo, veiculo){
		if(veiculo.ativo)
			{
				if(veiculo.placa.toLowerCase().match(texto_pesquisa.toLowerCase()))
					{
					$("#corpoPesqPlaca").append("<tr class='linha_veiculo' id='"+veiculo.id+
							"'>"+"<td>"+veiculo.placa+"</td>"+
							"<td>"+veiculo.modelo.nome+"</td>"+"<td>"+veiculo.cliente.nome+"</td>"+"</tr>");
					}
				
					
			}
			});
	$(".linha_veiculo").on("click", function(){
		
		acao_pesq_placa($(this).attr("id"));
	});
};
function acao_pesq_placa(id_plca){
	id_veiculo = id_plca;
	$(veiculos).each(function(i_veiculo, veiculo){
			if (veiculo.id == id_veiculo) {
				$("#cliente_selecionado").val(veiculo.cliente.nome);
				$("#cliente_selecionado_id").val(veiculo.cliente.id);
				$("#id_veiculo").val(id_veiculo);
				$("#veiculo_selecionado").val(veiculo.modelo.nome + " - "+veiculo.placa);
				$('#placaModal').modal('hide');
			}
			});
};

var pesquisar_por_placa = function(){
	
	$("#corpoPesqPlaca").find('tr').remove().end;
	
	texto_pesquisa = $("#pesq_placa").val();
	if (!texto_pesquisa) {
		texto_pesquisa = "-";
	}
	pesquisar_veiculo_placa();
};

$("#pesq_placa").keypress(function(){
	pesquisar_por_placa();
});
$("#pesq_placa").keydown(function(){
	pesquisar_por_placa();
});
$("#pesq_placa").keyup(function(){
	pesquisar_por_placa();
});
$("#pesq_placa").on("tap",function(){
	pesquisar_por_placa();
})
$(pesquisar_por_placa());
</script>
<script>
var preencher_pesq_modelo_marca = function(){
	$("#selecionar_pesq_modelo_marca").find('option').remove().end;
	$(marcas).each(function(i,e){
		$("#selecionar_pesq_modelo_marca").append("<option value='"+e.id+"'>"+e.nome+"</option>");
	});
	pesq_modelo_mudar_modelo();
};
var pesq_modelo_mudar_modelo = function(){
	$("#selecionar_pesq_modelo_modelo").find('option').remove().end;
	id_marca = $("#selecionar_pesq_modelo_marca").val();
	$(modelos).each(function(i,e){
		if(e.marca.id == id_marca && e.ativo)
		{
				$("#selecionar_pesq_modelo_modelo").append("<option value='"+e.id+"'>"+e.nome+"</option>");
		}
	});
	pesq_modelo_mudar_veiculo();
}
var pesq_modelo_mudar_veiculo = function(){
	
	$("#selecionar_pesq_modelo_veiculo").find('option').remove().end;
	id_pesq_modelo = $("#selecionar_pesq_modelo_modelo").val();
	$(veiculos).each(function(i,e){
		if (e.modelo.id == id_pesq_modelo && e.ativo) {
			$("#selecionar_pesq_modelo_veiculo").append("<option value='"+e.id+"'>"+e.placa+"</option>");
		}
	})
}
$("#btn_pesq_modelo").on("click", function(){
	preencher_pesq_modelo_marca();
})
$("#selecionar_pesq_modelo_marca").on("change", function(){
	pesq_modelo_mudar_modelo();
});
$("#selecionar_pesq_modelo_modelo").on("change", function(){
	pesq_modelo_mudar_veiculo();
});
var modelo_veiculo_selecionado = function(){
	acao_pesq_placa($("#selecionar_pesq_modelo_veiculo").val());
};
</script>
<c:import url="/WEB-INF/jsp/footer.jsp" />