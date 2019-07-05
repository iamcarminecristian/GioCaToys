package Model;

import java.io.Serializable;

public class ProductBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2561313042333484744L;
	public String getCodProdotto() {
		return codProdotto;
	}
	public void setCodProdotto(String codProdotto) {
		this.codProdotto = codProdotto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(String prezzo) {
		this.prezzo = prezzo;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getEta() {
		return eta;
	}
	public void setEta(String eta) {
		this.eta = eta;
	}
	public String getNovita() {
		return novita;
	}
	public void setNovita(String novita) {
		this.novita = novita;
	}
	private String novita;
	private String eta;
	private String prezzo;
	private String codProdotto;
	private String nome;
	private String categoria;
	private String descrizione;
	private String img;

}
