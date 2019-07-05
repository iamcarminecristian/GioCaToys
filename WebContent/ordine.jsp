<%@ page language="java" import="Model.OrderBean" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GioCa-Toys ~ Ordine</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="style\order.css">
	<script src="script\jquery.js"></script>
	<script src="script\script.js"></script>
</head>
<body onresize="resize2()">
	<%@include file="header.jsp" %>
	<main>
		<%
			OrderBean o = (OrderBean)request.getAttribute("ord");
		%>
		<div class="backG">
			<div class="order">
				<h1>L'ordine è stato effettuato con successo!!!</h1><br>
				<h3>Ecco il riepilogo del tuo ordine: </h3>
				<p class="pProd">Sig. <span><%= o.getUser()%></span><p>
				<p class="pProd">Data di aquisto: <span><%= o.getDate()%></span><p>
				<p class="pProd">Stato Ordine: <span><%= o.getState()%></span><p>
				<p class="pProd">Totale: <span><%= o.getTotale()%></span><p>
				<p>Pagamento alla consegna</p>		
			</div>
		</div>
	</main>
	<footer></footer>
</body>
</html>