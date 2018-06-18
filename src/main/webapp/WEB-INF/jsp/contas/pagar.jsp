    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@taglib tagdir="/WEB-INF/tags" prefix="alura" %>
<c:import url="/WEB-INF/jsp/header.jsp"/>
<div class="col-xs-12 col-md-12 text-center">
	 <h2><label><span class="glyphicon glyphicon-usd"></span>Conta Finalizada</label></h2>
 </div>
<div class="col-xs-12 text-center">
<h3>Parabéns! a Conta ${conta.nome } foi paga com sucesso! O que deseja fazer agora?</h3>    
</div>
<div class="col-xs-12 text-center">
<form method="get" style="display: inline;" action="${linkTo[ContasController].form() }">
<button type="submit" class="btn btn-success">
  Cadastrar
</button>
</form>
<form method="get" action="${linkTo[ContasController].lista() }" style="display: inline;">
<button type="submit" class="btn btn-info">
  Lista
</button>
</form>
</div>
<c:import url="/WEB-INF/jsp/footer.jsp"/>