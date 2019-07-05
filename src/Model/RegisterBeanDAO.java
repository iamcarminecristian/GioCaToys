package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

public class RegisterBeanDAO {
	public synchronized void doSave(RegisterBean r) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		/*TABELLA CLIENTE*/
		PreparedStatement query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
			"insert into giocatoys.cliente (username, password, nome, cognome, email, dataNascita) values (?, ?, ?, ?, ?, ?)");
		query.setString(1, r.getUsername());
		query.setString(2, r.getPassword());
		query.setString(3, r.getNome());
		query.setString(4, r.getCognome());
		query.setString(5, r.getMail());
		query.setString(6, r.getBday());
		query.executeUpdate();
		/*TABELLA INDIRIZZO*/
		PreparedStatement query2 = (PreparedStatement) ((java.sql.Connection) con).prepareStatement(
				"insert into giocatoys.indirizzo (nazione, citta, provincia, via, civico, cap, username) values (?, ?, ?, ?, ?, ?, ?)");
		query2.setString(1, r.getNazionalita());
		query2.setString(2, r.getCitta());
		query2.setString(3, r.getProvincia());
		query2.setString(4, r.getVia());
		query2.setString(5, r.getCivico());
		query2.setString(6, r.getCap());
		query2.setString(7, r.getUsername());
		query2.executeUpdate();
		DriverManagerConnectionPool.releaseConnection(con);
	}
	public synchronized void doSaveOrUpdate(RegisterBean r) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		RegisterBean s = doRetriveByUser(r.getUsername());
		if(s==null) {
			doSave(r);
		}
		else {
			PreparedStatement query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
					"update giocatoys.cliente set password=? , nome=?,cognome=? , email=? , dataNascita=? where username =?");	
			query.setString(1, r.getPassword());
			query.setString(2, r.getNome());
			query.setString(3, r.getCognome());	
			query.setString(4, r.getMail());
			query.setString(5, r.getBday());
			query.setString(6, r.getUsername());
			query.executeUpdate();
			PreparedStatement query2 = (PreparedStatement) ((java.sql.Connection) con).prepareStatement(
					"update giocatoys.indirizzo set nazione=?, citta=?, provincia=?, via=?, civico=?, cap=? where idIndirizzo=?");
			query2.setString(1, r.getNazionalita());
			query2.setString(2, r.getCitta());
			query2.setString(3, r.getProvincia());
			query2.setString(4, r.getVia());
			query2.setString(5, r.getCivico());
			query2.setString(6, r.getCap());
			query2.setString(7, r.getIdIndirizzo());
			query2.executeUpdate();
			query.close();
			query2.close();
		}
		DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public synchronized RegisterBean doRetriveByKey(String username,String password) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		RegisterBean r = new RegisterBean();
		r.setUsername(username);
		r.setPassword(password);
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM giocatoys.cliente join giocatoys.indirizzo on giocatoys.cliente.username=giocatoys.indirizzo.username where giocatoys.cliente.username=? AND password=?");
		query.setString(1, r.getUsername());
		query.setString(2, r.getPassword());
		ResultSet result = query.executeQuery();
		if(!result.next()) {
			throw new Exception();
		}
		r.setNome(result.getString("nome"));
		r.setCognome(result.getString("cognome"));
		r.setMail(result.getString("email"));
		r.setIdIndirizzo(result.getString("idIndirizzo"));
		r.setNazionalita(result.getString("nazione"));
		r.setCitta(result.getString("citta"));
		r.setProvincia(result.getString("provincia"));
		r.setVia(result.getString("via"));
		r.setCivico(result.getString("civico"));
		r.setCap(result.getString("cap"));
		r.setBday(result.getString("dataNascita"));
		r.setAdmin(result.getString("admin"));
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return r;	
	}
	public synchronized RegisterBean doRetriveByUser(String username) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		RegisterBean r = new RegisterBean();
		r.setUsername(username);
		PreparedStatement query = (PreparedStatement) ((java.sql.Connection) con).prepareStatement("SELECT * FROM giocatoys.cliente join giocatoys.indirizzo on giocatoys.cliente.username=giocatoys.indirizzo.username where giocatoys.cliente.username=?");
		query.setString(1, r.getUsername());
		ResultSet result = query.executeQuery();
		if(!result.next()) {
			throw new Exception();
		}
		r.setNome(result.getString("nome"));
		r.setCognome(result.getString("cognome"));
		r.setMail(result.getString("email"));
		r.setIdIndirizzo(result.getString("idIndirizzo"));
		r.setNazionalita(result.getString("nazione"));
		r.setCitta(result.getString("citta"));
		r.setProvincia(result.getString("provincia"));
		r.setVia(result.getString("via"));
		r.setCivico(result.getString("civico"));
		r.setCap(result.getString("cap"));
		r.setBday(result.getString("dataNascita"));
		r.setAdmin(result.getString("admin"));
		query.close();
		DriverManagerConnectionPool.releaseConnection(con);
		return r;	
	}
	public synchronized void doDelete(RegisterBean r) throws Exception{
		java.sql.Connection con = DriverManagerConnectionPool.getConnection();
		RegisterBean s = doRetriveByKey(r.getUsername(), r.getPassword());
		if(s!=null) {
			PreparedStatement query=(PreparedStatement) ((java.sql.Connection) con).prepareStatement(
					"delete from testform.users where username =?");	
			query.setString(1, r.getUsername());
			query.executeUpdate();
		}
		DriverManagerConnectionPool.releaseConnection(con);
	}
}
