/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.so;


import java.util.List;

import rs.ac.bg.fon.ai.np.NPCommon.domain.Automobil;
import rs.ac.bg.fon.ai.np.NPCommon.domain.DomenskiObjekat;

/**
 * Predstavlja konkretnu sistemsku operaciju za pretrazivanje automobila u bazi. Nasledjuje 
 * opstu sistemsku operaciju.
 *
 * @see AbstractSO
 * @author Luka Obrenic
 * @since 1.0.0
 */
public class PronadjiAutomobile extends AbstractSO {

	/**
	 * Lista automobila koji zadovoljavaju kriterijum pretrage.
	 * @see Automobil
	 */
    List<Automobil> automobili;

    /**
     * Vraca listu automobila koji zadovoljavaju kriterijum pretrage.
     * @return automobili koji zadovoljavaju kriterijum pretrage, kao lista objekata domenske klase Automobil.
     */
    public List<Automobil> getAutomobili() {
        return automobili;
    }
    

    /**
	 * Neparametrizovani konstruktor koji poziva konstruktor opste sistemske operacije koja je nasledjena.
	 * @throws Exception ako dodje do greske pri izvrsavanju konstruktora nadklase
	 */
    public PronadjiAutomobile() throws Exception {
        super();
    }

    /**
     * Konkretna implementacija sistemske operacije pretrazivanja automobila iz baze.
     * 
     * Poziva se metoda za vracanje vise objekata u database brokeru i 
     * prosledjuje joj se automobil koji sluzi kao kriterijum pretrage.
     */
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

    /**
     * Vrsi validaciju automobila.
     * 
     * Objekat mora biti instanca klase Automobil.
     */
    @Override
    protected void validate(DomenskiObjekat object) throws Exception {
        if(!(object instanceof Automobil))
            throw new Exception("Objekat nije instanca klase Automobil!");
    }

   

}
