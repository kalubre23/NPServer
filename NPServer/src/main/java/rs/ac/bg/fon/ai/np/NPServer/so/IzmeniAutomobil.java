/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.so;


import java.util.List;

import rs.ac.bg.fon.ai.np.NPCommon.domain.Automobil;
import rs.ac.bg.fon.ai.np.NPCommon.domain.DomenskiObjekat;
import rs.ac.bg.fon.ai.np.NPCommon.domain.UoceniKvar;

/**
 * Predstavlja konkretnu sistemsku operaciju za izmenu podataka o automobilu u bazi. Nasledjuje 
 * opstu sistemsku oepraciju.
 *
 * @see AbstractSO
 * @author Luka Obrenic
 * @since 1.0.0
 */
public class IzmeniAutomobil extends AbstractSO {

	/**
	 * Neparametrizovani konstruktor koji poziva konstruktor opste sistemske operacije koja je nasledjena.
	 * @throws Exception ako dodje do greske pri izvrsavanju konstruktora nadklase
	 */
    public IzmeniAutomobil() throws Exception {
        super();
    }

    /**
     * Konkretna implementacija sistemske operacije izmene podataka o automobilu.
     * 
     * Azuriraju se vrednosti automobila u bazi, a zatim se obrisu svi uoceni kvarovi za taj automobil
     * i dodaju novi.
     */
    @Override
    protected void executeOperation(DomenskiObjekat object) throws Exception {
        //obrisi sve kvarove i sacuvaj novu listu kvarova
        databaseBroker.izmeniSlozeni(object);
        databaseBroker.obrisi(((Automobil)object).getUoceniKvarovi().get(0));
        List<UoceniKvar> listaKvarova = ((Automobil) object).getUoceniKvarovi();
        for (UoceniKvar k : listaKvarova) {
            databaseBroker.sacuvaj(k);
        }
    }

    /**
     * Vrsi validaciju automobila.
     * 
     * Objekat mora biti instanca klase Automobil i lista uocenih kvarova ne sme biti prazna.
     */
    @Override
    protected void validate(DomenskiObjekat object) throws Exception {
        if (!(object instanceof Automobil)) {
            throw new Exception("Objekat nije instanca klase Automobil!");
        }
        if(((Automobil)object).getUoceniKvarovi().isEmpty()){
            throw new Exception("Lista kvarova je prazna!");
        }
    }

}
