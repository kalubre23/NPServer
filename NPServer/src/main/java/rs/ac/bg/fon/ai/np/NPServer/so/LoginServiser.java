/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.so;

import rs.ac.bg.fon.ai.np.NPCommon.domain.DomenskiObjekat;
import rs.ac.bg.fon.ai.np.NPCommon.domain.Korisnik;

/**
 * Predstavlja konkretnu sistemsku operaciju za login korisnika. Nasledjuje 
 * opstu sistemsku operaciju.
 *
 * @see AbstractSO
 * @author Luka Obrenic
 * @since 1.1.0
 */
public class LoginServiser extends AbstractSO {

    /**
     * Korisnik koji se loguje, tipa {@link Korisnik}.
     */
    private Korisnik serviser;

    /**
     * Neparametrizovani konstruktor koji poziva konstruktor opste sistemske operacije koja je nasledjena.
     * @throws Exception ako dodje do greske pri izvrsavanju konstruktora nadklase
    */
    public LoginServiser(boolean test) throws Exception {
        super(test);
    }

    /**
     * Vraca ulogovanog korisnika.
     * @return ulogovani korisnik, tipa {@link Korisnik}
     */
    public Korisnik getServiser() {
        return serviser;
    }

    /**
     * Konkretna implementacija sistemske operacije za login korisnika.
     * 
     * Poziva se metoda database brokera koja vraca korisnika iz baze.
     */
    @Override
    protected void executeOperation(DomenskiObjekat object) throws Exception {
        serviser = (Korisnik) databaseBroker.vratiJednog(object);
        System.out.println("u LoginServiser koja poziva dbbr je serviser: "+serviser);
        if (serviser == null) {
            throw new Exception("Korisnik ne postoji.");
        }
    }

    /**
     * Vrsi validaciju.
     * 
     * Objekat mora biti instanca klase Korisnik.
     */
    @Override
    protected void validate(DomenskiObjekat object) throws Exception {
        if (!(object instanceof Korisnik)) {
            throw new Exception("Object is not valid");
        }
    }

}
