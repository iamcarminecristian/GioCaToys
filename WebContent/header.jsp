<%@ page language="java" import="Model.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="style\header.css">
	<script src="script\validate.js"></script>
	
</head>
<body>
	<header>
		<span class="spanLogo">
			<a href="index.html"><img title="Gio.Ca-Toys" class="logo" src="img\logo.svg"></a>
		</span>
	 	<% 
	 		String nCarr = "0"; 
			if(session.getAttribute("nCarr")!=null){
				nCarr = ((int) session.getAttribute("nCarr"))+"";
			}
			
		%>
		<div class="divGest">
		<%
			if(session.getAttribute("ad")==null){
		%>
			<a title="Carrello" href="carrello.html">
			<span class="spanCarrello">
				<img class="login" src="img\carrello.svg"> Carrello <span class="nCarr"><%=nCarr %></span>
			</span>
			</a>
		    <% 
			}
    		RegisterBean user = (RegisterBean) session.getAttribute("bean");
			if(user!=null){
			%>
			<a title="Logout" href="LogoutServlet">
				<span class="spanLogout">
				<img class="login" src="img\logout.png"> Esci
				</span>
			</a>
			<a title="Area Utente" href="user.html">
			<span class="spanLogged">
				Ciao <%=user.getNome()%>
			</span>
			</a>
			<%
				if(session.getAttribute("ad")!=null){%>
				<br><br>
				<a title="Gestione Ordini" href="gestorder.html">
				<span class="spanGest">
						<img class="login" src="img\ordini.svg"> Gestione Ordini
				</span>
				</a>
				<a title="Gestione Prodotti" href="gestprod.html">
				<span class="spanGest">
				<img class="login" src="img\orsacchiotto.svg"> Gestione Prodotti
				</span>
				</a>
				<%}
			}
			else{ %>
				<div class="spanLogin">
				<a title="Accedi" class="aLog">
					<img class="login" src="img\login.svg"> Accedi
				</a>
				<% 
				String err= (String)request.getAttribute("err");
				if(err!=null){
				%>
				<form style="display:block;" onsubmit="return validateLog(this)" id="formLog" class="form" name="form2" method="post" action="index.html">
					<h3>Login</h3>
					<p class="error">Username o password errati</p>
					<%
				}else{
					%>
				<form onsubmit="return validateLog(this)" id="formLog" class="form" name="form2" method="post" action="index.html">
					<h3>Login</h3>
					<%
				}
					%>
					<input type="text" name="user" placeholder="Username">
					<input type="password" name="pass" placeholder="Password">
					<button class="subLogin" type="submit">Entra</button>
				</form>
				</div>
				
				<a title="Registrati" target="_blank" href="register.html">
				<span class="spanReg">
				<img class="login" src="img\signin.svg"> Registrati
				</span>
				</a>
		<% } %>
	</div>
	</header>
	<nav>
   		<button id="collapsed" class="collapsed">...</button>
		<ul id="navbar" class="navbar">
			<li><a title="Home" href="index.html">HOME</a></li>
			<li><a title="Mostra i prodotti" href="product.html">PRODOTTI</a></li>
			<li><a title="Seleziona le categorie" href="category.html">CATEGORIE</a></li>
			<li><a title="Visualizza le novità" href="novita.html">NOVITA'</a></li>
		</ul>
		<form name="formSearch" class="formSearch" action="product.html">
			 <button class="serButton" type="submit"><i class="fa fa-search"> </i></button>
    	 	<input class="ser" type="text" placeholder="Cerca..." name="name">
   		 </form>
	</nav>
</body>
</html>