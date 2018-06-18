<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal fade" id="autorizarModal" tabindex="-1" role="dialog" aria-labelledby="autorizarModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="autorizarModalLabel">Autorizar Ação</h4>
      </div>
      <div class="modal-body">
        <div class="col-xs-12 col-md-4 col-lg-4">
      <input type="hidden" name="precisa_autorizar" value="false" id="precisa_autorizar"/>
      </div>
      		<div class="col-xs-12 col-md-4 col-lg-4 text-center">
				<label for="login">Login:</label> <input type="text" id="login"
					name="usuario" class="form-control" /> 
					<label for="senha">Senha:</label>
				<input type="password" id="senha" name="senha" class="form-control" />
			</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Autorizar</button>
      </div>
    </div>
  </div>
</div>