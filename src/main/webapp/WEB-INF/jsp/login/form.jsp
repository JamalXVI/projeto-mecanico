
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="alura"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>${nome_mec }</title>
<link href="<c:url value='/css/bootstrap.css' />" rel="stylesheet" />
<link href="<c:url value='/css/bootstrap-combobox.css' />"
	rel="stylesheet" />
<link href="<c:url value='/css/bootstrap-datetimepicker.css' />"
	rel="stylesheet" />
<link href="<c:url value='/css/site.css' />" rel="stylesheet" />
<meta name="viewport" content="width=device-width" />
<script type="text/javascript"
	src="<c:url value="/js/jquery-latest.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/__jquery.tablesorter.js"/>"></script>
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
<script type="text/javascript" id="js">
	$(document).ready(function() {
	// call the tablesorter plugin
	$("table").tablesorter({
		sortList: [[0,0],[1,0],[2,0],[3,0]]
	});
	});
	</script>
<style>
html, body {
	height: 100%;
}

;
#wrap {
	min-height: 100%;
}

;
#main {
	overflow: auto;
	padding-bottom: 150px; /* this needs to be bigger than footer height*/
}

;
.mais {
	color: #0F6;
}

.navbar-header button {
	color: #FFFFFF;
}

.fullscreen {
	width: 100%;
}

main {
	padding-top: 50px;
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

#autenticar {
	margin-top: 10px;
}
</style>
</head>
<body>
	<div class="container" id="main">
		<main class="col-xs-12">
		<form action="${linkTo[LoginController].autentica(null, null)}"
			method="post">
			<div class="col-xs-12 col-md-12 col-lg-12 text-center">
				<img src='<c:url value="/resources/imagens/NV.png"></c:url>'
					width="180" height="180" class="img-circle"></img>
			</div>
			<div class="col-xs-12 col-md-4 col-lg-4"></div>
			<div class="col-xs-12 col-md-4 col-lg-4 text-center">
				<alura:validationMessage name="login_invalido" />
				<label for="login">Login:</label> <input type="text" id="login"
					name="usuario" class="form-control" /> <label for="senha">Senha:</label>
				<input type="password" id="senha" name="senha" class="form-control" />
				<div class="">
					<input type="submit" value="Autenticar" id="autenticar"
						class="btn btn-primary" />
				</div>
			</div>

		</form>
		<c:import url="/WEB-INF/jsp/footer.jsp" />