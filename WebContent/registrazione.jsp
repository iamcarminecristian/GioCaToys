<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>GioCa-Toys ~ Registrazione</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
 	<link rel="stylesheet" type="text/css" href="style\registrazione.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="script\jquery.js"></script>
	<script src="script\script.js"></script>
	<script src="script\validate.js"></script>
</head>
<body onresize="resize2()">
	<%@include file="header.jsp" %>
	<%
		if(user!=null){
			
			 response.sendRedirect("index.html");
		}
	%>
	<main>
		<h1>Registrati</h1>
		<hr>
		<%
			if(request.getAttribute("err1")!=null){
			%>
				<h2 style="color:red; text-align: center">Compila bene tutti i campi</h2>
				<h3 style="color:red; text-align: center">La password deve contenere almeno 1 carattere maiuscolo 1 minuscolo e 1 numero</h3>
			<% 
			}
		%>
		<div class="div-registation">
			<form onsubmit="return validate(this)" id="form_lettera" class="form-registration" action="registrati.html" method="post" name="regForm">
					
				<div class="inline-div">
					Nome<br>
					<input class="input" type="text" name="name">
				</div>
				<div class="inline-div">
					Cognome<br>
					<input class="input" type="text" name="surname">
				</div><br>
				<div class="inline-div">
					Data di nascita<br>
					<input class="input" type="date" name="bday">
				</div>
				<div class="inline-div">
					Indirizzo e-mail<br>
					<input class="input" type="text" name="mail">
				</div><br>
				<div class="inline-div">
					Nazione<br>
					<input class="input" type="text" name="nationality">
				</div>
				<div class="inline-div">
					Città<br>
					<input class="input" type="text" name="city">
				</div><br>
				<div class="inline-div">
					Via<br>
					<input class="input" type="text" name="street">
				</div>
				<div class="inline-div">
					Civico<br>
					<input class="input" type="text" name="civico">
				</div><br>
				<div class="inline-div">
					Provincia<br>
					<input class="input" type="text" name="provincia">
				</div>
				<div class="inline-div">
					CAP<br>
					<input class="input" type="text" name="cap">
				</div><br>
				<div class="div-reg-cred">
					<div>
						Username<br>
						<input class="input" type="text" name="username">
					</div><br>
					<div>
						Password <p id="pPass" class="pPass">(8 caratteri di cui 1 maiuscolo 1 minuscolo e 1 numero)</p><br>
						<input class="input" type="password" name="password">
					</div><br>
					<div>
						Conferma Password<br>
            			<input type="password" name="cpassword">
            		</div>
				</div><br>
				<div class="div-reg-button">
					<button type="reset" name="reset">Reset</button>
					<button id="ok" type="submit" name="prosegui">Avanti</button>
				</div>
			</form>
		</div>
	</main>
	<footer></footer>
</body>
</html>
