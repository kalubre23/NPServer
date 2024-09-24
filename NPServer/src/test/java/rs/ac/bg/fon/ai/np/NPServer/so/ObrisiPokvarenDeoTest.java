package rs.ac.bg.fon.ai.np.NPServer.so;

import java.sql.CallableStatement;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.np.NPCommon.domain.Automobil;
import rs.ac.bg.fon.ai.np.NPCommon.domain.PokvareniDeo;
import rs.ac.bg.fon.ai.np.NPCommon.domain.UoceniKvar;
import rs.ac.bg.fon.ai.np.NPServer.db.DatabaseConnection;
import rs.ac.bg.fon.ai.np.NPServer.logic.Controller;

class ObrisiPokvarenDeoTest {
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
        PokvareniDeo pd = new PokvareniDeo();
        pd.setCena(69.9);
        pd.setUsloviZaUpdate("BG333222", 1, 1);

        controller.obrisiPokvarenDeo(pd);

        PokvareniDeo pd1 = new PokvareniDeo();
        Automobil a1 = new Automobil();
        a1.setTablice("");
        UoceniKvar uk1 = new UoceniKvar();
        uk1.setAutomobil(a1);
        pd1.setUoceniKvar(uk1);
        
//        Exception e = assertThrows(Exception.class, ()->controller.pronadjiPokvareneDelove(pd1));
//        assertEquals(e.getMessage(), "Ne postoje takvi u bazi");
        
        assertTrue(controller.pronadjiPokvareneDelove(pd1).isEmpty());

    }
    
    private void ocistiTabelu() throws Exception {
        try {
            String query = "{CALL delete_data_pokvarenideo()}";
            
            CallableStatement callableStatement = DatabaseConnection.getInstance(true).getTestConnection().prepareCall(query);
            callableStatement.execute();
            System.out.println("Uspesno obrisani podaci za pokvarenideo");
            DatabaseConnection.getInstance(true).getTestConnection().commit();

        } catch (Exception ex) {
            System.out.println("Greska pri brisanju podataka za pokvarenideo");
            ex.printStackTrace();
        }
    }

    private void ubaciPodatke() {
        //treba ubaciti vlasnika, automobil, uoceni kvar, pokvarene delove
        try {
            String query = "{CALL pokvarenideo_update()}";
            
            CallableStatement callableStatement = DatabaseConnection.getInstance(true).getTestConnection().prepareCall(query);
            callableStatement.execute();
            System.out.println("Uspesno ubaceni podaci za pokvarenideo");
        } catch (Exception ex) {
            System.out.println("Greska pri ubacivanju podataka za pokvarenideo");
            ex.printStackTrace();
        }
    }

}
