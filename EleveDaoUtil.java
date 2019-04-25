package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EleveDaoUtil {

	private final static String QUERY_SAVE_ELEVES = "INSERT INTO eleve (nom, note) VALUES (?, ?);";

	public static void insertEleve(EleveBean eleve) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			// Pour travailler avec Tomcat
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection(ConnexionJDBC.URL, ConnexionJDBC.LOGIN, ConnexionJDBC.PASSWORD); // La
																												// connexion
			stmt = con.prepareStatement(QUERY_SAVE_ELEVES);
			// Remplir la requête
			stmt.setString(1, eleve.getNom());
			stmt.setInt(2, eleve.getNote());
			// Lancer la requête
			stmt.executeUpdate();
		} finally {
			// On ferme la connexion
			if (con != null) {
				try {
					con.close();
				} catch (final SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private final static String QUERY_FIND_ELEVES = "SELECT * FROM eleve ";

	public static ArrayList<EleveBean> getAllEleve() throws Exception {
		Connection con = null;
		Statement stmt = null;
		try {
			// Pour travailler avec Tomcat et wamp Rajouter :
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection(ConnexionJDBC.URL, ConnexionJDBC.LOGIN, ConnexionJDBC.PASSWORD); // La
			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery(QUERY_FIND_ELEVES);
			rset.last();
			ArrayList<EleveBean> resultat = new ArrayList<>();

			while (rset.next()) {
				EleveBean eleve = rsetToEleve(rset);
				resultat.add(eleve);
			}

			return resultat;
		} finally {
			if (con != null) {// On ferme la connexion
				try {
					con.close();
				} catch (final SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static EleveBean[] getEleve(Integer note) throws Exception {
		// Construire la requete
		String requete = QUERY_FIND_ELEVES;
		if (note != null) {
			requete += " WHERE note > " + note;
		}

		Connection con = null;
		Statement stmt = null;
		try {
			// Pour travailler avec Tomcat et wamp Rajouter :
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection(ConnexionJDBC.URL, ConnexionJDBC.LOGIN, ConnexionJDBC.PASSWORD); // La
			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery(requete);
			rset.last();
			EleveBean[] resultat = new EleveBean[rset.getRow()];
			rset.beforeFirst();
			int i = 0;
			while (rset.next()) {
				EleveBean eleve = rsetToEleve(rset);
				resultat[i] = eleve;
				i++;
			}

			return resultat;
		} finally {
			if (con != null) {// On ferme la connexion
				try {
					con.close();
				} catch (final SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static EleveBean rsetToEleve(ResultSet rset) throws SQLException {
		final EleveBean eleve = new EleveBean();

		eleve.setId(rset.getInt("id"));
		eleve.setNom(rset.getString("nom"));
		eleve.setNote(rset.getInt("note"));

		return eleve;
	}

}
