/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.so;


import java.util.List;

import rs.ac.bg.fon.ai.np.NPCommon.domain.DomenskiObjekat;
import rs.ac.bg.fon.ai.np.NPCommon.domain.PokvareniDeo;

/**
 *
 * @author Asus
 */
public class PronadjiPokvareneDelove extends AbstractSO {
    
    List<PokvareniDeo> listaPokvarenihDelova;

    public List<PokvareniDeo> getListaPokvarenihDelova() {
        return listaPokvarenihDelova;
    }
    
    public PronadjiPokvareneDelove() throws Exception {
        super();
    }
    

    @Override
    protected void executeOperation(DomenskiObjekat object) throws Exception {
        listaPokvarenihDelova = (List<PokvareniDeo>)(Object) databaseBroker.vratiViseSaUslovom(object);
        if(listaPokvarenihDelova == null || listaPokvarenihDelova.isEmpty()){
            System.out.println("LISTA JE PRAZNA U SO!");
        }
    }

    @Override
    protected void validate(DomenskiObjekat object) throws Exception {
        if(!(object instanceof PokvareniDeo))
            throw new Exception("Objekat nije instanca klase PokvareniDeo!");
    }
    
}
