package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class CarrelloBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7879469539000019944L;
	public CarrelloBean(){
		prodotti = new ArrayList<String>();
	}
	public String getCodCarrello() {
		return codCarrello;
	}
	public void setCodCarrello(String codCarrello) {
		this.codCarrello = codCarrello;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public ArrayList<String> getProdotti() {
		return prodotti;
	}
	public void setProdotti(ArrayList<String> prodotti) {
		this.prodotti = prodotti;
	}
	
	private String codCarrello;
	private String user;
	private ArrayList<String> prodotti;
}
