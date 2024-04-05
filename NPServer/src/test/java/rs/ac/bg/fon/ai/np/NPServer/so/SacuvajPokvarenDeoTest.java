package rs.ac.bg.fon.ai.np.NPServer.so;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.np.NPCommon.domain.DeoAutomobila;
import rs.ac.bg.fon.ai.np.NPCommon.domain.PokvareniDeo;
import rs.ac.bg.fon.ai.np.NPCommon.domain.UoceniKvar;
import rs.ac.bg.fon.ai.np.NPCommon.domain.Automobil;
import rs.ac.bg.fon.ai.np.NPServer.logic.Controller;

class SacuvajPokvarenDeoTest {

	@Test
	void executeOperationTest() throws Exception {
		PokvareniDeo pd = new PokvareniDeo();
		pd.setCena(49.9);
		
		Automobil a = new Automobil();
		a.setTablice("KG555333");
		
		UoceniKvar uk = new UoceniKvar();
		uk.setAutomobil(a);
		uk.setKvarID(1);
		pd.setUoceniKvar(uk);
		
		DeoAutomobila deo = new DeoAutomobila();
		deo.setDeoID(1);
		pd.setDeo(deo);
		
		Controller controller = new Controller();
		
		controller.sacuvajPokvarenDeo(pd);
		
		PokvareniDeo pd1 = new PokvareniDeo();
        Automobil a1 = new Automobil();
        a1.setTablice("");
        UoceniKvar uk1 = new UoceniKvar();
        uk1.setAutomobil(a1);
        pd1.setUoceniKvar(uk1);
        List<PokvareniDeo> pokvareniDelovi = controller.pronadjiPokvareneDelove(pd1);
        
        boolean sadrzi = false;
        for(PokvareniDeo pokvDeo : pokvareniDelovi) {
        	if(pokvDeo.equals(pd)) {
        		sadrzi = true;
        		break;
        	}
        }
		
        assertTrue(sadrzi);
		
	}

}
