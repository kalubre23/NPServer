package rs.ac.bg.fon.ai.np.NPServer.so;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.np.NPCommon.domain.Vlasnik;
import rs.ac.bg.fon.ai.np.NPServer.logic.Controller;

class SacuvajVlasnikaTest {

	@Test
	void executeOperationTest() throws Exception {
		Vlasnik v = new Vlasnik();
		v.setIme("Zika");
		v.setPrezime("Zikic");
		v.setEmail("zika@gmail.com");
		v.setTelefon("069969858");
		
		Controller controller = new Controller();
		controller.sacuvajVlasnika(v);
		
		Vlasnik v1 = new Vlasnik();
		v1.setIme("");
		
		//controller.pronadjiVlasnike() funkcija pretrazuje prema imenu vlasnika
		//Ako joj posaljemo ovako inicijalizovanog vlasnika, vratice sve vlasnike iz baze
		List<Vlasnik> vlasnici = controller.pronadjiVlasnike(v1);
		
		boolean sadrzi = false;
		for(Vlasnik vlasnik : vlasnici) {
			if(vlasnik.equals(v)) {
				sadrzi = true;
				break;
			}
		}
		
		assertTrue(sadrzi);
		
	}

}
