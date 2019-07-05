package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

public class ProductBeanDAO {

	public synchronized void doSave(ProductBean p) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		PreparedStatement query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
			"insert into giocatoys.prodotto (nome, categoria, descrizione, prezzo, img, eta, novita) values (?, ?, ?, ?, ?, ?, ?)");
		query.setString(1, p.getNome());
		query.setString(2, p.getCategoria());
		query.setString(3, p.getDescrizione());
		query.setString(4, p.getPrezzo());
		query.setString(5, p.getImg());
		if(p.getEta()!=null) {
			query.setString(6, p.getEta());
		} else {
			query.setString(6, "0-99");
		}
		query.setString(7, p.getNovita());
		query.executeUpdate();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	public synchronized void doSaveOrUpdate(ProductBean p) throws Exception{
		ProductBean temp = doRetriveByKey(p.getCodProdotto());
		if(temp==null) {
			doSave(p);
		}
		else {
			java.sql.Connection con = DriverManagerConnectionPool.getConnection();
			PreparedStatement query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
					"update giocatoys.prodotto set nome=? , categoria=? , descrizione=? , prezzo=? , img=?, eta=?, novita=? where codProdotto =?");	
			query.setString(1, p.getNome());
			query.setString(2, p.getCategoria());
			query.setString(3, p.getDescrizione());	
			query.setString(4, p.getPrezzo());
			query.setString(5, p.getImg());
			query.setString(6, p.getEta());
			query.setString(7, p.getNovita());
			query.setString(8, p.getCodProdotto());
			query.executeUpdate();
			DriverManagerConnectionPool.releaseConnection(con);
		}
	}
	public synchronized ProductBean doRetriveByKey(String codProdotto) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		ProductBean p = new ProductBean();
		p.setCodProdotto(codProdotto);
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM giocatoys.prodotto where codProdotto=?");
		query.setString(1, p.getCodProdotto());
		ResultSet result = query.executeQuery();
		if(!result.next()) {
			throw new Exception();
		}
		p.setCategoria(result.getString("categoria"));
		p.setNome(result.getString("nome"));
		p.setDescrizione(result.getString("descrizione"));
		p.setPrezzo(result.getString("prezzo")+"€");
		p.setImg(result.getString("img"));
		p.setEta(result.getString("eta"));
		p.setNovita(result.getString("novita"));
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return p;	
	}
	public synchronized ArrayList<ProductBean> doRetriveByCond(String categoria) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		ArrayList<ProductBean> prodotti = new ArrayList<ProductBean>();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM giocatoys.prodotto where categoria=?");
		query.setString(1, categoria);
		ResultSet result = query.executeQuery();
		while(result.next()) {
			ProductBean p = new ProductBean();
			p.setCodProdotto(result.getString("codProdotto"));
			p.setCategoria(result.getString("categoria"));
			p.setNome(result.getString("nome"));
			p.setDescrizione(result.getString("descrizione"));
			p.setPrezzo(result.getString("prezzo")+"€");
			p.setImg(result.getString("img"));
			p.setEta(result.getString("eta"));
			p.setNovita(result.getString("novita"));
			prodotti.add(p);
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return prodotti;
	}
	public synchronized ArrayList<ProductBean> doRetriveByCondN(String novita) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		ArrayList<ProductBean> prodotti = new ArrayList<ProductBean>();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM giocatoys.prodotto where novita=?");
		query.setString(1, novita);
		ResultSet result = query.executeQuery();
		while(result.next()) {
			ProductBean p = new ProductBean();
			p.setCodProdotto(result.getString("codProdotto"));
			p.setCategoria(result.getString("categoria"));
			p.setNome(result.getString("nome"));
			p.setDescrizione(result.getString("descrizione"));
			p.setPrezzo(result.getString("prezzo")+"€");
			p.setImg(result.getString("img"));
			p.setEta(result.getString("eta"));
			p.setNovita(result.getString("novita"));
			prodotti.add(p);
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return prodotti;
	}
	public synchronized ArrayList<ProductBean> doRetriveByName(String name) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		ArrayList<ProductBean> prodotti = new ArrayList<ProductBean>();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM giocatoys.prodotto where nome LIKE ?");
		String nome = "%"+name+"%";
		query.setString(1, nome);
		ResultSet result = query.executeQuery();
		while(result.next()) {
			ProductBean p = new ProductBean();
			p.setCodProdotto(result.getString("codProdotto"));
			p.setCategoria(result.getString("categoria"));
			p.setNome(result.getString("nome"));
			p.setDescrizione(result.getString("descrizione"));
			p.setPrezzo(result.getString("prezzo")+"€");
			p.setImg(result.getString("img"));
			p.setEta(result.getString("eta"));
			p.setNovita(result.getString("novita"));
			prodotti.add(p);
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return prodotti;
	}	
	public synchronized ArrayList<ProductBean> doRetriveByCondAdv(String categoria,String eta, String prezzo) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		ArrayList<ProductBean> prodotti = new ArrayList<ProductBean>();
		int price = Integer.parseInt(prezzo);
		PreparedStatement query=null;
		if(categoria.equals("Tutte")) {
			if(eta.equals("Tutte")) {
				if(price==0 || price==100) {
					prodotti = doRetriveAll();
					DriverManagerConnectionPool.releaseConnection(con);
					return prodotti;
				}else {
					query= (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM giocatoys.prodotto where prezzo<?");
					query.setInt(1, price);
				}
			}else {
				if(price==0 || price==100) {
					query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM giocatoys.prodotto where eta=?");
					query.setString(1, eta);
				}else {
					query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM giocatoys.prodotto where eta=? AND prezzo<?");
					query.setString(1, eta);
					query.setInt(2, price);				
				}
			}
		}else {
			if(eta.equals("Tutte")) {
				if(price==0 || price==100) {
					query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM giocatoys.prodotto where categoria=?");
					query.setString(1, categoria);
				}else {
					query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM giocatoys.prodotto where categoria=? AND prezzo<?");
					query.setString(1, categoria);
					query.setInt(2, price);				
				}
			}else {
				if(price==0 || price==100) {
					query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM giocatoys.prodotto where categoria=? AND eta=?");
					query.setString(1, categoria);
					query.setString(2, eta);
				}else {
					query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM giocatoys.prodotto where categoria=? AND eta=? AND prezzo<?");
					query.setString(1, categoria);
					query.setString(2, eta);
					query.setInt(3, price);				
				}
			}
		}
		ResultSet result = query.executeQuery();
		while(result.next()) {
			ProductBean p = new ProductBean();
			p.setCodProdotto(result.getString("codProdotto"));
			p.setCategoria(result.getString("categoria"));
			p.setNome(result.getString("nome"));
			p.setDescrizione(result.getString("descrizione"));
			p.setPrezzo(result.getString("prezzo")+"€");
			p.setImg(result.getString("img"));
			p.setEta(result.getString("eta"));
			p.setNovita(result.getString("novita"));
			prodotti.add(p);
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return prodotti;
	}
	public synchronized ArrayList<ProductBean> doRetriveByOrder() throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		ArrayList<ProductBean> prodotti = new ArrayList<ProductBean>();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement(
				"SELECT codProdotto, count(*) FROM giocatoys.componiordine group by codProdotto order by count(*) desc limit 3");
		ResultSet result = query.executeQuery();
		while(result.next()) {
			ProductBean p = doRetriveByKey(result.getString("codProdotto"));
			prodotti.add(p);
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return prodotti;
	}
	public synchronized ArrayList<ProductBean> doRetriveAll() throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		ArrayList<ProductBean> prodotti = new ArrayList<ProductBean>();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM giocatoys.prodotto");
		ResultSet result = query.executeQuery();
		while(result.next()) {
			ProductBean p = new ProductBean();
			p.setCodProdotto(result.getString("codProdotto"));
			p.setCategoria(result.getString("categoria"));
			p.setNome(result.getString("nome"));
			p.setDescrizione(result.getString("descrizione"));
			p.setPrezzo(result.getString("prezzo")+"€");
			p.setImg(result.getString("img"));
			p.setEta(result.getString("eta"));
			p.setNovita(result.getString("novita"));
			prodotti.add(p);
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return prodotti;
	}
	
	public void doDelete(String cod) throws Exception {
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		 PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("DELETE FROM giocatoys.prodotto WHERE codProdotto=?");
		query.setString(1, cod);
		query.executeUpdate();
		DriverManagerConnectionPool.releaseConnection(con);
	}
}
