/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.so;

import java.util.List;

import rs.ac.bg.fon.ai.np.NPCommon.domain.DomenskiObjekat;
import rs.ac.bg.fon.ai.np.NPCommon.domain.NalogZaServisiranje;

/**
 * Predstavlja konkretnu sistemsku operaciju za pretrazivanje naloga u bazi. Nasledjuje 
 * opstu sistemsku operaciju.
 *
 * @see AbstractSO
 * @author Luka Obrenic
 * @since 1.0.0
 */
public class PronadjiNalogeZaServisiranje extends AbstractSO {

	/**
	 * Lista naloga koji zadovoljavaju kriterijum pretrage.
	 * @see NalogZaServisiranje
	 */
    List<NalogZaServisiranje> listaNaloga;

    /**
     * Vraca listu naloga koji zadovoljavaju kriterijum pretrage.
     * 
     * @return listaNaloga koji zadovoljavaju kriterijum pretrage, kao lista objekata domenske klase {@link NalogZaServisiranje}.
     */
    public List<NalogZaServisiranje> getListaNaloga() {
        return listaNaloga;
    }

    /**
	 * Neparametrizovani konstruktor koji poziva konstruktor opste sistemske operacije koja je nasledjena.
	 * @throws Exception ako dodje do greske pri izvrsavanju konstruktora nadklase
	 */
    public PronadjiNalogeZaServisiranje() throws Exception {
        super();
    }

    /**
     * Konkretna implementacija sistemske operacije pretrazivanja naloga iz baze.
     * 
     * Poziva se metoda za vracanje vise objekata u database brokeru i 
     * prosledjuje joj se nalog koji sluzi kao kriterijum pretrage.
     */
    @SuppressWarnings("unchecked")
	@Override
    protected void executeOperation(DomenskiObjekat object) throws Exception {
        listaNaloga = (List<NalogZaServisiranje>) (Object) databaseBroker.vratiViseSaUslovom(object);

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
