    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@taglib tagdir="/WEB-INF/tags" prefix="alura" %>
    <c:import url="/WEB-INF/jsp/header.jsp"/>
    <div class="col-xs-12">
		<h2 class="text-center"><label><span class="glyphicon glyphicon-compressed"></span> Visualizar Fabricante</label></h2>
	</div>
    <div  class="col-md-4 col-xs-12 col-lg-3">
    <label for="nome">Nome:</label>
        <input type="text" name="cliente.nome" id="nome" class="form-control"
        value="${fornecedor.nome}" readonly="readonly"/>
        
     </div>
     <div class="col-md-4 col-xs-8 col-lg-2">
     	 <label>CNPJ</label>
     	 <input type="text" value="${fornecedor.identificacao.cnpj }" class="form-control" readonly="readonly">
     	 </div>
     	 <div class="col-md-4 col-xs-8 col-lg-2">
     	 <label>IE</label>
     	 <input type="text" value="${fornecedor.identificacao.inscricao_estadual }" class="form-control" readonly="readonly">
     </div>
     <div  class="col-md-4 col-xs-8 col-lg-2">
        <label for="rua">Endereço:</label>
        <input type="text" name="cliente.rua" id="rua" class="form-control"
        value="${fornecedor.rua}" readonly="readonly"/>
      </div>
        <div  class="col-md-4 col-xs-4 col-lg-1">
        <label for="num">Número:</label>
        <input type="text" name="cliente.num" id="num" class="form-control"
        value="${fornecedor.num}" readonly="readonly"/>
        </div>
        <div  class="col-md-4 col-xs-12 col-lg-2">
        <label for="comp">Complemento:</label>
        <input type="text" name="cliente.comp" id="comp" class="form-control"
        value="${fornecedor.comp}" readonly="readonly"/>
        </div>
        <div  class="col-md-4 col-xs-12 col-lg-2">
        <label for="bai">Bairro:</label>
        <input type="text" name="cliente.bai" id="bai" class="form-control"
        value="${fornecedor.bairro}" readonly="readonly"/>
        </div>
        <div  class="col-md-4 col-xs-12  col-lg-2">
        <label for="cep">CEP:</label>
        <input type="text" name="cliente.cep" id="cep" 
        class="form-control cep" value="${fornecedor.cep}"
        readonly="readonly"/>
        </div>
        <div  class="col-md-4 col-xs-12  col-lg-2">
        <label for="cidade">Estado:</label>
        <input type="text" id="cid" class="form-control"
        value="${cidade.estado.nome}" readonly="readonly"/>
        </div>
        <div  class="col-md-4 col-xs-12  col-lg-2">
        <label for="cidade">Cidade:</label>
        <input type="text" id="cid" class="form-control"
        value="${cidade.nome}" readonly="readonly"/>
        </div>
        <div  class="col-md-6 col-xs-12  col-lg-4">
        <label for="email">Email:</label>
        <input type="text" name="cliente.email" id="email" class="form-control"
        value="${fornecedor.email}" readonly="readonly"/>
        </div>
        <div  class="col-md-6 col-xs-12  col-lg-3">
        <label for="cidade">Condição de Pagamento:</label>
        <input type="text" class="form-control"
        value="${fornecedor.condicao_pagamento} Dias" readonly="readonly"/>
        </div>
        <div class="col-xs-12 text-center">
        	<h3>Telefones</h3>
        </div>
        <c:forEach items="${fornecedor.telefone }" var="telefone">
            <c:if test="${telefone.ativo }">
            <div class="col-xs-12">
            <div id="telefone_${telefone.id }_num" class="col-xs-12 col-md-4">
            	<label>Número</label>
            	<input type="text" readonly="readonly" value="(${telefone.ddd })${telefone.numero}" class="form-control"/>
            </div>
            <div id="telefone_${telefone.id }_tipo" class="col-xs-12 col-md-4">
            	<label>Tipo</label>
            	<input type="text" readonly="readonly" value="${telefone.tipo }" class="form-control"/>
            </div>
            <div id="telefone_${telefone.id }_operadora" class="col-xs-12 col-md-4">
            	<label>Operadora</label>
            	<input type="text" readonly="readonly" value="${telefone.operadora}" class="form-control"/>
            </div>
            </div>
            </c:if>
        </c:forEach>
        <div class="col-xs-12 text-center">
        	
        	<h3 style="display: inline;">Produtos do Fabricante</h3>
        	<a 
        	href="${linkTo[PecasController].cadastrar_f(fornecedor.id)}" class="mais">
        	<span class="glyphicon-stack">
<i class="glyphicon glyphicon-circle glyphicon-stack-2x"></i>
<i class="glyphicon glyphicon-plus glyphicon-stack glyphicon-stack-1x"></i>
</span></a>
        </div>
        <div class="col-xs-12">
        <table class="table table-hover table-bordered table-striped tablesorter" id="tabClientes">
        <thead>
            <tr>
                <th>Nome</th>
                <th>Código Fiscal</th>
                <th>Estoque</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody> 
        <c:forEach items="${fornecedor.pecas }" var="peca">
        	<c:if test="${peca.ativo }">
        	<tr><td>${peca.nome }</td>
        	<td>${peca.cod_fiscal }</td>
        	<td>${peca.estoque} </td><td>
        	<a href="#" onclick='iniciar_modal(${peca.id})'>
                    <span class="glyphicon glyphicon-trash"></span></a>
                    <a href="${linkTo[PecasController].editar(peca.id)}">
                    <span class="glyphicon glyphicon-pencil"></span></a></td></tr>
        	</c:if>
        </c:forEach>
        </tbody>
        </table>
        </div>
        
<c:import url="/WEB-INF/jsp/deletar/modal_deletar_peca.jsp" />
<script>
	function iniciar_modal(id){
		$("#id_esc_deletar").val(id);
		$("#deletarModal").modal("show");
	}
 </script>
    <c:import url="/WEB-INF/jsp/footer.jsp"/>
 