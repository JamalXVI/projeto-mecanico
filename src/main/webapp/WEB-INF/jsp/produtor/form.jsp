
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="alura"%>
<c:import url="/WEB-INF/jsp/header.jsp" />
<div class="col-xs-12">
	<h2 class="text-center"><label><span class="glyphicon glyphicon-compressed"></span> Novo Produtor</label></h2>
</div>
<form action="${linkTo[ProdutorController].cadastrar(null, null)}"
	method="post" enctype="multipart/form-data" role="form">
	<input type="hidden" name="produtor.id" id="idp" value="${produtor.id}" /> 
	<input type="hidden" name="produtor.cidade" id="cidade_esc" 
	value="${produtor.cidade}" /> 
	<div class="col-sm-12 col-md-6 col-lg-4 div_fisica">
		<div class="tipo_doc doc_CNPJ">
		<div class="col-sm-7">
		<label for="cnpj">CNPJ:</label>
		<input type="text" name="produtor.identificacao.cnpj"
			id="cnpj" class="form-control cnpj" value="${produtor.identificacao.cnpj}" />
		</div>
		<div class="col-sm-5">
		<label for="inscricao">IE:</label>
		<input type="text" name="produtor.identificacao.inscricao_estadual"
			id="inscricao_estadual" class="form-control" value="${produtor.identificacao.inscricao_estadual}" />
		</div>
		</div>
	</div>
	
	<div class="col-sm-6 col-md-8 col-lg-3">   
            <label for="nome">Nome:</label>
            <input type="text" name="produtor.nome" id="nome" class="form-control"
            value="${produtor.nome}"/>
        </div>
		<div class="col-sm-12 col-md-5 col-lg-3">   
            <label for="rua">Endere�o:</label>
            <input type="text" name="produtor.rua" id="rua" class="form-control"
            value="${produtor.rua}"/>
        </div>
		<div class="col-sm-6 col-md-4 col-lg-1">   
            <label for="num">N�:</label>
            <input type="text" name="produtor.num" id="num" class="form-control"
            value="${produtor.num}"/>
        </div>
		<div class="col-sm-6 col-md-4 col-lg-2">   
            <label for="comp">Complt.:</label>
            <input type="text" name="produtor.comp" id="comp" class="form-control"
            value="${produtor.comp}"/>
        </div>
		<div class="col-sm-6 col-md-4 col-lg-3">   
            <label for="bai">Bairro:</label>
            <input type="text" name="produtor.bairro" id="bai" class="form-control"
            value="${produtor.bai}"/>
       	</div>
		<div class="col-sm-6 col-md-4 col-lg-2">   
            <label for="cep">CEP:</label>
            <input type="text" name="produtor.cep" id="cep" 
            class="form-control cep" value="${produtor.cep}"/>
         </div>
        <div class="col-sm-12 col-md-5 col-lg-3">
            <label for="estado">Estado:</label>
            <select class="input-large form-control" name="produtor.estado" id="slct_estado">
            <c:forEach var="e" items="${estados }">
            	<option id="Estado ${e.nome }" value="${e.id}">${e.nome} - ${e.uf}</option>
            </c:forEach>
            </select>
        </div>
		<div class="col-sm-12 col-md-5 col-lg-3">   
            <label for="cidade">Cidade:</label>
            <c:forEach var="e" items="${estados }">
            	<div class="estado estado_${e.id }">
           		 <select class="input-large form-control selecionando_estado" id="slct_estado_${e.id }">
           		 	<c:forEach var="c" items="${e.cidades }">
            			<option id="Cidade ${c.nome }" value="${c.id}">${c.nome}</option>
           		 	</c:forEach>
           		 </select>
            		
            	</div>
            </c:forEach>
        </div>
		<div class="col-sm-6 col-md-6 col-lg-3">   
            <label for="email">Email:</label>
            <input type="text" name="produtor.email" id="email" class="form-control"
            value="${produtor.email}"/>
        </div>
		
		<div class="col-sm-6 col-md-6 col-lg-3">
			<label>Condi��o Pagamento</label>
			  <input id="condicao_pagamento" type="text" value="0" name="produtor.condicao_pagamento"
			  value="${produtor.condicao_pagamento }" />
        
		</div>
        <div class="col-sm-12 col-md-4 col-lg-2">
        <input type="button" value="Adicionar Telefone" id="btn_telefone"
        	 class="btn btn-primary botao_confirmar" onclick="adicionar_telefone()"/>
        </div>
        <div class="col-sm-12" id="telefones">
        	
        </div>
		<div class="col-sm-6 col-md-8">  
        	<label>Finalizar:</label>
        	<input type="submit" value="Cadastrar" id="btn_finalizar"
        	 class="btn btn-primary botao_confirmar" />
        </div>
        
