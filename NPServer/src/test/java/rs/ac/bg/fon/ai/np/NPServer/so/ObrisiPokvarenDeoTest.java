package rs.ac.bg.fon.ai.np.NPServer.so;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.np.NPCommon.domain.Automobil;
import rs.ac.bg.fon.ai.np.NPCommon.domain.PokvareniDeo;
import rs.ac.bg.fon.ai.np.NPCommon.domain.UoceniKvar;
import rs.ac.bg.fon.ai.np.NPServer.logic.Controller;

class ObrisiPokvarenDeoTest {

	@Test
	void executeOperationTest() throws Exception {
		PokvareniDeo pd = new PokvareniDeo();
		pd.setCena(69.9);
		pd.setUsloviZaUpdate("BG123123", 21, 1);
		
		Controller controller = new Controller();
		
		controller.obrisiPokvarenDeo(pd);
		
		PokvareniDeo pd1 = new PokvareniDeo();
        Automobil a1 = new Automobil();
        a1.setTablice("");
        UoceniKvar uk1 = new UoceniKvar();
        uk1.setAutomobil(a1);
        pd1.setUoceniKvar(uk1);
        List<PokvareniDeo> pokvareniDelovi = controller.pronadjiPokvareneDelove(pd1);
        
        assertFalse(pokvareniDelovi.contains(pd));
	}

}
