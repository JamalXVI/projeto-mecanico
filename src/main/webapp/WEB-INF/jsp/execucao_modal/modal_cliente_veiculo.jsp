<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal fade" id="clienteModal" tabindex="-1" role="dialog" aria-labelledby="clienteModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="clienteModalLabel">Selecionar Cliente</h4>
      </div>
      <div class="modal-body">
        <div class="col-sm-12">
        	<select class="input-large form-control cliente_selecionar" id="selecionar_cliente">
        	<c:forEach items="${clientes }" var="cliente">
        		<option value="${cliente.id }"> ${cliente.nome }</option>
        		
        	</c:forEach>
        	</select>
        </div>
        <div class="col-sm-12">
        	<label>Nome:</label>
        	<input type="text" class="form-control" readonly="readonly" id="nome_cliente"/>
        </div>
        <div class="col-sm-12">
        	<div id="cpf_cliente" class="identificacao_cliente"> 
        	<label>CPF:</label>
        	<input type="text"  class="form-control" readonly="readonly" id="vlr_cpf_cliente"/>
        	</div>
        	<div id="cnpj_cliente" class="identificacao_cliente"> 
        	<div class="col-sm-7">
        		<label>CNPJ:</label>
        		<input type="text"  class="form-control" readonly="readonly" id="vlr_cnpj_cliente"/>
        	</div>
        	<div class="col-sm-5">
        		<label>IE:</label>
        		<input type="text"  class="form-control" readonly="readonly" id="vlr_IE_cliente"/>
        	</div>
        	</div>
        </div>
        <div class="col-sm-12">
        	<label>Email:</label>
        	<input type="text" class="form-control" readonly="readonly" id="email_cliente"/>
        </div>
        <div class="col-sm-12">
        	<label>Veículo Escolhido</label>
        	<div class="seletor_carro_p_cliente">
        		<select id="pesq_cliente_seletor" class="input-large form-control">
        		</select>
        	</div>
        </div>
        <div class="col-sm-12">
        	<label>Solicitacao Escolhida</label>
        	<div>
        		<select id="pesq_cliente_solicitacoes_carro" class="input-large form-control">
        		</select>
        	</div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="selecionado();">Selecionar</button>
      </div>
    </div>
  </div>
</div>