<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal fade" id="fiscalModal" tabindex="-1" role="dialog" aria-labelledby="fiscalModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="fiscalModalLabel">Vizualizar Nota</h4>
      </div>
      <div class="modal-body">
      <div class="col-xs-12">
      <label>Número Nota:</label>
      <input type="text" readonly="readonly" class="form-control" id="ver_numero_nota" />
      </div>
      <div class="col-xs-12">
      <label>Número Duplicata:</label>
      <input type="text" readonly="readonly" class="form-control" id="ver_numero_duplicata" />
      </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
      </div>
    </div>
  </div>
</div>