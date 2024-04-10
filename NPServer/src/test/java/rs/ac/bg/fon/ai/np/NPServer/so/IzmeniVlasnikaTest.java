package rs.ac.bg.fon.ai.np.NPServer.so;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.np.NPCommon.domain.Vlasnik;
import rs.ac.bg.fon.ai.np.NPServer.logic.Controller;

class IzmeniVlasnikaTest {


	@Test
	void executeOperationTest() throws Exception {
		Vlasnik v = new Vlasnik();
		v.setVlasnikID(1);
		
		//nove vrednosti za vlasnika
		v.setIme("Ksenija");
		v.setPrezime("Kandic");
		v.setEmail("kena@gmail.com");
		v.setTelefon("061999000");
		
		Controller controller = new Controller();
		
		controller.izmeniVlasnika(v);
		
		
		Vlasnik v1 = new Vlasnik();
		v1.setIme("Ksenija");
		
		List<Vlasnik> vlasnici = controller.pronadjiVlasnike(v1);
		
		v1 = vlasnici.get(0);
		
		//v je vlasnik sa izmenjenim vrednostima koga menjamo u bazi
		//v1 je vlasnik koga smo dobili iz baze nakon izmene
		
		assertEquals(v.getVlasnikID(), v1.getVlasnikID());
		assertEquals(v.getIme(), v1.getIme());
		assertEquals(v.getPrezime(), v1.getPrezime());
		assertEquals(v.getEmail(), v1.getEmail());
		assertEquals(v.getTelefon(), v1.getTelefon());
	}

}