</form>

<c:import url="/WEB-INF/jsp/formatacao.jsp" />
<script>
            $("input[name='produtor.condicao_pagamento']").TouchSpin({
                min: 0,
                max: 10000,
                step: 1,
                decimals: 0,
                boostat: 5,
                maxboostedstep: 10,
                postfix: 'Dias'
            });
        </script>
<script type="text/javascript">
var remover_tel = function(i){
	$("#div_tel_"+i).remove();	
};
var n_telefones = 0;
$(".div_telefone").each(function(i,e)
		{
			n_telefones += 1;
		});
var adicionar_telefone = function(){
	$("#telefones").append("<div id='div_tel_"+n_telefones+"' class='div_telefone'>"+
			"<div class='col-sm-1'><label for='telefone_ddd"+n_telefones+"'>DDD:</label>"+
		     "<input type='text' name='telefone["+n_telefones+"].ddd' id='telefone_ddd_"+n_telefones+"'"+
		      "class='form-control ddd_telefone'/></div>"+
			"<div class='col-sm-3'>"+
            "<label for='telefone"+n_telefones+"'>Telefone:</label>"+
    "<input type='text' name='telefone["+n_telefones+"].numero' id='telefone"+n_telefones+"'"+
     "class='form-control sp_celphones'/>"+
     "</div><div class='col-sm-3'><label for='telefone_tipo"+n_telefones+"'>Tipo:</label>"+
     "<input type='text' name='telefone["+n_telefones+"].tipo' id='telefone_tipo_"+n_telefones+"'"+
      "class='form-control'/></div>"+
"<div class='col-sm-3'><label>Operadora:</label>"+  
    "<select class='input-large form-control'name='telefone["+n_telefones+"].operadora'>"+
     "<option id='telefone_op_"+n_telefones+"' value=''>Selecione Uma Operadora</option>"+
          "<option id='Tim_"+n_telefones+"' value='Tim'>Tim</option>"+
         "<option id='Claro_"+n_telefones+"' value='Claro'>Claro</option>"+
          "<option id='Vivo_"+n_telefones+"' value='Vivo'>Vivo</option>"+
          "<option id='Nextel_"+n_telefones+"' value='Nextel'>Nextel</option>"+
          "<option id='CTBC_"+n_telefones+"' value='CTBC'>CTBC</option>"+
          "<option id='Outras_"+n_telefones+"' value='Outras'>Outras</option>"+
    "</select>"+
"</div><div class='col-sm-2'><label>Apagar Telefone</label><button type='button' class='btn btn-danger'"+
	" onclick=remover_tel("+n_telefones+")>"+
 "<span class=' glyphicon glyphicon-remove-circle'></span>Apagar</button></div></div>");
n_telefones++;
}
</script>
<script type="text/javascript">
  $(document).ready(function(){
    $('.combobox').combobox();
  });
</script>
<script>
		var mudanca_estado = function()
		{
			var id_estado = $("#slct_estado").val();
			$(".estado").each(function(index, elemento)
			{
				$(elemento).hide();
				});
				nome = ".estado_"+id_estado;
				$(nome).show();
				$("#cidade_esc").val($("#slct_estado_"+id_estado).val());
		};
		$("#slct_estado").on("change", function()
		{
			mudanca_estado();	
		});
		$( document ).ready(function() {
			$("#slct_estado").val("26");
			$("#slct_estado_26").val("5195");
			$(mudanca_estado());
		});
		$(".selecionando_estado").on("change", function()
				{
					mudanca_estado();
				});
</script>
<script>
$("#senha_incorreta").hide();
$("#confirmar_senha").on("change", function()
{
		if ($("#senha").val() != $("#confirmar_senha").val())
		{
			$("#senha_incorreta").show();
			$("#btn_finalizar").hide();
		}else{
			$("#senha_incorreta").hide();
			$("#btn_finalizar").show();
		}
});
</script>
<c:import url="/WEB-INF/jsp/footer.jsp" />