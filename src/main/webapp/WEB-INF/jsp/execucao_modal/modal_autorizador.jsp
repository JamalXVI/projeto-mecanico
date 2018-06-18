<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal fade" id="autorizadorModal" tabindex="-1" role="dialog" aria-labelledby="autorizadorModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="autorizadorModalLabel">Selecionar Autorizador</h4>
      </div>
      <div class="modal-body">
        <div class="col-sm-12">
        	<select class="input-large form-control cliente_selecionar" id="selecionar_autorizador">
        	<c:forEach items="${clientes }" var="cliente">
        		<option value="${cliente.id }"> ${cliente.nome }</option>
        		
        	</c:forEach>
        	</select>
        </div>
        <div class="col-sm-12">
        	<label>Nome:</label>
        	<input type="text" class="form-control" readonly="readonly" id="nome_autorizador"/>
        </div>
        <div class="col-sm-12">
        	<div id="cpf_autorizador" class="identificacao_autorizador"> 
        	<label>CPF:</label>
        	<input type="text"  class="form-control" readonly="readonly" id="vlr_cpf_autorizador"/>
        	</div>
        	<div id="cnpj_autorizador" class="identificacao_autorizador"> 
        	<div class="col-sm-7">
        		<label>CNPJ:</label>
        		<input type="text"  class="form-control" readonly="readonly" id="vlr_cnpj_autorizador"/>
        	</div>
        	<div class="col-sm-5">
        		<label>IE:</label>
        		<input type="text"  class="form-control" readonly="readonly" id="vlr_IE_autorizador"/>
        	</div>
        	</div>
        </div>
        <div class="col-sm-12">
        	<label>Email:</label>
        	<input type="text" class="form-control" readonly="readonly" id="email_autorizador"/>
        </div>
      </div>
      <div class="col-sm-12">
        	<label>Responsável:</label>
        	<input type="text" class="form-control" id="nome_responsavel" />
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="selecionado_autorizador();">Selecionar</button>
      </div>
    </div>
  </div>
</div>