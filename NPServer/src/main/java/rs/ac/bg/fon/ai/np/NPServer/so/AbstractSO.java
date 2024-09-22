/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.so;

import java.sql.SQLException;

import rs.ac.bg.fon.ai.np.NPCommon.domain.DomenskiObjekat;
import rs.ac.bg.fon.ai.np.NPServer.db.DatabaseBroker;
import rs.ac.bg.fon.ai.np.NPServer.db.DatabaseConnection;

/**
 * Predstavlja opstu sistemsku operaciju. Implementira template method patern.
 * 
 * Svaka sistemska operacija nasledjuje ovu klasu.
 * Specificira kako se sistemska operacija izvrsava na opstem nivou pomocu execute metode. Na nivou konkretne 
 * sistemske operacije se izvrsava executeOperation i validate metoda.
 * 
 * @author Luka Obrenic
 * @since 1.0.0
 */
public abstract class AbstractSO {

	/**
	 * Referenca ka database brokeru tipa DatabaseBroker.
	 * @see DatabaseBroker
	 *
	 */
    protected DatabaseBroker databaseBroker;

    /**
     * Neparametrizovani konstruktor koji inicijalizuje database broker-a.
     * 
     * @see DatabaseBroker
     * @see DatabaseConnection
     * @throws Exception ako dodje do greske pri inicijalizaciji
     */
    public AbstractSO() throws Exception {
        databaseBroker = new DatabaseBroker(DatabaseConnection.getInstance().pop());
    }

    /**
     * Opsta metoda izvrsavanja zajednicka za sve sistemske operacije.
     * 
     * Na opstem nivou vrsi se validacija domenskog objekta nad kojim treba izvrsiti sistemsku operaciju, 
     * izvrsava se konkretna sistemska operacija i vrsi commit u bazi ili rollback ukoliko dodje do greske.
     * 
     * @param object domenski objekat nad kojim se prvo vrsi validacija pa izvrsava sistemska operacija, tipa DomenskiObjekat
     * @throws Exception ako dodje do greske prilikom validacije, izvrsavanja operacije, commit-a ili rollback-a
     */
    public void execute(DomenskiObjekat object) throws Exception {
        try {
            validate(object);
            executeOperation(object);
            commit();
        } catch (Exception ex) {
            rollback();
            throw ex;
        } finally {
            //valjda kad sam zavrsio sistemsku op treba da vratim konekciju
            DatabaseConnection.getInstance().push(databaseBroker.getConnection());
        }
    }

    /**
     * Operacija commit baze podataka koja se vrsi na opstem nivou za sve sistemske operacije, 
     * nakon uspesno izvrsenja.
     * 
     * @throws SQLException ako dodje do greske prilikom commitovanja
     */
    private void commit() throws SQLException {
        databaseBroker.getConnection().commit();
    }

    /**
     * Operacija rollback baze podataka koja se vrsi na opstem nivou za sve sistemske operacija,
     * ukoliko dodje do greske prilikom opsteg izvrsavanja sistemske operacije.
     * @throws SQLException
     */
    private void rollback() throws SQLException {
        databaseBroker.getConnection().rollback();
    }

    /**
     * Apsraktna metoda za izvrsavanje konkretne sistemske operacije.
     * Implementira je konkretna sistemska operacija.
     * @param object domenski objekat nad kojim treba izvrsiti sistemsku operaciju, tipa DomenskiObjekat
     * @throws Exception ako dodje do greske pri izvrsavanju sistemske operacije
     */
    protected abstract void executeOperation(DomenskiObjekat object) throws Exception;

    /**
     * Apsraktna metoda za validaciju konkretnog domenskog objekta.
     * Implementira je konkretna sistemska operacija.
     * @param object domenski objekat nad kojim treba izvrsiti validaciju, tipa DomenskiObjekat
     * @throws Exception ako dodje do greske pri validaciji
     */
    protected abstract void validate(DomenskiObjekat object) throws Exception;
}
