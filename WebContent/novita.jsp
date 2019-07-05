<%@ page language="java" import="Model.*" import="java.util.ArrayList" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="style/novita.css">
	<script src="script\jquery.js"></script>
	<script src="script\script.js"></script>
<title>GioCa-Toys ~ Novità</title>
</head>
<body onresize="resize2()">
	<%@include file="header.jsp" %>
	<main>
		<%
			ArrayList<ProductBean> prod = (ArrayList<ProductBean>) request.getAttribute("pNov");
			int i = 0;
			%>
			<h1>Le novità del mese</h1>
			<%
			while(i<prod.size()){
					
					if(i%2!=0){
					%>
						<a class="anchor" name=<%=prod.get(i).getCodProdotto() %>></a>
						<div class="spanProd" >
							<a title="Vai al prodotto" class="pLink" href="product.html?cod=<%=prod.get(i).getCodProdotto() %>">
							<div class="spanProd2">
								<img style="float:right; padding-right: 5%;" src=<%=prod.get(i).getImg()%> class="imgProd"> <br><br>
							<%
						} else{
						%>
						<a class="anchor" name=<%=prod.get(i).getCodProdotto() %>></a>
						<div class="spanProd" style="background-color: #99ccff">
							<a title="Vai al prodotto" class="pLink" href="product.html?cod=<%=prod.get(i).getCodProdotto() %>">
							<div class="spanProd2">
								<img style="float:left; padding-left: 5%;" src=<%=prod.get(i).getImg()%> class="imgProd"> <br><br>
						<% 
						}	
						%>
							<div class="descP">
								<span><%=prod.get(i).getNome()%></span> <br><br>
								<p>Categoria: <span><%=prod.get(i).getCategoria()%></span></p> <br>
								<span><%=prod.get(i).getPrezzo()%></span> <br><br>
							</div>
						</div>
						</a>
						<div id="addCarr" ><a onclick="alert('Prodotto aggiunto al carrello')" href="compcarr.html?cod=<%=prod.get(i).getCodProdotto() %>"><button class="prodButton">Aggiungi al carrello</button></a></div>
					</div>
				<%
				i++;
			}
		%>
	</main>
	<footer></footer>
</body>
</html>