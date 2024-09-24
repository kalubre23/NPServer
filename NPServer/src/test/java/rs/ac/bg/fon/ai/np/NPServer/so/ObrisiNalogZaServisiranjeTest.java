package rs.ac.bg.fon.ai.np.NPServer.so;

import java.sql.CallableStatement;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.np.NPCommon.domain.Automobil;
import rs.ac.bg.fon.ai.np.NPCommon.domain.NalogZaServisiranje;
import rs.ac.bg.fon.ai.np.NPCommon.domain.UoceniKvar;
import rs.ac.bg.fon.ai.np.NPServer.db.DatabaseConnection;
import rs.ac.bg.fon.ai.np.NPServer.logic.Controller;

class ObrisiNalogZaServisiranjeTest {

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
        NalogZaServisiranje n = new NalogZaServisiranje();
        n.setCena(66);
        n.setNalogID(1);

        controller.obrisiNalog(n);

        //controller.pronadjiNaloge...() funkcija pretrazuje naloge prema broju tablica
        //Ako joj posaljemo ovako inicijalizovan nalog, vratice sve naloge iz baze
        Automobil a = new Automobil();
        a.setTablice("");
        UoceniKvar uk1 = new UoceniKvar();
        uk1.setAutomobil(a);
        NalogZaServisiranje n1 = new NalogZaServisiranje();
        n1.setKvar(uk1);
        
        assertTrue(controller.pronadjiNalogeZaServisiranje(n1).isEmpty());
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
            String query = "{CALL insert_nalog_delete()}";
            
            CallableStatement callableStatement = DatabaseConnection.getInstance(true).getTestConnection().prepareCall(query);
            callableStatement.execute();
            System.out.println("Uspesno ubaceni podaci za nalog");
        } catch (Exception ex) {
            System.out.println("Greska pri ubacivanju podataka za nalog");
            ex.printStackTrace();
        }
    }

}
