package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -419749998673651533L;

	public String getIdCarello() {
		return idCarello;
	}
	public void setIdCarello(String idCarello) {
		this.idCarello = idCarello;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTotale() {
		return totale;
	}
	public void setTotale(String totale) {
		this.totale = totale;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getIdOrdine() {
		return idOrdine;
	}
	public void setIdOrdine(String idOrdine) {
		this.idOrdine = idOrdine;
	}
	public ArrayList<String> getProdotti() {
		return prodotti;
	}
	public void setProdotti(ArrayList<String> prodotti) {
		this.prodotti = prodotti;
	}
	private ArrayList<String> prodotti;
	private String idOrdine;
	private String idCarello;
	private String date;
	private String state;
	private String totale;
	private String user;
}
