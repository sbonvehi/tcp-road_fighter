package test;

import static org.junit.Assert.*;

import org.junit.Test;

import mapa.Ele;
import mapa.ElementoMapa;
import mapa.Mapa;
import mapa.Obstaculo;
import mapa.PowerUp;

public class TestMapa {

	@Test
	public void crearMapaYObstaculo() {
		
		Mapa mapa1 = new Mapa(20, 100);
		ElementoMapa obstaculo1 = new Obstaculo(15, 50);
		
		mapa1.agregarElemento(obstaculo1);
		assertEquals(1, mapa1.getElementosMapa().size());
	}
	
	@Test
	public void crearMapaYPowerUp() {
		
		Mapa mapa1 = new Mapa(20, 100);
		ElementoMapa powerUp = new PowerUp(15, 50);
		ElementoMapa powerUp2 = new PowerUp(5, 10);
		ElementoMapa powerUp3 = new PowerUp(8, 50);
		ElementoMapa powerUp4 = new PowerUp(10, 80);
		
		mapa1.agregarElemento(powerUp);
		mapa1.agregarElemento(powerUp2);
		mapa1.agregarElemento(powerUp3);
		mapa1.agregarElemento(powerUp4);
		assertEquals(4, mapa1.getElementosMapa().size());
	}
	
	
	
	
	
	
	
}
