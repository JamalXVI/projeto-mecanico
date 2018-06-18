<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/header.jsp" />
 <script src="bower_components/chartist/dist/chartist.min.js">
 </script>
 <div class="col-xs-12 col-md-12 text-center">
	 <h2><label><span class="glyphicon glyphicon-user"></span> Contagem de Clientes</label></h2>
 </div>
 <div class="col-xs-12 col-md-4 col-lg-2">
 <h3 id="minimum-setup">Mês</h3>
        <div class='input-group date' id='diapicker'>
            <input type='text' class="form-control" name="data_escolhida"
                value="${data_escolhida }" id="escolhendo_data"/> <span class="input-group-addon"> <span
                class="glyphicon glyphicon-calendar"></span>
            </span>
        </div>
        <div class="text-center">
     	</div>
     </div>
     <div class="col-xs-12 col-md-4 col-lg-2">
 		<h3 id="minimum-setup">Pesquisar</h3>
        <input type="submit" id="btn_pesquisar" value="Pesquisar"
         class="btn  btn-primary botao_confirmar" />
 	</div>
    <div class="col-xs-12 col-md-2 col-lg-2">
 		<h3 id="minimum-setup">Clientes</h3>
 		<input type="text" readonly="readonly" class="form-control" id="txt_clientes" />
 	 </div>
 	 <div class="col-xs-6 col-md-2 col-lg-2">
 		<h3 id="minimum-setup">Visitantes</h3>
 		<input type="text" readonly="readonly" class="form-control"  id="txt_visitantes" />
 	 </div>
 <div class="col-xs-12 col-md-6">
	<div class="ct-chart ct-perfect-fourth"></div>
 </div>
 <script>
 var ativar_graficos = function(){
	 var somas = [];
		var mes = $("#escolhendo_data").val();
	 $.ajax(
			 {
			 	type: "GET",
			 	url: '<c:url value="/Graficos/Contagem/'+mes+'/json"/>',
			 	dataType:'json',
			 	success: function(datas){
			 		$.each(datas, function(i,soma)
			 				{
			 					somas.push(soma);
			 				});
			 		var gasto = somas[0];
			 		 var ganho = somas[1];
			 		 total = gasto+ganho;
				 		document.getElementById("txt_clientes").value  = Number(ganho).toFixed(0);
				 		document.getElementById("txt_visitantes").value  = Number(gasto).toFixed(0);
			 		 var data = {
			 				labels: ["Visitantes "+Math.round(gasto/ total * 100) + '%',
			 				         "Clientes "+Math.round(ganho/ total * 100) + '%'],
			 				  series: [gasto,ganho]
			 				};
			 		var options = {
			 				  labelInterpolationFnc: function(value) {
			 				    return value[0];
			 				  }
			 				};
			 		var responsiveOptions = [
			 		                        ['screen and (min-width: 640px)', {
			 		                          chartPadding: 30,
			 		                          labelOffset: 100,
			 		                          labelDirection: 'explode',
			 		                          labelInterpolationFnc: function(value) {
			 		                            return value;
			 		                          },
			 		                         labelPosition: 'center'
			 		                        }],
			 		                        ['screen and (min-width: 1024px)', {
			 		                          labelOffset: 80,
			 		                          chartPadding: 20,
				 		                         labelPosition: 'center'
			 		                        }]
			 		                      ];

			 		                      new Chartist.Pie('.ct-chart', data, options, responsiveOptions);
			 	},
			 	error: function(){
			 		alert("Deu Merda");
			 	}});
 }
 $("#btn_pesquisar").on('click', ativar_graficos);
	$('#diapicker').datetimepicker({
		format : "MM",
		viewMode : 'months'
	});
 
 </script>
<c:import url="/WEB-INF/jsp/footer.jsp" />