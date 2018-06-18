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
	 <h2><label><span class="glyphicon glyphicon-bed"></span> Visualizar Veículo</label></h2>
 </div>
    <div  class="col-md-4 col-xs-12 col-lg-3">
    <label for="nome">Nome do Dono:</label>
        <input type="text" name="cliente.nome" id="nome" class="form-control"
        value="${veiculo.cliente.nome}" readonly="readonly"/>
        
     </div>
    <div  class="col-md-4 col-xs-12 col-lg-2">
    <label for="marca">Marca:</label>
        <input type="text" id="marca" class="form-control"
        value="${veiculo.modelo.marca.nome}" readonly="readonly"/>
        
     </div>
     <div  class="col-md-4 col-xs-12 col-lg-2">
    <label for="marca">Modelo:</label>
        <input type="text" id="marca" class="form-control"
        value="${veiculo.modelo.nome}" readonly="readonly"/>
        
     </div>
     <div  class="col-md-4 col-xs-12 col-lg-2">
    <label for="placa">Placa:</label>
        <input type="text" id="placa" class="form-control"
        value="${veiculo.placa}" readonly="readonly"/>
        
     </div>
     <div  class="col-md-4 col-xs-12 col-lg-1">
    <label for="ano">Ano:</label>
        <input type="text" id="ano" class="form-control"
        value="${veiculo.ano}" readonly="readonly"/>
        
     </div>
     <div  class="col-md-4 col-xs-12 col-lg-2">
    <label for="Cor">Cor:</label>
        <input type="text" id="marca" class="form-control"
        value="${veiculo.cor }" readonly="readonly"/>
        
     </div>
     <div class="col-xs-12 text-center">
        <h3 style="display: inline">Solicitações</h3>
        <a 
        	href="${linkTo[SolicitacaoController].cadastrar_v(veiculo.id)}" class="mais">
        	<span class="glyphicon-stack">
			<i class="glyphicon glyphicon-circle glyphicon-stack-2x"></i>
			<i class="glyphicon glyphicon-plus glyphicon-stack glyphicon-stack-1x"></i>
			</span></a>
        </div>
        
        <div class="col-xs-12">
        <table class="table table-hover table-bordered table-striped tablesorter" id="tabClientes">
        <thead>
            <tr>
            	<th>Código</th>
            	<th>Descrição</th>
                <th>Data</th>
                <th>Fotos</th>
                <th>Orçada?</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody> 
        <c:forEach items="${veiculo.solicitacoes }" var="solicitacao">
        <c:if test="${solicitacao.ativo }">
        <tr><td>${solicitacao.id }</td>
        <td>${solicitacao.solicitacoes }</td>
        <td><fmt:formatDate timeStyle="short"  value="${solicitacao.data_entrada.time}" type="BOTH"></fmt:formatDate></td>
        <td><c:forEach items="${solicitacao.fotos }" var="foto">
        <a href="${linkTo[VeiculoController].imagem(foto.id) }"><span class="glyphicon glyphicon-picture"></span></a>
        </c:forEach> </td>
        <td><c:if test="${solicitacao.atendida }">
        <a href="${linkTo[OrcamentoController].visualizar_s(solicitacao.id)}" onclick="">Sim</a>
        </c:if>
        <c:if test="${!solicitacao.atendida }">
        <a href="${linkTo[OrcamentoController].cadastro_solicitacao(solicitacao.id) }">Não</a></c:if> </td>
        <td>
        <a href="#" onclick="iniciar_modal(${solicitacao.id})">
                    <span class="glyphicon glyphicon-trash"></span></a>
        </td></tr>
        </c:if>
        </c:forEach>
        </tbody>
        </table>
        
        </div>
        <c:import url="/WEB-INF/jsp/deletar/modal_deletar_solicitacao.jsp" />
<script>
	function iniciar_modal(id){
		$("#id_esc_deletar").val(id);
		$("#deletarModal").modal("show");
	}
 </script>
<c:import url="/WEB-INF/jsp/footer.jsp"/>