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
import Model.RegisterBean;
import Model.RegisterBeanDAO;

/**
 * Servlet implementation class UserAreaServlet
 */
@WebServlet("/user.html")
public class UserAreaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAreaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegisterBean r  = (RegisterBean) request.getSession().getAttribute("bean");
		if(r!=null) {
			try {
				String mod = request.getParameter("mod");
				String user = request.getParameter("user");
				String userP = request.getParameter("userP");
				if(mod!=null) {
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("usermod.jsp");
					requestDispatcher.forward(request, response);	
				}
				else if(user!=null) {
					String name = request.getParameter("nome");
					String cognome = request.getParameter("cognome");
					String bday = request.getParameter("bday");
					String mail = request.getParameter("mail");
					String naz = request.getParameter("naz");
					String cit = request.getParameter("cit");
					String via = request.getParameter("via");
					String civ = request.getParameter("civ");
					String prov = request.getParameter("prov");
					String cap = request.getParameter("cap");
					RegisterBean temp = new RegisterBean();
					temp.setUsername(user);
					if(!name.equals("")) {
						temp.setNome(name);
					} else {
						temp.setNome(r.getNome());
					}
					if(!cognome.equals("")) {
						temp.setCognome(cognome);
					}else {
						temp.setCognome(r.getCognome());
					}
					if(!bday.equals("")) {
						temp.setBday(bday);
					} else { 
						temp.setBday(r.getBday());
					}
					if(!mail.equals("")) {
						temp.setMail(mail);
					} else {
						temp.setMail(r.getMail());
					}
					if(!naz.equals("")) {
						temp.setNazionalita(naz);
					} else {
						temp.setNazionalita(r.getNazionalita());
					}
					if(!via.equals("")) {
						temp.setVia(via);
					} else {
						temp.setVia(r.getVia());
					}
					if(!cit.equals("")) {
						temp.setCitta(cit);
					} else {
						temp.setCitta(r.getCitta());
					}
					if(!civ.equals("")) {
						temp.setCivico(civ);
					} else {
						temp.setCivico(r.getCivico());
					}
					if(!prov.equals("")) {
						temp.setProvincia(prov);
					} else {
						temp.setProvincia(r.getPassword());
					}
					if(!cap.equals("")) {
						temp.setCap(cap);
					} else {
						temp.setCap(r.getCap());
					}
					if(!cap.equals("")) {
						temp.setCap(cap);
					} else {
						temp.setCap(r.getCap());
					}
					temp.setPassword(r.getPassword());
					temp.setIdIndirizzo(r.getIdIndirizzo());
					RegisterBeanDAO rDAO = new RegisterBeanDAO();
					rDAO.doSaveOrUpdate(temp);
					response.sendRedirect("index.html");
				}
				else if(userP!=null) {
					RegisterBean temp = r;
					String pass = request.getParameter("pass2");
					temp.setPassword(pass);
					RegisterBeanDAO rDAO = new RegisterBeanDAO();
					rDAO.doSaveOrUpdate(temp);
					response.sendRedirect("user.html");
				}
				else {
					OrderBeanDAO oDAO = new OrderBeanDAO();
					ArrayList<OrderBean> ordini = oDAO.doRetriveByCond(r.getUsername());
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
					request.setAttribute("userOrd", ordini);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("userarea.jsp");
					requestDispatcher.forward(request, response);				
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.sendRedirect("error.html");
			}
		}
		else {
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
