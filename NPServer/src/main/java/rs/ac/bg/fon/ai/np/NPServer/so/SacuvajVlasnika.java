/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.so;

import rs.ac.bg.fon.ai.np.NPCommon.domain.DomenskiObjekat;
import rs.ac.bg.fon.ai.np.NPCommon.domain.Vlasnik;

/**
 *
 * @author Asus
 */
public class SacuvajVlasnika extends AbstractSO{

    public SacuvajVlasnika() throws Exception {
        super();
    }
    

    @Override
    protected void executeOperation(DomenskiObjekat object) throws Exception {
        databaseBroker.sacuvaj(object);
    }

    @Override
    protected void validate(DomenskiObjekat object) throws Exception {
        if(!(object instanceof Vlasnik))
            throw new Exception("Objekad nije instanca klase Vlasnik!");
    }
    
}
