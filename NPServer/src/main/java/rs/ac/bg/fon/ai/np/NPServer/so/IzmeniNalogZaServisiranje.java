/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.so;

import rs.ac.bg.fon.ai.np.NPCommon.domain.DomenskiObjekat;
import rs.ac.bg.fon.ai.np.NPCommon.domain.NalogZaServisiranje;

/**
 * Predstavlja konkretnu sistemsku operaciju za izmenu podataka o nalogu u bazi. Nasledjuje 
 * opstu sistemsku oepraciju.
 *
 * @see AbstractSO
 * @author Luka Obrenic
 * @since 1.0.0
 */
public class IzmeniNalogZaServisiranje extends AbstractSO{

    /**
     * Neparametrizovani konstruktor koji poziva konstruktor opste sistemske operacije koja je nasledjena.
     * @throws Exception ako dodje do greske pri izvrsavanju konstruktora nadklase
     */
    public IzmeniNalogZaServisiranje(boolean test) throws Exception {
        super(test);
    }

    /**
     * Konkretna implementacija sistemske operacije izmene podataka o nalogu.
     * 
     * Poziva se metoda brokera baze podataka za izmenu i prosledjuje joj se nalog.
     */
    @Override
    protected void executeOperation(DomenskiObjekat object) throws Exception {
        databaseBroker.izmeni(object);
    }

    /**
     * Vrsi validaciju naloga.
     * 
     * Objekat mora biti instanca klase {@link NalogZaServisiranje}.
     */
    @Override
    protected void validate(DomenskiObjekat object) throws Exception {
        if (!(object instanceof NalogZaServisiranje)) {
            throw new Exception("Objekat nije instanca klase NalogZaServisiranje!");
        }
    }
    
}
