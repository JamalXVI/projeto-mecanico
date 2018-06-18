<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal fade" id="solicitacaoModal" tabindex="-1" role="dialog"
 aria-labelledby="solicitacaoModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="solicitacaoModalLabel">Selecionar Solicitacao</h4>
      </div>
      <div class="modal-body">
      	<input type="hidden" id="pesq_placa_solicitacao_id_veiculo" />
        <div class="col-sm-12">
        	<select class="input-large form-control" id="selecionar_solicitacao_placa">
        	</select>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal"
         onclick="finalizar_modal_solicitacao();">Selecionar</button>
      </div>
    </div>
  </div>
</div>