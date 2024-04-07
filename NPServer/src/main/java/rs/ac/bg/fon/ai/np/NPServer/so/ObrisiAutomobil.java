/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.so;

import rs.ac.bg.fon.ai.np.NPCommon.domain.Automobil;
import rs.ac.bg.fon.ai.np.NPCommon.domain.DomenskiObjekat;

/**
 * Predstavlja konkretnu sistemsku operaciju za brisanje automobila iz baze. Nasledjuje 
 * opstu sistemsku oepraciju.
 *
 * @see AbstractSO
 * @author Luka Obrenic
 * @since 1.0.0
 */
public class ObrisiAutomobil extends AbstractSO {

	/**
	 * Neparametrizovani konstruktor koji poziva konstruktor opste sistemske operacije koja je nasledjena.
	 * @throws Exception ako dodje do greske pri izvrsavanju konstruktora nadklase
	 */
    public ObrisiAutomobil() throws Exception {
        super();
    }

    /**
     * Konkretna implementacija sistemske operacije brisanja automobila iz baze.
     * 
     * Poziva se metoda za brisanje u database brokeru i prosledjuje joj se automobil.
     */
    @Override
    protected void executeOperation(DomenskiObjekat object) throws Exception {
        databaseBroker.obrisi(object);
    }

    /**
     * Vrsi validaciju automobila.
     * 
     * Objekat mora biti instanca klase Automobil.
     */
    @Override
    protected void validate(DomenskiObjekat object) throws Exception {
        if(!(object instanceof Automobil))
            throw new Exception("Objekat nije instanca klase Automobil");
    }

}
