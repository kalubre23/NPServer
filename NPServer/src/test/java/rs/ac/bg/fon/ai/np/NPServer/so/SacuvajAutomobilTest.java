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


/**
 * @author Asus
 *
 */
public class SacuvajAutomobilTest {
	
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
    void executeOperationTest() throws Exception{
        Automobil auto = new Automobil();
        auto.setTablice("KG555333");
        auto.setGodiste(2000);
        
        Vlasnik v = new Vlasnik();
        v.setVlasnikID(1);
        auto.setVlasnik(v);

        Marka marka = new Marka();
        marka.setMarkaID(1);
        auto.setMarka(marka);

        UoceniKvar uk1 = new UoceniKvar();
        uk1.setOpis("Kvar motora");
        uk1.setAutomobil(auto);
        UoceniKvar uk2 = new UoceniKvar();
        uk2.setOpis("Kvar brisaca");
        uk2.setAutomobil(auto);

        List<UoceniKvar> uoceniKvarovi = new ArrayList<>();
        uoceniKvarovi.add(uk1);
        uoceniKvarovi.add(uk2);
        auto.setUoceniKvarovi(uoceniKvarovi);
        controller.sacuvajAutomobil(auto);

        //controller.pronadjiAutomobile() funkcija pretrazuje automobile prema tablicama
        //Ako joj posaljemo ovako inicijalizovan automobil, vratice sve automobile iz baze
        Automobil a1 = new Automobil();
        a1.setTablice("");

        boolean sadrziAutomobil = controller.pronadjiAutomobile(a1).contains(auto);
        //assertTrue(controller.pronadjiAutomobile(a1).isEmpty());
        assertTrue(sadrziAutomobil);


    }

    private void ocistiTabelu() throws Exception {
        try {
            String query = "{CALL delete_data_auto()}";
            
            CallableStatement callableStatement = DatabaseConnection.getInstance(true).getTestConnection().prepareCall(query);
            callableStatement.execute();
            System.out.println("Uspesno obrisani podaci za vlasnika");
            DatabaseConnection.getInstance(true).getTestConnection().commit();

        } catch (Exception ex) {
            System.out.println("Greska pri brisanju podataka za automobil");
            ex.printStackTrace();
        }
    }

    private void ubaciPodatke() {
        //treba ubaciti vlasnika, automobil, uoceni kvar, pokvarene delove
        try {
            String query = "{CALL insert_data_vlasnik()}";
            
            CallableStatement callableStatement = DatabaseConnection.getInstance(true).getTestConnection().prepareCall(query);
            callableStatement.execute();
            System.out.println("Uspesno ubaceni podaci za automobil");
        } catch (Exception ex) {
            System.out.println("Greska pri ubacivanju podataka za automobil");
            ex.printStackTrace();
        }
    }

}
