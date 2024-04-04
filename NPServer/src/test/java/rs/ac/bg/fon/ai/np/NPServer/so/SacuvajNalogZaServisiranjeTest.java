package rs.ac.bg.fon.ai.np.NPServer.so;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.np.NPCommon.domain.Automobil;
import rs.ac.bg.fon.ai.np.NPCommon.domain.NalogZaServisiranje;
import rs.ac.bg.fon.ai.np.NPCommon.domain.Serviser;
import rs.ac.bg.fon.ai.np.NPCommon.domain.UoceniKvar;
import rs.ac.bg.fon.ai.np.NPServer.logic.Controller;

class SacuvajNalogZaServisiranjeTest {


	@Test
	void executeOperationTest() {
		NalogZaServisiranje n = new NalogZaServisiranje();
		n.setCena(500);
		n.setDatum(LocalDate.now());
		
		Automobil a1 = new Automobil();
		a1.setTablice("KG555333");
		
		UoceniKvar uk = new UoceniKvar();
		uk.setKvarID(1);
		uk.setAutomobil(a1);
		n.setKvar(uk);
		
		Serviser s = new Serviser();
		s.setServiserID(1);
		n.setServiser(s);
		
		Controller controller = new Controller();
		try {
			controller.sacuvajNalogZaServisiranje(n);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//controller.pronadjiNaloge...() funkcija pretrazuje naloge prema broju tablica
		//Ako joj posaljemo ovako inicijalizovan nalog, vratice sve naloge iz baze
		Automobil a = new Automobil();
        a.setTablice("");
        UoceniKvar uk1 = new UoceniKvar();
        uk1.setAutomobil(a);
        NalogZaServisiranje n1 = new NalogZaServisiranje();
        n1.setKvar(uk);
        
        List<NalogZaServisiranje> nalozi = new ArrayList<>();
        try {
			nalozi = controller.pronadjiNalogeZaServisiranje(n1);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        boolean sadrzi = false;
        for(NalogZaServisiranje nalog : nalozi) {
        	if(nalozi.contains(n)) {
        		sadrzi = true;
        		break;
        	}
        }
        
        assertTrue(sadrzi);
		
	}

}
