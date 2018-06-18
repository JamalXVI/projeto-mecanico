<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/header.jsp" />
<link href="../../../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<form action="${linkTo[PenduraController].cadastrar(null)}" method="post">
 
  <div class="col-xs-12">
	<h2 class="text-center"><label><span class="glyphicon glyphicon-list-alt"></span> Nova Pendura</label></h2>
	</div>
  <div class="col-sm-6 col-md-4 col-lg-2">
  	<div>
    	<label for="" id="lbl_produto_preco">Dia para Pagar</label>
        <div id="produto_preco">
        	<input type="text" class="form-control horario_dia" name="dia_pendura" id="txt_dia_contas"/>
        </div>
    </div>
  </div>
  <div class="col-sm-12 col-md-4 col-lg-2">
	<label for="tipo_documento">Documento Fiscal:</label>
		 <select class="input-large form-control" id="tipo_documento" name="pendura.nota_fiscal">
		 	<option  value="true">Sim</option>
		 	<option  value="false">Não</option>
		 </select>
  </div>
  <div id="documento_fiscal">
  <div class="col-sm-12 col-md-4 col-lg-2">
  	<div>
    	<label for="pendura.numero_nota" id="lbl_numero_nota">Número Nota:</label>
        <input type="text" class="form-control" name="pendura.numero_nota" />
    </div>
  </div>
  <div class="col-sm-12 col-md-4 col-lg-2">
  	<div>
    	<label for="pendura.numero_duplicata" id="lbl_numero_duplicata">Número Duplicata:</label>
        <input type="text" class="form-control" name="pendura.duplicata" />
    </div>
  </div>
  </div>
  <div class="col-sm-6 col-md-4 col-lg-2">
  	<div>
    	<label for="pendura.valor" id="lbl_contas_custo">Valor</label>
        <div class="input-group">
  			<span class="input-group-addon span_addon">R$</span>
        <input type="text" class="form-control dinheiro" name="pendura.valor" id="txt_contas_custo"
        />	
        </div>
    </div>
  </div>
  <div class="col-sm-12 col-md-5 col-lg-4">
  	<div>
  		
    	<label for="contas.motivo" id="lbl_motivo_contas">Descrição</label>
        <input type="text" class="form-control" name="pendura.descricao" id="txt_contas_motivo_contas"
        />
    </div>
  </div>
 <div class="col-sm-8 col-md-6 col-lg-2">
<label>Executado:</label>
<input type="hidden" name="execucao" value="${execucao }"/>
<input type="hidden" id="executado_selecionado_id" value="${executado.id }" name="execucao_id" />
<input type="text" class="form-control" readonly="readonly" id="execucao_selecionado"
 value="${executado.orcamento.solicitacao.solicitacoes }" />
</div> 
<div class="col-sm-12 col-md-6 col-lg-2">
<label>Veiculo:</label>
<input type="hidden" id="veiculo_selecionado_id" value="${executado.orcamento.solicitacao.veiculo.id }" name="id_veiculo" />
<input type="text" class="form-control" readonly="readonly" id="veiculo_selecionado"
 value="${executado.orcamento.solicitacao.veiculo.modelo.nome }-  ${executado.orcamento.solicitacao.veiculo.placa  }" />
</div>
<div class="col-sm-12 col-md-6 col-lg-3">
<label>Cliente:</label>
<input type="hidden" id="cliente_selecionado_id" value="${executado.orcamento.solicitacao.veiculo.cliente.id }" name="id_cliente" />
<input type="text" class="form-control" readonly="readonly" id="cliente_selecionado"
 value="${executado.orcamento.solicitacao.veiculo.cliente.nome }" />
</div>
<div class="col-sm-6 col-md-8 col-lg-2">  
      	<input type="submit" value="Cadastrar" id="btn_finalizar"
        	 class="btn btn-primary botao_confirmar btn-lg" />
</div>
</form>  
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