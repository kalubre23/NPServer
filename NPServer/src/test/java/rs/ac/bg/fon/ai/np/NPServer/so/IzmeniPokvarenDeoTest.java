package rs.ac.bg.fon.ai.np.NPServer.so;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.np.NPCommon.domain.Automobil;
import rs.ac.bg.fon.ai.np.NPCommon.domain.DeoAutomobila;
import rs.ac.bg.fon.ai.np.NPCommon.domain.PokvareniDeo;
import rs.ac.bg.fon.ai.np.NPCommon.domain.UoceniKvar;
import rs.ac.bg.fon.ai.np.NPServer.logic.Controller;

class IzmeniPokvarenDeoTest {


	@Test
	void executeOperationTest() throws Exception {
		//nove vrednosti za pokvareni deo
		PokvareniDeo pd = new PokvareniDeo();
		pd.setCena(69.9);
		
		Automobil a = new Automobil();
		a.setTablice("BG123123");
		
		UoceniKvar uk = new UoceniKvar();
		uk.setAutomobil(a);
		uk.setKvarID(21);
		pd.setUoceniKvar(uk);
		
		DeoAutomobila deo = new DeoAutomobila();
		deo.setDeoID(1);
		pd.setDeo(deo);
		//stare vrednosti prema kojima nalazi red u bazi
		pd.setUsloviZaUpdate("KG555333", 20, 3);
		
		Controller controller = new Controller();
		controller.izmeniPokvarenDeo(pd);
		
		PokvareniDeo pd1 = new PokvareniDeo();
        Automobil a1 = new Automobil();
        a1.setTablice("BG123123");
        UoceniKvar uk1 = new UoceniKvar();
        uk1.setAutomobil(a1);
        pd1.setUoceniKvar(uk1);
		
		List<PokvareniDeo> pokvareniDelovi = controller.pronadjiPokvareneDelove(pd1);
		
		PokvareniDeo pd2 = pokvareniDelovi.get(0);
		
		//pd2 je pokvareni deo koji je vracen iz baze
		//pd je pokvareni deo sa izmenjenim vrednostima nad kojim pozivamo sistemsku operaciju
		assertEquals(pd.getCena(), pd2.getCena());
		assertEquals(pd.getDeo(), pd2.getDeo());
		assertEquals(pd.getUoceniKvar(), pd2.getUoceniKvar());
	
	}

}
