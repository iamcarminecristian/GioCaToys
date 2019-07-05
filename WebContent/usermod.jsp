<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="style\usermod.css">
	<script src="script\jquery.js"></script>
	<script src="script\validate.js"></script>
	<script src="script\script.js"></script>
<title>GioCa-Toys ~ Modifica dati</title>
</head>
<body onresize="resize2()">
	<%@include file="header.jsp" %>
	
	<main>
		<%
			RegisterBean r = (RegisterBean) session.getAttribute("bean");
		%>
		<h2 class="intenst">Modifica i tuoi dati</h2>
		<h4 class="intenst">Inserisci solamente i campi che vuoi modificare</h4>
			<form id="modU" action="user.html" method="post">
			<div class="userD">
				<h2>Modifica dati personali</h2>
				<p>Nome attuale: <span><%=r.getNome() %></span></p>
				<p>Modifica:<input type="text" name="nome" placeholder="Nome" value=""></p>
				
				<p>Cognome attuale: <span><%=r.getCognome() %></span></p>
				<p>Modifica:<input type="text" name="cognome" placeholder="Cognome" value=""></p>
				
				<p>Data di nascita attuale: <span><%=r.getBday() %></span></p>
				<p>Modifica:<input class="input" type="date" name="bday"></p>
				
				<p>Email attuale: <span><%=r.getMail() %></span></p>
				<p>Modifica:<input type="text" name="mail" placeholder="Email" value=""></p>
			</div><br>
			<div class="userInd">
				<h2>Modifica Indirizzo</h2>			
				<p>Nazione attuale: <span><%=r.getNazionalita() %></span></p>	
				<p>Modifica:<input type="text" name="naz" placeholder="Nazione" value=""></p>
				
				<p>Città attuale: <span><%=r.getCitta()%></span></p>	
				<p>Modifica:<input type="text" name="cit" placeholder="Città" value=""></p>
				
				<p>Via attuale: <span><%=r.getVia()%></span></p>	
				<p>Modifica:<input type="text" name="via" placeholder="Via" value=""></p>
				
				<p>Civico attuale: <span><%=r.getCivico()%></span></p>	
				<p>Modifica:<input type="text" name="civ" placeholder="Civico" value=""></p>
				
				<p>Provincia attuale: <span><%=r.getProvincia()%></span></p>	
				<p>Modifica:<input type="text" name="prov" placeholder="Provincia" value=""></p>
				
				<p>CAP attuale: <span><%=r.getCap()%></span></p>	
				<p>Modifica:<input type="text" name="cap" placeholder="CAP" value=""></p>
				
				<input type="hidden" name="user" value=<%=r.getUsername() %>>
				<button class="accept" type="submit">Conferma</button>
				<button type="reset">Reset</button>
			</div>
			</form><br>
			<form onsubmit="return validateUs(this)" action="user.html" method="post">
			<div class="userP">
				<h2>Modifica Password</h2>
				
				<p>Inserire password attuale: <input type="password" name="pass" placeholder="Vacchia Password" value=""></p>
				
				<p>Inserire nuova password: <input type="password" name="pass2" placeholder="Nuova Password" value=""></p>	
				<p>Confermare nuova password: <input type="password" name="pass3" placeholder="Conferma Password" value=""></p>					
				<input type="hidden" name="userP" value=<%=r.getPassword() %>>
				<button class="accept" type="submit">Conferma</button>
				<button type="reset">Reset</button>
			</div>
			</form>
	</main>
	
	<footer></footer>
	
</body>
</html>