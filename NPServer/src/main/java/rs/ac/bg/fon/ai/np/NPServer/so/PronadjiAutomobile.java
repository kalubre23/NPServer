/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.so;


import java.util.List;

import rs.ac.bg.fon.ai.np.NPCommon.domain.Automobil;
import rs.ac.bg.fon.ai.np.NPCommon.domain.DomenskiObjekat;

/**
 *
 * @author Asus
 */
public class PronadjiAutomobile extends AbstractSO {

    List<Automobil> automobili;

    public List<Automobil> getAutomobili() {
        return automobili;
    }
    

    public PronadjiAutomobile() throws Exception {
        super();
    }

    @SuppressWarnings("unchecked")
	@Override
    protected void executeOperation(DomenskiObjekat object) throws Exception {
        automobili = (List<Automobil>)(Object)databaseBroker.vratiViseSlozenihSaUslovom(object);
        
        System.out.println("Vraceni automobili:");
        if(automobili.isEmpty())
            System.out.println("Lista je prazna :(");
        for(Automobil automobil: automobili){
            System.out.println(automobil);
        }
        System.out.println();
    }

    @Override
    protected void validate(DomenskiObjekat object) throws Exception {
        if(!(object instanceof Automobil))
            throw new Exception("Objekat nije instanca klase Automobil!");
    }

   

}
