package rs.ac.bg.fon.ai.np.NPServer.so;

import java.sql.CallableStatement;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.np.NPCommon.domain.Automobil;
import rs.ac.bg.fon.ai.np.NPCommon.domain.DeoAutomobila;
import rs.ac.bg.fon.ai.np.NPCommon.domain.PokvareniDeo;
import rs.ac.bg.fon.ai.np.NPCommon.domain.UoceniKvar;
import rs.ac.bg.fon.ai.np.NPServer.db.DatabaseConnection;
import rs.ac.bg.fon.ai.np.NPServer.logic.Controller;

class IzmeniPokvarenDeoTest {
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
        //nove cena za pokvareni deo
        PokvareniDeo pd = new PokvareniDeo();
        pd.setCena(69.9);

        pd.setUsloviZaUpdate("BG333222", 1, 1);

        controller.izmeniPokvarenDeo(pd);

        PokvareniDeo pd1 = new PokvareniDeo();
        Automobil a1 = new Automobil();
        a1.setTablice("BG333222");
        UoceniKvar uk1 = new UoceniKvar();
        uk1.setAutomobil(a1);
        pd1.setUoceniKvar(uk1);
		
        List<PokvareniDeo> pokvareniDelovi = controller.pronadjiPokvareneDelove(pd1);

        PokvareniDeo pd2 = pokvareniDelovi.get(0);

        //pd2 je pokvareni deo koji je vracen iz baze
        //pd je pokvareni deo sa izmenjenim vrednostima nad kojim pozivamo sistemsku operaciju
        assertEquals(pd.getCena(), pd2.getCena());
        
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
