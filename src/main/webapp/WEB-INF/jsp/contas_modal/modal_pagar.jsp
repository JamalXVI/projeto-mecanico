<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal fade" id="pagarModal" tabindex="-1" role="dialog" aria-labelledby="pagarModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="pagarModalLabel">Pagar</h4>
      </div>
      <form action="${linkTo[ContasController].pagar(null, null, null) }" method="post">
      <div class="modal-body">
      	<input type="hidden" name="id_conta_pagar" id="id_pendura_pagar">
      	<div class="col-xs-12">
      		<label>Dia do Pagamento:</label>
      		<input type="text" class="form-control horario_dia" name="data_pago"/>
      	</div>
      	<div class="col-xs-12">
      		<label>Observação:</label>
      		<input type="text" class="form-control" name="observacao"/>
      	</div>
      </div>
      <div class="modal-footer">
        <button type="submit" class="btn btn-default">Confirmar</button>
      </div>
      </form>
    </div>
  </div>
</div>