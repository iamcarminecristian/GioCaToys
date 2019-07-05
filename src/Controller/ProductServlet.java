package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.ProductBeanDAO;
import Model.ProductBean;
/**
 * Servlet implementation class ProductServley
 */
@WebServlet("/product.html")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductBeanDAO pDAO = new ProductBeanDAO();
		ArrayList<ProductBean> prod;
		ProductBean p;
		String cat = request.getParameter("cat");
		String cod = request.getParameter("cod");
		String name = request.getParameter("name");
		String adv = request.getParameter("Adv");
		try {	
			if(cod!=null) {
				p = pDAO.doRetriveByKey(cod);
				request.setAttribute("prodCod", p);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("prod.jsp");
				requestDispatcher.forward(request, response);
			}else if(adv!=null){
				String age = request.getParameter("age");
				String category = request.getParameter("category");
				String range = request.getParameter("range");
				prod = pDAO.doRetriveByCondAdv(category, age, range);
				request.setAttribute("prodAdv", prod);
				request.setAttribute("page", 1);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("prodotti.jsp");
				requestDispatcher.forward(request, response);
			}else if(name!=null) {
				prod = pDAO.doRetriveByName(name);
				request.setAttribute("prodN", prod);
				request.setAttribute("page", 1);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("prodotti.jsp");
				requestDispatcher.forward(request, response);
			}else if(cat!=null) {
				prod = pDAO.doRetriveByCond(cat);
				request.setAttribute("prodCat", prod);
				request.setAttribute("page", 1);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("prodotti.jsp");
				requestDispatcher.forward(request, response);
			}else {
				prod = pDAO.doRetriveAll();
				request.setAttribute("prodA", prod);
				request.setAttribute("page", 1);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("prodotti.jsp");
				requestDispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.html");
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
