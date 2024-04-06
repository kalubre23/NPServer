package rs.ac.bg.fon.ai.np.NPServer.so;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.np.NPCommon.domain.Automobil;
import rs.ac.bg.fon.ai.np.NPCommon.domain.Marka;
import rs.ac.bg.fon.ai.np.NPCommon.domain.UoceniKvar;
import rs.ac.bg.fon.ai.np.NPServer.logic.Controller;


/**
 * @author Asus
 *
 */
public class SacuvajAutomobilTest {
	

	@Test
	void executeOperationTest() throws Exception{
		Automobil auto = new Automobil();
		auto.setTablice("KG555333");
		auto.setGodiste(2000);
		auto.setImePrezimeVlasnika("Zika Zikic");
		
		Marka marka = new Marka();
		marka.setMarkaID(3);
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
		
		Controller controller = new Controller();
		controller.sacuvajAutomobil(auto);
		
		//controller.pronadjiAutomobile() funkcija pretrazuje automobile prema imenu i prezimenu vlasnika
		//Ako joj posaljemo ovako inicijalizovan automobil, vratice sve automobile iz baze
		Automobil a1 = new Automobil();
		a1.setImePrezimeVlasnika("");
	
		List<Automobil> sviAutomobili = controller.pronadjiAutomobile(a1);
		
		boolean sadrziAutomobil = false;
		for(Automobil a : sviAutomobili) {
			if(a.equals(auto)) {
				sadrziAutomobil = true;
				break;
			}
		}
		
		assertTrue(sadrziAutomobil);
	}
	

}
