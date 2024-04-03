/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.so;

import rs.ac.bg.fon.ai.np.NPCommon.domain.DomenskiObjekat;
import rs.ac.bg.fon.ai.np.NPCommon.domain.PokvareniDeo;

/**
 *
 * @author Asus
 */
public class IzmeniPokvarenDeo extends AbstractSO {

    public IzmeniPokvarenDeo() throws Exception {
        super();
    }
    
    @Override
    protected void executeOperation(DomenskiObjekat object) throws Exception {
        databaseBroker.izmeni(object);
    }

    @Override
    protected void validate(DomenskiObjekat object) throws Exception {
        if (!(object instanceof PokvareniDeo)) {
            throw new Exception("Objekat nije instanca klase PokvareniDeo!");
        }
    }
    
}
