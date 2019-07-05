<%@ page language="java" import="Model.*" import="java.util.ArrayList" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="style/carrello.css">
	<script src="script\jquery.js"></script>
	<script src="script\script.js"></script>
<title>GioCa-Toys ~ Carrello</title>
</head>
<body onresize="resize2()">
	<%@include file="header.jsp" %>
	<main>
		<%
			if(session.getAttribute("ad")!=null){
				response.sendRedirect("index.html");
			}
			ArrayList<ProductBean> prod = (ArrayList<ProductBean>) request.getSession().getAttribute("carrello");
			String idCarr = (String) request.getSession().getAttribute("idcarr");

			if(prod==null||prod.size()==0){
		%>
				<div class="chefoss">
					<div class="nic">
						<h2>Non hai ancora nessun prodotto nel tuo carrello</h2>
						<p><a href="product.html">Corri ad aggiungerne qualcuno</a></p>
					</div>	
				</div>	
		<%
			}
			else{
				int prezzo = 0;
				int temp;
				int i = 0;
				while(i<prod.size()){
			%>
					<div class="spanProd">
						<a title="Vai al prodotto" href="product.html?cod=<%=prod.get(i).getCodProdotto()%>">
						<div>
						<img src=<%=prod.get(i).getImg()%> class="imgProd"> <br><br>
						<span><%=prod.get(i).getNome()%></span> <br><br>
						<p>Prezzo: <span><%=prod.get(i).getPrezzo()%></span></p> <br><br>
						</div>
						</a>
						<%
						if(idCarr!=null){
						%>
							<a id="rem" title="Rimuovi il prodotto"  href="compcarr.html?rem=<%= prod.get(i).getCodProdotto() %>&idcarr=<%=idCarr%>">Rimuovi</a>
						<%
						}else{
						%>
							<a id="rem" title="Rimuovi il prodotto"  href="compcarr.html?rem=<%= prod.get(i).getCodProdotto() %>">Rimuovi</a>
						<%
						}
						%>
					</div>
					<%
					int l = prod.get(i).getPrezzo().length();
					temp = Integer.parseInt(prod.get(i).getPrezzo().substring(0, l-1));
					prezzo+=temp;
					i++;
					
				}
				
					%>
				<div class="tot">
					<p>Totale: <span><%=prezzo %>€</span></p><br><br>
					<% 
					RegisterBean r = (RegisterBean) session.getAttribute("bean");
					if(r!=null){
					%>
						<a href="order.html"><button class="accept">Acquista</button></a>
					<%
					}else{
					%>
						<button onclick="alert('Accedi o Registrati per effettuare il tuo ordine')" class="accept">Acquista</button>
					<%
					}
					%>
				</div>
		<%
			}
		%>
	</main>
	<footer></footer>
</body>
</html>