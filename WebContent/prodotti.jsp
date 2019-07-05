<%@ page language="java" import="Model.*" import="java.util.ArrayList" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GioCa-Toys ~ Prodotti</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="style\prodotti.css">
	 <script src="script\jquery.js"></script>
	<script src="script\script.js"></script>
	<title>GioCa-Toys ~ Prodotti</title>
</head>
<body onresize="resize2()">
	<%@include file="header.jsp" %>
	<main>
		
		<a style="height: 1px; width: 100%; float:left;" name="prod"></a>
		
		<%
			int pagina = (int)request.getAttribute("page");
			ArrayList<ProductBean> prod;
			if(request.getParameter("Adv")!=null){
				prod = (ArrayList<ProductBean>)request.getAttribute("prodAdv");%>
				<div class="hDiv">Ecco i risultati della tua ricerca avanzata</div>
				
				<%
			}
			else if(request.getParameter("cat")!=null){  
				prod = (ArrayList<ProductBean>)request.getAttribute("prodCat");%>
				<div class="hDiv">Scegli tra i nostri prodotti della categoria <%=request.getParameter("cat")%></div>
				<%
			}
			else if(request.getParameter("name")!=null){
				prod = (ArrayList<ProductBean>)request.getAttribute("prodN");%>
				<div class="hDiv">Ecco i risultati della tua ricerca con nome:  <%=request.getParameter("name")%></div>
				<%
			}else{
				prod = (ArrayList<ProductBean>)request.getAttribute("prodA");%>
				<div class="hDiv">Scegli tra i nostri migliori prodotti</div>
				<%
			}
			%>
			<div class="prod" id="prod">
			<%
			if(prod.size()==0){
				%>
				<h2>Spiacenti la ricerca non ha prodotto risultati</h2>
				<%
			}
			session.setAttribute("prod", prod);
			for( int i =(pagina-1)*8; (i< pagina*8)&&(i < prod.size()); i++){
			%>
			<div class="spanProd">
				<a title="Vai al prodotto" class="pLink" href="product.html?cod=<%=prod.get(i).getCodProdotto() %>">
				<div class="spanProd2">
					<img src=<%=prod.get(i).getImg()%> class="imgProd"> <br><br>
					<span><%=prod.get(i).getNome()%></span> <br><br>
					<p>Categoria: <span><%=prod.get(i).getCategoria()%></span></p> <br>
					<span><%=prod.get(i).getPrezzo()%></span> <br><br>
				</div>
				</a>
				<%
				if(session.getAttribute("ad")==null){
				%>
					<a onclick="alert('Prodotto aggiunto al carrello')" href="compcarr.html?cod=<%=prod.get(i).getCodProdotto() %>"><button class="prodButton">Aggiungi al carrello</button></a>
				<%
				}
				%>
			</div>
			<%
			}
			%>
			</div>
			<div class="spanPage">
			<%
				if(prod.size()>8){
					for(int i=1;i <= (prod.size()/8) + 1; i++){
					%>
					<a href="#prod" onclick="changePage(<%= i %>)"><%= i %></a>
					<%
					}
				}
			%>
			</div>
	</main>
	<footer></footer>
</body>
</html>