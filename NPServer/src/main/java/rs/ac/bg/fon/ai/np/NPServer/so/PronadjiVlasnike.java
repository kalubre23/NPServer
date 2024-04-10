/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.so;

import java.util.List;
import rs.ac.bg.fon.ai.np.NPCommon.domain.DomenskiObjekat;
import rs.ac.bg.fon.ai.np.NPCommon.domain.Vlasnik;

/**
 * Predstavlja konkretnu sistemsku operaciju za pretrazivanje vlasnika u bazi. Nasledjuje 
 * opstu sistemsku operaciju.
 *
 * @see AbstractSO
 * @author Luka Obrenic
 * @since 1.1.0
 */
public class PronadjiVlasnike extends AbstractSO{
    
	/**
	 * Lista vlasnika koji zadovoljavaju kriterijum pretrage.
	 * @see Vlasnik
	 */
    List<Vlasnik> listaVlasnika;

    /**
     * Vraca listu vlasnika koji zadovoljavaju kriterijum pretrage.
     * 
     * @return listaVlasnika koji zadovoljavaju kriterijum pretrage, kao lista objekata domenske klase {@link Vlasnik}.
     */
    public List<Vlasnik> getListaVlasnika() {
        return listaVlasnika;
    }
    
    /**
	 * Neparametrizovani konstruktor koji poziva konstruktor opste sistemske operacije koja je nasledjena.
	 * @throws Exception ako dodje do greske pri izvrsavanju konstruktora nadklase
	 */
    public PronadjiVlasnike() throws Exception {
        super();
    }
    
    /**
     * Konkretna implementacija sistemske operacije pretrazivanja vlasnika iz baze.
     * 
     * Poziva se metoda za vracanje vise objekata u database brokeru i 
     * prosledjuje joj se vlasnik koji sluzi kao kriterijum pretrage.
     */
    @SuppressWarnings("unchecked")
	@Override
    protected void executeOperation(DomenskiObjekat object) throws Exception {
        listaVlasnika = (List<Vlasnik>) (Object) databaseBroker.vratiViseSaUslovom(object);
    }

    /**
     * Vrsi validaciju vlasnika.
     * 
     * Objekat mora biti instanca klase {@link Vlasnik}.
     */
    @Override
    protected void validate(DomenskiObjekat object) throws Exception {
        if(!(object instanceof Vlasnik))
            throw new Exception("Objekat nije instanca klase Vlasnik!");
    }
    
}
