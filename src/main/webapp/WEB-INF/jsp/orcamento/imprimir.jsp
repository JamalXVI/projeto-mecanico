    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@taglib tagdir="/WEB-INF/tags" prefix="alura" %>
<html  moznomarginboxes mozdisallowselectionprint>
<head>
<title>${nome_mec }</title>
<link href="<c:url value='/css/bootstrap.css' />" rel="stylesheet" />
<link href="<c:url value='/css/bootstrap-combobox.css' />"
	rel="stylesheet" />
<link href="<c:url value='/css/jquery.bootstrap-touchspin.css' />"
	rel="stylesheet" />
<link href="<c:url value='/css/bootstrap-datetimepicker.css' />"
	rel="stylesheet" />
<link href="<c:url value='/css/site.css' />" rel="stylesheet" />
<link href="<c:url value='/css/chartist.min.css' />" rel="stylesheet" />
<meta name="viewport" content="width=device-width" />
<script type="text/javascript"
	src="<c:url value="/js/jquery-latest.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/__jquery.tablesorter.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery.tablesorter.pager.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/chili-1.8b.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/docs.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/examples.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/highlight.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/bootstrap-combobox.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/moment-with-locales.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/bootstrap-datetimepicker.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery.mask.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/chartist.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery.bootstrap-touchspin.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/Gruntfile.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.mask.js"/>"></script>
<style>
	 @media print {
  .col-sm-1, .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8, .col-sm-9, .col-sm-10, .col-sm-11, .col-sm-12 {
    float: left;
    
  }
  .container{
  	width: 100%;
  }
  .col-sm-12 {
    width: 100%;
  }
  .col-sm-11 {
    width: 91.66666667%;
  }
  .col-sm-10 {
    width: 83.33333333%;
  }
  .col-sm-9 {
    width: 75%;
  }
  .col-sm-8 {
    width: 66.66666667%;
  }
  .col-sm-7 {
    width: 58.33333333%;
  }
  .col-sm-6 {
    width: 50%;
  }
  .col-sm-5 {
    width: 41.66666667%;
  }
  .col-sm-4 {
    width: 33.33333333%;
  }
  .col-sm-3 {
    width: 25%;
  }
  .col-sm-2 {
    width: 16.66666667%;
  }
  .col-sm-1 {
    width: 8.33333333%;
  }
  .col-sm-pull-12 {
    right: 100%;
  }
  .col-sm-pull-11 {
    right: 91.66666667%;
  }
  .col-sm-pull-10 {
    right: 83.33333333%;
  }
  .col-sm-pull-9 {
    right: 75%;
  }
  .col-sm-pull-8 {
    right: 66.66666667%;
  }
  .col-sm-pull-7 {
    right: 58.33333333%;
  }
  .col-sm-pull-6 {
    right: 50%;
  }
  .col-sm-pull-5 {
    right: 41.66666667%;
  }
  .col-sm-pull-4 {
    right: 33.33333333%;
  }
  .col-sm-pull-3 {
    right: 25%;
  }
  .col-sm-pull-2 {
    right: 16.66666667%;
  }
  .col-sm-pull-1 {
    right: 8.33333333%;
  }
  .col-sm-pull-0 {
    right: auto;
  }
  .col-sm-push-12 {
    left: 100%;
  }
  .col-sm-push-11 {
    left: 91.66666667%;
  }
  .col-sm-push-10 {
    left: 83.33333333%;
  }
  .col-sm-push-9 {
    left: 75%;
  }
  .col-sm-push-8 {
    left: 66.66666667%;
  }
  .col-sm-push-7 {
    left: 58.33333333%;
  }
  .col-sm-push-6 {
    left: 50%;
  }
  .col-sm-push-5 {
    left: 41.66666667%;
  }
  .col-sm-push-4 {
    left: 33.33333333%;
  }
  .col-sm-push-3 {
    left: 25%;
  }
  .col-sm-push-2 {
    left: 16.66666667%;
  }
  .col-sm-push-1 {
    left: 8.33333333%;
  }
  .col-sm-push-0 {
    left: auto;
  }
  .col-sm-offset-12 {
    margin-left: 100%;
  }
  .col-sm-offset-11 {
    margin-left: 91.66666667%;
  }
  .col-sm-offset-10 {
    margin-left: 83.33333333%;
  }
  .col-sm-offset-9 {
    margin-left: 75%;
  }
  .col-sm-offset-8 {
    margin-left: 66.66666667%;
  }
  .col-sm-offset-7 {
    margin-left: 58.33333333%;
  }
  .col-sm-offset-6 {
    margin-left: 50%;
  }
  .col-sm-offset-5 {
    margin-left: 41.66666667%;
  }
  .col-sm-offset-4 {
    margin-left: 33.33333333%;
  }
  .col-sm-offset-3 {
    margin-left: 25%;
  }
  .col-sm-offset-2 {
    margin-left: 16.66666667%;
  }
  .col-sm-offset-1 {
    margin-left: 8.33333333%;
  }
  .col-sm-offset-0 {
    margin-left: 0%;
  }
  .visible-xs {
    display: none !important;
  }
  .hidden-xs {
    display: block !important;
  }
  table.hidden-xs {
    display: table;
  }
  tr.hidden-xs {
    display: table-row !important;
  }
  th.hidden-xs,
  td.hidden-xs {
    display: table-cell !important;
  }
  .hidden-xs.hidden-print {
    display: none !important;
  }
  .hidden-sm {
    display: none !important;
  }
  .visible-sm {
    display: block !important;
  }
  table.visible-sm {
    display: table;
  }
  tr.visible-sm {
    display: table-row !important;
  }
  th.visible-sm,
  td.visible-sm {
    display: table-cell !important;
  }
  html, body {
    /*changing width to 100% causes huge overflow and wrap*/
    height:100%; 
    overflow: hidden;
    background: #FFF; 
    font-size: 9.5pt;
  }

    tr { line-height: 14px;
	 font-size: small; };
}
@media print{@page {size: portrait;}}
.input-xs {
    height: 20px;
    padding: 2px 5px;
    font-size: 10px;
    line-height: 1.5;
    border-radius: 3px;
    };


