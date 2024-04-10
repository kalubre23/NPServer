/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.so;

import rs.ac.bg.fon.ai.np.NPCommon.domain.DomenskiObjekat;
import rs.ac.bg.fon.ai.np.NPCommon.domain.Vlasnik;

/**
 * Predstavlja konkretnu sistemsku operaciju za dodavanje vlasnika u bazu. Nasledjuje 
 * opstu sistemsku oepraciju.
 *
 * @see AbstractSO
 * @author Luka Obrenic
 * @since 1.1.0
 */
public class SacuvajVlasnika extends AbstractSO{

	/**
	 * Neparametrizovani konstruktor koji poziva konstruktor opste sistemske operacije koja je nasledjena.
	 * @throws Exception ako dodje do greske pri izvrsavanju konstruktora nadklase
	 */
    public SacuvajVlasnika() throws Exception {
        super();
    }
    

    /**
     * Konkretna implementacija sistemske operacije dodavanja vlasnika u bazu.
     * 
     * Poziva se metoda za dodavanje u database brokeru i prosledjuje joj se vlasnik.
     */
    @Override
    protected void executeOperation(DomenskiObjekat object) throws Exception {
        databaseBroker.sacuvaj(object);
    }

    /**
     * Vrsi validaciju vlasnika.
     * 
     * Objekat mora biti instanca klase {@link Vlasnik}.
     */
    @Override
    protected void validate(DomenskiObjekat object) throws Exception {
        if(!(object instanceof Vlasnik))
            throw new Exception("Objekad nije instanca klase Vlasnik!");
    }
    
}
