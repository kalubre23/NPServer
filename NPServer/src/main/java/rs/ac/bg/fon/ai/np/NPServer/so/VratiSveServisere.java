/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.so;

import java.util.List;
import rs.ac.bg.fon.ai.np.NPCommon.domain.DomenskiObjekat;
import rs.ac.bg.fon.ai.np.NPCommon.domain.Korisnik;

/**
 *
 * @author Asus
 */
public class VratiSveServisere extends AbstractSO{
    
    List<Korisnik> listaServisera;

    public List<Korisnik> getListaServisera() {
        return listaServisera;
    }

    public VratiSveServisere() throws Exception {
        super();
    }
    
    

    @Override
    protected void executeOperation(DomenskiObjekat object) throws Exception {
        listaServisera = (List<Korisnik>)(Object) databaseBroker.vratiViseSaUslovom(object);
        if(listaServisera == null || listaServisera.isEmpty()){
            System.out.println("LISTA SERVISERA JE PRAZNA U SO!");
        }
    }

    @Override
    protected void validate(DomenskiObjekat object) throws Exception {
    }
    
}
