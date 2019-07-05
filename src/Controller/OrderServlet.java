package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.CarrelloBeanDAO;
import Model.OrderBean;
import Model.OrderBeanDAO;
import Model.ProductBean;
import Model.RegisterBean;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/order.html")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegisterBean r = (RegisterBean) request.getSession().getAttribute("bean");
		if(r!=null) {
			@SuppressWarnings("unchecked")
			ArrayList<ProductBean> prod = (ArrayList<ProductBean>) request.getSession().getAttribute("carrello");
			if(prod==null||prod.size()==0){
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("carrello.jsp");
				requestDispatcher.forward(request, response);
			}
			else {
				int i = 0;
				int totale= 0;
				int temp;
				ArrayList<String> prodotti = new ArrayList<String>();
				while(i<prod.size()) {
					prodotti.add(prod.get(i).getCodProdotto());
					int l = prod.get(i).getPrezzo().length();
					temp = Integer.parseInt(prod.get(i).getPrezzo().substring(0, l-1));
					totale+=temp;
					i++;
				}
				String state = "In elaborazione";
				GregorianCalendar today = new GregorianCalendar();
				String idCarr = (String) request.getSession().getAttribute("idcarr");
				OrderBean o = new OrderBean();
				OrderBeanDAO oDAO = new OrderBeanDAO();
				o.setProdotti(prodotti);
				o.setDate(today.get(Calendar.YEAR)+"-"+(today.get(Calendar.MONTH)+1)+"-"+today.get(Calendar.DAY_OF_MONTH));
				o.setIdCarello(idCarr);
				o.setState(state);
				o.setUser(r.getUsername());
				o.setTotale(totale+"€");
				prod=null;
				CarrelloBeanDAO cDAO = new CarrelloBeanDAO();
				try {
					cDAO.doDelete(idCarr);
					request.getSession().setAttribute("carrello", prod);
					request.getSession().setAttribute("nCarr", 0);
					oDAO.doSave(o);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.sendRedirect("error.html");
				}
				request.setAttribute("ord", o);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("ordine.jsp");
				requestDispatcher.forward(request, response);
			}
		}
		else {
			response.sendRedirect("carrello.html");
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
