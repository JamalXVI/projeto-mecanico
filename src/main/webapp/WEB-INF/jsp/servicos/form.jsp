
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="alura"%>
<c:import url="/WEB-INF/jsp/header.jsp" />
<div class="col-xs-12 col-md-12 text-center">
	 <h2><label><span class="glyphicon glyphicon-copy"></span> Novo Serviço</label></h2>
 </div>
<form action="${linkTo[ServicosController].cadastrar(null) }" method="post" role="form">
<div class="text-center">
<label>Nome do Serviço:</label>
<input type="text" class="form-control" name="servico.nome" value="${servico.nome }" />
</div>

<div class="col-sm-6 col-md-8">  
       	<label>Finalizar:</label>
      	<input type="submit" value="Cadastrar" id="btn_finalizar"
        	 class="btn btn-primary botao_confirmar" />
</div>
</form>
<c:import url="/WEB-INF/jsp/footer.jsp" />