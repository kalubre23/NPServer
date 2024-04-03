/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.so;


import java.util.List;

import rs.ac.bg.fon.ai.np.NPCommon.domain.Automobil;
import rs.ac.bg.fon.ai.np.NPCommon.domain.DomenskiObjekat;
import rs.ac.bg.fon.ai.np.NPCommon.domain.UoceniKvar;

/**
 *
 * @author Asus
 */
public class IzmeniAutomobil extends AbstractSO {

    public IzmeniAutomobil() throws Exception {
        super();
    }

    @Override
    protected void executeOperation(DomenskiObjekat object) throws Exception {
        //obrisi sve kvarove i sacuvaj novu listu kvarova
        databaseBroker.izmeniSlozeni(object);
        databaseBroker.obrisi(((Automobil)object).getUoceniKvarovi().get(0));
        List<UoceniKvar> listaKvarova = ((Automobil) object).getUoceniKvarovi();
        for (UoceniKvar k : listaKvarova) {
            databaseBroker.sacuvaj(k);
        }
    }

    @Override
    protected void validate(DomenskiObjekat object) throws Exception {
        if (!(object instanceof Automobil)) {
            throw new Exception("Objekat nije instanca klase Automobil!");
        }
        if(((Automobil)object).getUoceniKvarovi().isEmpty()){
            throw new Exception("Lista kvarova je prazna!");
        }
    }

}
