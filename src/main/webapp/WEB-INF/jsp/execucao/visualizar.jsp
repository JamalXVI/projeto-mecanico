    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@taglib tagdir="/WEB-INF/tags" prefix="alura" %>
    <c:import url="/WEB-INF/jsp/header.jsp"/>
    <link href="../../../css/bootstrap.css" rel="stylesheet" type="text/css" />
    <style>
		#tabClientes{
			clear:both;
		}
	</style>
	<div class="col-xs-12 col-md-12 text-center">
	 <h2><label><span class="glyphicon glyphicon-cog"></span>Visualizar Execução</label></h2>
 </div>
    <div  class="col-md-4 col-xs-12 col-lg-3">
    <label for="nome">Nome do Dono:</label>
        <input type="text" name="cliente.nome" id="nome" class="form-control"
        value="${executado.orcamento.solicitacao.veiculo.cliente.nome}" readonly="readonly"/>
        
     </div>
    <div  class="col-md-4 col-xs-12 col-lg-2">
    <label for="marca">Marca:</label>
        <input type="text" id="marca" class="form-control"
        value="${executado.orcamento.solicitacao.veiculo.modelo.marca.nome}" readonly="readonly"/>
        
     </div>
     <div  class="col-md-4 col-xs-12 col-lg-2">
    <label for="marca">Modelo:</label>
        <input type="text" id="marca" class="form-control"
        value="${executado.orcamento.solicitacao.veiculo.modelo.nome}" readonly="readonly"/>
        
     </div>
     <div  class="col-md-4 col-xs-12 col-lg-2">
    <label for="placa">Placa:</label>
        <input type="text" id="placa" class="form-control"
        value="${executado.orcamento.solicitacao.veiculo.placa}" readonly="readonly"/>
        
     </div>
     <div  class="col-md-4 col-xs-12 col-lg-1">
    <label for="ano">Ano:</label>
        <input type="text" id="ano" class="form-control"
        value="${executado.orcamento.solicitacao.veiculo.ano}" readonly="readonly"/>
        
     </div>
     <div  class="col-md-4 col-xs-12 col-lg-2">
    <label for="Cor">Cor:</label>
        <input type="text" id="marca" class="form-control"
        value="${executado.orcamento.solicitacao.veiculo.cor }" readonly="readonly"/>
        
     </div>
     <div  class="col-md-8 col-xs-12 col-lg-3">
    <label for="solicitacao">Solitacão:</label>
        <input type="text" id="marca" class="form-control"
        value="${executado.orcamento.solicitacao.solicitacoes}" readonly="readonly"/>
        
     </div>
     <c:if test="${executado.orcamento.desconto > 0 }">
		<div class="col-md-4 col-xs-12 col-lg-2 ">
     		<label>Valor</label>
     		<input type="text" readonly="readonly" value="${executado.orcamento.total + orcamento.desconto}"
     		 class="form-control  "/>
     	</div>
     	<div class="col-md-4 col-xs-12 col-lg-2 ">
     		<label>Desconto</label>
     		<input type="text" readonly="readonly" value="${executado.orcamento.desconto}"
     		 class="form-control  "/>
     	</div>
		</c:if>
     	<div class="col-md-4 col-xs-12 col-lg-2 ">
     		<label>Total</label>
     		<input type="text" readonly="readonly" value="${executado.orcamento.total}"
     		 class="form-control  "/>
     	</div>
     	<div class="col-md-6 col-xs-12 col-lg-2 ">
     		<label>Entrada</label>
     		<input type="text" readonly="readonly"
    		 value="<fmt:formatDate timeStyle="short"  value="${executado.orcamento.solicitacao.data_entrada.time}" type="BOTH"/>"
     		 class="form-control "/>
     	</div>
     	<div class="col-md-6 col-xs-12 col-lg-2 ">
     		<label>Saída</label>
     		<input type="text" readonly="readonly"
    		 value="<fmt:formatDate timeStyle="short"  value="${executado.entrega.time}" type="BOTH"/>"
     		 class="form-control "/>
     	</div>
     	<div class="col-md-6 col-xs-12 col-lg-2 ">
     		<label>Forma Pagamento</label>
     		<input type="text" readonly="readonly"
    		 value="${executado.forma_pagamento }"
     		 class="form-control "/>
     	</div>
     	<div class="col-md-6 col-xs-12 col-lg-3 ">
     		<label>Mecânico</label>
     		<input type="text" readonly="readonly"
    		 value="${executado.mecanico.cliente.nome }"
     		 class="form-control "/>
     	</div>
     	<div class="col-md-6 col-xs-12 col-lg-3 ">
     		<label>Autorizador</label>
     		<input type="text" readonly="readonly"
    		 value="${executado.autorizador.nome }"
     		 class="form-control "/>
     	</div>
     	<div class="col-md-6 col-xs-12 col-lg-3 ">
     		<label>Responsável</label>
     		<input type="text" readonly="readonly"
    		 value="${executado.responsavel }"
     		 class="form-control "/>
     	</div>
     	<div  class="col-md-4 col-xs-12 col-lg-1">
	    	<label for="codigo">Código:</label>
	        <input type="text" id="codigo" class="form-control"
	        value="${executado.id}" readonly="readonly"/>
    	</div>
     	<c:if test="${!executado.pago }">
     	<div class="col-md-4 col-xs-12 col-lg-1 ">
     		<label>Pendurar</label>
                    <form method="GET" action="${linkTo[PenduraController].cadastro_executado(executado.id) }">
     					<button type="submit" class="btn-warning btn">Pendurar</button>
     				</form>
                    
     	</div>
     	</c:if>
     	<div class="col-md-4 col-xs-12 col-lg-1 ">
     		<label>Pendurar</label>
     		<c:if test="${!executado.pago }">
     		<form method="GET" action="${linkTo[ExecucaoController].pagar(executado.id) }">
     					<button type="submit" class="btn-success btn">Pagar</button>
     				</form>
     		</c:if>
     		<c:if test="${executado.pago }">
     		<input type="text" id="pago" class="form-control"
	        value="Pago" readonly="readonly"/>
     		</c:if>
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
		     	<c:forEach items="${executado.orcamento.pecas }" var="peca">
		     		<tr><td>${peca.peca.nome }</td><td>${peca.preco_venda }</td><td>${peca.quantidade_usada }</td>
		     		<td>${peca.preco_venda * peca.quantidade_usada }</td></tr>
		     	</c:forEach>
		     	<tr><td><b>Serviços</b></td><td></td><td></td><td></td></tr>
		     	<c:forEach items="${executado.orcamento.servicos }" var="servico">
		     		<tr><td>${servico.servico.nome }</td><td>${servico.preco }</td><td>-</td><td>${servico.preco }</td></tr>
		     	</c:forEach>
		     </tbody>
     	</table>
     	</div>
     	<div class="col-xs-12 text-right">
     	<a href="#" onclick="iniciar_modal(${executado.id})"><span class="glyphicon glyphicon-trash"></span></a>
     	<a href="${linkTo[ExecucaoController].imprimir(executado.id) }"><span class="glyphicon glyphicon-print"></span></a>
     	<a href="${linkTo[PenduraController].lista_execucao(executado.id) }"><span class="glyphicon glyphicon-list-alt"></span></a>
     	</div>
     	<c:import url="/WEB-INF/jsp/deletar/modal_deletar_executado.jsp" />
<script>
	function iniciar_modal(id){
		$("#id_esc_deletar").val(id);
		$("#deletarModal").modal("show");
	}
 </script>
 <script type="text/javascript">
 $("td").css("padding-bottom", "0px");
 $("td").css("padding-top", "0px");
 </script>
<c:import url="/WEB-INF/jsp/footer.jsp"/>