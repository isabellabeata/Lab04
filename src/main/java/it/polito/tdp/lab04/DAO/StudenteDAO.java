package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
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
	
	public List<Corso> getCorsiStudente (int matricola){
		String sql = "SELECT c.codins, c.crediti, c.nome, c.pd "
				+ "FROM corso c, studente s, iscrizione i "
				+ "WHERE c.codins=i.codins AND i.matricola=s.matricola AND i.matricola=?";
		
		List<Corso> list=new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				
				list.add(new Corso(codins, numeroCrediti, nome, periodoDidattico));
			}

			conn.close();
			
			return list;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	}
	
