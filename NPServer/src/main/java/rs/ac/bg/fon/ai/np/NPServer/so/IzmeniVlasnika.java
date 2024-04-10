/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.so;

import rs.ac.bg.fon.ai.np.NPCommon.domain.DomenskiObjekat;
import rs.ac.bg.fon.ai.np.NPCommon.domain.Vlasnik;

/**
 * Predstavlja konkretnu sistemsku operaciju za izmenu podataka o vlasniku u bazi. Nasledjuje 
 * opstu sistemsku operaciju.
 *
 * @see AbstractSO
 * @author Luka Obrenic
 * @since 1.1.0
 */
public class IzmeniVlasnika extends AbstractSO{

	/**
	 * Neparametrizovani konstruktor koji poziva konstruktor opste sistemske operacije koja je nasledjena.
	 * @throws Exception ako dodje do greske pri izvrsavanju konstruktora nadklase
	 */
    public IzmeniVlasnika() throws Exception {
        super();
    }
    
    

    /**
     * Konkretna implementacija sistemske operacije izmene podataka o vlasniku.
     * 
     * Poziva se metoda database brokera za izmenu podataka u bazi.
     */
    @Override
    protected void executeOperation(DomenskiObjekat object) throws Exception {
        databaseBroker.izmeni(object);
    }

    /**
     * Vrsi validaciju vlasnika.
     * 
     * Objekat mora biti instanca klase Vlasnik.
     */
    @Override
    protected void validate(DomenskiObjekat object) throws Exception {
        if (!(object instanceof Vlasnik)) {
            throw new Exception("Objekat nije instanca klase Vlasnik!");
        }
    }
    
}
