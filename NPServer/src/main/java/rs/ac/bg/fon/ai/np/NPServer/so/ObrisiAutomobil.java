/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.so;

import rs.ac.bg.fon.ai.np.NPCommon.domain.Automobil;
import rs.ac.bg.fon.ai.np.NPCommon.domain.DomenskiObjekat;

/**
 *
 * @author Asus
 */
public class ObrisiAutomobil extends AbstractSO {

    public ObrisiAutomobil() throws Exception {
        super();
    }

    @Override
    protected void executeOperation(DomenskiObjekat object) throws Exception {
        databaseBroker.obrisi(object);
        //valjda je ovo dovoljno jebemliga
    }

    @Override
    protected void validate(DomenskiObjekat object) throws Exception {
        if(!(object instanceof Automobil))
            throw new Exception("Objekat nije instanca klase Automobil");
        //ovde ne moram da priveravam listu kvarova 
        //jer se nadam da ce baza automatski da obrise kvarove
    }

}
