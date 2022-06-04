package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import coordenada.Coordenada;
import road_fighter.objects.Auto;
import usuario.Usuario;

public class TestAuto {
	
	Auto auto = new Auto(new Usuario("seba", "1234"), 0);

	@Test
	public void testMoverUp() {
		auto.setDirectionUpSpeed1(true);
		assertTrue(auto.getUbicacion().equals(new Coordenada(0, 45)));
	}

	@Test
	public void testMoverRight() {
		auto.setDirectionRight(true);
		assertTrue(auto.getUbicacion().equals(new Coordenada(0, 15)));
	}

	@Test
	public void testMoverLeft() {
		auto.setDirectionLeft(true);
		assertTrue(auto.getUbicacion().equals(new Coordenada(0, 60)));
	}

	@Test
	public void testPerderControl() throws InterruptedException {
//		auto.setVelocidad(200);
		auto.perderControl();
		assertEquals(0, auto.getVelocidad());
	}

}
