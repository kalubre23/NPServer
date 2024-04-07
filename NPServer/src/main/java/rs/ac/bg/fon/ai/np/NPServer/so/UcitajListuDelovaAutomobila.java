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
 * Predstavlja konkretnu sistemsku operaciju koja vraca sve delove automobila iz baze. Nasledjuje 
 * opstu sistemsku operaciju.
 *
 * @see AbstractSO
 * @author Luka Obrenic
 * @since 1.0.0
 */
public class UcitajListuDelovaAutomobila extends AbstractSO {
	/**
	 * Lista svih delova automobila.
	 * @see DeoAutomobila
	 */
    List<DeoAutomobila> deloviAutomobila;

    /**
     * Vraca listu svih delova automobila.
     * @return deloviAutomobila vraceni iz baze, kao lista objekata domenske klase Automobil.
     */
    public List<DeoAutomobila> getDeloviAutomobila() {
        return deloviAutomobila;
    }
    
    /**
	 * Neparametrizovani konstruktor koji inicijalizuje listu svih delova automobila.
	 * @throws Exception ako dodje do greske
	 */
    public UcitajListuDelovaAutomobila() throws Exception {
        deloviAutomobila = new ArrayList<>();
    }
    
    /**
     * Konkretna implementacija sistemske operacije vracanja svih delova automobila iz baze.
     * 
     * Poziva se metoda za vracanje svih objekata domenske klase u database brokeru.
     */
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
