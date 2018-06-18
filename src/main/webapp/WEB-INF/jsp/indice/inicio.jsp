<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:import url="/WEB-INF/jsp/header.jsp" />
<div class="col-xs-12 col-md-4 col-lg-2">

	<img src="<c:url value="/Imagem/Cliente/${funcionario.cliente.id }/" />"  width="180" height="180"
    class="img-circle"/>
</div>
<div class="col-xs-12 col-md-8 col-lg-10">
<div class="alert alert-info" role="alert">
Seja Bem-Vindo ${funcionario.cliente.nome } !
</div>
</div>
<div class="col-xs-12 text-center">
<h4>Solicitações Não Orçadas</h4>
</div>
<div class="col-xs-12">
<table class="table table-hover table-bordered table-striped tablesorter" id="tabSolicitacoes">
        <thead>
            <tr>
            	<th>Código</th>
            	<th>Solicitacao</th>
                <th>Veiculo</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody> 
          <c:forEach items="${solicitacoes}" var="solicitacao">
                <tr>
                	<td>
                	${solicitacao.id }
                	</td>
               		<td>
               		${solicitacao.solicitacoes }
                	</td>
                    <td>${solicitacao.veiculo.modelo.nome} - ${solicitacao.veiculo.placa }</td>
                    <td>
                    <a href="${linkTo[VeiculoController].visualizar(solicitacao.veiculo.id)}">
                    <span class="glyphicon glyphicon-search"></span></a>
                    <a href="${linkTo[OrcamentoController].cadastro_solicitacao(solicitacao.id) }">
                    <span class="glyphicon glyphicon-cog"></span></a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<div class="col-xs-12 text-center">
<h4>Orçamentos Não Executados</h4>
</div>
<div class="col-xs-12">
<table class="table table-hover table-bordered table-striped tablesorter" id="tabClientes">
        <thead>
            <tr>
            	<th>Código</th>
            	<th>Solicitacao</th>
                <th>Veiculo</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody> 
          <c:forEach items="${orcamentos}" var="orcamento">
                <tr>
                	<td>${orcamento.id }</td>
               		<td>
               		${orcamento.solicitacao.solicitacoes }
                	</td>
                    <td>${orcamento.solicitacao.veiculo.modelo.nome} - ${orcamento.solicitacao.veiculo.placa }</td>
                    <td>
                    <a href="${linkTo[OrcamentoController].visualizar(orcamento.id)}">
                    <span class="glyphicon glyphicon-search"></span></a>
                    <a href="${linkTo[ExecucaoController].cadastro_orcamento(orcamento.id) }">
                    <span class="glyphicon glyphicon-cog"></span></a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<c:import url="/WEB-INF/jsp/footer.jsp" />