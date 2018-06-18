
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="alura"%>
<c:import url="/WEB-INF/jsp/header.jsp" />
<div class="col-xs-12">
	<h2 class="text-center"><label><span class="glyphicon glyphicon-send"></span> Novo Modelo</label></h2>
	</div>
<form action="${linkTo[ModeloController].cadastrar(null) }" method="post" role="form">
<div class="col-sm-6">
<label for="tipo_documento">Marca:</label>
<select class="input-large form-control" id="id_marca" name="id">
<c:forEach var="marca" items="${marcas }">
<c:if test="${ marca_a.id == marca.id}">
<option value="${marca.id }" selected="selected">${marca.nome }</option>
</c:if>
<c:if test="${ marca_a.id != marca.id}">
<option value="${marca.id }">${marca.nome }</option>
</c:if>
</c:forEach>
</select>
</div>
<div class="col-sm-6">
<label>Nome da Modelo</label>
<input type="text" class="form-control" name="modelo.nome" value="${marca.nome }" />
</div>

<div class="col-sm-6 col-md-8">  
       	<label>Finalizar:</label>
      	<input type="submit" value="Cadastrar" id="btn_finalizar"
        	 class="btn btn-primary botao_confirmar" />
</div>
</form>
<c:import url="/WEB-INF/jsp/footer.jsp" />