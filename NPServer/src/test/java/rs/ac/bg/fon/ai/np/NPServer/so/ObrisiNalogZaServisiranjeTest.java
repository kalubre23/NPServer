package rs.ac.bg.fon.ai.np.NPServer.so;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.np.NPCommon.domain.Automobil;
import rs.ac.bg.fon.ai.np.NPCommon.domain.NalogZaServisiranje;
import rs.ac.bg.fon.ai.np.NPCommon.domain.UoceniKvar;
import rs.ac.bg.fon.ai.np.NPServer.logic.Controller;

class ObrisiNalogZaServisiranjeTest {

	@Test
	void executeOperationTest() throws Exception {
		NalogZaServisiranje n = new NalogZaServisiranje();
		n.setCena(500);
		n.setNalogID(6);

		
		Controller controller = new Controller();
		controller.obrisiNalog(n);
		
		//controller.pronadjiNaloge...() funkcija pretrazuje naloge prema broju tablica
		//Ako joj posaljemo ovako inicijalizovan nalog, vratice sve naloge iz baze
		Automobil a = new Automobil();
        a.setTablice("");
        UoceniKvar uk1 = new UoceniKvar();
        uk1.setAutomobil(a);
        NalogZaServisiranje n1 = new NalogZaServisiranje();
        n1.setKvar(uk1);
        
        List<NalogZaServisiranje> nalozi = controller.pronadjiNalogeZaServisiranje(n1);
        
        assertFalse(nalozi.contains(n1));
	}

}
