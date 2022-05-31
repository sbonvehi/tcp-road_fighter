package road_fighter.objects;

import java.util.concurrent.TimeUnit;

import animation.SpriteAnimation;
import colision.colisionObstaculo;
import coordenada.Coordenada;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import road_fighter.Config;
import road_fighter.interfaces.Actualizable;
import road_fighter.interfaces.Colisionable;
import road_fighter.interfaces.Colisionador;
import road_fighter.interfaces.Renderizable;
import road_fighter.utils.GameObject;
import usuario.Usuario;

public class Auto extends GameObject implements Actualizable, Renderizable, Colisionador {

	
	private static int cantAutos = 0;
	
	private static final int VELOCIDAD_INICIAL = 0;
	private final int VELOCIDAD_MAX1 = 200;
	private final int VELOCIDAD_MAX2 = 400;
	private final int TASA_ACELERACION = 2;
	private final int TASA_FRENADO = 1;
	private final int VEL_HORIZONTAL = 300; 
	

	private Image spriteImages;
    private SpriteAnimation crash1;
    private SpriteAnimation crash2;
    private SpriteAnimation crash3;
	
	public static final int posXAutoInicial = 297;
	public static final int posYAutoInicial = 650;
	private boolean flagFueraDeMapa = false;

	private int topeVelocidad = VELOCIDAD_MAX1;
	private static double velocidad = VELOCIDAD_INICIAL;

	private Coordenada ubicacion;
	private Usuario piloto;

	private boolean directionLeft = false;
	private boolean directionRight = false;
	private boolean directionUpSpeed1 = false;
	private boolean directionUpSpeed2 = false;

	private Rectangle collider;
	private ImageView render;
	private Image spriteNormal;
	private boolean tieneModificadorVelocidad = false;
	private boolean dead;

	public static double getVelocidad() {
		return velocidad;
	}
	
	public Auto(Usuario piloto) {
//		render.setViewport(new Rectangle2D(3, 3, 14*3, 19*3));
		
		collider = new Rectangle(Config.ANCHO_AUTO, Config.ANCHO_AUTO);
		collider.setFill(Color.DARKBLUE);
		collider.setStroke(Color.FUCHSIA);

		
		cantAutos++;
		this.ubicacion = new Coordenada(posXAutoInicial, 0);
		this.piloto = piloto;

		spriteNormal = new Image(Config.CAR_IMG, Config.ANCHO_AUTO, Config.ALTO_AUTO, false, false);
		render = new ImageView(spriteNormal);
		render.setViewport(new Rectangle2D(0, 0, Config.ANCHO_AUTO, Config.ALTO_AUTO));
		render.setViewOrder(5);
		
		///ubicacion inicial
		render.setX(posXAutoInicial);
		render.setY(posYAutoInicial);
		collider.setX(posXAutoInicial);
		collider.setY(posYAutoInicial);

		

		
		
	}
	
	public Node getRender() {
		return render;
	}

	@Override
	public void colisionar(Colisionable colisionable) {		
		if(colisionable.getClass() == Enemy.class) {
			Auto.velocidad /= 2;
			System.out.println("choque contra auto NPC");
		}
		///Si "colisiono" con la calle es que estoy bien, si dejo de colisionar entonces me fui del mapa
		if(colisionable.getClass() == Background.class) {
			flagFueraDeMapa = false;
		}
		
		if(colisionable.getClass() == FinishLine.class) {
			Auto.velocidad = 0;
			System.out.println("GANASTE PAPUU");
			///aca habria que llamar a una funcion para que termine la partida / saque al auto de la competencia
		}
		
		if(colisionable.getClass() == ColisionPowerUp.class) {
//			Auto.velocidad = 0;
			this.aumentarVelocidadPowerUp();
			System.out.println("COLISION CON POWERUP");
		}
		if(colisionable.getClass() == ColisionObstaculo.class) {
//			Auto.velocidad = 0;
//			this.aumentarVelocidadPowerUp();
			System.out.println("COLISION CON OBSTACULO");
		}
		
	}
	
	public Coordenada getUbicacion() {
		return ubicacion;
	}

	public Usuario getPiloto() {
		return piloto;
	}

		
	//esto seria como die()?  si es asi, lo podemos sacar
	public void perderControl() throws InterruptedException {

		while (velocidad > 1) {
			velocidad *= 0.3;
			TimeUnit.SECONDS.sleep(1);

//			if(golpe margen) {
//				auto.resetearPosicion();
//			}
		}
		// no explote..
		velocidad = 0;
	}

	public void reducirVelocidadPowerUp() {
		tieneModificadorVelocidad = true;
		topeVelocidad -= 200;
		
	}

