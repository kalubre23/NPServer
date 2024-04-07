/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.logic;

import java.util.List;

import rs.ac.bg.fon.ai.np.NPCommon.domain.Automobil;
import rs.ac.bg.fon.ai.np.NPCommon.domain.DeoAutomobila;
import rs.ac.bg.fon.ai.np.NPCommon.domain.Marka;
import rs.ac.bg.fon.ai.np.NPCommon.domain.NalogZaServisiranje;
import rs.ac.bg.fon.ai.np.NPCommon.domain.PokvareniDeo;
import rs.ac.bg.fon.ai.np.NPCommon.domain.Serviser;
import rs.ac.bg.fon.ai.np.NPServer.so.IzmeniAutomobil;
import rs.ac.bg.fon.ai.np.NPServer.so.IzmeniPokvarenDeo;
import rs.ac.bg.fon.ai.np.NPServer.so.LoginServiser;
import rs.ac.bg.fon.ai.np.NPServer.so.ObrisiAutomobil;
import rs.ac.bg.fon.ai.np.NPServer.so.ObrisiNalogZaServisiranje;
import rs.ac.bg.fon.ai.np.NPServer.so.ObrisiPokvarenDeo;
import rs.ac.bg.fon.ai.np.NPServer.so.PronadjiAutomobile;
import rs.ac.bg.fon.ai.np.NPServer.so.PronadjiNalogeZaServisiranje;
import rs.ac.bg.fon.ai.np.NPServer.so.PronadjiPokvareneDelove;
import rs.ac.bg.fon.ai.np.NPServer.so.SacuvajAutomobil;
import rs.ac.bg.fon.ai.np.NPServer.so.SacuvajNalogZaServisiranje;
import rs.ac.bg.fon.ai.np.NPServer.so.SacuvajPokvarenDeo;
import rs.ac.bg.fon.ai.np.NPServer.so.UcitajListuDelovaAutomobila;
import rs.ac.bg.fon.ai.np.NPServer.so.UcitajListuMarki;


/**
 * Predstavlja kontrolera aplikacione logike.
 * 
 * Povezuje klijentsku nit sa sistemskim operacijiama.
 *
 * @author Luka Obrenic
 * @since 1.0.0
 */
public class Controller {

	/**
	 * Poziva sistemsku operaciju za logovanje servisera u sistem.
	 * 
	 * Vraca inicijalizovanog servisera (sa podacima iz baze) ili baca izuzetak ukoliko dodje do greske.
	 * 
	 * @param serviser koga treba ulogovati
	 * @return inicijalizovanog servisera sa vrednostima iz baze
	 * @throws Exception ako dodje do greske prilikom poziva sistemske operacije
	 */
    public Serviser login(Serviser serviser) throws Exception {
        LoginServiser loginServiser = new LoginServiser();
        // User u= loginUser.execute(user);
        loginServiser.execute(serviser);
        return loginServiser.getServiser();
    }

    /**
	 * Poziva sistemsku operaciju koja vraca listu svih marki automobila iz baze.
	 * 
	 * Vraca popunjenu listu svih marki automobila iz baze ili baca izuzetak ukoliko dodje do greske.
	 * 
	 * @return listu svih marki kao tipiziranu listu marki
	 * @throws Exception ako dodje do greske prilikom poziva sistemske operacije
	 */
    public List<Marka> ucitajListuMarki() throws Exception {
        UcitajListuMarki ucitajListuMarki = new UcitajListuMarki();
        ucitajListuMarki.execute(new Marka());
        return ucitajListuMarki.getListaMarki();
    }

    /**
	 * Poziva sistemsku operaciju koja dodaje automobil u bazu.
	 * 
	 * @param automobil koga treba dodati u bazu
	 * @throws Exception ako dodje do greske prilikom poziva sistemske operacije
	 */
    public void sacuvajAutomobil(Automobil automobil) throws Exception {
        SacuvajAutomobil sacuvajAutomobil = new SacuvajAutomobil();
        sacuvajAutomobil.execute(automobil);
    }
    
    /**
	 * Poziva sistemsku operaciju koja pronalazi automobile i vraca ih iz baze.
	 * 
	 * Vraca popunjenu listu automobila iz baze ili baca izuzetak ukoliko dodje do greske.
	 * 
	 * @param automobil koji sluzi kao uslov za pretragu
	 * @return listu automobila koji zadovoljavaju kriterijum pretrage
	 * @throws Exception ako dodje do greske prilikom poziva sistemske operacije
	 */
    public List<Automobil> pronadjiAutomobile(Automobil automobil) throws Exception{
        PronadjiAutomobile pronadjiAutomobile = new PronadjiAutomobile();
        pronadjiAutomobile.execute(automobil);
        return pronadjiAutomobile.getAutomobili();
    }
    
    /**
	 * Poziva sistemsku operaciju koja menja podatke o automobilu u bazi.
	 * 
	 * @param autmomobil sa novim(izmenjenim) podacima
	 * @throws Exception ako dodje do greske prilikom poziva sistemske operacije
	 */
    public void izmeniAutomobil(Automobil automobil) throws Exception{
        IzmeniAutomobil izmeniAutomobil = new IzmeniAutomobil();
        izmeniAutomobil.execute(automobil);
    }

    /**
	 * Poziva sistemsku operaciju koja brise automobil iz baze.
	 * 
	 * @param autmomobil koga treba obrisati
	 * @throws Exception ako dodje do greske prilikom poziva sistemske operacije
	 */
    public void obrisiAutomobil(Automobil automobilBrisanje) throws Exception {
        ObrisiAutomobil obrisiAutomobil = new ObrisiAutomobil();
        obrisiAutomobil.execute(automobilBrisanje);
    }

