    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@taglib tagdir="/WEB-INF/tags" prefix="alura" %>
<c:import url="/WEB-INF/jsp/header.jsp"/>
<div class="col-xs-12 col-md-12 text-center">
	 <h2><label><span class="glyphicon glyphicon-wrench"></span> Solicitação Finalizada</label></h2>
</div>
<div class="col-xs-12 text-center">
<h3>Parabéns! o Veículo de Placa ${solicitacao.veiculo.placa } do ${solicitacao.veiculo.cliente.nome } teve a solicitação ${solicitacao.solicitacoes} cadastrada com sucesso ! O Que deseja fazer agora?</h3>    
</div>
<div class="col-xs-12 text-center">
<form method="get" style="display: inline;" action="${linkTo[SolicitacaoController].cadastrar_v(solicitacao.veiculo.id) }">
<button type="submit" class="btn btn-success">
  Cadastrar
</button>
</form>
<form method="get" action="${linkTo[ClienteController].listar('') }" style="display: inline;">
<button type="submit" class="btn btn-info">
  Lista
</button>
</form>
<form method="get" action="${linkTo[VeiculoController].visualizar(solicitacao.veiculo.id) }" style="display: inline;">
<button type="submit" class="btn btn-default">
  Visualizar
</button>
</form>
<form method="get" action="${linkTo[OrcamentoController].cadastro_solicitacao(solicitacao.id) }" style="display: inline;">
<button type="submit" class="btn btn-success">
  Orçamento
</button>
</form>
</div>
<c:import url="/WEB-INF/jsp/footer.jsp"/>