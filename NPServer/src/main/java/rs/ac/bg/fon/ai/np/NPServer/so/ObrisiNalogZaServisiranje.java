/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.so;


import javax.swing.table.AbstractTableModel;

import rs.ac.bg.fon.ai.np.NPCommon.domain.DomenskiObjekat;
import rs.ac.bg.fon.ai.np.NPCommon.domain.NalogZaServisiranje;

/**
 *
 * @author Asus
 */
public class ObrisiNalogZaServisiranje extends AbstractSO {

    public ObrisiNalogZaServisiranje() throws Exception {
        super();
    }
    
    @Override
    protected void executeOperation(DomenskiObjekat object) throws Exception {
        databaseBroker.obrisi(object);
    }

    @Override
    protected void validate(DomenskiObjekat object) throws Exception {
        if (!(object instanceof NalogZaServisiranje)) {
            throw new Exception("Objekat nije instanca klase NalogZaServisiranje!");
        }
    }
    
}
