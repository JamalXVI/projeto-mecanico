
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="alura"%>
<c:import url="/WEB-INF/jsp/header.jsp" />
<div class="col-xs-12">
	<h2 class="text-center"><label><span class="glyphicon glyphicon-plane"></span> Nova Marca</label></h2>
	</div>
<form action="${linkTo[MarcaController].cadastrar(null) }" method="post" role="form">
<div class="text-center">
<label>Nome da Marca</label>
<input type="text" class="form-control" name="marca.nome" value="${marca.nome }" />
</div>
<div class="col-sm-6 col-md-8">  
       	<label>Finalizar:</label>
      	<input type="submit" value="Cadastrar" id="btn_finalizar"
        	 class="btn btn-primary botao_confirmar" />
</div>
</form>
<c:import url="/WEB-INF/jsp/footer.jsp" />