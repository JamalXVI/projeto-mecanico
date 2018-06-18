
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="alura"%>
<c:import url="/WEB-INF/jsp/header.jsp" />
<div class="col-xs-12 col-md-12 text-center">
	 <h2><label><span class="glyphicon glyphicon-user"></span> Cadastrar Cliente</label></h2>
 </div>
<form action="${linkTo[ClienteController].cadastrar(null, null, null)}"
	method="post" enctype="multipart/form-data" role="form">
	<input type="hidden" name="cliente.id" id="idp" value="${cliente.id}" /> 
	<input type="hidden" name="cliente.cidade" id="cidade_esc" 
	value="${cliente.cidade}" /> 
	<label for="imagem">Foto de Perfil:</label>
	
	<input type="file" name="imagem" size="50" class="btn" 
	value="<c:url value="/resources/imagens/Desconhecido.png"/>" />
	
	<div class="col-sm-12 col-md-4 col-lg-2">
	<label for="tipo_documento">Tipo de Documento:</label>
		 <select class="input-large form-control" id="tipo_documento" name="cliente.identificacao.tipo">
		 	<option  value="CPF">CPF</option>
		 	<option  value="CNPJ">CPNJ</option>
		 </select>
	</div>
	<div class="col-sm-12 col-md-12 col-lg-6 div_fisica">
		<div class="tipo_doc doc_CPF">
			<div class="col-sm-6">
			<label for="cpf">CPF:</label>
			<input type="text" name="cliente.identificacao.cpf"
				id="cpf" class="form-control cpf" value="${cliente.identificacao.cpf}" />
			</div>
			<div class="col-sm-6">
			<label for="cnpj">RG:</label>
			<input type="text" name="cliente.identificacao.rg"
				id="rg" class="form-control rg" value="${cliente.identificacao.rg}" />
			</div>
		</div>
		<div class="tipo_doc doc_CNPJ">
		<div class="col-sm-6">
		<label for="cnpj">CNPJ:</label>
		<input type="text" name="cliente.identificacao.cnpj"
			id="cnpj" class="form-control cnpj" value="${cliente.identificacao.cnpj}" />
		</div>
		<div class="col-sm-3">
		<label for="inscricao">IE:</label>
		<input type="text" name="cliente.identificacao.inscricao_estadual"
			id="inscricao_estadual" class="form-control" value="${cliente.identificacao.inscricao_estadual}" />
		</div>
		<div class="col-sm-3">
		<label for="inscricao">IM:</label>
		<input type="text" name="cliente.identificacao.inscricao_municipal"
			id="inscricao_municipal" class="form-control" value="${cliente.identificacao.inscricao_municipal}" />
		</div>
		</div>
	</div>
	
	<div class="col-sm-6 col-md-8 col-lg-3">   
            <label for="nome">Nome:</label>
            <input type="text" name="cliente.nome" id="nome" class="form-control"
            value="${cliente.nome}"/>
        </div>
		<div class="col-sm-12 col-md-5 col-lg-3">   
            <label for="rua">Endereço:</label>
            <input type="text" name="cliente.rua" id="rua" class="form-control"
            value="${cliente.rua}"/>
        </div>
		<div class="col-sm-6 col-md-4 col-lg-1">   
            <label for="num">Nº:</label>
            <input type="text" name="cliente.num" id="num" class="form-control"
            value="${cliente.num}"/>
        </div>
		<div class="col-sm-6 col-md-4 col-lg-2">   
            <label for="comp">Complt.:</label>
            <input type="text" name="cliente.comp" id="comp" class="form-control"
            value="${cliente.comp}"/>
        </div>
		<div class="col-sm-6 col-md-4 col-lg-3">   
            <label for="bai">Bairro:</label>
            <input type="text" name="cliente.bai" id="bai" class="form-control"
            value="${cliente.bai}"/>
       	</div>
		<div class="col-sm-6 col-md-4 col-lg-2">   
            <label for="cep">CEP:</label>
            <input type="text" name="cliente.cep" id="cep" 
            class="form-control cep" value="${cliente.cep}"/>
         </div>
        <div class="col-sm-12 col-md-5 col-lg-3">
            <label for="estado">Estado:</label>
            <select class="input-large form-control" name="cliente.estado" id="slct_estado">
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
            <input type="text" name="cliente.email" id="email" class="form-control"
            value="${cliente.email}"/>
        </div>
		
        <div class="col-sm-12 col-md-4 col-lg-2">
        <input type="button" value="Adicionar Telefone" id="btn_telefone"
        	 class="btn btn-primary botao_confirmar" onclick="adicionar_telefone();"/>
        </div>
        <div class="col-sm-12 col-md-6 col-lg-4">
		<label for="lbl_cliente_informacao">Informações:</label>
		<textarea class="form-control" rows="5" id="cliente_informacao" name="cliente.informacao" value="${cliente.informacao }"></textarea>
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