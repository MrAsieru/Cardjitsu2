package cardjitsu;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JokalariaTest {
	
	Jokalaria j1,j2;
	ListaKartak gk,jk;
	Karta k1,k2,k3;
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		KartaSorta.getKartaSorta().jsonetikKartetara();
	}

	@Before
	public void setUp() throws Exception {
		j1=  JokalariaLokala.getNireJokalaria("Lorena");
		jk=new ListaKartak();
		gk=new ListaKartak();
		k1=KartaSorta.getKartaSorta().getKarta();
		k2=KartaSorta.getKartaSorta().getKarta();
		k3=KartaSorta.getKartaSorta().getKarta();
	}

	@After
	public void tearDown() throws Exception {
		j1=null;
	}

	@Test
	public void testJokalaria() {
		assertNotNull(j1);
	}

	@Test
	public void testGehituJolastekoKarta() {
		assertEquals(j1.jolastekoKartenKantitatea(),0);
		j1.gehituJolastekoKarta(k1);
		assertEquals(j1.jolastekoKartenKantitatea(),1);
		j1.gehituJolastekoKarta(k3);
		j1.gehituJolastekoKarta(k2);
		assertEquals(j1.jolastekoKartenKantitatea(),3);
	}

	@Test
	public void testKenduJolastekoKarta() {
		assertEquals(j1.jolastekoKartenKantitatea(),0);
		j1.gehituJolastekoKarta(k1);
		assertEquals(j1.jolastekoKartenKantitatea(),1);
		j1.gehituJolastekoKarta(k3);
		j1.gehituJolastekoKarta(k2);
		assertEquals(j1.jolastekoKartenKantitatea(),3);	
		j1.kenduJolastekoKarta(k2);
		assertEquals(j1.jolastekoKartenKantitatea(),2);	
		j1.kenduJolastekoKarta(k1);
		j1.kenduJolastekoKarta(k3);
		assertEquals(j1.jolastekoKartenKantitatea(),0);
	}

	@Test
	public void testGehituGordetakoKarta() {
		assertEquals(j1.gordetakoKartenKantitatea(),0);
		j1.gehituGordetakoKarta(k1);
		j1.gehituGordetakoKarta(k2);
		assertEquals(j1.gordetakoKartenKantitatea(),2);
	}

	@Test
	public void testKenduGordetakoKarta() {
		assertEquals(j1.gordetakoKartenKantitatea(),0);
		j1.gehituGordetakoKarta(k1);
		j1.gehituGordetakoKarta(k2);
		assertEquals(j1.gordetakoKartenKantitatea(),2);
		j1.kenduGordetakoKarta(k2);
		assertEquals(j1.gordetakoKartenKantitatea(),1);
	}

	@Test
	public void testLortuJolastekoKartaPosz() {
		j1.gehituJolastekoKarta(k1);
		j1.gehituJolastekoKarta(k2);
		assertEquals(j1.lortuJolastekoKartaPosz(1),k1);
		assertEquals(j1.lortuJolastekoKartaPosz(2),k2);
	}

	@Test
	public void testLortuGordetakoKartaPosz() {
		j1.gehituGordetakoKarta(k3);
		j1.gehituGordetakoKarta(k2);
		assertEquals(j1.lortuGordetakoKartaPosz(1),k3);
		j1.kenduGordetakoKarta(k3);
		assertEquals(j1.lortuGordetakoKartaPosz(1),k2);
	}

	@Test
	public void testGordetakoKartenKantitatea() {
		assertEquals(j1.gordetakoKartenKantitatea(),0);
		j1.gehituGordetakoKarta(k3);
		j1.gehituGordetakoKarta(k1);
		assertEquals(j1.gordetakoKartenKantitatea(),2);
		j1.kenduGordetakoKarta(k3);
		assertEquals(j1.gordetakoKartenKantitatea(),1);
	}

	@Test
	public void testJolastekoKartenKantitatea() {
		assertEquals(j1.jolastekoKartenKantitatea(),0);
		j1.gehituGordetakoKarta(k2);
		assertEquals(j1.gordetakoKartenKantitatea(),1);
	}

	@Test
	public void testGetIzena() {
		assertEquals(j1.getIzena(),"Lorena");
	}

	@Test
	public void testKartaAukeratu() {
		fail("Not yet implemented");
	}

}