    /**
	 * Poziva sistemsku operaciju koja vraca listu svih delova automobila iz baze.
	 * 
	 * Vraca popunjenu listu svih delova automobila iz baze ili baca izuzetak ukoliko dodje do greske.
	 * 
	 * @return listu svih delova automobila kao listu Object-a
	 * @throws Exception ako dodje do greske prilikom poziva sistemske operacije
	 */
    public Object ucitajListuDelovaAutomobila() throws Exception {
        UcitajListuDelovaAutomobila ucitajListuDelovaAutomobila = new UcitajListuDelovaAutomobila();
        ucitajListuDelovaAutomobila.execute(new DeoAutomobila());
        return ucitajListuDelovaAutomobila.getDeloviAutomobila();
    }

    /**
	 * Poziva sistemsku operaciju koja dodaje pokvareni deo u bazu.
	 * 
	 * @param pokvareniDeo koga treba dodati u bazu
	 * @throws Exception ako dodje do greske prilikom poziva sistemske operacije
	 */
    public void sacuvajPokvarenDeo(PokvareniDeo pokvareniDeo) throws Exception {
        SacuvajPokvarenDeo sacuvajPokvarenDeo = new SacuvajPokvarenDeo();
        sacuvajPokvarenDeo.execute(pokvareniDeo);
    }

    /**
	 * Poziva sistemsku operaciju koja pronalazi pokvarene delove i vraca ih iz baze.
	 * 
	 * Vraca popunjenu listu pokvarenih delova iz baze ili baca izuzetak ukoliko dodje do greske.
	 * 
	 * @param pd pokvareni deo koji sluzi kao kriterijum za pretragu
	 * @return listu pokvarenih delova koji zadovoljavaju kriterijum pretrage
	 * @throws Exception ako dodje do greske prilikom poziva sistemske operacije
	 */
    public List<PokvareniDeo> pronadjiPokvareneDelove(PokvareniDeo pd) throws Exception {
        PronadjiPokvareneDelove pronadjiPokvareneDelove = new PronadjiPokvareneDelove();
        pronadjiPokvareneDelove.execute(pd);
        return pronadjiPokvareneDelove.getListaPokvarenihDelova();
    }

    /**
	 * Poziva sistemsku operaciju koja menja podatke o pokvarenom delu u bazi.
	 * 
	 * @param pdIzmena sa novim (izmenjenim) podacima o pokvrenom delu
	 * @throws Exception ako dodje do greske prilikom poziva sistemske operacije
	 */
    public void izmeniPokvarenDeo(PokvareniDeo pdIzmena) throws Exception {
        IzmeniPokvarenDeo izmeniPokvarenDeo = new IzmeniPokvarenDeo();
        izmeniPokvarenDeo.execute(pdIzmena);
    }

    /**
	 * Poziva sistemsku operaciju koja brise pokvareni deo iz baze.
	 * 
	 * @param pdBrisanje pokvareni deo koga treba obrisati
	 * @throws Exception ako dodje do greske prilikom poziva sistemske operacije
	 */
    public void obrisiPokvarenDeo(PokvareniDeo pdBrisanje) throws Exception {
        ObrisiPokvarenDeo obrisiPokvarenDeo = new ObrisiPokvarenDeo();
        obrisiPokvarenDeo.execute(pdBrisanje);
        
    }

    /**
	 * Poziva sistemsku operaciju koja dodaje nalog za servisiranje u bazu.
	 * 
	 * @param nalogZaServisiranje koga treba dodati u bazu
	 * @throws Exception ako dodje do greske prilikom poziva sistemske operacije
	 */
    public void sacuvajNalogZaServisiranje(NalogZaServisiranje nalogZaServisiranje) throws Exception {
        SacuvajNalogZaServisiranje sacuvajNalogZaServisiranje = new SacuvajNalogZaServisiranje();
        sacuvajNalogZaServisiranje.execute(nalogZaServisiranje);
    }

    /**
	 * Poziva sistemsku operaciju koja pronalazi naloge i vraca ih iz baze.
	 * 
	 * Vraca popunjenu listu naloga iz baze ili baca izuzetak ukoliko dodje do greske.
	 * 
	 * @param nalogPretraga koji sluzi kao kriterijum za pretragu
	 * @return listu naloga za servisiranje koji zadovoljavaju kriterijum pretrage
	 * @throws Exception ako dodje do greske prilikom poziva sistemske operacije
	 */
    public List<NalogZaServisiranje> pronadjiNalogeZaServisiranje(NalogZaServisiranje nalogPretraga) throws Exception {
        PronadjiNalogeZaServisiranje pronadjiNalogeZaServisiranje = new PronadjiNalogeZaServisiranje();
        pronadjiNalogeZaServisiranje.execute(nalogPretraga);
        return pronadjiNalogeZaServisiranje.getListaNaloga();
    }

    /**
	 * Poziva sistemsku operaciju koja brise nalog iz baze.
	 * 
	 * @param nalogBrisanje nalog koga treba obrisati
	 * @throws Exception ako dodje do greske prilikom poziva sistemske operacije
	 */
    public void obrisiNalog(NalogZaServisiranje nalogBrisanje) throws Exception {
        ObrisiNalogZaServisiranje obrisiNalogZaServisiranje = new ObrisiNalogZaServisiranje();
        obrisiNalogZaServisiranje.execute(nalogBrisanje);
    }
}
