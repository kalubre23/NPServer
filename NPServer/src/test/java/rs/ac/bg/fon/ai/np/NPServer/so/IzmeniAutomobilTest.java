package rs.ac.bg.fon.ai.np.NPServer.so;

import java.sql.CallableStatement;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.np.NPCommon.domain.Automobil;
import rs.ac.bg.fon.ai.np.NPCommon.domain.Marka;
import rs.ac.bg.fon.ai.np.NPCommon.domain.UoceniKvar;
import rs.ac.bg.fon.ai.np.NPCommon.domain.Vlasnik;
import rs.ac.bg.fon.ai.np.NPServer.db.DatabaseConnection;
import rs.ac.bg.fon.ai.np.NPServer.logic.Controller;

class IzmeniAutomobilTest {

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
    void executeOperationTest() throws Exception {
        //Izmena Automobil(BG333222, 2014, 1, 1)
        Automobil a = new Automobil();
        a.setTablice("BG333222");
        //novo godiste
        a.setGodiste(2005);
        //novi vlasnik
        Vlasnik v = new Vlasnik();
        v.setVlasnikID(2);
        a.setVlasnik(v);
        //nova marka
        Marka marka = new Marka();
        marka.setMarkaID(2);
        a.setMarka(marka);
        //novi kvar
        UoceniKvar uk1 = new UoceniKvar();
        uk1.setOpis("Kvar baterije");
        uk1.setAutomobil(a);
        List<UoceniKvar> uoceniKvarovi = new ArrayList<>();
        uoceniKvarovi.add(uk1);
        a.setUoceniKvarovi(uoceniKvarovi);

        controller.izmeniAutomobil(a);

        Automobil a1 = new Automobil();
        a1.setTablice("BG333222");

        //pretraga automobila prema tablicama
        List<Automobil> sviAutomobili = controller.pronadjiAutomobile(a1);

        a1 = sviAutomobili.get(0);

        //a1 je automobil koji je vracen iz baze
        //a je automobil sa izmenjenim vrednostima nad kojim pozivamo sistemsku operaciju
        assertEquals(a1.getMarka(), a.getMarka());
        assertEquals(a1.getGodiste(), a.getGodiste());
        assertEquals(a1.getVlasnik(), a.getVlasnik());
        assertEquals(a1.getUoceniKvarovi(), a.getUoceniKvarovi());

    }
    
    private void ocistiTabelu() throws Exception {
        try {
            String query = "{CALL delete_data_auto()}";
            
            CallableStatement callableStatement = DatabaseConnection.getInstance(true).getTestConnection().prepareCall(query);
            callableStatement.execute();
            System.out.println("Uspesno obrisani podaci za auto");
            DatabaseConnection.getInstance(true).getTestConnection().commit();

        } catch (Exception ex) {
            System.out.println("Greska pri brisanju podataka za auto");
            ex.printStackTrace();
        }
    }

    private void ubaciPodatke() {
        //treba ubaciti vlasnika, automobil, uoceni kvar, pokvarene delove
        try {
            String query = "{CALL insert_data_auto()}";
            
            CallableStatement callableStatement = DatabaseConnection.getInstance(true).getTestConnection().prepareCall(query);
            callableStatement.execute();
            System.out.println("Uspesno ubaceni podaci za auto");
        } catch (Exception ex) {
            System.out.println("Greska pri ubacivanju podataka za auto");
            ex.printStackTrace();
        }
    }

}
