
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="alura"%>
<c:import url="/WEB-INF/jsp/header.jsp" />
<form action="${linkTo[ExecucaoController].cadastrar(null, null, null, null, null) }" method="post" role="form">
<div class="col-xs-12 col-md-12 text-center">
	 <h2><label><span class="glyphicon glyphicon-cog"></span>Nova Execução</label></h2>
 </div>
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
<input type="hidden" id="cliente_selecionado_id" value="${orcamento.solicitacao.veiculo.cliente.id }" name="id_cliente" />
<input type="text" class="form-control" readonly="readonly" id="cliente_selecionado"
 value="${orcamento.solicitacao.veiculo.cliente.nome }" />
</div>
<div class="col-sm-12 col-md-6 col-lg-3">
<label>Veículo:</label>
<input type="hidden" id="id_veiculo" value="${orcamento.solicitacao.veiculo.id }" name="id_veiculo" />
<input type="text" class="form-control" readonly="readonly" id="veiculo_selecionado"
value="${orcamento.solicitacao.veiculo.modelo.nome } - ${orcamento.solicitacao.veiculo.placa}" />
</div>
<div class="col-sm-12 col-md-6 col-lg-3">
<label>Orçamento:</label>
<input type="hidden" id="id_solicitacao" value="${orcamento.solicitacao.id }" name="id_solicitacao" />
<input type="hidden" id="id_orcamento" value="${orcamento.id}" name="id_orcamento" />
<input type="text" class="form-control" readonly="readonly" id="solicitacao_selecionada" 
 value="${orcamento.solicitacao.solicitacoes }"/>
</div>
<div class="col-sm-12 col-md-6 col-lg-3">
<label>Desconto:</label>
<input type="text"  class="form-control" readonly="readonly" id="orcamento_desconto"
value="${orcamento.desconto }" />
</div>
<div class="col-sm-12 col-md-6 col-lg-3">
<label>Total (Com Desconto):</label>
<input type="text" class="form-control" readonly="readonly" id="orcamento_total"
value="${orcamento.total }" />
</div>
<div class="col-sm-12 col-md-8 col-lg-3">
<label class="text-center">Buscar Autorizador:</label>
<div>
<button type="button" class="btn btn-info" data-toggle="modal" data-target="#autorizadorModal">
  Autorizador
</button>
</div>
</div>
<div class="col-sm-12 col-md-6 col-lg-3">
<label>Cliente:</label>
<input type="hidden" id="autorizador_selecionado_id" value="${id_autorizador }" name="id_autorizador" />
<input type="text" class="form-control" readonly="readonly" id="autorizador_selecionado" />
</div>
<div class="col-sm-12 col-md-8 col-lg-3">
<label class="text-center">Buscar Mecânico:</label>
<div>
<button type="button" class="btn btn-info" data-toggle="modal" data-target="#funcionarioModal">
  Funcionário
</button>
</div>
</div>
<div class="col-sm-12 col-md-6 col-lg-3">
<label>Funcionário:</label>
<input type="hidden" id="funcionario_selecionado_id" value="${id_funcionario }" name="id_funcionario" />
<input type="text" class="form-control" readonly="readonly" id="funcionario_selecionado" />
</div>
<div class="col-sm-12 col-md-4 col-lg-3">
  	<label id="lbl_forma_pagamento">Forma Pagamento</label>
    <select class="input-large form-control" id="cbx_forma_pagamento" name="executado.forma_pagamento">
      <option value="Dinheiro">Dinheiro</option>
      <option value="Cheque">Cheque</option>
      <option value="Cartao Crédito">Cartão Crédito</option>
      <option value="Catão Débito">Cartão Débito</option>
    </select>	
</div>
<div class="col-sm-12 col-md-8 col-lg-3">
<label>Responsável:</label>
<input type="text" class="form-control" id="responsavel" name="executado.responsavel" 
value='${executado.responsavel }' />
</div>
<div class="col-sm-12 col-md-8 col-lg-3">
<label>Data Entrega:</label>
<input type="text" class="form-control horario_dia" id="data_entrada" name="horario" 
value='<fmt:formatDate value="${executado.entrega.time}" />' />
</div>
<div class="col-sm-6 col-md-8">  
       	<label>Finalizar:</label>
      	<input type="submit" value="Cadastrar" id="btn_finalizar"
        	 class="btn btn-primary botao_confirmar" />
