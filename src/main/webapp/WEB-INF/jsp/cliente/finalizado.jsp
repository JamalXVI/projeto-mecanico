    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@taglib tagdir="/WEB-INF/tags" prefix="alura" %>
<c:import url="/WEB-INF/jsp/header.jsp"/>
<div class="col-xs-12 col-md-12 text-center">
	 <h2><label><span class="glyphicon glyphicon-user"></span>Cliente Finalizado</label></h2>
 </div>
<div class="col-xs-12 text-center">
<h3>Parab�ns! o cliente ${cliente.nome } foi cadastrado com sucesso! O Que deseja fazer agora?</h3>    
</div>
<div class="col-xs-12 text-center">
<form method="get" style="display: inline;" action="${linkTo[ClienteController].form() }">
<button type="submit" class="btn btn-success">
  Cadastrar
</button>
</form>
<form method="get" action="${linkTo[ClienteController].listar('') }" style="display: inline;">
<button type="submit" class="btn btn-info">
  Lista
</button>
</form>
<form method="get" action="${linkTo[ClienteController].visualizar(cliente.id) }" style="display: inline;">
<button type="submit" class="btn btn-default">
  Visualizar
</button>
</form>
<form method="get" action="${linkTo[VeiculoController].cadastrar_c(cliente.id) }" style="display: inline;">
<button type="submit" class="btn btn-success">
  Novo Veiculo
</button>
</form>
</div>
<c:import url="/WEB-INF/jsp/footer.jsp"/>