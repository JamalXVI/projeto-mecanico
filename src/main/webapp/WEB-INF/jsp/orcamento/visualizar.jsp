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
	 <h2><label><span class="glyphicon glyphicon-barcode"></span> Visualizar Orçamento</label></h2>
 </div>
    <div  class="col-md-4 col-xs-12 col-lg-3">
    <label for="nome">Nome do Dono:</label>
        <input type="text" name="cliente.nome" id="nome" class="form-control"
        value="${orcamento.solicitacao.veiculo.cliente.nome}" readonly="readonly"/>
        
     </div>
    <div  class="col-md-4 col-xs-12 col-lg-2">
    <label for="marca">Marca:</label>
        <input type="text" id="marca" class="form-control"
        value="${orcamento.solicitacao.veiculo.modelo.marca.nome}" readonly="readonly"/>
        
     </div>
     <div  class="col-md-4 col-xs-12 col-lg-2">
    <label for="marca">Modelo:</label>
        <input type="text" id="marca" class="form-control"
        value="${orcamento.solicitacao.veiculo.modelo.nome}" readonly="readonly"/>
        
     </div>
     <div  class="col-md-4 col-xs-12 col-lg-2">
    <label for="placa">Placa:</label>
        <input type="text" id="placa" class="form-control"
        value="${orcamento.solicitacao.veiculo.placa}" readonly="readonly"/>
        
     </div>
     <div  class="col-md-4 col-xs-12 col-lg-1">
    <label for="ano">Ano:</label>
        <input type="text" id="ano" class="form-control"
        value="${orcamento.solicitacao.veiculo.ano}" readonly="readonly"/>
        
     </div>
     <div  class="col-md-4 col-xs-12 col-lg-2">
    <label for="Cor">Cor:</label>
        <input type="text" id="marca" class="form-control"
        value="${orcamento.solicitacao.veiculo.cor }" readonly="readonly"/>
        
     </div>
     
     <div  class="col-md-8 col-xs-12 col-lg-3">
    <label for="solicitacao">Solitacão:</label>
        <input type="text" id="marca" class="form-control"
        value="${orcamento.solicitacao.solicitacoes}" readonly="readonly"/>
        
     </div>
     <div class="col-md-4 col-xs-12 col-lg-1 ">
     		<label>Executado</label>
     		<c:if test="${orcamento.atendido }">
     				<form method="GET" action="${linkTo[ExecucaoController].visualizar_o(orcamento.id) }">
     					<button type="submit" class="btn-success btn">Sim</button>
     				</form>
                    </c:if>
                    <c:if test="${!orcamento.atendido }">
                    <form method="GET" action="${linkTo[ExecucaoController].cadastro_orcamento(orcamento.id) }">
     					<button type="submit" class="btn-warning btn">Não</button>
     				</form>
                    </c:if>
     	</div>
     <c:if test="${orcamento.desconto > 0 }">
		<div class="col-md-4 col-xs-12 col-lg-2 ">
     		<label>Valor</label>
     		<input type="text" readonly="readonly" value="${orcamento.total + orcamento.desconto}"
     		 class="form-control  "/>
     	</div>
     	<div class="col-md-4 col-xs-12 col-lg-2 ">
     		<label>Desconto</label>
     		<input type="text" readonly="readonly" value="${orcamento.desconto}"
     		 class="form-control  "/>
     	</div>
		</c:if>
     	<div class="col-md-4 col-xs-12 col-lg-2 ">
     		<label>Total</label>
     		<input type="text" readonly="readonly" value="${orcamento.total}"
     		 class="form-control  "/>
     	</div>
     	<div class="col-md-6 col-xs-12 col-lg-2 ">
     		<label>Entrada</label>
     		<input type="text" readonly="readonly"
    		 value="<fmt:formatDate timeStyle="short" value="${orcamento.solicitacao.data_entrada.time}" type="BOTH"/>"
     		 class="form-control "/>
     	</div>
     	<div  class="col-md-4 col-xs-12 col-lg-1">
	    	<label for="ano">Código:</label>
	        <input type="text" id="ano" class="form-control"
	        value="${orcamento.id}" readonly="readonly"/>
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
     	<div class="col-xs-12 text-right">
     	<a href="#" onclick="iniciar_modal(${orcamento.id})"><span class="glyphicon glyphicon-trash"></span></a>
     	<a href="${linkTo[OrcamentoController].imprimir(orcamento.id) }"><span class="glyphicon glyphicon-print"></span></a>
     	<a href="${linkTo[OrcamentoController].editar(orcamento.id)}"><span class="glyphicon glyphicon-pencil"></span></a>
     	</div>
     	<c:import url="/WEB-INF/jsp/deletar/modal_deletar_orcamento.jsp" />
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