@media print{@page { margin: 0mm; }}
</style>
<style>
tr { line-height: 14px;
	 font-size: smaller; };
</style>
<style>
.container{
  	width: 100%;
  	}
</style>
</head>
<body>
	<div class="container" id="main">
		<main>
		<div class="col-sm-12 text-center">
				<img src='<c:url value="/resources/imagens/NV.png"></c:url>'
					width="50" height="50"></img>
				<h6>${nome_mec}, ${rua }, Nº ${num }, ${cidade }, Telefone: ${telefone }</h6>
			</div>
			<div class="col-sm-12 text-center">
				<h5>Dados do Cliente</h5>
			</div>
			<div class="col-sm-5 ">
				<label>Cliente</label>
				<input type="text" readonly="readonly" value="${orcamento.solicitacao.veiculo.cliente.nome }" 
				class="form-control input-xs"/>
			</div>
			<div class="col-sm-4 ">
	     		<label>Email</label>
	     		<input type="text" readonly="readonly" value="${orcamento.solicitacao.veiculo.cliente.email }" 
	     		class="form-control  input-xs"/>
     		</div>
			<c:if test="${orcamento.solicitacao.veiculo.cliente.identificacao.tipo == 'CPF' }">
     	 	<div class="col-sm-3 ">
     	 	<label>CPF</label>
     	 	<input type="text" value="${orcamento.solicitacao.veiculo.cliente.identificacao.cpf }"
     	 	 class="form-control  input-xs" readonly="readonly">
     	 	</div>
     	 	<div class="col-sm-3 ">
     	 	<label>RG</label>
     	 	<input type="text" value="${orcamento.solicitacao.veiculo.cliente.identificacao.rg }"
     	 	 class="form-control  input-xs" readonly="readonly">
     	 	</div>
     	</c:if>
     	<c:if test="${orcamento.solicitacao.veiculo.cliente.identificacao.tipo != 'CPF' }">
     	 	<div class="col-sm-3 ">
     	 	<label>CNPJ</label>
     	 	<input type="text" value="${orcamento.solicitacao.veiculo.cliente.identificacao.cnpj }" 
     	 	class="form-control  input-xs" readonly="readonly">
     	 	</div>
     	 	<div class="col-sm-3 ">
     	 	<label>IE</label>
     	 	<input type="text" value="${orcamento.solicitacao.veiculo.cliente.identificacao.inscricao_estadual }"
     	 	 class="form-control  input-xs" readonly="readonly">
     	 	</div>
     	 	<div class="col-sm-3 ">
     	 	<label>IM</label>
     	 	<input type="text" value="${orcamento.solicitacao.veiculo.cliente.identificacao.inscricao_municipal }"
     	 	 class="form-control  input-xs" readonly="readonly">
     	 	</div>
     	</c:if>
     	
     	<div class="col-sm-6">
     		<label>Endereço</label>
     		<input type="text" readonly="readonly" value="${orcamento.solicitacao.veiculo.cliente.rua }" 
     		class="form-control  input-xs"/>
     	</div>
     	<div class="col-sm-3">
     		<label>Número</label>
     		<input type="text" readonly="readonly" value="${orcamento.solicitacao.veiculo.cliente.num }" 
     		class="form-control  input-xs"/>
     	</div>
     	<div class="col-sm-3 ">
     		<label>Complemento</label>
     		<input type="text" readonly="readonly" value="${orcamento.solicitacao.veiculo.cliente.comp }" 
     		class="form-control  input-xs"/>
     	</div>
     	<div class="col-sm-3 ">
     		<label>Bairro</label>
     		<input type="text" readonly="readonly" value="${orcamento.solicitacao.veiculo.cliente.bai }" 
     		class="form-control  input-xs"/>
     	</div>
     	<div class="col-sm-12 text-center">
				<h5>Dados do Veículo</h5>
			</div>
     	<div class="col-sm-3 ">
     		<label>Modelo do Veículo</label>
     		<input type="text" readonly="readonly" value="${orcamento.solicitacao.veiculo.modelo.nome }" 
     		class="form-control  input-xs"/>
     	</div>
     	<div class="col-sm-3 ">
     		<label>Placa do Veículo</label>
     		<input type="text" readonly="readonly" value="${orcamento.solicitacao.veiculo.placa }" 
     		class="form-control  input-xs"/>
     	</div>
     	<div class="col-sm-3 ">
     		<label>Cor do Veículo</label>
     		<input type="text" readonly="readonly" value="${orcamento.solicitacao.veiculo.cor }"
     		 class="form-control  input-xs"/>
     	</div>
     	<div class="col-sm-3 ">
     		<label>Ano de Fabricação</label>
     		<input type="text" readonly="readonly" value="${orcamento.solicitacao.veiculo.ano }"
     		 class="form-control  input-xs"/>
     	</div>
     	<div class="col-sm-12 text-center">
				<h5>Dados do Orçamento</h5>
		</div>
		<div class="col-sm-3">
		<label>Código Orçamento</label>
		<input type="text" readonly="readonly" value="${orcamento.id}"
     		 class="form-control  input-xs"/>
		</div>
		<c:if test="${orcamento.desconto > 0 }">
		<div class="col-sm-3 ">
     		<label>Valor</label>
     		<input type="text" readonly="readonly" value="${orcamento.total + orcamento.desconto}"
     		 class="form-control  input-xs"/>
     	</div>
     	<div class="col-sm-3 ">
     		<label>Desconto</label>
     		<input type="text" readonly="readonly" value="${orcamento.desconto}"
     		 class="form-control  input-xs"/>
     	</div>
		</c:if>
     	<div class="col-sm-3 ">
     		<label>Total</label>
     		<input type="text" readonly="readonly" value="${orcamento.total}"
     		 class="form-control  input-xs"/>
     	</div>
     	<div class="col-sm-3 ">
     		<label>Entrada</label>
     		<input type="text" readonly="readonly"
    		 value="<fmt:formatDate  timeStyle="short" value="${orcamento.solicitacao.data_entrada.time}" type="BOTH"/>"
     		 class="form-control  input-xs"/>
     	</div>
     	<div class="col-sm-3 ">
     		<label>Autorizado Por:</label>
     		<input type="text" readonly="readonly" class="form-control  input-xs"/>
     	</div>
     	<div class="col-sm-3">
     		<label>Forma Pagamento	:</label>
     		<input type="text" readonly="readonly" class="form-control  input-xs"/>
     	</div>
     	<div class="col-sm-12 text-center">
				<h5>Itens e Serviços</h5>
		</div>
     	<div class="col-sm-12">
     	<table class="table  table-hover table-bordered table-striped">
     		<thead>
		       	<tr>
		       		<th>Nome</th>
		       		<th>Preço Un.</th>
		       		<th>Quantidade</th>
		       		<th>Total</th>
		       	</tr>
		     </thead>
		     <tbody id="corpoComprado">
		     	<tr><td><b>Produtos</b></td><td></td><td></td><td></td></tr>
		     	<c:forEach items="${orcamento.pecas }" var="peca">
		     		<tr><td>${peca.peca.nome }</td><td>${peca.preco_venda }</td><td>${peca.quantidade_usada }</td>
		     		<td>${peca.preco_venda * peca.quantidade_usada }</td></tr>
		     	</c:forEach>
		     	<tr><td><b>Serviços</b></td><td></td><td></td><td></td></tr>
		     	<c:forEach items="${orcamento.servicos }" var="servico">
		     		<tr><td>${servico.servico.nome }</td><td>${servico.preco }</td><td>-</td><td>${servico.preco }</td></tr>
		     	</c:forEach>
		     </tbody>
     	</table>
     	</div>
     	<script type="text/javascript">
 $("td").css("padding-bottom", "0px");
 $("td").css("padding-top", "0px");
 </script>
		</main>
	</div>
</body>
</html>
		