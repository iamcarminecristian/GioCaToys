package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.*;

/**
 * Servlet implementation class ComponiCarrelloServlet
 */
@WebServlet("/compcarr.html")
public class ComponiCarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComponiCarrelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("ad")==null) {
			String cod = request.getParameter("cod");
			String rem = request.getParameter("rem");
			String idCarr = (String) request.getSession().getAttribute("idcarr");
			String id = request.getParameter("idcarr");
			RegisterBean r = (RegisterBean) request.getSession().getAttribute("bean");
			ArrayList<ProductBean> prod;
			ProductBeanDAO pDAO = new ProductBeanDAO();
			CarrelloBeanDAO cDAO = new CarrelloBeanDAO();
			try {
				if(r!=null) {
					CarrelloBean c = cDAO.doRetriveByKey(r.getUsername());
					prod = new ArrayList<ProductBean>();
					int i = 0;
					if(c.getProdotti()!=null) {
						while(i<c.getProdotti().size()) {
							prod.add(pDAO.doRetriveByKey(c.getProdotti().get(i)));
							i++;
						}
					}
				} else {
					prod = (ArrayList<ProductBean>) request.getSession().getAttribute("carrello");
					if(prod==null)
						prod = new ArrayList<ProductBean>();
				}
				request.getSession().setAttribute("nCarr", prod.size());
				request.getSession().setAttribute("carrello", prod);		
				if(rem!=null) {
					if(r!=null) 
						cDAO.doDeleteByKey(rem, id);
					for(int i =0; i<prod.size(); i++) {
						if(prod.get(i).getCodProdotto().equals(rem))
							prod.remove(i);
					}
					request.getSession().setAttribute("nCarr", prod.size());
					request.getSession().setAttribute("carrello", prod);
					response.sendRedirect("compcarr.html");
				}
				else if(cod!=null) {
					ProductBean p = pDAO.doRetriveByKey(cod);
					if(r!=null) {
						prod.add(p);
						cDAO.doSave(idCarr, cod);
						request.getSession().setAttribute("nCarr", prod.size());
						request.getSession().setAttribute("carrello", prod);
					}else {
						prod.add(p);
						request.getSession().setAttribute("nCarr", prod.size());
						request.getSession().setAttribute("carrello", prod);
					}
					response.sendRedirect("product.html");
				}else {
					response.sendRedirect("carrello.html");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.sendRedirect("error.html");
			}
		}else {
			response.sendRedirect("index.html");
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
