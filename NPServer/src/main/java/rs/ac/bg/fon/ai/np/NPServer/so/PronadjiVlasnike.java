/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.so;

import java.util.List;
import rs.ac.bg.fon.ai.np.NPCommon.domain.DomenskiObjekat;
import rs.ac.bg.fon.ai.np.NPCommon.domain.Vlasnik;

/**
 *
 * @author Asus
 */
public class PronadjiVlasnike extends AbstractSO{
    
    List<Vlasnik> listaVlasnika;

    public List<Vlasnik> getListaVlasnika() {
        return listaVlasnika;
    }
    
    public PronadjiVlasnike() throws Exception {
        super();
    }
    
    @Override
    protected void executeOperation(DomenskiObjekat object) throws Exception {
        listaVlasnika = (List<Vlasnik>) (Object) databaseBroker.vratiViseSaUslovom(object);
    }

    @Override
    protected void validate(DomenskiObjekat object) throws Exception {
        if(!(object instanceof Vlasnik))
            throw new Exception("Objekat nije instanca klase Vlasnik!");
    }
    
}
