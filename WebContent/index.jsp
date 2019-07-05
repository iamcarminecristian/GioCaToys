<%@ page language="java" import="Model.*" import="java.util.ArrayList" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<title>GioCa-Toys ~ Home</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="style\range.css">
	<link rel="stylesheet" type="text/css" href="style\index.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="script\jquery.js"></script>
	<script src="script\script.js"></script>
</head>
<body onresize="resize()" onload="showSlides(1)">
	<%@include file="header.jsp" %>
	<main>
			<!— SLIDESHOW —>
			<div class="slideshow-container">
  				<!— Full-width images with number and caption text —>
  				<div class="mySlides fade">
  				 <a href="novita.html#228"><img class="slide" src="img\slider1.jpg"></a>
  				</div>
  				<div class="mySlides fade">
  				  <a href="novita.html#227"><img class="slide" src="img\slider2.jpg"></a>
  				</div>
  				<div class="mySlides fade">
    			  <a href="novita.html#226"><img class="slide" src="img\slider3.jpg"></a>
  				</div>
  				<!— Next and previous buttons —>
  					<a class="prev" onclick="plusSlides(-1)">&#10094;</a>
  					<a class="next" onclick="plusSlides(1)">&#10095;</a><br>
				<!— The dots/circles —>
				<div class="dots" style="text-align:center">
					<span class="dot" onclick="currentSlide(1)"></span>
  					<span class="dot" onclick="currentSlide(2)"></span>
					<span class="dot" onclick="currentSlide(3)"></span>
				</div>
			</div>
			<div id="div-mobil" class="div-mobil" onclick="showAdvSearch()">
				<p id="p-mobil">Clicca qui e trova le migliori idee regalo</p>
			</div>
			<div id="div-source" class="div-source">
				<p>Trova le migliori idee regalo</p><hr>
				<form action="product.html" method="post">
					<select class="age-source" name="age">
						<option selected value="Tutte">Età:  (Tutte)</option>
						<option value="0-5">0-5</option>
						<option value="6-8">6-8</option>
						<option value="9-12">9-12</option>
						<option value="12+">12+</option>
						<option value="0-99">0-99</option>
					</select>
					<div class="category-div">
						<select class="category-source" name="category">
							<option selected value="Tutte">Categoria:  (Tutte)</option>
							<option value="Sportivi">Giochi Sportivi</option>
							<option value="Costruzioni">Costruzioni</option>
							<option value="Tech">Giochi Elettronici</option>
							<option value="Veicoli">Veicoli Giocattolo</option>
							<option value="Peluche">Peluche</option>
							<option value="Azione">Personaggi d'azione</option>
						</select>
					</div>
					<div class="slidecontainer">
  					<input type="range" name="range" step="5" min="0" max="100" value="100" class="slider" id="myRange">
  					<p id="all">Qualsiasi <span id="demo"></span> </p>
					</div>
					<script src="script\range.js"></script>
					<input type="hidden" name="Adv" value="Adv">
					<button class="botton-source" type="submit" name="button"><p>CERCA</p></button>
				</form>
			</div>
			

			<div class="shProd">
				<h2>I nostri BestSeller</h2>
				<%
				ArrayList<ProductBean> bestS = (ArrayList<ProductBean>) request.getAttribute("bestS");
				int i = 0;
				while(i<bestS.size()){
					%>
					<div class="spanProd">
						<a title="Vai al prodotto" class="pLink" href="product.html?cod=<%=bestS.get(i).getCodProdotto() %>">
						<div class="spanProd2">
							<img src=<%=bestS.get(i).getImg()%> class="imgProd"> <br><br>
							<span><%=bestS.get(i).getNome()%></span> <br><br>
							<p>Categoria: <span><%=bestS.get(i).getCategoria()%></span></p> <br>
							<span><%=bestS.get(i).getPrezzo()%></span> <br><br>
						</div>
						</a>
						<%
						if(session.getAttribute("ad")==null){
						%>
							<a onclick="alert('Prodotto aggiunto al carrello')" href="compcarr.html?cod=<%=bestS.get(i).getCodProdotto() %>"><button class="prodButton">Aggiungi al carrello</button></a>
						<%
						}
						%>
					</div>
					<%
					i++;
				}
				%>
			</div>
			<div class="indLink">
				<a href="product.html"><div class="linkProd">Vai al catalogo prodotti</div></a>
				<a href="category.html"><div class="linkCat">Guarda le nostre categorie</div></a>
			</div>
		
	</main>
	<footer>
	</footer>
</body>
</html>