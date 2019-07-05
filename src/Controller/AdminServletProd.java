package Controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Model.ProductBean;
import Model.ProductBeanDAO;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/gestprod.html")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
				maxFileSize=1024*1024*20,      	// 10MB
				maxRequestSize=1024*1024*200)		//200MB
public class AdminServletProd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR="C:\\Users\\Prova\\eclipse-workspace-WEB\\Gio.Ca-Toys (4).zip_expanded\\Gio.Ca-Toys2\\WebContent\\img\\prodotti";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServletProd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object ad = request.getSession().getAttribute("ad");
		if(ad!=null) {
			ProductBeanDAO pDAO = new ProductBeanDAO();
			ArrayList<ProductBean> prod;
			ProductBean p;
			String add = request.getParameter("add");
			String e = request.getParameter("e");
			String cod = request.getParameter("cod");
			try {
				if(e!=null) {
					if(request.getParameter("select").equals("edit")) {
						ProductBean temp = pDAO.doRetriveByKey(e);
						request.getSession().setAttribute("gestPC", temp);
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("gestProd.jsp");
						requestDispatcher.forward(request, response);
					}
					else {
						pDAO.doDelete(e);
						response.sendRedirect("gestprod.html");
					}
				}
				else if(cod!=null) {
					ProductBean temp = pDAO.doRetriveByKey(cod);
					String name = request.getParameter("nome");
					String cat = request.getParameter("cat");
					String desc = request.getParameter("desc");
					String prezzo = request.getParameter("prezzo");
					String eta = request.getParameter("eta");
					String novita = request.getParameter("novita");
					p = new ProductBean();
					p.setCodProdotto(cod);
					if(!name.equals("")) {
						p.setNome(name);
					} else {
						p.setNome(temp.getNome());
					}
					if(!cat.equals("")) {
						p.setCategoria(cat);
					}else {
						p.setCategoria(temp.getCategoria());
					}
					if(!desc.equals("")) {
						p.setDescrizione(desc);
					} else { 
						p.setDescrizione(temp.getDescrizione());
					}
					if(!prezzo.equals("")) {
						p.setPrezzo(prezzo);
					} else {
						p.setPrezzo(temp.getPrezzo().substring(0, temp.getPrezzo().length()-1));
					}
					if(!eta.equals("")) {
						p.setEta(eta);
					} else {
						p.setEta(temp.getPrezzo());
					}
					if(!novita.equals("")) {
						p.setNovita(novita);
					} else {
						p.setNovita(temp.getNovita());
					}
					p.setImg(temp.getImg());
					pDAO.doSaveOrUpdate(p);
					response.sendRedirect("gestprod.html");
				} else if(add!=null){
					String name = request.getParameter("nome");
					String cat = request.getParameter("cat");
					String desc = request.getParameter("desc");
					String prezzo = request.getParameter("prezzo");
					String eta = request.getParameter("eta");
					String novita = request.getParameter("novita");
					File uploads = new File(SAVE_DIR);
					Part filePart = request.getPart("photo");
					String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
					InputStream fileContent = filePart.getInputStream();
					File file = new File(uploads, fileName);
					Files.copy(fileContent, file.toPath());
					String path = "img\\prodotti\\"+fileName;
					p = new ProductBean();
					p.setNome(name);
					p.setCategoria(cat);
					p.setDescrizione(desc);
					p.setPrezzo(prezzo);
					p.setImg(path);
					if(!eta.equals("")) {
						p.setEta(eta);
					} else {
						p.setEta("0-99");
					}	
					p.setNovita(novita);
					pDAO.doSave(p);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("gestProd.jsp");
					requestDispatcher.forward(request, response);
				}
				else {
					prod = pDAO.doRetriveAll();
					request.getSession().setAttribute("prodA", prod);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("gestProd.jsp");
					requestDispatcher.forward(request, response);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
