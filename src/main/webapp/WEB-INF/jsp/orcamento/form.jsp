
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="alura"%>
<c:import url="/WEB-INF/jsp/header.jsp" />
<div class="col-xs-12 col-md-12 text-center">
	 <h2><label><span class="glyphicon glyphicon-barcode"></span> Cadastrar Orçamento</label></h2>
 </div>
<form action="${linkTo[OrcamentoController].cadastrar(null) }" method="post" role="form">
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
<input type="hidden" id="cliente_selecionado_id" value="${solicitacao.veiculo.cliente.id }" name="id_cliente" />
<input type="text" class="form-control" readonly="readonly" id="cliente_selecionado" 
value="${solicitacao.veiculo.cliente.nome }"/>
</div>
<div class="col-sm-12 col-md-6 col-lg-3">
<label>Veículo:</label>
<input type="hidden" id="id_veiculo" value="${solicitacao.veiculo.id }" name="id_veiculo" />
<input type="text" class="form-control" readonly="readonly" id="veiculo_selecionado"  
value="${solicitacao.veiculo.modelo.nome } - ${solicitacao.veiculo.placa }"/>
</div>
<div class="col-sm-12 col-md-6 col-lg-3">
<label>Solicitacao:</label>
<input type="hidden" id="id_solicitacao" value="${solicitacao.id}" name="id_solicitacao" />
<input type="text" class="form-control" readonly="readonly" id="solicitacao_selecionada" value="${solicitacao.solicitacoes }" />
</div>
<div class="col-sm-12 col-md-8 col-lg-3">
<label class="text-center">Adicionar:</label>
<div>
<button type="button" class="btn btn-info" data-toggle="modal" data-target="#pecasModal">
  Produto
</button>
<button type="button" class="btn btn-info" data-toggle="modal" data-target="#servicoModal">
  Servico
</button>
</div>
</div>
<div class="col-sm-6 col-md-4 col-lg-2">
  	<label id="lbl_desconto">Desconto?</label>
    <select class="input-large form-control" id="cbx_desconto">
      <option value="nao">Nao</option>
      <option value="porcentagem">Porcentagem</option>
      <option value="valor">Valor</option>
    </select>
  </div>
  <div class="col-sm-6 col-md-4 col-lg-2">
  	<label id="lbl_desconto">Valor do Desconto</label>
        <div class="input-group">
  			<span class="input-group-addon span_addon">R$</span>
    <input type="text" class="form-control dvalor" id="txt_valor_desconto" />
   	</div>
   	</div>
   	<div class="col-sm-6 col-md-4 col-lg-2">
   		<label id="lbl_total_desconto">Total De Desconto R$:</label>
   		<input type="text" readonly="readonly" class="form-control"
   		 id="txt_total_de_desconto" name="orcamento.desconto" />
   </div>
<div class="col-sm-12 col-md-8 col-lg-3">
<label>Total:</label>
<div class="input-group">
  <span class="input-group-addon" id="sizing-addon1">R$</span>
  <input type="text" class="form-control" readonly="readonly" name="orcamento.total" value="" id="total_gasto"/>
</div>
</div>
<div class="col-sm-12">
  <table class="table table-hover table-bordered table-striped" id="tabComprados">
     <thead>
       	<tr>
       		<th>Nome</th>
       		<th>Preço Un.</th>
       		<th>Quantidade</th>
       		<th>Ação</th>
       	</tr>
     </thead>
     <tbody id="corpoComprado">
     </tbody>
  </table>
</div>
<div id="resto">
</div>
<div class="col-sm-6 col-md-8">  
       	<label>Finalizar:</label>
      	<input type="submit" value="Cadastrar" id="btn_finalizar"
        	 class="btn btn-primary botao_confirmar" />
