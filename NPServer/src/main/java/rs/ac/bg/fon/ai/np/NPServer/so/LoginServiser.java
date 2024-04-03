/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.so;

import rs.ac.bg.fon.ai.np.NPCommon.domain.DomenskiObjekat;
import rs.ac.bg.fon.ai.np.NPCommon.domain.Serviser;

/**
 *
 * @author student2
 */
public class LoginServiser extends AbstractSO {

    private Serviser serviser;

    public LoginServiser() throws Exception {
        super();
    }

    public Serviser getServiser() {
        return serviser;
    }

    @Override
    protected void executeOperation(DomenskiObjekat object) throws Exception {
        serviser = (Serviser) databaseBroker.vratiJednog(object);
        System.out.println("u LoginServiser koja poziva dbbr je serviser: "+serviser);
        if (serviser == null) {
            throw new Exception("Korisnik ne postoji.");
        }
    }

    @Override
    protected void validate(DomenskiObjekat object) throws Exception {
        if (!(object instanceof Serviser)) {
            throw new Exception("Object is not valid");
        }
    }

}
