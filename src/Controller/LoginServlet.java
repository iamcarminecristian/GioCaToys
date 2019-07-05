package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/index.html")
public class LoginServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// recupero login e password e li memorizzo in userB se presenti
			RegisterBean userB = getUsrPsw(request);
			try{
				ProductBeanDAO pDAO = new ProductBeanDAO();
				ArrayList<ProductBean> bestSeller = pDAO.doRetriveByOrder();
				request.setAttribute("bestS", bestSeller);
				if(userB==null) {
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
					requestDispatcher.forward(request, response);
				}
				else{
					RegisterBeanDAO rbDAO = new RegisterBeanDAO();
					RegisterBean rb = rbDAO.doRetriveByKey(userB.getUsername(),userB.getPassword());
					// l'utente è ammesso al sito: inserisco dati di login in cookies e do risposta
					Cookie usrcookie = new Cookie("usr", rb.getUsername());
					Cookie pswcookie = new Cookie("psw", rb.getPassword());
					response.addCookie(usrcookie);
					response.addCookie(pswcookie);
					/*request.setAttribute("registerBean", rb);*/  // l'output ha bisogno di queste informazioni
					HttpSession session = request.getSession();
					if(rb.getAdmin()!=null) {
						Object o = new Object();
						session.setAttribute("ad", o);
					}
					session.setAttribute("bean", rb);
					CarrelloBeanDAO cDAO = new CarrelloBeanDAO();
					CarrelloBean c = cDAO.doRetriveByKey(rb.getUsername());
					ArrayList<ProductBean> prod = new ArrayList<ProductBean>();
					int i = 0;
					if(c.getProdotti()!=null) {
						while(i<c.getProdotti().size()) {
							prod.add(pDAO.doRetriveByKey(c.getProdotti().get(i)));
							i++;
						}
					}
					request.getSession().setAttribute("idcarr", c.getCodCarrello());
					request.getSession().setAttribute("nCarr", prod.size());
					request.getSession().setAttribute("carrello", prod);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
					requestDispatcher.forward(request, response);
				}
			} catch(Exception e) {  
				request.setAttribute("err", "err");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
				requestDispatcher.forward(request, response);
			}
		}

			
		private RegisterBean getUsrPsw(HttpServletRequest request) {
			
			RegisterBean rb = null;
			String usr = null, psw = null;
			Cookie[] c = request.getCookies();   // tramite cookie
			if (c!=null) { 
				for(int i=0;i<c.length;i++) {
					if (c[i].getName().equals("usr")) 
						usr = c[i].getValue();
					if (c[i].getName().equals("psw")) 
						psw = c[i].getValue();	
				}
			} 	
			if (usr == null || psw == null){		// se recupero tramite cookie fallisce, allora tramite parametri	
				String temp;                        
				temp = request.getParameter("user");
				if (temp!= null) {
					usr = temp;
					temp = request.getParameter("pass");   
					if (temp!= null) {
						psw = temp;
					}
				}
			}
			if (usr != null && psw != null) {    // se recupero ha avuto successo riempio bean
				 rb = new RegisterBean();
				 rb.setUsername(usr);
				 rb.setPassword(psw);
			}
			return rb;
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			doGet(request, response);
			
		}
	}

