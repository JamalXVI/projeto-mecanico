<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/header.jsp" />
<link href="../../../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<div class="col-xs-12 col-md-12 text-center">
	 <h2><label><span class="glyphicon glyphicon-usd"></span> Nova Conta</label></h2>
 </div>
<form action="${linkTo[ContasController].cadastrar(null)}" method="post">
  <div class="col-sm-6 col-md-4 col-lg-3">
  	<div>
    	<label for="contas.nome" id="lbl_produto_nome">Nome:</label>
        <div id="produto_nome">
        	<input type="text" name="contas.nome" class="form-control" id="txt_contas_nome"
        	value="${contas.nome}" />
       		<alura:validationMessage name="contas.nome"/>
        </div>
    </div>
  </div>
  <div class="col-sm-6 col-md-3">
  	<label for="is_produtor">È Produtor?</label>
  	<select class="input-large form-control" name="contas.tem_produtor" value="${contas.tem_produtor}" id="cbx_is_produtor">
      <option value="false">Nao</option>
      <option value="true">Sim</option>
    </select>
  </div>
  <div class="col-sm-6 col-md-4" id="selecionar_produtor">
  	<div>
    	<label for="produtor_id" id="lbl_produtor_nome_antigo">Nome Produtor:</label>
        <div id="produtor_id">
        	<select name="produtor_id" class="form-control"
        	 id="txt_produtor_id" value="${produtor_id}" />
       		<option value=''>Selecione um Produtor</option>
			<c:forEach items='${produtores }' var='s'>
					<option value='${s.id }'>${s.nome }</option>
			</c:forEach>
		</select>
        </div>
    </div>
  </div>
  <div class="col-sm-12 col-md-4 col-lg-2">
	<label for="tipo_documento">Documento Fiscal:</label>
		 <select class="input-large form-control" id="tipo_documento" name="contas.nota_fiscal">
		 	<option  value="true">Sim</option>
		 	<option  value="false">Não</option>
		 </select>
  </div>
  <div id="documento_fiscal">
  <div class="col-sm-12 col-md-4 col-lg-2">
  	<div>
    	<label for="contas.numero_nota" id="lbl_numero_nota">Número Nota:</label>
        <input type="text" class="form-control" name="contas.numero_nota" />
    </div>
  </div>
  <div class="col-sm-12 col-md-4 col-lg-2">
  	<div>
    	<label for="contas.numero_duplicata" id="lbl_numero_duplicata">Número Duplicata:</label>
        <input type="text" class="form-control" name="contas.duplicata" />
    </div>
  </div>
  </div>
  <div class="col-sm-6 col-md-4 col-lg-2">
  	<div>
    	<label for="contas.preco" id="lbl_produto_preco">Dia para Pagar</label>
        <div id="produto_preco">
        	<input type="text" class="form-control horario_dia" name="dia_contas" id="txt_dia_contas"
        	value="${contas.dia_contas}" />
       		<alura:validationMessage name="contas.dia_contass"/>
        </div>
    </div>
  </div>
  
  <div class="col-sm-6 col-md-4 col-lg-2">
  	<div>
    	<label for="contas.custo" id="lbl_contas_custo">Custo</label>
        <div class="input-group">
  			<span class="input-group-addon span_addon">R$</span>
        <input type="text" class="form-control dinheiro" name="contas.valor" id="txt_contas_custo"
        value="${contas.valor }" />	
        </div>
       	<alura:validationMessage name="contas.descricao"/>
    </div>
  </div>
  <div class="col-sm-12 col-md-5 col-lg-4">
  	<div>
    	<label for="contas.motivo" id="lbl_motivo_contas">Motivo Gasto</label>
        <input type="text" class="form-control" name="contas.motivo" id="txt_contas_motivo_contas"
        value="${contas.motivo_contass}" />
       	<alura:validationMessage name="contas.motivo"/>
    </div>
  </div>
  <div class="col-sm-6 col-md-3">
  	<label for="is_produto">È Produto?</label>
  	<select class="input-large form-control" name="contas.produto" value="${contas.produto}" id="cbx_is_produto">
      <option value="false">Nao</option>
      <option value="true">Sim</option>
    </select>
  </div>
  
  <div class="col-sm-6 col-md-4" id="selecao_nome">
  	<div>
    	<label for="produto_id" id="lbl_produto_nome_antigo">Nome Produto:</label>
        <div id="produto_id">
        	<select name="produto_id" class="form-control"
        	 id="txt_produto_id" value="${produto_id}" />
       		<option value=''>Selecione Um Produto</option>
			<c:forEach items='${produtos }' var='s'>
					<option value='${s.id }'>${s.nome }</option>
			</c:forEach>
		</select>
        </div>
    </div>
  </div>
  <div class="col-sm-6 col-md-4" id="div_qtd_produto">
  <label for="txt_qtd_produto">Qtd. do Produto</label>
  <input type="text" name="contas.quantidade" class="form-control quantidade"id="txt_qtd_produto" value="${contas.quantidade }"
  name="contas.quantidade"/>
  </div>
  <div class="col-md-4 col-lg-2">
        <input type="submit" id="btn_cadastrar" value="Cadastrar"
         class="btn btn-primary botao_confirmar" />
    </div>
</form>  
<script>

$("#cbx_is_produto").on("change", function ativar_produto()
{
	if ($("#cbx_is_produto").val() == "true")
	{
		$("#div_qtd_produto").show();
		$("#selecao_nome").show();
	}else{
		$("#div_qtd_produto").hide();
		$("#selecao_nome").hide();
	}
});
if ($("#cbx_is_produto").val() == "true")
	{
		$("#div_qtd_produto").show();
		$("#selecao_nome").show();
	}else{
		$("#div_qtd_produto").hide();
		$("#selecao_nome").hide();
	}
</script>
<script>

$("#cbx_is_produtor").on("change", function ativar_produto()
{
	if ($("#cbx_is_produtor").val() == "true")
	{
		$("#selecionar_produtor").show();
	}else{
		$("#selecionar_produtor").hide();
	}
});
if ($("#cbx_is_produtor").val() == "true")
	{
		$("#selecionar_produtor").show();
	}else{
		$("#selecionar_produtor").hide();
	}
</script>
<script type="text/javascript">
var mudar_documento = function(){
	$("#documento_fiscal").hide();
	if ($("#tipo_documento").val() == "true") {
		$("#documento_fiscal").show();
	}
}
$(mudar_documento());
$("#tipo_documento").on("change", function(){
	mudar_documento();
});

</script>
<c:import url="/WEB-INF/jsp/formatacao.jsp"/>
<c:import url="/WEB-INF/jsp/footer.jsp" />