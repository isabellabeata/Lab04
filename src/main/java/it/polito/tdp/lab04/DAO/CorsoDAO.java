package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;


public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				
				corsi.add(new Corso(codins, numeroCrediti, nome, periodoDidattico));
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(String codins) {
		

		String sql = "SELECT * "
				+ "FROM corso c "
				+ "WHERE c.codins=?";

		Corso c=null ;

			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				st.setString(1, codins);
				ResultSet rs = st.executeQuery();

				while (rs.next()) {

					
					int numeroCrediti = rs.getInt("crediti");
					String nome = rs.getString("nome");
					int periodoDidattico = rs.getInt("pd");
					
					c=new Corso(codins, numeroCrediti, nome, periodoDidattico);
				}

				conn.close();
				
				return c;
				

			} catch (SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException("Errore Db", e);
			}
		
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		

		String sql = "SELECT s.matricola, s.nome, s.cognome, s.CDS "
				+ "FROM studente s, iscrizione i "
				+ "WHERE i.matricola=s.matricola AND i.codins=? "
				+ "GROUP BY s.matricola, s.nome, s.cognome, s.CDS";

		List<Studente> studenti = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			String s= corso.getCodins();
			st.setString(1, s);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				studenti.add(new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS")));
			}

			conn.close();
			
			return studenti;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
		


	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean iscriviStudenteACorso(Studente studente, Corso corso) {
		try {
			Connection conn= ConnectDB.getConnection();

			String sql= "INSERT INTO iscrizione (matricola, codins) VALUES (?, ?)";
			PreparedStatement st= conn.prepareStatement(sql);

			st.setInt(1, studente.getMatricola());
			st.setString(2, corso.getCodins());

			st.executeUpdate();
			st.close();

			conn.close();
			return true;
		} catch (SQLException e) {
			System.out.print(e);
			e.printStackTrace();
			return false;
		}
		
	}


	
	

}
