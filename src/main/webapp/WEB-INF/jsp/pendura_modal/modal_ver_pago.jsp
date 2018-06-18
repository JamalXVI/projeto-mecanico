<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal fade" id="pagamentoModal" tabindex="-1" role="dialog" aria-labelledby="pagamentoModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="pagamentoModalLabel">Vizualizar Pagamento</h4>
      </div>
      <div class="modal-body">
      <div class="col-xs-12">
      <label>Data do Pagamento:</label>
      <input type="text" readonly="readonly" class="form-control" id="ver_dia_pagamento" />
      </div>
      <div class="col-xs-12">
      <label>Observação:</label>
      <input type="text" readonly="readonly" class="form-control" id="ver_observacao" />
      </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
      </div>
    </div>
  </div>
</div>