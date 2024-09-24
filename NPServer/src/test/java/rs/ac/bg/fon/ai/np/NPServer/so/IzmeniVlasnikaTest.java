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

class IzmeniVlasnikaTest {
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

        //nove vrednosti za vlasnika
        v.setIme("Ksenija");
        v.setPrezime("Kandic");
        v.setEmail("kena@gmail.com");
        v.setTelefon("061999000");

        controller.izmeniVlasnika(v);

        Vlasnik v1 = new Vlasnik();
        v1.setIme("Ksenija");

        List<Vlasnik> vlasnici = controller.pronadjiVlasnike(v1);

        v1 = vlasnici.get(0);

        //v je vlasnik sa izmenjenim vrednostima koga menjamo u bazi
        //v1 je vlasnik koga smo dobili iz baze nakon izmene

        assertEquals(v.getVlasnikID(), v1.getVlasnikID());
        assertEquals(v.getIme(), v1.getIme());
        assertEquals(v.getPrezime(), v1.getPrezime());
        assertEquals(v.getEmail(), v1.getEmail());
        assertEquals(v.getTelefon(), v1.getTelefon());
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
