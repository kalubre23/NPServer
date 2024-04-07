/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.so;

import rs.ac.bg.fon.ai.np.NPCommon.domain.DomenskiObjekat;
import rs.ac.bg.fon.ai.np.NPCommon.domain.PokvareniDeo;

/**
 * Predstavlja konkretnu sistemsku operaciju za brisanje pokvarenog dela u bazi. Nasledjuje 
 * opstu sistemsku oepraciju.
 *
 * @see AbstractSO
 * @author Luka Obrenic
 * @since 1.0.0
 */
public class ObrisiPokvarenDeo extends AbstractSO {

	/**
	 * Neparametrizovani konstruktor koji poziva konstruktor opste sistemske operacije koja je nasledjena.
	 * @throws Exception ako dodje do greske pri izvrsavanju konstruktora nadklase
	 */
    public ObrisiPokvarenDeo() throws Exception {
        super();
    }
    
    /**
     * Konkretna implementacija sistemske operacije brisanja pokvarenog dela iz baze.
     * 
     * Poziva se metoda za brisanje u database brokeru i prosledjuje joj se pokvareni deo.
     */
    @Override
    protected void executeOperation(DomenskiObjekat object) throws Exception {
        databaseBroker.obrisi(object);
    }

    /**
     * Vrsi validaciju pokvarenog dela.
     * 
     * Objekat mora biti instanca klase PokvareniDeo.
     */
    @Override
    protected void validate(DomenskiObjekat object) throws Exception {
        if (!(object instanceof PokvareniDeo)) {
            throw new Exception("Objekat nije instanca klase PokvareniDeo!");
        }
    }
    
}