</div>
<c:import url="/WEB-INF/jsp/orcamento_modal/modal_autorizar.jsp" />
<c:import url="/WEB-INF/jsp/orcamento_modal/modal_modelo.jsp" />
<c:import url="/WEB-INF/jsp/orcamento_modal/modal_cliente_veiculo.jsp" />
<c:import url="/WEB-INF/jsp/orcamento_modal/modal_placa.jsp" />
<c:import url="/WEB-INF/jsp/orcamento_modal/modal_solicitacao.jsp" />
<c:import url="/WEB-INF/jsp/orcamento_modal/modal_pecas.jsp" />
<c:import url="/WEB-INF/jsp/orcamento_modal/modal_servico.jsp" />
</form>
<script type="text/javascript">
$(document).on('show.bs.modal', '.modal', function () {
    var zIndex = 1040 + (10 * $('.modal:visible').length);
    $(this).css('z-index', zIndex);
    setTimeout(function() {
        $('.modal-backdrop').not('.modal-stack').css('z-index', zIndex - 1).addClass('modal-stack');
    }, 0);
});
var ja_autorizou = false;
function autorizar(){
	if(!ja_autorizou)
	{
		ja_autorizou = true;
		$("#precisa_autorizar").val("true");
		$("#autorizarModal").modal('show');
	}
}
</script>
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
<script>
	
	var desconto = 0;
	var mudar_desconto = function()
	{
		//mudar_desconto();
		if ($("#cbx_desconto").val() == "valor")
		{
			autorizar();
			$("#txt_valor_desconto").prop('disabled',false);
			$("#txt_valor_desconto").removeClass("porcentagem_rev");
			$("#txt_valor_desconto").addClass("valor_rev");
			$(".span_addon").text("R$");
			$("#txt_total_de_desconto").val($("#txt_valor_desconto").val());
			desconto = parseFloat($("#txt_total_de_desconto").val());
		}else if ($("#cbx_desconto").val() == "porcentagem")
		{
			autorizar();
			$("#txt_valor_desconto").prop('disabled',false);
			$("#txt_valor_desconto").addClass("porcentagem_rev");
			$("#txt_valor_desconto").removeClass("valor_rev");
			$(".span_addon").text("%");
					$("#txt_total_de_desconto").val(((Number($("#txt_valor_desconto").val())
					*Number(total_gasto))/100).toFixed(2));
					desconto = parseFloat($("#txt_total_de_desconto").val());
		}else{
			$("#txt_valor_desconto").prop('disabled',true);
			$(".span_addon").text(" ");
			desconto = 0;
		}
		contar_total();
	};
	$("#cbx_desconto").change(mudar_desconto);
	$("#cbx_desconto").on("focus", mudar_desconto);
	$("#cbx_desconto").on("keydown", mudar_desconto);
	$("#cbx_desconto").on("keyup", mudar_desconto);
	$("#pesq_pecas").keypress(function(){
		pesquisar_peca();
	});
	$("#txt_valor_desconto").keydown(function(){
		mudar_desconto();
	});
	$("#txt_valor_desconto").keyup(function(){
		mudar_desconto();
	});
	$("#txt_valor_desconto").on("tap",function(){
		mudar_desconto();
	});
	mudar_desconto();
