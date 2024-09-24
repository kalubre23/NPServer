package rs.ac.bg.fon.ai.np.NPServer.so;

import java.sql.CallableStatement;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.np.NPCommon.domain.Automobil;
import rs.ac.bg.fon.ai.np.NPCommon.domain.Marka;
import rs.ac.bg.fon.ai.np.NPServer.db.DatabaseConnection;
import rs.ac.bg.fon.ai.np.NPServer.logic.Controller;

class ObrisiAutomobilTest {
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
        Automobil auto = new Automobil();
        auto.setMarka(new Marka());
        auto.setTablice("BG333222");

        controller.obrisiAutomobil(auto);

        //controller.pronadjiAutomobile() funkcija pretrazuje automobile prema tablicama
        //Ako joj posaljemo ovako inicijalizovan automobil, vratice sve automobile iz baze
        Automobil a1 = new Automobil();
        a1.setTablice("");

        //List<Automobil> sviAutomobili = controller.pronadjiAutomobile(a1);

        Exception e = assertThrows(Exception.class, ()->controller.pronadjiAutomobile(a1));
        assertEquals(e.getMessage(), "Ne postoje takvi u bazi");
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
