<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/header.jsp" />
 <script src="bower_components/chartist/dist/chartist.min.js">
 </script>
 <div class="col-xs-12 col-md-12 text-center">
	 <h2><label><span class="glyphicon glyphicon-user"></span> Total de Clientes</label></h2>
 </div>
 <div class="col-xs-12 col-md-6 col-lg-6">
 		<h3 id="minimum-setup">Clientes</h3>
 		<ul class="list-group" id="clientes">
 		</ul>
 	 </div>
 	 <div class="col-xs-12 col-md-6 col-lg-6">
 		<h3 id="minimum-setup">Visitantes</h3>
 		<ul class="list-group" id="visitantes">
 		</ul>
 	 </div>
 <div class="col-xs-12 col-md-12">
	<div class="ct-chart ct-perfect-fourth"></div>
 </div>
 <script>
 var visitas = [];
 var visitantes = [];
 var meses = ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'];
 var ativar_graficos = function(){
	 $.ajax(
			 {
			 	type: "GET",
			 	url: '<c:url value="/Graficos/Contagem/Total/json"/>',
			 	dataType:'json',
			 	success: function(datas){
			 		$.each(datas, function(i,soma)
			 				{
			 		
			 					visitantes.push(soma[0]);
			 					visitas.push(soma[1])
			 					$("#visitantes").append("<li class='list-group-item'>"+meses[i]+" - "+soma[0]+"</li>");
			 					$("#clientes").append("<li class='list-group-item'>"+meses[i]+" - "+soma[1]+"</li>");
			 				});
			 		var data = {
			 				  labels: meses,
			 				  series: [
			 				    visitas,
			 				    visitantes
			 				  ]
			 				};

			 				var options = {
			 				  seriesBarDistance: 10
			 				};

			 				var responsiveOptions = [
			 				  ['screen and (max-width: 2400px)', {
			 				    seriesBarDistance: 10,
			 				    axisX: {
			 				      labelInterpolationFnc: function (value) {
			 				        return value[0];
			 				      }
			 				  	
			 				    },
			 				   axisY: {
			 				  		scaleMinSpace: 15,
			 				  	    onlyInteger: true
			 				  	},
			 				  	chartPadding: {
			 				  	    top: 25,
			 				  	    right: 5,
			 				  	    bottom: 5,
			 				  	    left: 10
			 				  	  },
			 				  }]
			 				];

			 				new Chartist.Bar('.ct-chart', data, options, responsiveOptions);
			 	},
			 	error: function(){
			 		alert("Deu Merda");
			 	}});
 }
 ativar_graficos();
 </script>
<c:import url="/WEB-INF/jsp/footer.jsp" />