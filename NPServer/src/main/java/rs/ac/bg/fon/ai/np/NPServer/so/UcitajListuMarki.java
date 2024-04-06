/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.so;


import java.util.List;

import rs.ac.bg.fon.ai.np.NPCommon.domain.DomenskiObjekat;
import rs.ac.bg.fon.ai.np.NPCommon.domain.Marka;

/**
 *
 * @author Asus
 */
public class UcitajListuMarki extends AbstractSO {

    List<Marka> listaMarki;

    public List<Marka> getListaMarki() {
        return listaMarki;
    }

    public UcitajListuMarki() throws Exception {
        super();
    }

    @SuppressWarnings("unchecked")
	@Override
    protected void executeOperation(DomenskiObjekat object) throws Exception {
        listaMarki = (List<Marka>) (Object) databaseBroker.vratiSve(object);
    }

    @Override
    protected void validate(DomenskiObjekat object) throws Exception {
        //
    }

}
