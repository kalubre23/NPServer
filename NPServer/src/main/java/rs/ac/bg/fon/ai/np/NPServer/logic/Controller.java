/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.logic;



import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
 *
 * @author Cartman
 */
public class Controller {

    public Serviser login(Serviser serviser) throws Exception {
        LoginServiser loginServiser = new LoginServiser();
        // User u= loginUser.execute(user);
        loginServiser.execute(serviser);
        return loginServiser.getServiser();
    }

    public List<Marka> ucitajListuMarki() throws Exception {
        UcitajListuMarki ucitajListuMarki = new UcitajListuMarki();
        ucitajListuMarki.execute(new Marka());
        return ucitajListuMarki.getListaMarki();
    }

    public void sacuvajAutomobil(Automobil automobil) throws Exception {
        SacuvajAutomobil sacuvajAutomobil = new SacuvajAutomobil();
        sacuvajAutomobil.execute(automobil);
    }
    
    public List<Automobil> pronadjiAutomobile(Automobil automobil) throws Exception{
        PronadjiAutomobile pronadjiAutomobile = new PronadjiAutomobile();
        pronadjiAutomobile.execute(automobil);
        return pronadjiAutomobile.getAutomobili();
    }
    
    public void izmeniAutomobil(Automobil automobil) throws Exception{
        IzmeniAutomobil izmeniAutomobil = new IzmeniAutomobil();
        izmeniAutomobil.execute(automobil);
    }

    public void obrisiAutomobil(Automobil automobilBrisanje) throws Exception {
        ObrisiAutomobil obrisiAutomobil = new ObrisiAutomobil();
        obrisiAutomobil.execute(automobilBrisanje);
    }

    public Object ucitajListuDelovaAutomobila() throws Exception {
        UcitajListuDelovaAutomobila ucitajListuDelovaAutomobila = new UcitajListuDelovaAutomobila();
        ucitajListuDelovaAutomobila.execute(new DeoAutomobila());
        return ucitajListuDelovaAutomobila.getDeloviAutomobila();
    }

    public void sacuvajPokvarenDeo(PokvareniDeo pokvareniDeo) throws Exception {
        SacuvajPokvarenDeo sacuvajPokvarenDeo = new SacuvajPokvarenDeo();
        sacuvajPokvarenDeo.execute(pokvareniDeo);
    }

    public List<PokvareniDeo> pronadjiPokvareneDelove(PokvareniDeo pd) throws Exception {
        PronadjiPokvareneDelove pronadjiPokvareneDelove = new PronadjiPokvareneDelove();
        pronadjiPokvareneDelove.execute(pd);
        return pronadjiPokvareneDelove.getListaPokvarenihDelova();
    }

    public void izmeniPokvarenDeo(PokvareniDeo pdIzmena) throws Exception {
        IzmeniPokvarenDeo izmeniPokvarenDeo = new IzmeniPokvarenDeo();
        izmeniPokvarenDeo.execute(pdIzmena);
    }

    public void obrisiPokvarenDeo(PokvareniDeo pdBrisanje) throws Exception {
        ObrisiPokvarenDeo obrisiPokvarenDeo = new ObrisiPokvarenDeo();
        obrisiPokvarenDeo.execute(pdBrisanje);
        
    }

    public void sacuvajNalogZaServisiranje(NalogZaServisiranje nalogZaServisiranje) throws Exception {
        SacuvajNalogZaServisiranje sacuvajNalogZaServisiranje = new SacuvajNalogZaServisiranje();
        sacuvajNalogZaServisiranje.execute(nalogZaServisiranje);
    }

    public List<NalogZaServisiranje> pronadjiNalogeZaServisiranje(NalogZaServisiranje nalogPretraga) throws Exception {
        PronadjiNalogeZaServisiranje pronadjiNalogeZaServisiranje = new PronadjiNalogeZaServisiranje();
        pronadjiNalogeZaServisiranje.execute(nalogPretraga);
        return pronadjiNalogeZaServisiranje.getListaNaloga();
    }

    public void obrisiNalog(NalogZaServisiranje nalogBrisanje) throws Exception {
        ObrisiNalogZaServisiranje obrisiNalogZaServisiranje = new ObrisiNalogZaServisiranje();
        obrisiNalogZaServisiranje.execute(nalogBrisanje);
    }
}
