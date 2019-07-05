<%@ page language="java" import="Model.*" import="java.util.ArrayList" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="style\order.css">
	<script src="script\jquery.js"></script>
	<script src="script\script.js"></script>
<title>GioCa-Toys ~ Gestione Ordini</title>
</head>
<body onresize="resize2()">
	<%@include file="header.jsp" %>
	<main>
	<%
		if(session.getAttribute("ad")!=null){
			ArrayList<OrderBean> ordini = (ArrayList<OrderBean>) request.getAttribute("ordini");
			int n = ordini.size();
			int i = 0;
			if(n==0){
				%>
				<h2>Non sono presenti ordini</h2>
				<% 
			}
			%>
			<div class="divOrdini">
			<%
			while(i<n){
			%>
				<div class="spanOrdini">
					<p>ID: <span><%=ordini.get(i).getIdOrdine()%></span></p>
					<p>User: <span><%=ordini.get(i).getUser() %></span></p>
					<p>Data: <span><%=ordini.get(i).getDate()%></span></p>
					<p>Totale: <span><%=ordini.get(i).getTotale() %></span></p>
					<p>Stato: <span><%=ordini.get(i).getState() %></span></p>
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
					<form action="gestorder.html" method="post">
						<input type="radio" name="select" value="accept">Accept<br><br>
						<input type="radio" name="select" value="reject">Reject<br><br>
						<input type="hidden" name="do" value=<%=ordini.get(i).getIdOrdine() %>>
						<button type="submit">Do</button>
					</form>
				</div>
			<%
				i++;
			}
			%>
			</div>
			<%
		} else{
			response.sendRedirect("index.html");
		}
	%>
	</main>
	<footer></footer>
</body>
</html>