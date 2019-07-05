<%@ page language="java" import="Model.*" import="java.util.ArrayList" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="style\userarea.css">
	<script src="script\jquery.js"></script>
	<script src="script\script.js"></script>
<title>GioCa-Toys ~ Area Utente</title>
</head>
<body onresize="resize2()">
	<%@include file="header.jsp" %>
	<main>
		<%
			ArrayList<OrderBean> ordini = (ArrayList<OrderBean>) request.getAttribute("userOrd");
			RegisterBean r = (RegisterBean) session.getAttribute("bean");
			%>
			<h2>I tuoi dati personali</h2>
			<div class="userD">
				<p>Nome : <span><%=r.getNome() %> <%=r.getCognome() %></span></p>
				<p>Username : <span><%= r.getUsername() %></span></p>
				<p>Email : <span><%=r.getMail() %></span></p>
				<p>Data di Nascita : <span><%=r.getBday() %></span></p>
				<p>Indirizzo principale : <span>Via <%=r.getVia() %>  <%=r.getCivico() %> <%=r.getCitta() %> (<%=r.getProvincia() %>) <%=r.getCap() %> <%=r.getNazionalita() %></span></p>
 			</div>
 			<form action="user.html">
 			<input type="hidden" name="mod" value="mod">
 			<button>Modifica i tuoi dati</button>
 			</form>
 			
 			<h2>Qui trovi tutti gli ordini che hai effettuato</h2>
 			
			<%
			if(ordini.size()==0){
				%>
				<h2 class="notOrder">Non hai mai effettuato un Ordine</h2>
				<%
			}else{
				int i = 0;
				while(i<ordini.size()){
					if(i!=0){
						%>
						<hr>
						<%
					}
				%>
					<div class="userO">
						<p>Ordine effettuato da <span><%=ordini.get(i).getUser() %></span></p>
						<p>Id Ordine: <span><%=ordini.get(i).getIdOrdine() %></span></p>
						<p>Data Ordine: <span><%=ordini.get(i).getDate() %></span></p>
						<p>Stato Ordine: <span><%=ordini.get(i).getState() %></span></p>
						<p>Prodotti Acquistati:<br>
						<%
							ArrayList<ProductBean> prod = (ArrayList<ProductBean>)request.getAttribute(ordini.get(i).getIdOrdine());
							int j = 0;
							while(j<prod.size()){
								%>
									<span>- <%=prod.get(j).getNome() %></span><br>
								<%
								j++;
							}
						%>
						</p>
						<p>Totale: <span><%=ordini.get(i).getTotale() %></span></p>
					</div>
				<%
					i++;
				}
			}
		%>
	</main>
	<footer></footer>
</body>
</html>