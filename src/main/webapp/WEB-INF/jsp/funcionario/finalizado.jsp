    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@taglib tagdir="/WEB-INF/tags" prefix="alura" %>
<c:import url="/WEB-INF/jsp/header.jsp"/>
<div class="col-xs-12">
		<h2 class="text-center"><label><span class="glyphicon glyphicon-user"></span> Funcionário Finalizado</label></h2>
</div>
<div class="col-xs-12 text-center">
<h3>Parabéns! o Funcionário ${funcionario.cliente.nome } foi cadastrado com sucesso! O Que deseja fazer agora?</h3>    
</div>
<div class="col-xs-12 text-center">
<form method="get" style="display: inline;" action="${linkTo[FuncionarioController].form() }">
<button type="submit" class="btn btn-success">
  Cadastrar
</button>
</form>
<form method="get" action="${linkTo[FuncionarioController].listar('') }" style="display: inline;">
<button type="submit" class="btn btn-info">
  Lista
</button>
</form>
<form method="get" action="${linkTo[FuncionarioController].visualizar(funcionario.id) }" style="display: inline;">
<button type="submit" class="btn btn-default">
  Visualizar
</button>
</form>
<form method="get" action="${linkTo[VeiculoController].cadastrar_c(funcionario.cliente.id) }" style="display: inline;">
<button type="submit" class="btn btn-success">
  Novo Veiculo
</button>
</form>
</div>
<c:import url="/WEB-INF/jsp/footer.jsp"/>