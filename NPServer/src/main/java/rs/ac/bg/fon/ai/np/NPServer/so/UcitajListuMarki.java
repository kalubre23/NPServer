/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.so;


import java.util.List;


import rs.ac.bg.fon.ai.np.NPCommon.domain.DomenskiObjekat;
import rs.ac.bg.fon.ai.np.NPCommon.domain.Marka;

/**
 * Predstavlja konkretnu sistemsku operaciju koja vraca sve marke automobila iz baze. Nasledjuje 
 * opstu sistemsku operaciju.
 *
 * @see AbstractSO
 * @author Luka Obrenic
 * @since 1.0.0
 */
public class UcitajListuMarki extends AbstractSO {

	/**
	 * Lista svih marki automobila.
	 * @see Marka
	 */
    List<Marka> listaMarki;

    /**
     * Vraca listu svih marki automobila.
     * @return listaMarki vraceni iz baze, kao lista objekata domenske klase Marka.
     */
    public List<Marka> getListaMarki() {
        return listaMarki;
    }
    /**
	 * Neparametrizovani konstruktor koji poziva konstruktor opste sistemske operacije koja je nasledjena.
	 * @throws Exception ako dodje do greske pri izvrsavanju konstruktora nadklase
	 */
    public UcitajListuMarki() throws Exception {
        super();
    }

    /**
     * Konkretna implementacija sistemske operacije vracanja svih marki automobila iz baze.
     * 
     * Poziva se metoda za vracanje svih objekata domenske klase u database brokeru.
     */
    @SuppressWarnings("unchecked")
	@Override
    protected void executeOperation(DomenskiObjekat object) throws Exception {
        listaMarki = (List<Marka>) (Object) databaseBroker.vratiSve(object);
    }

    @Override
    protected void validate(DomenskiObjekat object) throws Exception {
        //
    }

}
