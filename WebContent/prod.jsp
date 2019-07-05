<%@ page language="java" import="Model.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GioCa-Toys ~ Prodotti</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
 	<link rel="stylesheet" type="text/css" href="style\prod.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="script\jquery.js"></script>
	<script src="script\script.js"></script>
</head>
<body onresize="resize2()">
	<%@include file="header.jsp" %>
	<main>
		<%
			ProductBean p = (ProductBean)request.getAttribute("prodCod");
			
		%>
		<div class="divProd">
			<div class="spanImg">
				<img class="imgProd" src=<%=p.getImg()%>>
			</div>
			<div class="spanProd">
				<div class="pName"><%=p.getNome()%></div><br><br>
				<div class="pText">Categoria: <span><%=p.getCategoria()%></span> </div> <br><br>
				<div class="pText">Descrizione:<br><p class="pDesc"><span><%=p.getDescrizione()%></span></p></div><br><br>
				<div class="pText">Prezzo: <span id="prezzo"><%=p.getPrezzo()%></span></div>
								<%
				if(session.getAttribute("ad")==null){
				%>
					<a onclick="alert('Prodotto aggiunto al carrello')" href="compcarr.html?cod=<%=p.getCodProdotto() %>"><button class="prodButton">Aggiungi al carrello</button></a>
				<%
				}
				%>
			</div>
		</div>
			<a href="product.html?cat=<%= p.getCategoria() %>">
			<div class="pCat">
				Visualizza altri prodotti della stessa categoria
			</div>
			</a>
  	</main>
	<footer></footer>
</body>
</html>
