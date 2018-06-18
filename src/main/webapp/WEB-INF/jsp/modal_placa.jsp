<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal fade" id="placaModal" tabindex="-1" role="dialog" aria-labelledby="placaModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="placaModalLabel">Selecionar Placa</h4>
      </div>
      <div class="modal-body">
        <div class="col-sm-12">
        <div class="input-group">
  			<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-search"></span></span>
        	<input class="form-control input veiculo" type="text" placeholder="Filtrar Placa" id="pesq_placa" />
       	</div>
       	</div>
       	<div class="col-sm-12">
       	<table class="table table-hover table-bordered table-striped" id="tabPlacas">
       		<thead>
       			<tr>
       				<th>Placa</th>
       				<th>Modelo</th>
       				<th>Proprietário</th>
       			</tr>
       		</thead>
       		<tbody id="corpoPesqPlaca">
       		</tbody>
       	</table>
       	</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="">Fechar</button>
      </div>
    </div>
  </div>
</div>