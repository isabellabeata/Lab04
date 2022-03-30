package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	public Studente getStudenteByMatricola(int matricola) {
		String sql = "SELECT s.matricola, s.nome, s.cognome, s.CDS "
				+ "FROM studente s "
				+ "WHERE s.matricola=?";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
			
			Studente studente=null;
			while (rs.next()) {
				
				studente= new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS"));
			}

			conn.close();
			
			return studente;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	}
	
