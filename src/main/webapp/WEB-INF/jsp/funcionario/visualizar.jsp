    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@taglib tagdir="/WEB-INF/tags" prefix="alura" %>
    <c:import url="/WEB-INF/jsp/header.jsp"/>
    <div class="col-xs-12">
		<h2 class="text-center"><label><span class="glyphicon glyphicon-user"></span> Visualizar Funcionário</label></h2>
</div>
    <link href="../../../css/bootstrap.css" rel="stylesheet" type="text/css" />
    <style>
		#tabClientes{
			clear:both;
		}
		 @media print and (width: 21cm) and (height: 29.7cm) {
		    @page .print-landscape-a4 {
		       margin: 3cm;
		       size: A4 landscape;
		    }
		 }
	</style>
    <div class="col-md-4 col-xs-12 col-lg-2">
    <img src="<c:url value="/Imagem/Cliente/${funcionario.cliente.id }/" />" width="180" height="180"
    class="img-circle"/>
    </div>
    <div  class="col-md-4 col-xs-12 col-lg-3">
    <label for="nome">Nome:</label>
        <input type="text" name="cliente.nome" id="nome" class="form-control"
        value="${funcionario.cliente.nome}" readonly="readonly"/>
        
     </div>
     	<c:if test="${funcionario.cliente.identificacao.tipo == 'CPF' }">
     	 	<div class="col-md-4 col-xs-8 col-lg-2">
     	 	<label>CPF</label>
     	 	<input type="text" value="${funcionario.cliente.identificacao.cpf }" class="form-control" readonly="readonly">
     	 	</div>
     	</c:if>
     	<c:if test="${funcionario.cliente.identificacao.tipo != 'CPF' }">
     	 	<div class="col-md-4 col-xs-8 col-lg-2">
     	 	<label>CNPJ</label>
     	 	<input type="text" value="${funcionario.cliente.identificacao.cnpj }" class="form-control" readonly="readonly">
     	 	</div>
     	 	<div class="col-md-4 col-xs-8 col-lg-2">
     	 	<label>IE</label>
     	 	<input type="text" value="${funcionario.cliente.identificacao.inscricao_estadual }" class="form-control" readonly="readonly">
     	 	</div>
     	</c:if>
     <div  class="col-md-4 col-xs-8 col-lg-2">
        <label for="rua">Endereço:</label>
        <input type="text" name="cliente.rua" id="rua" class="form-control"
        value="${funcionario.cliente.rua}" readonly="readonly"/>
      </div>
        <div  class="col-md-4 col-xs-4 col-lg-1">
        <label for="num">Número:</label>
        <input type="text" name="cliente.num" id="num" class="form-control"
        value="${funcionario.cliente.num}" readonly="readonly"/>
        </div>
        <div  class="col-md-4 col-xs-12 col-lg-2">
        <label for="comp">Complemento:</label>
        <input type="text" name="cliente.comp" id="comp" class="form-control"
        value="${funcionario.cliente.comp}" readonly="readonly"/>
        </div>
        <div  class="col-md-4 col-xs-12 col-lg-2">
        <label for="bai">Bairro:</label>
        <input type="text" name="cliente.bai" id="bai" class="form-control"
        value="${funcionario.cliente.bai}" readonly="readonly"/>
        </div>
        <div  class="col-md-4 col-xs-12  col-lg-2">
        <label for="cep">CEP:</label>
        <input type="text" name="cliente.cep" id="cep" 
        class="form-control cep" value="${funcionario.cliente.cep}"
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
        <div  class="col-md-4 col-xs-12  col-lg-2">
        <label for="email">Usuário:</label>
        <input type="text" class="form-control"
        value="${funcionario.usuario}" readonly="readonly"/>
        </div>
        <div  class="col-md-6 col-xs-12  col-lg-4">
        <label for="email">Email:</label>
        <input type="text" name="cliente.email" id="email" class="form-control"
        value="${funcionario.cliente.email}" readonly="readonly"/>
        </div>
        <div  class="col-md-4 col-xs-12  col-lg-2">
        <label>Entrada:</label>
        <input type="text" class="form-control"
        value="${funcionario.hora_entrada}" readonly="readonly"/>
        </div>
        <div  class="col-md-4 col-xs-12  col-lg-2">
        <label>Saída:</label>
        <input type="text" class="form-control"
        value="${funcionario.hora_saida}" readonly="readonly"/>
        </div>
        <div class="col-xs-12 text-center">
        	<h3>Telefones</h3>
        </div>
        <c:forEach items="${funcionario.cliente.telefones }" var="telefone">
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
            	<label>Tipo</label>
            	<input type="text" readonly="readonly" value="${telefone.operadora}" class="form-control"/>
            </div>
            </div>
        </c:if>
        </c:forEach>
        <div class="col-xs-12 text-center">
        <h3 style="display: inline;">Veículos</h3>
        	<a 
        	href="${linkTo[VeiculoController].cadastrar_c(funcionario.cliente.id)}" class="mais">
        	<span class="glyphicon-stack">
<i class="glyphicon glyphicon-circle glyphicon-stack-2x"></i>
<i class="glyphicon glyphicon-plus glyphicon-stack glyphicon-stack-1x"></i>
</span></a>
        </div>
        <div class="col-xs-12">
        <table class="table table-hover table-bordered table-striped tablesorter" id="tabClientes">
        <thead>
            <tr>
                <th>Marca</th>
                <th>Modelo</th>
                <th>Placa</th>
                <th>Ano</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody> 
        <c:forEach items="${funcionario.cliente.veiculos }" var="veiculo">
        	<c:if test="${veiculo.ativo }">
        	<tr><td>${veiculo.modelo.marca.nome }</td><td>${veiculo.modelo.nome }</td>
        	<td>${veiculo.placa }</td><td>${veiculo.ano }</td><td>
        	<a href="#" onclick='iniciar_modal(${veiculo.id})'>
                    <span class="glyphicon glyphicon-trash"></span></a>
                    <a href="${linkTo[VeiculoController].visualizar(veiculo.id)}">
                    <span class="glyphicon glyphicon-search"></span></a></td></tr>
        	</c:if>
        </c:forEach>
        </tbody>
        </table>
        </div>
        <div class="col-xs-12 text-right">
        <a href="${linkTo[PenduraController].lista_cliente(funcionario.cliente.id) }"><span class="glyphicon glyphicon-list-alt"></span></a>
        </div>
<c:import url="/WEB-INF/jsp/deletar/modal_deletar_veiculo.jsp" />
<script>
	function iniciar_modal(id){
		$("#id_esc_deletar").val(id);
		$("#deletarModal").modal("show");
	}
 </script>
        
    <c:import url="/WEB-INF/jsp/footer.jsp"/>