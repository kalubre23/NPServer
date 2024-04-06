package rs.ac.bg.fon.ai.np.NPServer.so;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.np.NPCommon.domain.Automobil;
import rs.ac.bg.fon.ai.np.NPServer.logic.Controller;

class ObrisiAutomobilTest {

	@Test
	void executeOperationTest() throws Exception {
		Automobil auto = new Automobil();
		auto.setTablice("KG555333");
		
		Controller controller = new Controller();
		controller.obrisiAutomobil(auto);
		
		//controller.pronadjiAutomobile() funkcija pretrazuje automobile prema imenu i prezimenu vlasnika
		//Ako joj posaljemo ovako inicijalizovan automobil, vratice sve automobile iz baze
		Automobil a1 = new Automobil();
		a1.setImePrezimeVlasnika("");
	
		List<Automobil> sviAutomobili = controller.pronadjiAutomobile(a1);
		
		assertFalse(sviAutomobili.contains(auto));
	}

}
