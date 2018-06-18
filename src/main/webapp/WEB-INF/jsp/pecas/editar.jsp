
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="alura"%>
<c:import url="/WEB-INF/jsp/header.jsp" />
<div class="col-xs-12">
		<h2 class="text-center"><label><span class="glyphicon glyphicon-oil"></span> Editar Peça</label></h2>
</div>
<form action="${linkTo[PecasController].editado(null, null) }" method="post" role="form">
<div class="col-sm-6 col-md-6 col-lg-3">
<label>Nome da Peça:</label>
<input type="hidden" name="peca.id" value="${peca.id }" />
<input type="text" class="form-control" readonly="readonly"
 name="peca.nome" value="${peca.nome }" />
</div>
<div class="col-sm-6 col-md-6 col-lg-3">
<label>Código Fiscal:</label>
<input type="text" class="form-control" name="peca.cod_fiscal" value="${peca.cod_fiscal }" />
</div>
<div class="col-sm-6 col-md-6 col-lg-3">
	<label>Quantidade em Estoque</label>
	 <input id="qtd_estoque" type="text" name="peca.estoque" value="${peca.estoque}" />
</div>
<div class="col-sm-6 col-md-6 col-lg-3">
<label>Fabricante</label>
<input type="hidden" value="${peca.fornecedor.id }" name="id_fornecedor" />
<input type="text" class="form-control" readonly="readonly"
 value="${peca.fornecedor.nome }" />
</div>

<div class="col-sm-6 col-md-8">  
       	<label>Finalizar:</label>
      	<input type="submit" value="Editar" id="btn_finalizar"
        	 class="btn btn-primary botao_confirmar" />
</div>
</form>
<script>
            $("input[name='peca.estoque']").TouchSpin({
                min: 0,
                max: 100000,
                step: 1,
                decimals: 0,
                boostat: 5,
                maxboostedstep: 10,
            });
</script>
<c:import url="/WEB-INF/jsp/footer.jsp" />