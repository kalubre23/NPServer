package rs.ac.bg.fon.ai.np.NPServer.so;

import java.sql.CallableStatement;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.np.NPCommon.domain.DeoAutomobila;
import rs.ac.bg.fon.ai.np.NPCommon.domain.PokvareniDeo;
import rs.ac.bg.fon.ai.np.NPCommon.domain.UoceniKvar;
import rs.ac.bg.fon.ai.np.NPCommon.domain.Automobil;
import rs.ac.bg.fon.ai.np.NPServer.db.DatabaseConnection;
import rs.ac.bg.fon.ai.np.NPServer.logic.Controller;

class SacuvajPokvarenDeoTest {

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
        pd.setCena(49.9);

        Automobil a = new Automobil();
        a.setTablice("BG333222");

        UoceniKvar uk = new UoceniKvar();
        uk.setAutomobil(a);
        uk.setKvarID(1);
        pd.setUoceniKvar(uk);

        DeoAutomobila deo = new DeoAutomobila();
        deo.setDeoID(1);
        pd.setDeo(deo);

        List<PokvareniDeo> l = new ArrayList<>();
        l.add(pd);
        controller.sacuvajPokvarenDeo(l);

        PokvareniDeo pdPretraga = new PokvareniDeo();
        Automobil a1 = new Automobil();
        a1.setTablice("");
        UoceniKvar uk1 = new UoceniKvar();
        uk1.setAutomobil(a1);
        pdPretraga.setUoceniKvar(uk1);
        boolean sadrzi = controller.pronadjiPokvareneDelove(pdPretraga).contains(pd);
        	
        assertTrue(sadrzi);	
    }
    
    
    
    private void ocistiTabelu() throws Exception {
        try {
            String query = "{CALL delete_data_pokvarenideo()}";
            
            CallableStatement callableStatement = DatabaseConnection.getInstance(true).getTestConnection().prepareCall(query);
            callableStatement.execute();
            System.out.println("Uspesno obrisani podaci za pokvarenideo");
            DatabaseConnection.getInstance(true).getTestConnection().commit();

        } catch (Exception ex) {
            System.out.println("Greska pri brisanju podataka za nalog");
            ex.printStackTrace();
        }
    }

    private void ubaciPodatke() {
        //treba ubaciti vlasnika, automobil, uoceni kvar, pokvarene delove
        try {
            String query = "{CALL insert_data_pokvarenideo()}";
            
            CallableStatement callableStatement = DatabaseConnection.getInstance(true).getTestConnection().prepareCall(query);
            callableStatement.execute();
            System.out.println("Uspesno ubaceni podaci za pokvarenideo");
        } catch (Exception ex) {
            System.out.println("Greska pri ubacivanju podataka za pokvarenideoni");
            ex.printStackTrace();
        }
    }

}
