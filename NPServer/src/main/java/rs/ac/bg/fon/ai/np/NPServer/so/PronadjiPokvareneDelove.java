/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.so;


import java.util.List;

import rs.ac.bg.fon.ai.np.NPCommon.domain.DomenskiObjekat;
import rs.ac.bg.fon.ai.np.NPCommon.domain.PokvareniDeo;

/**
 * Predstavlja konkretnu sistemsku operaciju za pretrazivanje pokvarenih delova u bazi. Nasledjuje 
 * opstu sistemsku operaciju.
 *
 * @see AbstractSO
 * @author Luka Obrenic
 * @since 1.0.0
 */
public class PronadjiPokvareneDelove extends AbstractSO {
	/**
	 * Lista pokvarenih delova koji zadovoljavaju kriterijum pretrage.
	 * @see PokvareniDeo
	 */
    List<PokvareniDeo> listaPokvarenihDelova;

    /**
     * Vraca listu pokvarenih delova koji zadovoljavaju kriterijum pretrage.
     * 
     * @return listaPokvarenihDelova koji zadovoljavaju kriterijum pretrage, kao lista objekata domenske klase {@link PokvareniDeo}.
     */
    public List<PokvareniDeo> getListaPokvarenihDelova() {
        return listaPokvarenihDelova;
    }
    
    /**
	 * Neparametrizovani konstruktor koji poziva konstruktor opste sistemske operacije koja je nasledjena.
	 * @throws Exception ako dodje do greske pri izvrsavanju konstruktora nadklase
	 */
    public PronadjiPokvareneDelove() throws Exception {
        super();
    }
    

    /**
     * Konkretna implementacija sistemske operacije pretrazivanja pokvarenih delova iz baze.
     * 
     * Poziva se metoda za vracanje vise objekata u database brokeru i 
     * prosledjuje joj se pokvareni deo koji sluzi kao kriterijum pretrage.
     */
    @SuppressWarnings("unchecked")
	@Override
    protected void executeOperation(DomenskiObjekat object) throws Exception {
        listaPokvarenihDelova = (List<PokvareniDeo>)(Object) databaseBroker.vratiViseSaUslovom(object);
        if(listaPokvarenihDelova == null || listaPokvarenihDelova.isEmpty()){
            System.out.println("LISTA JE PRAZNA U SO!");
        }
    }

    /**
     * Vrsi validaciju pokvarenog dela.
     * 
     * Objekat mora biti instanca klase PokvareniDeo.
     */
    @Override
    protected void validate(DomenskiObjekat object) throws Exception {
        if(!(object instanceof PokvareniDeo))
            throw new Exception("Objekat nije instanca klase PokvareniDeo!");
    }
    
}
