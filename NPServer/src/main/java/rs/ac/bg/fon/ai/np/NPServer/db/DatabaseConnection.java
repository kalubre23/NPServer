/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja klasu koja je zaduzena za pravljenje konekcija ka bazi i dodeljivanje tih konekcija
 * DatabaseBroker-u i sistemskim operacijama zbog commit-ovanja i rollback-ovanja.
 * 
 * Inicijalizuje fiksirani broj konekcija koji se implementira kao queue.
 * Konekcija se dodeljuje pomocu pop(), a vraca u queue pomocu push() metode.
 * 
 * @author Luka Obrenic
 * @since 1.0.0
 */
public class DatabaseConnection {

	/**
	 * Lista konekcija kao lista tipa Connection.
	 */
    private List<Connection> connectionPool;
    /**
     * Atribut tipa DatabaseConnection za implementaciju singleton paterna.
     */
    private static DatabaseConnection instance;
    
    /**
     * Neparametrizovani privatni konstruktor za implementaciju singleton paterna.
     * 
     * Inicijalizuje listu konekcija, inicijalizuje 10 konekcija i dodaje ih u tu listu.
     * 
     * @throws SQLException ukoliko dodje do greske pri inicijalizaciji konekcije ka bazi
     */
    private DatabaseConnection() throws SQLException {
        connectionPool = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            try {
                String url = "jdbc:mysql://localhost:3306/seminarskips";
                String user = "root";
                String password = "";
                Connection connection = DriverManager.getConnection(url, user, password);
                System.out.println("Konekcija sa bazom podataka uspesno uspostavljena!");

                //iskljucujemo automatsko potvrdjivanje transakcije nakon svakog upita
                connection.setAutoCommit(false);
                connectionPool.add(connection);
            } catch (SQLException ex) {
                System.out.println("Greska! Konekcija sa bazom nije uspesno uspostavljena!\n" + ex.getMessage());
                ex.printStackTrace();
                throw ex;
            }
        }
    }

    /**
     * Staticka metoda za implementaciju singleton paterna.
     * 
     * Vraca jedinu instancu ove klase. Ukoliko je ona null prvo ce se inicijalizovati.
     * 
     * @return instanca ove klase
     * @throws SQLException izuzetak koji baca ukoliko ga baci poziv konstruktora DatabaseConnection()
     */
    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    /**
     * Implementacija queue metode push.
     * 
     * Dodaje se konekcija na kraj liste.
     * 
     * @param connection konekcija tipa Connection
     */
    public synchronized void push(Connection connection) {
        connectionPool.add(connection);
    }

    /**
     * Implementacija queue metode pop.
     * 
     * Vraca se konekcija sa pocetka liste. Ukoliko je lista prazna baca se izuzetak.
     * 
     * @return connection konekcija tipa Connection
     * @throws Exception ukoliko je lista prazna
     */
    public synchronized Connection pop() throws Exception {
        if (connectionPool.isEmpty()) {
            throw new Exception("Nema slobodne konekcije");
        }
        Connection connection = connectionPool.get(0);
        connectionPool.remove(0);
        return connection;
    }
}
