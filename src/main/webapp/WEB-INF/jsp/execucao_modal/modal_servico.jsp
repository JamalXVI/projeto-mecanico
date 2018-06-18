<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal fade" id="servicoModal" tabindex="-1" role="dialog" aria-labelledby="servicoModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="servicoModalLabel">Selecionar Serviço</h4>
      </div>
      <div class="modal-body">
        <div class="col-sm-12">
        <div class="input-group">
  			<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-search"></span></span>
        	<input class="form-control input" type="text" placeholder="Filtrar Peça" id="pesq_servico" />
       	</div>
       	</div>
       	<div class="col-sm-12">
       	<table class="table table-hover table-bordered table-striped" id="tabServico">
       		<thead>
       			<tr>
       				<th>Nome</th>
       			</tr>
       		</thead>
       		<tbody id="corpoServico">
       		</tbody>
       	</table>
       	</div>
       	<div class="col-sm-12">
       		<label for="servico_escolhido">Nome da Peça Escolhida:</label>
       		<input type="hidden" readonly="readonly" id="id_servico_escolhido" value="" />
       		<input type="text" readonly="readonly" class="form-control" id="servico_escolhido" value="" />
       	</div>
       <div class="col-sm-6">
      	<label for="valor_pago">Preço do Serviço:</label>
      	<input class="form-control valor_rev" id="servico_pago" type="text"/>
      </div>
       <div class="col-sm-6">
      	<label for="valor_pago">Número de Horas:</label>
      	<input class="form-control numero_horas" id="servico_horas" type="text"/>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="finalizar_servico();">Finalizar</button>
      </div>
    </div>
  </div>
</div>