	public void aumentarVelocidadPowerUp() {
		tieneModificadorVelocidad = true;
		topeVelocidad = VELOCIDAD_MAX2 + 200;
		
		System.out.println("antes del delay");
		
		new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		        		topeVelocidad = VELOCIDAD_MAX2;
		        		Auto.velocidad = topeVelocidad;
		        		tieneModificadorVelocidad = false;
		            }
		        }, 
		        10000);
	}
	
	public void die() {
		setX(posXAutoInicial);
		Auto.velocidad = VELOCIDAD_INICIAL;
		
//		Image spriteBoom = new Image(Config.CRASH_CAR, Config.ANCHO_AUTO, Config.ALTO_AUTO, false, false);
//		render = new ImageView(spriteBoom);
//		render.setViewport(new Rectangle2D(0, 0, Config.ANCHO_AUTO, Config.ALTO_AUTO));
////
//		TranslateTransition explotarEnElLugar = new TranslateTransition(Duration.millis(500), render);
//		explotarEnElLugar.setToX(300);
//		explotarEnElLugar.play();

		System.out.println("Mori");

//		this.dead = true;
//		resetearRender();

	}

	public void setX(double x) {
		this.ubicacion.setX(x);
		render.setX(x);
		collider.setX(x);
		
	}

	public void setY(double y) {
		this.ubicacion.setY(y); ///esta es la unica Y que se va cambiar, porque es la que determina que tan avanzado esta un auto respecto desde que arrancó la carrera
//		
//		System.out.println(ubicacion.toString());
//		System.out.println("render player:" + render.getX() + " " + render.getY());
//		System.out.println("collider: " + collider.toString());
	}

	public void update(double deltaTime) { 
		if(flagFueraDeMapa) {
			die();
//			System.out.println("me fui del mapa :(");
		}
		
		///acelerando mientras no me pase del limite
		flagFueraDeMapa = true;
		
		if((directionUpSpeed1 || directionUpSpeed2) && velocidad < topeVelocidad) {
			velocidad += TASA_ACELERACION;			
		}
		
		///si estoy en la primer velocidad y vengo de la segunda velocidad, desacelero
		//ó si no estoy tocando para acelerar, desacelero
		if( (directionUpSpeed1 && velocidad >= topeVelocidad) ||
			(!directionUpSpeed1 && !directionUpSpeed2)) {
			velocidad -= TASA_FRENADO;
		}
		
		///para que la velocidad no sea negativa
		if(velocidad < VELOCIDAD_INICIAL) {
			velocidad = VELOCIDAD_INICIAL;
		}


		int direction = directionLeft ? -1 : (directionRight ? 1 : 0);
		setX( this.ubicacion.getX() + direction * VEL_HORIZONTAL * deltaTime);
		setY( this.ubicacion.getY() + Auto.velocidad * deltaTime);
	}

	public void setDirectionRight(boolean b) {
		this.directionRight = b;
	}

	public void setDirectionLeft(boolean b) {
		this.directionLeft = b;
	}

	public void setDirectionUpSpeed1(boolean b) {
		if(!tieneModificadorVelocidad) {
			topeVelocidad = VELOCIDAD_MAX1;				
		}
		this.directionUpSpeed1 = b;
	}

	public void setDirectionUpSpeed2(boolean b) {
		if(!tieneModificadorVelocidad) {			
			topeVelocidad = VELOCIDAD_MAX2;
		}
		this.directionUpSpeed2 = b;
	}



	// reseteo la posicion en el origen despues de explotar..
	// no seria como lo mismo que die()?
	public void resetearRender() {
		
		this.dead = false;
		ubicacion.setX(posXAutoInicial);
		ubicacion.setY(posYAutoInicial);
		render = new ImageView(spriteNormal);
		render.setViewport(new Rectangle2D(0, 0, Config.ANCHO_AUTO, Config.ALTO_AUTO));
		render.relocate(-Config.ANCHO_AUTO / 2, -Config.ALTO_AUTO / 2); // render en la mitad del auto..

		setX(posXAutoInicial);
		setY(posYAutoInicial);

	}

	private void restablecerViewPort() {
        render.setViewport(new Rectangle2D(3, 3, 14*3, 19*3));
    }
    private void initImages() {
//        spriteNormal = new Image(Config.CAR_IMG, Config.ANCHO_AUTO, Config.ALTO_AUTO, false, false);
        spriteImages = new Image(Config.GENERAL_SPRITES_IMG, 328 * 3, 179 * 3, false, false);
    }
    
    private void initSpriteAnimations() { ///estan bien cargados los sprites.        
        crash1 = new SpriteAnimation(render,Duration.millis(200), 3, 3, 15 * 3, 34 * 3, 0, 14*3, 19*3);
//        crash1.play();
        crash2 = new SpriteAnimation(render,Duration.millis(200), 3, 3, 30 * 3, 34 * 3, 0, 15*3, 19*3);
//        crash2.play();
        crash3 = new SpriteAnimation(render,Duration.millis(200), 3, 3, 49 * 3, 34 * 3, 0, 14*3, 19*3); 
//        crash3.play();
    }
	
	@Override
	public Shape getCollider() {
		return collider;
	}

	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}