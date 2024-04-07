/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.so;

import rs.ac.bg.fon.ai.np.NPCommon.domain.DomenskiObjekat;
import rs.ac.bg.fon.ai.np.NPCommon.domain.NalogZaServisiranje;

/**
 * Predstavlja konkretnu sistemsku operaciju za dodavanje naloga za servisiranje u bazu. Nasledjuje 
 * opstu sistemsku oepraciju.
 *
 * @see AbstractSO
 * @author Luka Obrenic
 * @since 1.0.0
 */
public class SacuvajNalogZaServisiranje extends AbstractSO {

	/**
	 * Neparametrizovani konstruktor koji poziva konstruktor opste sistemske operacije koja je nasledjena.
	 * @throws Exception ako dodje do greske pri izvrsavanju konstruktora nadklase
	 */
    public SacuvajNalogZaServisiranje() throws Exception {
        super();
    }
    

    /**
     * Konkretna implementacija sistemske operacije dodavanja naloga u bazu.
     * 
     * Poziva se metoda za dodavanje u database brokeru i prosledjuje joj se nalog.
     */
    @Override
    protected void executeOperation(DomenskiObjekat object) throws Exception {
        databaseBroker.sacuvaj(object);
    }

    /**
     * Vrsi validaciju naloga za servisiranje.
     * 
     * Objekat mora biti instanca klase NalogZaServisiranje.
     */
    @Override
    protected void validate(DomenskiObjekat object) throws Exception {
        if(!(object instanceof NalogZaServisiranje))
            throw new Exception("Objekat nije instanca klase NalogZaServisiranje!");
    }
    
}
