<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal fade" id="funcionarioModal" tabindex="-1" role="dialog" aria-labelledby="funcionarioModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="funcionarioModalLabel">Selecionar Funcionário</h4>
      </div>
      <div class="modal-body">
        <div class="col-sm-12">
        	<select class="input-large form-control" id="selecionar_funcionario">
        	<c:forEach items="${funcionarios }" var="funcionario">
        		<option value="${funcionario.id }" selected="selected"> ${funcionario.cliente.nome }</option>
        		
        	</c:forEach>
        	</select>
        </div>
        <div class="col-sm-12">
        	<label>Nome:</label>
        	<input type="text" class="form-control" readonly="readonly" id="nome_funcionario"/>
        </div>
        <div class="col-sm-12">
        	<div id="cpf_funcionario" class="identificacao_funcionario"> 
        	<label>CPF:</label>
        	<input type="text"  class="form-control" readonly="readonly" id="vlr_cpf_funcionario"/>
        	</div>
        	
        </div>
        <div class="col-sm-12">
        	<label>Email:</label>
        	<input type="text" class="form-control" readonly="readonly" id="email_funcionario"/>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="selecionado_funcionario();">Selecionar</button>
      </div>
    </div>
  </div>
</div>