    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@taglib tagdir="/WEB-INF/tags" prefix="alura" %>
<c:import url="/WEB-INF/jsp/header.jsp"/>
<div class="col-xs-12">
	<h2 class="text-center"><label><span class="glyphicon glyphicon-send"></span> Modelo Finalizado</label></h2>
	</div>
<div class="col-xs-12 text-center">
<h3>Parab�ns! O modelo ${modelo.nome} da marca ${modelo.marca.nome} foi cadastrado com sucesso! O que deseja fazer agora?</h3>    
</div>
<div class="col-xs-12 text-center">
<form method="get" style="display: inline;" action="${linkTo[ModeloController].form() }">
<button type="submit" class="btn btn-success">
  Modelo
</button>
</form>
<form method="get" action="${linkTo[ClienteController].listar('') }" style="display: inline;">
<button type="submit" class="btn btn-info">
  Lista
</button>
</form>
<form method="get" action="${linkTo[VeiculoController].cadastro_modelo(modelo.id) }" style="display: inline;">
<button type="submit" class="btn btn-success">
  Ve�culo
</button>
</form>
</div>
<c:import url="/WEB-INF/jsp/footer.jsp"/>