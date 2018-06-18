   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <c:import url="/WEB-INF/jsp/header.jsp"/>
    <style>
		.pesquisar_form{
			clear:both;
		}
	</style>
<h2 class="text-center"><label><span class="glyphicon glyphicon-list-alt"></span> Lista de Contas</label></h2>
	<form action="${linkTo[ContasController].lista(null, null) }"
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
<div class="col-sm-12 text-center">
<h2 class="text-center"><label>Contas Não Pagas</label></h2>
</div>
<div class="col-sm-12">
<div id="tabela_gastos">
<table class="table table-hover table-bordered table-striped tablesorter" id="tabNPagas">
        <thead>
            <tr>
            	<th>Nome</th>
                <th>Custo</th>
                <th>Produtor</th>
                <th>Dia</th>
                <th>Motivo dos Gastos</th>
                <th>Produto</th>
                <th>Qtd.</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody> 
          <c:forEach items="${contas}" var="c">
          <c:if test="${!c.paga }">
          <tr>
                    <td>${c.nome}</td>
                    <td class="valor_devendo">${c.valor}</td>
                    <c:if test="${c.tem_produtor}">
                    <td>${c.produtor.nome }</td>
                    </c:if>
                    <c:if test="${!c.tem_produtor}">
                    <td>-</td>
                    </c:if>
                    <td><fmt:formatDate value="${c.dia.time}" type="BOTH"/></td>
                    <td>${c.motivo}</td>
                    <c:if test="${c.produto}">
                    <td>${c.peca.nome }</td>
                    <td>${c.quantidade }</td>
                    </c:if>
                    <c:if test="${!c.produto}">
                    <td>-</td>
                    <td>-</td>
                    </c:if>
                    <td>
                    <a href="#" onclick="iniciar_modal(${c.id })">
                    <span class="glyphicon glyphicon-trash"></span></a>
                    <a href="#" onclick="iniciar_modal_pagar(${c.id})"> <span
						class="glyphicon glyphicon-usd"></span></a>
						<c:if test="${c.nota_fiscal }">
					<a href="#" onclick="iniciar_modal_fiscal('${c.numero_nota}','${c.duplicata }' )"	> <span
						class="glyphicon glyphicon-tags"></span></a>
					</c:if>
                   </td>
                   
                </tr>
          </c:if>
                
            </c:forEach>
        </tbody>
    </table>
    </div>
    <div class="col-sm-12 col-md-6 col-lg-2 text-right">
		<label>Total Devendo:</label>
		<input type="text" class="form-control" readonly="readonly" id="total_devendo" value="0" />
	</div>
<div class="col-sm-12 text-center">
<h2 class="text-center"><label>Contas Pagas</label></h2>
</div>
<div class="col-sm-12">
<div id="tabela_gastos">
<table class="table table-hover table-bordered table-striped tablesorter" id="tabPagas">
        <thead>
            <tr>
            	<th>Nome</th>
                <th>Custo</th>
                <th>Dia</th>
                <th>Motivo dos Gastos</th>
                <th>Produto</th>
                <th>Qtd.</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody> 
          <c:forEach items="${contas}" var="c">
          <c:if test="${c.paga }">
          <tr>
                    <td>${c.nome}</td>
                    <td class="valor_pago">${c.valor}</td>
                    <td><fmt:formatDate value="${c.dia.time}" type="BOTH"/></td>
                    <td>${c.motivo}</td>
                    <c:if test="${c.produto}">
                    <td>${c.peca.nome }</td>
                    <td>${c.quantidade }</td>
                    </c:if>
                    <c:if test="${!c.produto}">
                    <td>-</td>
                    <td>-</td>
                    </c:if>
                    <td>
                    <a href="#" onclick="iniciar_modal(${c.id })">
                    <span class="glyphicon glyphicon-trash"></span></a>
					 <a href="#" onclick="iniciar_modal_pagamento('<fmt:formatDate timeStyle="short" value="${c.dia_pago.time }" type="BOTH"/>', '${c.observacao }' )"	> <span
						class="glyphicon glyphicon-ok"></span></a>
					<c:if test="${c.nota_fiscal }">
					<a href="#" onclick="iniciar_modal_fiscal('${c.numero_nota}','${c.duplicata }' )"	> <span
						class="glyphicon glyphicon-tags"></span></a>
					</c:if>
                   </td>
                </tr>
          </c:if>
                
            </c:forEach>
        </tbody>
    </table>
    </div>
    <div class="col-sm-12 col-md-6 col-lg-2 text-right">
		<label>Total Pago:</label>
		<input type="text" class="form-control" readonly="readonly" id="total_pago" value="0" />
	</div>
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
    <script>
		$('#diapicker').datetimepicker({
			format : "MM",
			viewMode : 'months'
		});
	</script>
</div>    
<c:import url="/WEB-INF/jsp/deletar/modal_deletar_contas.jsp" />
<c:import url="/WEB-INF/jsp/formatacao.jsp"/>
<c:import url="/WEB-INF/jsp/contas_modal/modal_pagar.jsp" />
<c:import url="/WEB-INF/jsp/contas_modal/modal_ver_pago.jsp" />
<c:import url="/WEB-INF/jsp/contas_modal/modal_fiscal.jsp" />
<script>
	function iniciar_modal(id){
		$("#id_esc_deletar").val(id);
		$("#deletarModal").modal("show");
	}
 </script>
<script>
function iniciar_modal_fiscal(nota, duplicata){
	$("#ver_numero_nota").val(nota);
	$("#ver_numero_duplicata").val(duplicata);
	$("#fiscalModal").modal("show");
}
</script>
<script>
function iniciar_modal_pagamento(dia, observacao){
		$("#ver_dia_pagamento").val(dia);
	$("#ver_observacao").val(observacao);
	$("#pagamentoModal").modal("show");
}
</script>

<script>
	function iniciar_modal_pagar(id){
		$("#id_pendura_pagar").val(id);
		$("#pagarModal").modal("show");
	}
 </script>
 
<c:import url="/WEB-INF/jsp/footer.jsp"/>