   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <c:import url="/WEB-INF/jsp/header.jsp"/>
    <style>
		.pesquisar_form{
			clear:both;
		}
	</style>
	<div class="col-xs-12">
	<h2 class="text-center"><label><span class="glyphicon glyphicon-list-alt"></span> Lista de Penduras</label></h2>
	</div>
	<form action="${linkTo[PenduraController].lista(null, null) }"
	method="get">
	<div class="col-sm-12 col-md-2 col-lg-4"></div>
    <div class="col-sm-12 col-md-4 col-lg-2">
        <h3 id="minimum-setup">Mês</h3>
        <div class='input-group date' id='diapicker'>
            <input type='text' class="form-control" name="data_escolhida"
                value="${data_escolhida }" id="escolhendo_data"/> <span class="input-group-addon"> <span
                class="glyphicon glyphicon-calendar"></span>
            </span>
        </div>
        
     </div>
     <div class="col-sm-12 col-md-4 col-lg-2">
     <h3>Ano</h3>
     <input type="text" name="ano" class="form-control ano"/>
     </div>
     <div class="col-sm-12 col-md-4 col-lg-2">
        <h3>Pesquisar</h3>
        <input type="submit" id="btn_pesquisar" value="Pesquisar"
         class="btn  btn-primary" />
     </div>
</form>
<div class="col-xs-12">
	<h2 class="text-center"><label>Penduras Não Pagas</label></h2>
	</div>
<div class="col-sm-12">
<div id="tabela_gastos">
<table class="table table-hover table-bordered table-striped tablesorter" id="tabNPagos">
        <thead>
            <tr>
                <th>Cliente</th>
            	<th>Descrição</th>
                <th>Valor</th>
                <th>Dia</th>
                <th>Executado</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody> 
          <c:forEach items="${penduras}" var="p">
          <c:if test="${!p.pago }">
                <tr>
                    <td><a href="${linkTo[ClienteController].visualizar(p.cliente.id)}">${p.cliente.nome}</a></td>
                     <td>${p.descricao}</td>
                    <td class="valor_devendo">${p.valor}</td>
                    <td><fmt:formatDate timeStyle="short" value="${p.dia_para_pagar.time}" type="BOTH"/></td>
                    <td>
                    <c:if test="${!p.execucao }">
                    Não
                    </c:if>
                    <c:if test="${p.execucao }">
                    <a href="${linkTo[ExecucaoController].visualizar(p.servicosExecutados.id) }" >Sim</a>
                    </c:if>
                    </td>
                    <td>
                    <a href="#" onclick="iniciar_modal(${p.id })">
                    <span class="glyphicon glyphicon-trash"></span></a>
                    <c:if test="${!p.pago }">
                    <a href="#" onclick="iniciar_modal_pagar(${p.id})"> <span
						class="glyphicon glyphicon-usd"></span></a>
                    </c:if>
                    </td>
                </tr>
            </c:if>
            </c:forEach>
        </tbody>
    </table>
    </div>
</div>    
<div class="col-sm-12 col-md-6 col-lg-2 text-right">
<label>Total Devendo:</label>
<input type="text" class="form-control" readonly="readonly" id="total_devendo" value="0" />
</div>
<div class="col-xs-12">
	<h2 class="text-center"><label>Penduras Pagas</label></h2>
	</div>
<div class="col-sm-12">
<div id="tabela_gastos">
<table class="table table-hover table-bordered table-striped tablesorter" id="tabPagos">
        <thead>
            <tr>
                <th>Cliente</th>
            	<th>Descrição</th>
                <th>Valor</th>
                <th>Dia</th>
                <th>Executado</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody> 
          <c:forEach items="${penduras}" var="p">
           <c:if test="${p.pago }">
                <tr>
                    <td><a href="${linkTo[ClienteController].visualizar(p.cliente.id)}">${p.cliente.nome}</a></td>
                     <td>${p.descricao}</td>
                    <td class="valor_pago">${p.valor}</td>
                    <td><fmt:formatDate timeStyle="short" value="${p.dia_para_pagar.time}" type="BOTH"/></td>
                    <td>
                    <c:if test="${!p.execucao }">
                    Não
                    </c:if>
                    <c:if test="${p.execucao }">
                    <a href="${linkTo[ExecucaoController].visualizar(p.servicosExecutados.id) }" >Sim</a>
                    </c:if>
                    </td>
                    <td>
                    <a href="#" onclick="iniciar_modal(${p.id })">
                    <span class="glyphicon glyphicon-trash"></span></a>
                    <a href="#" onclick="iniciar_modal_pagamento('<fmt:formatDate timeStyle="short" value="${p.dia_pago.time }" type="BOTH"/>', '${p.observacao }' )"	> <span
						class="glyphicon glyphicon-ok"></span></a>
                    </td>
                </tr>
               </c:if>
            </c:forEach>
        </tbody>
    </table>
    </div>
</div>    
<div class="col-sm-12 col-md-6 col-lg-2 text-right">
<label>Total Pago:</label>
<input type="text" class="form-control" readonly="readonly" id="total_pago" value="0" />
</div>
<script>
	function iniciar_modal_pagar(id){
		$("#id_pendura_pagar").val(id);
		$("#pagarModal").modal("show");
	}
 </script>
    <script>
		$('#diapicker').datetimepicker({
			format : "MM",
			viewMode : 'months'
		});
	</script>
	<script>
	$($(".valor_devendo").each(function(i,e)
			{
				$("#total_devendo").val(Number($("#total_devendo").val())+Number($(e).html()));
			}));
	$($(".valor_pago").each(function(i,e)
			{
				$("#total_pago").val(Number($("#total_pago").val())+Number($(e).html()));
			}));
	</script>
<c:import url="/WEB-INF/jsp/deletar/modal_deletar_pendura.jsp" />
<c:import url="/WEB-INF/jsp/pendura_modal/modal_pagar.jsp" />
<c:import url="/WEB-INF/jsp/pendura_modal/modal_ver_pago.jsp" />
<c:import url="/WEB-INF/jsp/formatacao.jsp"/>
<script type="text/javascript">
function iniciar_modal(id){
	$("#id_esc_deletar").val(id);
	$("#deletarModal").modal("show");
}
</script>
<script>
function iniciar_modal_pagamento(dia, observacao){
		$("#ver_dia_pagamento").val(dia);
	$("#ver_observacao").val(observacao);
	$("#pagamentoModal").modal("show");
}
</script>
<c:import url="/WEB-INF/jsp/footer.jsp"/>