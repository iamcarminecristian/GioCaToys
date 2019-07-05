package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.OrderBean;
import Model.OrderBeanDAO;
import Model.ProductBean;
import Model.ProductBeanDAO;

/**
 * Servlet implementation class AdminServletOrder
 */
@WebServlet("/gestorder.html")
public class AdminServletOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServletOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object ad = request.getSession().getAttribute("ad");
		if(ad!=null) {
			OrderBeanDAO oDAO = new OrderBeanDAO();
			ArrayList<OrderBean> ordini;
			OrderBean o;
			String s = request.getParameter("do");
			try {
				if(s!=null) {
					o = oDAO.doRetriveByKey(s);
					if(request.getParameter("select").equals("accept")) {
						o.setState("Accettato");
						oDAO.doSaveOrUpdate(o);
					} else {
						o.setState("Rifiutato");
						oDAO.doSaveOrUpdate(o);
					}
					response.sendRedirect("gestorder.html");
				}else {
					ordini = oDAO.doRetriveAll();
					ProductBeanDAO pDAO = new ProductBeanDAO();
					int i = 0;
					while(i<ordini.size()) {
						ArrayList<ProductBean> prod = new ArrayList<ProductBean>();
						int j = 0;
						while(j<ordini.get(i).getProdotti().size()) {
							prod.add(pDAO.doRetriveByKey(ordini.get(i).getProdotti().get(j)));
							j++;
						}
						request.setAttribute(ordini.get(i).getIdOrdine(), prod);
						i++;
					}
					request.setAttribute("ordini", ordini);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("gestOrder.jsp");
					requestDispatcher.forward(request, response);
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
