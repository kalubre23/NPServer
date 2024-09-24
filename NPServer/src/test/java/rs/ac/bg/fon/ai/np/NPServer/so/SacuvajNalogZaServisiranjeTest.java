package rs.ac.bg.fon.ai.np.NPServer.so;

import java.sql.CallableStatement;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.np.NPCommon.domain.Automobil;
import rs.ac.bg.fon.ai.np.NPCommon.domain.NalogZaServisiranje;
import rs.ac.bg.fon.ai.np.NPCommon.domain.Korisnik;
import rs.ac.bg.fon.ai.np.NPCommon.domain.Uloga;
import rs.ac.bg.fon.ai.np.NPCommon.domain.UoceniKvar;
import rs.ac.bg.fon.ai.np.NPServer.db.DatabaseConnection;
import rs.ac.bg.fon.ai.np.NPServer.logic.Controller;

class SacuvajNalogZaServisiranjeTest {
    
    Controller controller;
    
    @BeforeEach
    void setUp() throws Exception {
        ubaciPodatke();
        controller = new Controller(true);
    }
    
    @AfterEach
    void tearDown() throws Exception {
        ocistiTabelu();
        controller = null;
    }

    @Test
    void executeOperationTest() {
        NalogZaServisiranje n = new NalogZaServisiranje();
        n.setCena(500);
        n.setDatumKreiranja(LocalDate.now());

        Automobil a1 = new Automobil();
        a1.setTablice("BG333222");

        UoceniKvar uk = new UoceniKvar();
        uk.setKvarID(1);
        uk.setAutomobil(a1);
        n.setKvar(uk);

        Korisnik s = new Korisnik();
        Uloga uloga = new Uloga();
        uloga.setUlogaId(2);
        
        s.setKorisnikID(1);
        s.setUloga(uloga);
        n.setServiser(s);

        try {
            controller.sacuvajNalogZaServisiranje(n);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //controller.pronadjiNaloge...() funkcija pretrazuje naloge prema broju tablica
        //Ako joj posaljemo ovako inicijalizovan nalog, vratice sve naloge iz baze
        Automobil a = new Automobil();
        a.setTablice("");
        UoceniKvar uk1 = new UoceniKvar();
        uk1.setAutomobil(a);
        NalogZaServisiranje n1 = new NalogZaServisiranje();
        n1.setKvar(uk);

        boolean sadrzi = false;
        try {
            sadrzi = controller.pronadjiNalogeZaServisiranje(n1).contains(n);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(sadrzi);

    }

    private void ocistiTabelu() throws Exception {
        try {
            String query = "{CALL delete_data_nalog()}";
            
            CallableStatement callableStatement = DatabaseConnection.getInstance(true).getTestConnection().prepareCall(query);
            callableStatement.execute();
            System.out.println("Uspesno obrisani podaci za nalog");
            DatabaseConnection.getInstance(true).getTestConnection().commit();

        } catch (Exception ex) {
            System.out.println("Greska pri brisanju podataka za nalog");
            ex.printStackTrace();
        }
    }

    private void ubaciPodatke() {
        //treba ubaciti vlasnika, automobil, uoceni kvar, pokvarene delove
        try {
            String query = "{CALL insert_data_nalog()}";
            
            CallableStatement callableStatement = DatabaseConnection.getInstance(true).getTestConnection().prepareCall(query);
            callableStatement.execute();
            System.out.println("Uspesno ubaceni podaci za nalog");
        } catch (Exception ex) {
            System.out.println("Greska pri ubacivanju podataka za nalog");
            ex.printStackTrace();
        }
    }

}
