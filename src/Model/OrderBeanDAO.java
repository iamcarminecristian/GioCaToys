package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

public class OrderBeanDAO {
	public synchronized void doSave(OrderBean o) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		PreparedStatement query2=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
				"SELECT max(idOrdine) as idOrdine FROM giocatoys.ordine");
		ResultSet result = query2.executeQuery();
		if(!result.next()) {
			throw new Exception();
		}
		int idO;
		if(result.getString("idOrdine")!=null) {
			String id = result.getString("idOrdine");
			idO= Integer.parseInt(id);
			idO+=1;
		}
		else {
			idO = 100;
		}
		o.setIdOrdine(idO+"");
		query2.close();
		PreparedStatement query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
			"insert into giocatoys.ordine (dataOrdine, statoOrdine, idCarrello , username, totale) values (?, ?, ? ,?, ?)");
		query.setString(1, o.getDate());
		query.setString(2, o.getState());
		query.setString(3, o.getIdCarello());
		query.setString(4, o.getUser());
		query.setString(5, o.getTotale());
		query.executeUpdate();	
		query.close();
		int i = 0;
		while(i<o.getProdotti().size()) {
			PreparedStatement query3=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
					"insert into giocatoys.componiordine (idOrdine, codProdotto) values (?, ?)");
			query3.setString(1, o.getIdOrdine());
			query3.setString(2, o.getProdotti().get(i));
			query3.executeUpdate();
			query3.close();
			i++;
		}
		DriverManagerConnectionPool.releaseConnection(con);
	}
	public synchronized void doSaveOrUpdate(OrderBean o) throws Exception{
		if(doRetriveByKey(o.getIdOrdine())==null) {
			doSave(o);
		}
		else {
			java.sql.Connection con = DriverManagerConnectionPool.getConnection();
			PreparedStatement query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
					"update giocatoys.ordine set dataOrdine = ?, statoOrdine = ?,  idCarrello = ?, username = ?, totale = ? where idOrdine= ? ");
			query.setString(1, o.getDate());
			query.setString(2, o.getState());
			query.setString(3, o.getIdCarello());
			query.setString(4, o.getUser());
			query.setString(5, o.getTotale());
			query.setString(6, o.getIdOrdine());
			query.executeUpdate();
			DriverManagerConnectionPool.releaseConnection(con);
		}
	}
	public synchronized ArrayList<OrderBean> doRetriveAll() throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		ArrayList<OrderBean> ordini = new ArrayList<OrderBean>();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM giocatoys.ordine");
		ResultSet result = query.executeQuery();
		while(result.next()) {
			OrderBean o= new OrderBean();
			o.setDate(result.getString("dataOrdine"));
			o.setState(result.getString("statoOrdine"));
			o.setTotale(result.getString("totale"));
			o.setUser(result.getString("username"));
			o.setIdCarello(result.getString("idCarrello"));
			o.setIdOrdine(result.getString("idOrdine"));
			ArrayList<String> prod = new ArrayList<String>();
			PreparedStatement query2 = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM giocatoys.componiordine WHERE idOrdine=?");
			query2.setString(1, o.getIdOrdine());
			ResultSet result2 = query2.executeQuery();
			while(result2.next()) {
				prod.add(result2.getString("codProdotto"));
			}
			o.setProdotti(prod);
			ordini.add(o);
			query2.close();
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return ordini;	
	}
	public synchronized OrderBean doRetriveByKey(String id) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		OrderBean o = new OrderBean();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM giocatoys.ordine where idOrdine= ?");
		o.setIdOrdine(id);
		query.setString(1, o.getIdOrdine());
		ResultSet result = query.executeQuery();
		if(!result.next()) {
			throw new Exception();
		}else {
			o.setIdCarello(result.getString("idCarrello"));
			o.setDate(result.getString("dataOrdine"));
			o.setState(result.getString("statoOrdine"));
			o.setTotale(result.getString("totale"));
			o.setUser(result.getString("username"));
			ArrayList<String> prod = new ArrayList<String>();
			PreparedStatement query2 = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM giocatoys.componiordine WHERE idOrdine=?");
			query2.setString(1, o.getIdOrdine());
			ResultSet result2 = query2.executeQuery();
			while(result2.next()) {
				prod.add(result2.getString("codProdotto"));
			}
			o.setProdotti(prod);
			query2.close();
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return o;	
	}
	
	public synchronized ArrayList<OrderBean> doRetriveByCond(String user) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		ArrayList<OrderBean> ordini = new ArrayList<OrderBean>();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM giocatoys.ordine where username= ?");
		query.setString(1, user);
		ResultSet result = query.executeQuery();
		while(result.next()) {
			OrderBean o= new OrderBean();
			o.setDate(result.getString("dataOrdine"));
			o.setState(result.getString("statoOrdine"));
			o.setTotale(result.getString("totale"));
			o.setUser(result.getString("username"));
			o.setIdCarello(result.getString("idCarrello"));
			o.setIdOrdine(result.getString("idOrdine"));
			ArrayList<String> prod = new ArrayList<String>();
			PreparedStatement query2 = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM giocatoys.componiordine WHERE idOrdine=?");
			query2.setString(1, o.getIdOrdine());
			ResultSet result2 = query2.executeQuery();
			while(result2.next()) {
				prod.add(result2.getString("codProdotto"));
			}
			o.setProdotti(prod);
			ordini.add(o);
			query2.close();
		}
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return ordini;	
	}

	public void doDelete(String id) throws Exception {
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("DELETE FROM giocatoys.ordine WHERE idOrdine=?");
		query.setString(1, id);
		query.executeUpdate();
		DriverManagerConnectionPool.releaseConnection(con);
	}
}
