package rs.ac.bg.fon.ai.np.NPServer.so;

import java.sql.CallableStatement;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.np.NPCommon.domain.Vlasnik;
import rs.ac.bg.fon.ai.np.NPServer.db.DatabaseConnection;
import rs.ac.bg.fon.ai.np.NPServer.logic.Controller;

class ObrisiVlasnikaTest {
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
        Vlasnik v = new Vlasnik();
        v.setVlasnikID(1);

        controller.obrisiVlasnika(v);

        Vlasnik v1 = new Vlasnik();
        v1.setIme("");

        //controller.pronadjiVlasnike() funkcija pretrazuje prema imenu vlasnika
        //Ako joj posaljemo ovako inicijalizovanog vlasnika, vratice sve vlasnike iz baze
        assertTrue(controller.pronadjiVlasnike(v1).isEmpty());

    }
    
    private void ocistiTabelu() throws Exception {
        try {
            String query = "{CALL delete_data_vlasnik()}";
            
            CallableStatement callableStatement = DatabaseConnection.getInstance(true).getTestConnection().prepareCall(query);
            callableStatement.execute();
            System.out.println("Uspesno obrisani podaci za vlasnika");
            DatabaseConnection.getInstance(true).getTestConnection().commit();

        } catch (Exception ex) {
            System.out.println("Greska pri brisanju podataka za vlasnika");
            ex.printStackTrace();
        }
    }

    private void ubaciPodatke() {
        //treba ubaciti vlasnika, automobil, uoceni kvar, pokvarene delove
        try {
            String query = "{CALL insert_data_vlasnik()}";
            
            CallableStatement callableStatement = DatabaseConnection.getInstance(true).getTestConnection().prepareCall(query);
            callableStatement.execute();
            System.out.println("Uspesno ubaceni podaci za vlasnika");
        } catch (Exception ex) {
            System.out.println("Greska pri ubacivanju podataka za vlasnika");
            ex.printStackTrace();
        }
    }


}
