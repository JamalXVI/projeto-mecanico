<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal fade" id="deletarModal" tabindex="-1" role="dialog" aria-labelledby="deletarModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="deletarModalLabel">Deletar Produtor</h4>
      </div>
      <div class="modal-body">
      	<div class="col-xs-12 text-center">
			<form action="${linkTo[ProdutorController].deletar(null) }"
			 method="post" role="form">
			<input type="hidden" name="id_esc_deletar" id="id_esc_deletar" />
			<h3>Você tem certeza que deseja fazer isto?</h3>
			<button type="submit" class="btn btn-danger btn-lg">
			Deletar
			</button>
			</form>
			
      	</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-warning" data-dismiss="modal" onclick="">Agora não</button>
      </div>
    </div>
  </div>
</div>