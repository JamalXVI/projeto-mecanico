    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@taglib tagdir="/WEB-INF/tags" prefix="alura" %>
<c:import url="/WEB-INF/jsp/header.jsp"/>
<div class="col-xs-12">
	<h2 class="text-center"><label><span class="glyphicon glyphicon-compressed"></span> Fornecedor Finalizado</label></h2>
</div>
<div class="col-xs-12 text-center">
<h3>Parabéns! o Fabricante ${fornecedor.nome } foi cadastrado com sucesso! O que deseja fazer agora?</h3>    
</div>
<div class="col-xs-12 text-center">
<form method="get" style="display: inline;" action="${linkTo[FornecedorController].form() }">
<button type="submit" class="btn btn-success">
  Cadastrar
</button>
</form>
<form method="get" action="${linkTo[FornecedorController].listar('') }" style="display: inline;">
<button type="submit" class="btn btn-info">
  Lista
</button>
</form>
<form method="get" action="${linkTo[PecasController].cadastrar_f(fornecedor.id) }"
 style="display: inline;">
<button type="submit" class="btn btn-success">
  Peças
</button>
</form>
</div>
<c:import url="/WEB-INF/jsp/footer.jsp"/>