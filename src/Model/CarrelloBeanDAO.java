package Model;

import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

public class CarrelloBeanDAO {
	
	public synchronized void doCreate(String user) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		PreparedStatement query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
				"insert into giocatoys.carrello (username) values (?)");
		query.setString(1, user);
		query.executeUpdate();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	public synchronized void doSave(String codC,String codP) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		PreparedStatement query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
			"insert into giocatoys.componicarrello (idCarrello , codProdotto) values (?, ?)");
		query.setString(1, codC);
		query.setString(2, codP);
		query.executeUpdate();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	public synchronized CarrelloBean doRetriveByKey(String user) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		CarrelloBean c = new CarrelloBean();
		c.setUser(user);
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM giocatoys.carrello where username=?");
		query.setString(1, c.getUser());
		ResultSet result = query.executeQuery();
		if(!result.next()) {
			throw new Exception();
		}
		c.setCodCarrello(result.getString("idCarrello"));
		query.close();
		PreparedStatement query2 = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM giocatoys.componicarrello where idCarrello=?");
		query2.setString(1, c.getCodCarrello());
		ResultSet result2 = query2.executeQuery();
		while(result2.next()) {
			c.getProdotti().add(result2.getString("codProdotto"));
		}
		query2.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return c;
	}
	public void doDeleteByKey(String codProd,String idCarrello) throws Exception {
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("DELETE FROM giocatoys.componicarrello WHERE idCarrello=? AND codProdotto=? LIMIT 1");
		query.setString(1, idCarrello);
		query.setString(2, codProd);
		query.executeUpdate();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	public void doDelete(String idCarrello) throws Exception {
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("DELETE FROM giocatoys.componicarrello WHERE idCarrello=?");
		query.setString(1, idCarrello);
		query.executeUpdate();
		DriverManagerConnectionPool.releaseConnection(con);
	}
}