</div>
<c:import url="/WEB-INF/jsp/execucao_modal/modal_modelo.jsp" />
<c:import url="/WEB-INF/jsp/execucao_modal/modal_cliente_veiculo.jsp" />
<c:import url="/WEB-INF/jsp/execucao_modal/modal_autorizador.jsp" />
<c:import url="/WEB-INF/jsp/execucao_modal/modal_funcionario.jsp" />
<c:import url="/WEB-INF/jsp/execucao_modal/modal_placa.jsp" />
<c:import url="/WEB-INF/jsp/execucao_modal/modal_solicitacao.jsp" />
</form>
<script type="text/javascript">
var formatar = function(){
    $('.horario_dia').mask('00/00/0000 00:00');
    $(".veiculo").mask('AAA-9999');
    $('.valor_rev').mask('000000.00', {placeholder:' ', reverse: true});
    $('.porcentagem_rev').mask('0000.00', {placeholder:' ', reverse: true});
    $('.quantidade').mask('000', {placeholder:' ', reverse: true});
    $('.numero_horas').mask('0000', {placeholder:' ', reverse: true});
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
};
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
var solicitacoes = null;
var orcamentos = null;
var funcionarios = null;
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
	$.ajax({
		type:"GET",
		url: '<c:url value= "/Json/Lista/Solicitacao/Inativas/" />',
		dataType:"json",
		success: function(data)
		{
			solicitacoes = data;
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
		url: '<c:url value= "/Json/Lista/Orcamento/Ativos/" />',
		dataType:"json",
		success: function(data)
		{
			orcamentos = data;
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
		url: '<c:url value= "/Json/Lista/Funcionario/" />',
		dataType:"json",
		success: function(data)
		{
			funcionarios = data;
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
};

$(dados());
</script>
<script type="text/javascript">
function pesquisar_solicitacoes(id_veiculo, modal){
$(modal).find('option').remove().end;
$(solicitacoes).each(function(i,solicitacao){
		if (solicitacao.veiculo.id == id_veiculo) {
			$(modal).append("<option value='"+solicitacao.id+"'>"+solicitacao.solicitacoes+"</option>");
		}
	});
};
function posicionar_solicitacao(seletor){
	var id_solicitacao = $(seletor).val();
	$("#solicitacao_selecionada").val(
	$(seletor).find('option:selected').text());
	$("#id_solicitacao").val(id_solicitacao);
	verificar_orcamento_escolhido(id_solicitacao);
}
</script>
<script>
function verificar_orcamento_escolhido(id)
{
	$(orcamentos).each(function(i,e){
		if (e.solicitacao.id == id) {
			$("#id_orcamento").val(e.id);
			$("#orcamento_desconto").val(e.desconto);
			$("#orcamento_total").val(e.total);
			
		}
	}); 
}
</script>
<script>
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
	pesquisar_solicitacoes(id_veiculo, "#pesq_cliente_solicitacoes_carro");
};
$("#pesq_cliente_seletor").on("change", function(){
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
			posicionar_solicitacao("#pesq_cliente_solicitacoes_carro");
		}
};
</script>
<script>
var autorizador_escolhido = null;
var autorizador_selecionado = function()
{
	id_autorizador = $("#selecionar_autorizador").val();
	$(clientes).each( function(indice, cliente){
		if(id_autorizador == cliente.id)
		{
			autorizador_escolhido = cliente;
			$("#nome_autorizador").val(cliente.nome);
			$("#email_autorizador").val(cliente.email);
			$(".identificacao_autorizador").each(function(i,e){
				$(this).hide();
			});
			//$("#perfil_cliente").attr("src", imagem_perfil(cliente.id));
			var identidade = clientes[indice].identificacao;
			if(identidade.tipo == "CPF")
			{
				$("#cpf_autorizador").show();
				$("#vlr_cpf_autorizador").val(identidade.cpf);
			}else{
				$("#cnpj_autorizador").show();
				$("#vlr_cnpj_autorizador").val(identidade.cnpj);
				$("#vlr_IE_autorizador").val(identidade.inscricao_estadual);
			}
			$("#nome_responsavel").val(cliente.nome);
		}
	});
};
$(autorizador_selecionado());
$("#selecionar_autorizador").on("change", function(){
	autorizador_selecionado();
});
var selecionado_autorizador =  function(){
	$("#autorizador_selecionado").val(autorizador_escolhido.nome);
	$("#autorizador_selecionado_id").val(autorizador_escolhido.id);
	$("#responsavel").val($("#nome_responsavel").val())
};
</script>
<script>
var funcionario_escolhido = null;
var funcionario_selecionado = function()
{
	id_funcionario = $("#selecionar_funcionario").val();
	$(funcionarios).each( function(indice, funcionario){
		if(id_funcionario == funcionario.id)
		{
			funcionario_escolhido = funcionario;
			$("#nome_funcionario").val(funcionario.cliente.nome);
			$("#email_funcionario").val(funcionario.cliente.email);
			//$("#perfil_cliente").attr("src", imagem_perfil(cliente.id));
			var identidade = null;
			$(clientes).each(function(i,e){
				if (funcionario.cliente.id == e.id) {
					identidade = clientes[i].identificacao;
				}
			})
			
			$("#vlr_cpf_funcionario").val(identidade.cpf);
		}
	});
};
$(funcionario_selecionado());
$("#selecionar_funcionario").on("change", function(){
	funcionario_selecionado();
});
var selecionado_funcionario =  function(){
	funcionario_selecionado();
	$("#funcionario_selecionado").val(funcionario_escolhido.cliente.nome);
	$("#funcionario_selecionado_id").val(funcionario_escolhido.id);
};
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
	$("#pesq_placa_solicitacao_id_veiculo").val(id_plca);
	$("#placaModal").modal('hide');
	pesquisar_solicitacoes(id_plca, "#selecionar_solicitacao_placa");
	$("#solicitacaoModal").modal('show');
	
};
function finalizar_modal_solicitacao(){
	id_veiculo = $("#pesq_placa_solicitacao_id_veiculo").val();
	$(veiculos).each(function(i_veiculo, veiculo){
		if (veiculo.id == id_veiculo) {
			$("#cliente_selecionado").val(veiculo.cliente.nome);
			$("#cliente_selecionado_id").val(veiculo.cliente.id);
			$("#id_veiculo").val(id_veiculo);
			$("#veiculo_selecionado").val(veiculo.modelo.nome + " - "+veiculo.placa);
			posicionar_solicitacao("#selecionar_solicitacao_placa");
			
		}
		});
}
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
});
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
};
var pesq_modelo_mudar_veiculo = function(){
	$("#selecionar_pesq_modelo_veiculo").find('option').remove().end;
	id_pesq_modelo = $("#selecionar_pesq_modelo_modelo").val();
	$(veiculos).each(function(i,e){
		if (e.modelo.id == id_pesq_modelo && e.ativo) {
			$("#selecionar_pesq_modelo_veiculo").append("<option value='"+e.id+"'>"+e.placa+"</option>");
		}
	});
	var id_veiculo = $("#selecionar_pesq_modelo_veiculo").val();
	pesquisar_solicitacoes(id_veiculo, "#selecionar_pesq_modelo_solicitacao");
};
$("#btn_pesq_modelo").on("click", function(){
	preencher_pesq_modelo_marca();
});
$("#selecionar_pesq_modelo_marca").on("change", function(){
	pesq_modelo_mudar_modelo();
});
$("#selecionar_pesq_modelo_modelo").on("change", function(){
	pesq_modelo_mudar_veiculo();
});
$("#selecionar_pesq_modelo_veiculo").on("change", function(){
	var id_veiculo = $("#selecionar_pesq_modelo_veiculo").val();
	pesquisar_solicitacoes(id_veiculo, "#selecionar_pesq_modelo_solicitacao");
});
var modelo_veiculo_selecionado = function(){
	id_veiculo = $("#selecionar_pesq_modelo_veiculo").val();
	$(veiculos).each(function(i_veiculo, veiculo){
		if (veiculo.id == id_veiculo) {
			$("#cliente_selecionado").val(veiculo.cliente.nome);
			$("#cliente_selecionado_id").val(veiculo.cliente.id);
			$("#id_veiculo").val(id_veiculo);
			$("#veiculo_selecionado").val(veiculo.modelo.nome + " - "+veiculo.placa);
			posicionar_solicitacao("#selecionar_solicitacao_placa");
			
		}
		});
	posicionar_solicitacao("#selecionar_pesq_modelo_solicitacao");
};
</script>
<c:import url="/WEB-INF/jsp/footer.jsp" />