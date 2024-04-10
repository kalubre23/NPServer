/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.so;

import rs.ac.bg.fon.ai.np.NPCommon.domain.DomenskiObjekat;
import rs.ac.bg.fon.ai.np.NPCommon.domain.Vlasnik;

/**
 * Predstavlja konkretnu sistemsku operaciju za brisanje vlasnika u bazi. Nasledjuje 
 * opstu sistemsku oepraciju.
 *
 * @see AbstractSO
 * @author Luka Obrenic
 * @since 1.1.0
 */
public class ObrisiVlasnika extends AbstractSO{

	/**
	 * Neparametrizovani konstruktor koji poziva konstruktor opste sistemske operacije koja je nasledjena.
	 * @throws Exception ako dodje do greske pri izvrsavanju konstruktora nadklase
	 */
    public ObrisiVlasnika() throws Exception {
        super();
    }
    
    

    /**
     * Konkretna implementacija sistemske operacije brisanja vlasnika iz baze.
     * 
     * Poziva se metoda za brisanje u database brokeru i prosledjuje joj se vlasnik.
     * @param object koji predstavlja vlasnika koga treba obrisati, tipa {@link DomenskiObjekat}
     */
    @Override
    protected void executeOperation(DomenskiObjekat object) throws Exception {
        databaseBroker.obrisi(object);
    }

    /**
     * Vrsi validaciju vlasnika.
     * 
     * Objekat mora biti instanca klase {@link Vlasnik}.
     * @param object koji predstavlja vlasnika koga treba validirati, tipa {@link DomenskiObjekat}
     */
    @Override
    protected void validate(DomenskiObjekat object) throws Exception {
        if (!(object instanceof Vlasnik)) {
            throw new Exception("Objekat nije instanca klase Vlasnik!");
        }
    }
    
}
