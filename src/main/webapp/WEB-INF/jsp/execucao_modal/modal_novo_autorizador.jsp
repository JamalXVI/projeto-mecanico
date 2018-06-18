<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal fade" id="nvautorizadorModal" tabindex="-1" role="dialog" aria-labelledby="nvautorizadorModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="nvautorizadorModalLabel">Selecionar Autorizador</h4>
      </div>
      <div class="modal-body">
        <div class="col-sm-12">
        <div class="input-group">
  			<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-search"></span></span>
        	<input class="form-control input veiculo" type="text" placeholder="Filtrar Placa" id="pesq_placa" />
       	</div>
       	</div>
       	<div class="col-sm-12">
       	<table class="table table-hover table-bordered table-striped" id="tabnvAutorizador">
       		<thead>
       			<tr>
       				<th>Nome</th>
       				<th>Documento</th>
       				<th>Email</th>
       			</tr>
       		</thead>
       		<tbody id="corponvAutorizador">
       		</tbody>
       	</table>
       	</div>
       	<div class="col-sm-12">
        	<label>Autorizador:</label>
        	<input type="hidden" id="nvautorizador_id_autorizador" />
        	<input type="text" class="form-control" readonly="readonly"  id="nome_autorizador" />
      </div>
       	<div class="col-sm-12">
        	<label>Responsável:</label>
        	<input type="text" class="form-control" id="nome_responsavel" />
      </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="finalizar_nv_autorizador();">Fechar</button>
      </div>
    </div>
  </div>
</div>