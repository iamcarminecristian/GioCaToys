package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.ProductBean;

/**
 * Servlet implementation class getProdotti
 */
@WebServlet("/prodotti.html")
public class getProdotti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getProdotti() {
        super();
      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		ArrayList<ProductBean> prod = (ArrayList<ProductBean>)request.getSession().getAttribute("prod");
		int pagina = Integer.parseInt(request.getParameter("page"));
		response.setCharacterEncoding("UTF-8");
		for( int i =(pagina-1)*8; (i< pagina*8)&&(i < prod.size()); i++){
			response.getWriter().append("<div class=\"spanProd\"> " 
				+ "	<a class=\"pLink\" href=\"product.html?cod= "+prod.get(i).getCodProdotto()+ "\"> " 
				+ "	<div class=\"spanProd2\"> " 
				+ "	<img src=\" " + prod.get(i).getImg()+"\" class=\"imgProd\"> <br><br> " 
				+ "	<span> " + prod.get(i).getNome() + " </span> <br><br> " 
				+ "	<span>Categoria: " + prod.get(i).getCategoria() +"</span> <br><br> " 
				+ "	<span> " + prod.get(i).getPrezzo()+"</span> <br><br>     " 
				+ "	</div> " 
				+ "	</a> " 
				+ "	<a onclick=\"alert('Prodotto aggiunto al carrello')\" href=\"compcarr.html?cod=" + prod.get(i).getCodProdotto() + " \"><button class=\"prodButton\">Aggiungi al carrello</button></a> "
				+ "	</div> ");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