</script>
<script>
var total_gasto = 0;
var contar_total = function()
{
	 total_gasto = 0;
	 $('.servico_gasto').each(function(i,e){
		 total_gasto +=  parseFloat($(this).find(":nth-child(2)").text());
	 }); 
	 $('.produto_gasto').each(function(i,e){
		 total_gasto +=  (parseFloat($(this).find(":nth-child(2)").text()) * parseFloat($(this).find(":nth-child(3)").text()));
	 }); 
	 $("#total_gasto").val(total_gasto - desconto);
}
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
var pecas = null;
var servicos = null;
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
		url: '<c:url value= "/Json/Lista/Solicitacao/Ativas/" />',
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
		url: '<c:url value= "/Json/Lista/Pecas/" />',
		dataType:"json",
		success: function(data)
		{
			pecas = data;
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
		url: '<c:url value= "/Json/Lista/Servico/" />',
		dataType:"json",
		success: function(data)
		{
			servicos = data;
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
var num_pecas = 0;
var pesquisa_peca = "";
var pesquisar_pecas = function(){
	$(pecas).each(function(i, peca){
		if (peca.nome.toLowerCase().match(pesquisa_peca.toLowerCase())) {
			$("#corpoPecas").append("<tr class='linha_peca' id='"+peca.id+
					"'>"+"<td>"+peca.nome+"</td>"+
					"<td>"+peca.fornecedor.nome+"</td>"+"<td style='display:none;'>"+peca.fornecedor.porcentagem+"</td><td>"+peca.estoque+"</td>"+"</tr>");
		}
	});
	$(".linha_peca").on("click", function(){
		$("#id_peca_escolhida").val($(this).attr('id'));
		$("#peca_escolhida").val($(this).find(":first").text());
		
		$("#qtd_total_peca").val($(this).find(":last").text());
		
		$("#valor_porcentagem").val($($(this).children()[2]).text());
	});	
};

var pesquisar_peca = function(){
	$("#corpoPecas").find('tr').remove().end;
	pesquisa_peca = $("#pesq_pecas").val();
	pesquisar_pecas();
};
var mudar_pelo_custo = function(){
	var porcentagem = $("#valor_porcentagem").val();
	var valor_pago = $("#valor_pago").val();
	if (porcentagem && valor_pago) {
		var valor =  (parseFloat(valor_pago) * (100 + parseFloat(porcentagem)))/100;
		$("#valor_venda").val(valor);
	}
};
var mudar_porcentagem = function(){
	if (ja_autorizou) {
		var porcentagem = $("#valor_porcentagem").val();
		var valor_pago = $("#valor_pago").val();
		if (porcentagem && valor_pago) {
			var valor =  (parseFloat(valor_pago) * (100 + parseFloat(porcentagem)))/100;
			$("#valor_venda").val(valor);
		}
	}else{
		autorizar();
	}
};
$("#pesq_pecas").keypress(function(){
	pesquisar_peca();
});
$("#pesq_pecas").keydown(function(){
	pesquisar_peca();
});
$("#pesq_pecas").keyup(function(){
	pesquisar_peca();
});
$("#pesq_pecas").on("tap",function(){
	pesquisar_peca();
});
$("#valor_pago").keydown(function(){
	mudar_pelo_custo();
});
$("#valor_pago").keyup(function(){
	mudar_pelo_custo();
});
$("#valor_pago").on("tap",function(){
	mudar_pelo_custo();
});
$("#valor_porcentagem").keydown(function(){
	mudar_porcentagem();
});
$("#valor_porcentagem").keyup(function(){
	mudar_porcentagem();
});
$("#valor_porcentagem").on("tap",function(){
	mudar_porcentagem();
});
var finalizar_peca = function(){
	id_peca = $("#id_peca_escolhida").val();
	qtd_peca = $("#pecas_quantidade").val();
	vlr_venda = $("#valor_venda").val();
	vlr_pago = $("#valor_pago").val();
	nom_peca = $("#peca_escolhida").val();
	qtd_total = $("#qtd_total_peca").val();
	if ( id_peca && qtd_peca && vlr_venda && vlr_pago) {
		if(Number(qtd_total) > Number(qtd_peca))
		{
			$("#corpoComprado").append("<tr  class='produto_gasto' id='tr_peca_"+num_pecas+"'><td>"+
					nom_peca+"</td><td>"+vlr_venda+"</td><td>"+qtd_peca+"</td>"+
					"<td><a href='#' onclick='remover_item_tabela("+num_pecas+");'>"+
					"<span class='glyphicon glyphicon-trash'></span></a></td></tr>");	
			$("#resto").append("<div id='div_pecas_id_"+num_pecas
		    +"'><input type='hidden' name='produtos["+num_pecas
		    +"].quantidade_usada' value='"+qtd_peca+"' />"+
		    "<input type='hidden' name='produtos["+num_pecas+"].preco_compra'"+
		    " value='"+parseFloat(vlr_pago)+"' /><input type='hidden' name='produtos["+num_pecas
		    +"].preco_venda' value='"+parseFloat(vlr_venda)+"' />"+
		    "<input type='hidden' name='produtos["+num_pecas
		    +"].peca.id' value='"+id_peca+"' /></div>");
			num_pecas++;
		}
		
	}
	mudar_desconto();
};
function remover_item_tabela(id_peca)
{
	$("#corpoComprado").find("#tr_peca_"+id_peca).remove().end;
	$("#resto").find("#div_pecas_id_"+id_peca).remove().end;
	mudar_desconto();
};
</script>
<script type="text/javascript">
var num_servico = 0;
var pesquisa_servico = "";
var pesquisar_servicos = function(){
	$(servicos).each(function(i, servico){
		if (servico.nome.toLowerCase().match(pesquisa_servico.toLowerCase())) {
			$("#corpoServico").append("<tr class='linha_servico' id='"+servico.id+
					"'>"+"<td>"+servico.nome+"</td></tr>");
		}
});
$(".linha_servico").on("click", function(){
	$("#id_servico_escolhido").val($(this).attr('id'));
	$("#servico_escolhido").val($(this).find(":first").text());
});	
};
var pesquisar_servico = function(){
	$("#corpoServico").find('tr').remove().end;
	pesquisa_servico = $("#pesq_servico").val();
	pesquisar_servicos();
};
$("#pesq_servico").keypress(function(){
		pesquisar_servico();
});
$("#pesq_servico").keydown(function(){
		pesquisar_servico();
});
$("#pesq_servico").keyup(function(){
		pesquisar_servico();
});
$("#pesq_servico").on("tap",function(){
		pesquisar_servico();
});
var finalizar_servico = function(){
	id_servico = $("#id_servico_escolhido").val();
	vlr_servico = $("#servico_pago").val();
	servico_horas = $("#servico_horas").val();
	nom_servico = $("#servico_escolhido").val();
	if ( id_servico && vlr_servico && servico_horas) {
			$("#corpoComprado").append("<tr class='servico_gasto' id='tr_servico_"+num_servico+"'><td>"+
					nom_servico+"</td><td>"+vlr_servico+"</td><td>-</td>"+
					"<td><a href='#' onclick='remover_servico_tabela("+num_servico+");'>"+
					"<span class='glyphicon glyphicon-trash'></span></a></td></tr>");	
			$("#resto").append("<div id='div_servicos_id_"+num_servico
		    +"'>"+
		    "<input type='hidden' name='servicos["+num_servico+"].preco'"+
		    " value='"+parseFloat(vlr_servico)+"' />"+
		    "<input type='hidden' name='servicos["+num_servico+"].numero_horas'"+
		    " value='"+parseFloat(servico_horas)+"' />"+
		    "<input type='hidden' name='servicos["+num_servico
		    +"].servico.id' value='"+id_servico+"' /></div>");
			num_servico++;
		
	}
	mudar_desconto();
};
function remover_servico_tabela(id_peca)
{
	$("#corpoComprado").find("#tr_servico_"+id_peca).remove().end;
	$("#resto").find("#div_servicos_id_"+id_peca).remove().end;
	mudar_desconto();
};
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