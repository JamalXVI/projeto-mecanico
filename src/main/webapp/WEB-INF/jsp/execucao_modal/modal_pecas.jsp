<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal fade" id="pecasModal" tabindex="-1" role="dialog" aria-labelledby="pecasModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="pecasModalLabel">Selecionar Placa</h4>
      </div>
      <div class="modal-body">
        <div class="col-sm-12">
        <div class="input-group">
  			<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-search"></span></span>
        	<input class="form-control input" type="text" placeholder="Filtrar Peça" id="pesq_pecas" />
       	</div>
       	</div>
       	<div class="col-sm-12">
       	<table class="table table-hover table-bordered table-striped" id="tabPecas">
       		<thead>
       			<tr>
       				<th>Nome</th>
       				<th>Fornecedor</th>
       				<th>Estoque</th>
       			</tr>
       		</thead>
       		<tbody id="corpoPecas">
       		</tbody>
       	</table>
       	</div>
       	<div class="col-sm-12">
       		<label for="peca_escolhida">Nome da Peça Escolhida:</label>
       		<input type="hidden" readonly="readonly" id="id_peca_escolhida" value="" />
       		<input type="hidden" readonly="readonly" id="qtd_total_peca" value="" />
       		<input type="text" readonly="readonly" class="form-control" id="peca_escolhida" value="" />
       	</div>
       	<div class="col-sm-6">
      	<label for="valor_pago">Preço de Custo:</label>
      	<input class="form-control valor_rev" id="valor_pago" type="text"/>
      </div>
      <div class="col-sm-6">
      	<label for="valor_porcentagem">Porcentagem:</label>
      	<input class="form-control porcentagem_rev" id="valor_porcentagem" type="text"/>
      </div>
      <div class="col-sm-6">
      	<label for="valor_venda">Preço de Venda:</label>
      	<input class="form-control valor_rev" id="valor_venda" type="text"/>
      </div>
      <div class="col-sm-6">
      	<label for="pecas_quantidade">Quantidade:</label>
      	<input class="form-control quantidade" id="pecas_quantidade" type="text"/>
      </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="finalizar_peca();">Finalizar</button>
      </div>
    </div>
  </div>
</div>