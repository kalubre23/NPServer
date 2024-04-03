/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.so;


import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.fon.ai.np.NPCommon.domain.DeoAutomobila;
import rs.ac.bg.fon.ai.np.NPCommon.domain.DomenskiObjekat;

/**
 *
 * @author Asus
 */
public class UcitajListuDelovaAutomobila extends AbstractSO {
    
    List<DeoAutomobila> deloviAutomobila;

    public List<DeoAutomobila> getDeloviAutomobila() {
        return deloviAutomobila;
    }
    
    public UcitajListuDelovaAutomobila() throws Exception {
        deloviAutomobila = new ArrayList<>();
    }
    
    @SuppressWarnings("unchecked")
	@Override
    protected void executeOperation(DomenskiObjekat object) throws Exception {
         deloviAutomobila = (List<DeoAutomobila>) (Object) databaseBroker.vratiSve(object);
    }

    @Override
    protected void validate(DomenskiObjekat object) throws Exception {
        //
    }

}
