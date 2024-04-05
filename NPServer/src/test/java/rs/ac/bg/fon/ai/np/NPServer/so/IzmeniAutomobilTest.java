package rs.ac.bg.fon.ai.np.NPServer.so;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.np.NPCommon.domain.Automobil;
import rs.ac.bg.fon.ai.np.NPCommon.domain.Marka;
import rs.ac.bg.fon.ai.np.NPCommon.domain.UoceniKvar;
import rs.ac.bg.fon.ai.np.NPServer.logic.Controller;

class IzmeniAutomobilTest {

	@Test
	void executeOperationTest() throws Exception {
		//Izmenicemo automobil sa tablicama KG555333 koji vec postoji u bazi
		Automobil a = new Automobil();
		a.setTablice("KG555333");
		
		//Izmenjene vrednosti
		a.setGodiste(2005);
		a.setImePrezimeVlasnika("Petar Petrovic");
		Marka marka = new Marka();
		marka.setMarkaID(2);
		a.setMarka(marka);
		
		UoceniKvar uk1 = new UoceniKvar();
		uk1.setOpis("Kvar kvacila");
		uk1.setAutomobil(a);
		
		List<UoceniKvar> uoceniKvarovi = new ArrayList<>();
		uoceniKvarovi.add(uk1);
		a.setUoceniKvarovi(uoceniKvarovi);
		
		Controller controller = new Controller();
		controller.izmeniAutomobil(a);
		
		Automobil a1 = new Automobil();
		a1.setImePrezimeVlasnika("Petar Petrovic");
	
		
		List<Automobil> sviAutomobili = controller.pronadjiAutomobile(a1);
		
		a1 = sviAutomobili.get(0);
		
		//a1 je automobil koji je vracen iz baze
		//a je automobil sa izmenjenim vrednostima nad kojim pozivamo sistemsku operaciju
		assertEquals(a1.getMarka(), a.getMarka());
		assertEquals(a1.getGodiste(), a.getGodiste());
		assertEquals(a1.getImePrezimeVlasnika(), a.getImePrezimeVlasnika());
		assertEquals(a1.getMarka(), a.getMarka());
		assertEquals(a1.getUoceniKvarovi(), a.getUoceniKvarovi());
		
	}

}
