<%@ page language="java" import="Model.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="style\category.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="script\jquery.js"></script>
	<script src="script\script.js"></script>
<title>GioCa-Toys ~ Categorie</title>
</head>
<body onresize="resize2()">
	<%@include file="header.jsp" %>
	<main>
		<div class="hDiv">
			Scegli tra le nostre categorie
		</div>
		<a class="catLink" href="product.html?cat=sportivi"><div class="category" id="gSport">Giochi Sportivi <img class="imgCat" src="img\football.jpg"></div></a>
		<a class="catLink" href="product.html?cat=costruzioni"><div class="category" id="gCostr">Costruzioni <img class="imgCat" src="img\lego.jpg"></div></a>
		<a class="catLink" href="product.html?cat=tech"><div class="category" id="gTech">Giochi Elettronici <img class="imgCat" src="img\gameboy.jpg"></div></a>
		<a class="catLink" href="product.html?cat=veicoli"><div class="category" id="gVeic">Veicoli giocattolo <img class="imgCat" src="img\car.jpg"></div></a>
		<a class="catLink" href="product.html?cat=peluche"><div class="category" id="gPeluche">Peluche <img class="imgCat" src="img\goku.jpg"></div></a>
		<a class="catLink" href="product.html?cat=azione"><div class="category" id="gPers">Personaggi d'azione <img class="imgCat" src="img\batman.jpg"></div></a>
	</main>
	<footer></footer>
</body>
</html>