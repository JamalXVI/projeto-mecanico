<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>${nome_mec }</title>
<link href="<c:url value='/css/bootstrap.css' />" rel="stylesheet" />
<link href="<c:url value='/css/bootstrap-combobox.css' />"
	rel="stylesheet" />
<link href="<c:url value='/css/jquery.bootstrap-touchspin.css' />"
	rel="stylesheet" />
<link href="<c:url value='/css/bootstrap-datetimepicker.css' />"
	rel="stylesheet" />
<link href="<c:url value='/css/site.css' />" rel="stylesheet" />
<link href="<c:url value='/css/chartist.min.css' />" rel="stylesheet" />
<meta name="viewport" content="width=device-width" />
<script type="text/javascript"
	src="<c:url value="/js/jquery-latest.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery.tablesorter.pager.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/chili-1.8b.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/docs.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/examples.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/highlight.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/bootstrap-combobox.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/moment-with-locales.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/bootstrap-datetimepicker.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery.mask.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/chartist.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery.bootstrap-touchspin.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/Gruntfile.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.mask.js"/>"></script>
<style>

.mais {
	color: white;
}
.glyphicon-stack {
position: relative;
display: inline-block;
width: 1.5em;
height: 1.5em;
line-height: 1.5em;
vertical-align: middle;
}

.glyphicon-circle{
position: relative;
border-radius: 50%;
width: 100%;
height: auto;
padding-top: 100%;
background: #00cb05;
}

.glyphicon-stack-1x {
line-height: inherit;
}

.glyphicon-stack-1x, .glyphicon-stack-2x {
position: absolute;
left: 0;
width: 100%;
text-align: center;
}
</style>
<style>
.botao_confirmar {
	margin-top: 20px;
	margin-bottom: 20px;
}

table {
	margin-top: 20px;
	margin-bottom: 20px;
}

#main {
	overflow: auto;
	padding-bottom: 150px; /* this needs to be bigger than footer height*/
}




.fullscreen {
	width: 100%;
}

main {
	padding-top: 50px;
	padding-bottom: 50px;
}

footer {
	background: #333;
	color: #FFF;
	text-align: center;
	padding: 10px 0;
	position: relative;
	margin-top: 20px;
	clear: both;
}

header {
	margin-bottom: 20px;
}

.ct-label {
	font-size: 18px;
}

.limpar_lados {
	clear: both;
}
</style>
</head>
<body>
	<header class="">
		<div class="container">
		<nav class="navbar navbar-fixed-top navbar-inverse">
			<div class="navbar-header">
				<a href="${linkTo[IndiceController].inicio() }" class="navbar-brand"><img src='<c:url value="/resources/imagens/NV.png"></c:url>'
					width="20" height="20"></img></a>
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Menu</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					</button>
			</div>
			<div class="collapse navbar-collapse" id="bs-navbar" style="height: 1px;">
				<ul class="navbar-nav nav">
					<li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-plus-sign"></span>
                Novo
                <span class="caret"></span></a>
                <ul class="dropdown-menu">
                <li><a href="${linkTo[ClienteController].form() }">Cliente</a></li>
                <li><a href="${linkTo[FuncionarioController].form() }">Funcionários</a></li>
                <li><a href="${linkTo[VeiculoController].form() }">Veiculo</a></li>
                <li><a href="${linkTo[SolicitacaoController].form() }">Solicitação</a></li>
                <li><a href="${linkTo[OrcamentoController].form() }">Orçamento</a></li>
                <li><a href="${linkTo[ExecucaoController].form() }">Execução</a></li>
                <li><a href="${linkTo[FornecedorController].form() }">Fabricante</a></li>
                <li><a href="${linkTo[PecasController].form() }">Peça</a></li>
                <li><a href="${linkTo[ServicosController].form() }">Serviço</a></li>
                <li><a href="${linkTo[MarcaController].form() }">Marca</a></li>
                <li><a href="${linkTo[ModeloController].form() }">Modelo</a></li>
                <li><a href="${linkTo[ContasController].form() }">Conta</a></li>
                <li><a href="${linkTo[PenduraController].form() }">Pendura</a></li>
                <li><a href="${linkTo[ProdutorController].form() }">Produtor</a></li>
                </ul>
                </li>
                <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-list-alt"></span>
                Lista
                <span class="caret"></span></a>
                <ul class="dropdown-menu">
                <li><a href="${linkTo[ClienteController].listar() }">Cliente</a></li>
                <li><a href="${linkTo[ClienteController].listar_informacao() }">Cliente <b>(Informação)</b></a></li>
                <li><a href="${linkTo[ClienteController].listar_identidade() }">Cliente <b>(Identidade)</b></a></li>
                <li><a href="${linkTo[VeiculoController].listar() }">Veiculos</a></li>
                <li><a href="${linkTo[FuncionarioController].listar() }">Funcionario</a></li>
                <li><a href="${linkTo[SolicitacaoController].listar() }">Solicitações</a></li>
                <li><a href="${linkTo[SolicitacaoController].listar_id() }">Solicitações<b>(Cód)</b></a></li>
                <li><a href="${linkTo[OrcamentoController].listar() }">Orçamento</a></li>
                <li><a href="${linkTo[OrcamentoController].listar_id() }">Orçamento<b>(Cód)</b></a></li>
                <li><a href="${linkTo[FornecedorController].listar() }">Fabricantes</a></li>
                <li><a href="${linkTo[ProdutorController].listar() }">Produtores</a></li>
                <li><a href="${linkTo[PecasController].listar() }">Peças</a></li>
                <li><a href="${linkTo[ServicosController].listar() }">Serviços</a></li>
                <li><a href="${linkTo[MarcaController].listar() }">Marcas</a></li>
                <li><a href="${linkTo[ModeloController].listar() }">Modelos</a></li>
                <li><a href="${linkTo[ContasController].lista() }">Contas</a></li>
                <li><a href="${linkTo[ContasController].lista_data() }">Contas <b>(Dias)</b></a></li>
                <li><a href="${linkTo[PenduraController].lista() }">Pendura</a></li>
                <li><a href="${linkTo[PenduraController].lista_data() }">Pendura <b>(Dias)</b></a></li>
                <li><a href="${linkTo[PenduraController].lista_nf() }">Pendura <b>Fiscal</b></a></li>
                <li><a href="${linkTo[PenduraController].lista_data_nf() }">Pendura <b>Fiscal (Dias)</b></a></li>
                </ul>
                <li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-stats"></span> Graficos
	                <span class="caret"></span></a>
	                <ul class="dropdown-menu">
	                  <li><a href="${linkTo[GraficosController].contas() }"><span class="glyphicon glyphicon-usd"></span> Balanço</a></li>
	                  <li><a href="${linkTo[GraficosController].pendura() }"><span class="glyphicon glyphicon-check"></span> Pendura</a></li>
	                    </ul>
	                  </li>
					<li><a href="${linkTo[LoginController].deslogar() }"><span
							class="glyphicon glyphicon-off"></span> Deslogar</a></li>
				
				</ul>
				
			</div>
		</nav>
		</div>
	</header>
	<div class="container" id="main">
		<main>