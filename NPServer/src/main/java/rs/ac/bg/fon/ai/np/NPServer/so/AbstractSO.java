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
 *
 * @author student2
 */
public abstract class AbstractSO {

    protected DatabaseBroker databaseBroker;

    public AbstractSO() throws Exception {
        databaseBroker = new DatabaseBroker(DatabaseConnection.getInstance().pop());
    }

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

    private void commit() throws SQLException {
        databaseBroker.getConnection().commit();
    }

    private void rollback() throws SQLException {
        databaseBroker.getConnection().rollback();
    }

    protected abstract void executeOperation(DomenskiObjekat object) throws Exception;

    protected abstract void validate(DomenskiObjekat object) throws Exception;
}
