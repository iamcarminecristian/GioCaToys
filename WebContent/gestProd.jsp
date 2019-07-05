<%@ page language="java" import="Model.*" import="java.util.ArrayList" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="style\prodotti.css">
		<script src="script\jquery.js"></script>
	<script src="script\script.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GioCa-Toys ~ Gestione Prodotti</title>
</head>
<body onresize="resize2()">
	<%@include file="header.jsp" %>
	<main>
		<%
			if(session.getAttribute("ad")!=null){
				if(request.getParameter("select")==null){
					ArrayList<ProductBean> prod;
					prod =(ArrayList<ProductBean>)session.getAttribute("prodA");
					int n = prod.size();
					int i=0;%>
					<div class="addP">
						<h2>Aggiungi prodotti</h2>
						<form  id="addPr" action="gestprod.html" method="post" enctype="multipart/form-data">
							<input type="text" name="nome" placeholder="Nome" required>
							Categoria: <select  name="cat">
								<option selected value="Sportivi">Giochi Sportivi</option>
								<option value="Costruzioni">Costruzioni</option>
								<option value="Elettronici">Giochi Elettronici</option>
								<option value="Veicoli">Veicoli Giocattolo</option>
								<option value="Peluche">Peluche</option>
								<option value="Azione">Personaggi d'azione</option>
							</select>							
							<textarea form="addPr" name="desc" placeholder="Descrizione" required></textarea>
							<input type="number" step="0.01" min="0" name="prezzo" placeholder="Prezzo" required>
							Età: <select name="eta">
								<option value="0-5">0-5</option>
								<option value="6-8">6-8</option>
								<option value="9-12">9-12</option>
								<option value="12+">12+</option>
								<option selected value="0-99">0-99</option>
							</select>
							Novità: <select name="novita">
								<option value="Si">Si</option>
								<option selected value="No">No</option>
							</select>					
							<input type="file" name="photo">
							<input type="hidden" name="add" value="add">
							<button class="accept" type="submit">Add</button>
							<button type="reset">Reset</button>
						</form>
					</div>
					<div class="prod prodgest">
					<h2>Modifica Prodotti</h2>
					<%
					while(i<n){
					%>
						<div class="spanProd gestProd">
							<a class="pLink" href="product.html?cod=<%=prod.get(i).getCodProdotto() %>">
							<div class="spanProd2">
                				<img src=<%=prod.get(i).getImg()%> class="imgProd">
                				<p>Nome: <span><%=prod.get(i).getNome()%></span></p>
                				<p>Categoria: <span><%=prod.get(i).getCategoria() %></span></p>
                				<p>Prezzo: <span><%=prod.get(i).getPrezzo()%></span></p>
              				</div>
							</a>
								<form action="gestprod.html" method="post">
									<input type="radio" name="select" value="edit">Edit<br><br>
									<input type="radio" name="select" value="remove">Remove<br><br>
									<input type="hidden" name="e" value=<%=prod.get(i).getCodProdotto() %>>
									<button class="accept" type="submit">Do</button>
								</form>
						</div>
					<%
						i++;
					}
					%>
					</div>
					<%
				}
				else{
					ProductBean prod = (ProductBean)session.getAttribute("gestPC");
					if(request.getParameter("select").equals("edit")){
						%>
						<form id="modPr" action="gestprod.html" method="post">
							<p>Nome attuale: <span><%=prod.getNome() %></span></p>
							<p>Modifica:<input type="text" name="nome" placeholder="Nome" value=""></p>
							<p>Categoria attuale: <span><%=prod.getCategoria() %></span></p>
							<p>Modifica:<select  name="cat">
								<option selected value=""></option>
								<option value="Sportivi">Giochi Sportivi</option>
								<option value="Costruzioni">Costruzioni</option>
								<option value="Elettronici">Giochi Elettronici</option>
								<option value="Veicoli">Veicoli Giocattolo</option>
								<option value="Peluche">Peluche</option>
								<option value="Azione">Personaggi d'azione</option>
							</select></p>
							<p id="des">Descrizione attuale: <span><br><%=prod.getDescrizione()%></span></p>
							<p>Modifica:<textarea form="modPr" name="desc" placeholder="Descrizione"></textarea></p>
							<p>Prezzo attuale: <span><%=prod.getPrezzo() %></span></p>	
							<p>Modifica:<input type="number" min="0" name="prezzo" placeholder="Prezzo"></p>
							<p>Età attuale: <span><%=prod.getEta() %></span></p>	
							<p>Modifica: <select name="eta">
									<option selected value=""></option>
									<option value="0-5">0-5</option>
									<option value="6-8">6-8</option>
									<option value="9-12">9-12</option>
									<option value="12+">12+</option>
									<option value="0-99">0-99</option>
								</select>	
							</p>
							<p>Novità attuale: <span><%=prod.getNovita() %></span></p>	
							<p>Modifica: <select name="novita">
								<option selected value=""></option>
								<option value="Si">Si</option>
								<option value="No">No</option>
							</select>
							</p>
						<input type="hidden" name="cod" value=<%=prod.getCodProdotto() %>>
						<button class="accept" type="submit">Submit</button>
						<button type="reset">Reset</button>
						</form>
						<%
					}
				}
			}
			else{
				response.sendRedirect("index.html");
			}
			
		%>
	</main>
	<footer></footer>
</body>
</html>