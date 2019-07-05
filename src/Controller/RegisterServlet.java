package Controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.CarrelloBeanDAO;
import Model.RegisterBean;
import Model.RegisterBeanDAO;

@WebServlet("/registrati.html")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegisterBean utente = new RegisterBean();
		String username = request.getParameter("username");
		try {
			if(username.matches("^[0-9A-Za-z\\.-]+$"))
				utente.setUsername(username);
			else {
				request.setAttribute("err1", "err");
				RequestDispatcher view = request.getRequestDispatcher("registrazione.jsp");
				view.forward(request, response);
			}
			String password = request.getParameter("password");
			if(password.length()>7)
				utente.setPassword(password);
			else {
				request.setAttribute("err1", "err");
				RequestDispatcher view = request.getRequestDispatcher("registrazione.jsp");
				view.forward(request, response);
			}
			String nome = request.getParameter("name");
			if(!nome.equals("")) 
				utente.setNome(nome);
			else {
				request.setAttribute("err1", "err");
				RequestDispatcher view = request.getRequestDispatcher("registrazione.jsp");
				view.forward(request, response);
			}
			String cognome = request.getParameter("surname");
			if(!cognome.equals(""))
				utente.setCognome(cognome);
			else {
				request.setAttribute("err1", "err");
				RequestDispatcher view = request.getRequestDispatcher("registrazione.jsp");
				view.forward(request, response);
			}
			String dataNascita = request.getParameter("bday");
			if(!dataNascita.equals("")) 
				utente.setBday(dataNascita);
			else {
				request.setAttribute("err1", "err");
				RequestDispatcher view = request.getRequestDispatcher("registrazione.jsp");
				view.forward(request, response);
			}
			String nazionalita = request.getParameter("nationality");
			if(!nazionalita.equals("")) 
				utente.setNazionalita(nazionalita);
			else {
				request.setAttribute("err1", "err");
				RequestDispatcher view = request.getRequestDispatcher("registrazione.jsp");
				view.forward(request, response);
			}	
			String via = request.getParameter("street");
			if(!via.equals("")) 
				utente.setVia(via);
			else {
				request.setAttribute("err1", "err");
				RequestDispatcher view = request.getRequestDispatcher("registrazione.jsp");
				view.forward(request, response);
			}
			String provincia = request.getParameter("provincia");
			if(!provincia.equals("")) 
				utente.setProvincia(provincia);
			else {
				request.setAttribute("err1", "err");
				RequestDispatcher view = request.getRequestDispatcher("registrazione.jsp");
				view.forward(request, response);
			}
			String mail = request.getParameter("mail");
			if(mail.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$"))
				utente.setMail(mail);
			else {
				request.setAttribute("err1", "err");
				RequestDispatcher view = request.getRequestDispatcher("registrazione.jsp");
				view.forward(request, response);
			}
			String citta = request.getParameter("city");
			if(!citta.equals("")) 
				utente.setCitta(citta);
			else {
				request.setAttribute("err1", "err");
				RequestDispatcher view = request.getRequestDispatcher("registrazione.jsp");
				view.forward(request, response);
			}
			String civico = request.getParameter("civico");
			if(!civico.equals("")) 
				utente.setCivico(civico);
			else {
				request.setAttribute("err1", "err");
				RequestDispatcher view = request.getRequestDispatcher("registrazione.jsp");
				view.forward(request, response);
			}
			String cap = request.getParameter("cap");
			if(!cap.equals("")) 
				utente.setCap(cap);
			else {
				request.setAttribute("err1", "err");
				RequestDispatcher view = request.getRequestDispatcher("registrazione.jsp");
				view.forward(request, response);
			}
			RegisterBeanDAO rbDAO = new RegisterBeanDAO();	
			CarrelloBeanDAO carrello = new CarrelloBeanDAO();
			rbDAO.doSave(utente);
			carrello.doCreate(utente.getUsername());
			response.sendRedirect("index.html");
		} catch (Exception e) {
			e.printStackTrace();	
			request.setAttribute("err1", "err");
			RequestDispatcher view = request.getRequestDispatcher("registrazione.jsp");
			view.forward(request, response);
		}
	}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
