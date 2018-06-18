			<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal fade" id="pesqmodeloModal" tabindex="-1" role="dialog" aria-labelledby="pesqmodeloModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="pesqmodeloModalLabel">Selecionar Modelo</h4>
      </div>
      <div class="modal-body">
        <div class="col-sm-12">
        	<label>Marca:</label>
        	<select class="input-large form-control" id="selecionar_pesq_modelo_marca" value="">
        	</select>
        </div>
        <div class="col-sm-12">
        	<label>Modelo:</label>
        	<select class="input-large form-control" id="selecionar_pesq_modelo_modelo" value="">
        	</select>
        </div>
        <div class="col-sm-12">
        	<label>Placa:</label>
        	<select class="input-large form-control" id="selecionar_pesq_modelo_veiculo" value="">
        	</select>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="modelo_veiculo_selecionado();">Selecionar</button>
      </div>
    </div>
  </div>
</div>