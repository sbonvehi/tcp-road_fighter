package road_fighter.objects;

import animation.SpriteAnimation;
import coordenada.Coordenada;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import road_fighter.Config;
import road_fighter.GameSceneHandler;
import road_fighter.interfaces.Actualizable;
import road_fighter.interfaces.Colisionable;
import road_fighter.interfaces.Colisionador;
import road_fighter.interfaces.Renderizable;
import road_fighter.utils.AudioResources;
import road_fighter.utils.GameObject;
import usuario.Usuario;

public class Auto extends GameObject implements Actualizable, Renderizable, Colisionador {

	private static int cantAutos = 0;

	private static final double VELOCIDAD_INICIAL = 0.0000000001;
	private final int VELOCIDAD_MAX1 = 200;
	private final int VELOCIDAD_MAX2 = 400;
	private final double TASA_ACELERACION1 = 1.5;
	private final double TASA_ACELERACION2 = 0.9;
	private final double TASA_FRENADO = 0.8;
	private final int VEL_HORIZONTAL = 300;

	private Image spriteImages;
	private SpriteAnimation crash;
	private Image imageLostControl;
//	private ImageView renderLostControl;
	private SpriteAnimation lostControlSpriteLeft;
	private SpriteAnimation lostControlSpriteRight;
	private int multiplic = 3;
	private int anchoAuto = 14;
	private int altoAuto = 19;
	private int espaciador = 2;
	private int anchoImagen = 328;
	private int altoImagen = 179;

	public static final int posXAutoInicial = 270;
	public static final int posYAutoInicial = 650;
	private boolean flagFueraDeMapa = false;

	private int topeVelocidad = VELOCIDAD_MAX1;
	private double aceleracion = TASA_ACELERACION1;
	private static double velocidad = VELOCIDAD_INICIAL;

	private static Coordenada ubicacion;
	private Usuario piloto;

	private boolean directionLeft = false;
	private boolean directionRight = false;
	private boolean directionUpSpeed1 = false;
	private boolean directionUpSpeed2 = false;

	private Rectangle collider;
	private ImageView render;
	private boolean dead;
	private boolean perdiElControl;
	private boolean ultimaDireccionRight;
	private boolean noEstoyAcelerando;
	private boolean tienePowerUp = false;

	private AudioClip driveAudio;
	private AudioClip skidAudio;
	private AudioClip explosionAudio;
	private AudioClip powerUpAudio;
	private boolean colisioneConObstaculo;

	public static double getVelocidad() {
		return velocidad;
	}

	public Auto(Usuario piloto) {
		collider = new Rectangle(Config.ANCHO_AUTO, Config.ANCHO_AUTO);
		collider.setFill(Color.DARKBLUE);
		collider.setStroke(Color.FUCHSIA);

		cantAutos++;
		Auto.ubicacion = new Coordenada(posXAutoInicial, 0);
		this.piloto = piloto;

		spriteImages = new Image(Config.GENERAL_SPRITES_IMG, anchoImagen * multiplic, altoImagen * multiplic, false,
				false);
		imageLostControl = new Image(Config.LOST_CONTROL_SPRITES_IMG, 328 * multiplic, 40 * multiplic, false, false);
		render = new ImageView(spriteImages);
		render.setViewport(new Rectangle2D(espaciador, espaciador, anchoAuto * multiplic, altoAuto * multiplic));
		render.setViewOrder(5);

		initSpriteAnimations();
		initAudios();
		/// ubicacion inicial
		render.setX(posXAutoInicial);
		render.setY(posYAutoInicial);
		collider.setX(posXAutoInicial);
		collider.setY(posYAutoInicial);
	}

	private void initSpriteAnimations() { /// estan bien cargados los sprites.
		crash = new SpriteAnimation(render, Duration.millis(1000), 3, 3, 41 * 3, 34 * 3, 3 * 3, 14 * 3, 19 * 3);
		crash.setCycleCount(Animation.INDEFINITE);
	}

	private void initAudios() {
		driveAudio = AudioResources.getDriveAudio();
		driveAudio.setVolume(0.1);
		driveAudio.setCycleCount(AudioClip.INDEFINITE);
		skidAudio = AudioResources.getSkidAudio();
		skidAudio.setVolume(0.15);
		skidAudio.setCycleCount(AudioClip.INDEFINITE);
		explosionAudio = AudioResources.getExplosionAudio();
		explosionAudio.setVolume(0.3);
		powerUpAudio = AudioResources.getPowerUpAudio();
		powerUpAudio.setVolume(0.2);
		
		
	}

	private void resetViewPort() {
		render.setViewport(new Rectangle2D(espaciador, espaciador, anchoAuto * multiplic, altoAuto * multiplic));
	}

	public Node getRender() {
		return render;
	}

	
	
	@Override
	public void colisionar(Colisionable colisionable) {
		if (colisionable.getClass() == Enemy.class && !perdiElControl) {
			perderControl();
			System.out.println("choque contra auto NPC");
		}			
		
		/// Si "colisiono" con la calle es que estoy bien, si dejo de colisionar
		/// entonces me fui del mapa
		if (colisionable.getClass() == Background.class) {
			flagFueraDeMapa = false;
		}

		if (colisionable.getClass() == FinishLine.class) {
			Auto.velocidad = 0;
			GameSceneHandler.apagarMusica();
			
			
		}

		if (colisionable.getClass() == ColisionPowerUp.class) {
			this.aumentarVelocidadPowerUp();
			if (!colisioneConObstaculo) {
				colisioneConObstaculo = true;
				powerUpAudio.play();
				new java.util.Timer().schedule(new java.util.TimerTask() {
					@Override
					public void run() {
//						powerUpAudio.stop();
						colisioneConObstaculo = false;
					}
				}, 800);
				System.out.println("COLISION CON POWER-UP");
			}
		}
		
		
		if (colisionable.getClass() == ColisionObstaculo.class) {
			this.reducirVelocidadObstaculo();
			if (!colisioneConObstaculo) {
				colisioneConObstaculo = true;
				skidAudio.play();
				new java.util.Timer().schedule(new java.util.TimerTask() {
					@Override
					public void run() {
						skidAudio.stop();
						colisioneConObstaculo = false;
					}
				}, 800);
				System.out.println("COLISION CON OBSTACULO");
			}
		}

	}

	public static Coordenada getUbicacion() {
		return ubicacion;
	}

	public Usuario getPiloto() {
		return piloto;
	}

	public void perderControl() {
		if (!perdiElControl) {
			Auto.velocidad /= 2;
			perdiElControl = true;
			skidAudio.play();
			render.setImage(imageLostControl);

			if (ultimaDireccionRight) {
				lostControlSpriteLeft = new SpriteAnimation(render, Duration.millis(1000), 13, 13, 0, 2 * 3, 2 * 3,
						15 * 3, 18 * 3);
				lostControlSpriteLeft.setCycleCount(Animation.INDEFINITE);
			} else {
				lostControlSpriteRight = new SpriteAnimation(render, Duration.millis(1000), 13, 13, 0, 20 * 3, 2 * 3,
						15 * 3, 18 * 3);
				lostControlSpriteRight.setCycleCount(Animation.INDEFINITE);
			}
			SpriteAnimation sprite = ultimaDireccionRight ? lostControlSpriteLeft : lostControlSpriteRight;

			setDirectionRight(false);
			setDirectionLeft(false);
			setDirectionUpSpeed1(false);
			setDirectionUpSpeed2(false);

			sprite.play();

			new java.util.Timer().schedule(new java.util.TimerTask() {
				@Override
				public void run() {
					sprite.stop();
					render.setImage(spriteImages);
					resetViewPort();
					perdiElControl = false;
					skidAudio.stop();
				}
			}, 1100);
		}
	}

	public void reducirVelocidadObstaculo() {
		Auto.velocidad /= 1.1;

	}

	public void aumentarVelocidadPowerUp() {
//		int velocidad anterior
		tienePowerUp = true;
		topeVelocidad = VELOCIDAD_MAX2 + 200;

		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				topeVelocidad = VELOCIDAD_MAX2;
				if (Auto.velocidad > topeVelocidad) {
					Auto.velocidad = topeVelocidad;
				}
				tienePowerUp = false;
			}
		}, 10000);
	}

	/**
	 * Cuando choca contra los costados de la calle se podricen estas animaciones..
	 */
	public void die() {
		if (!dead) {
			setDirectionRight(false);
			setDirectionLeft(false);
			setDirectionUpSpeed1(false);
			setDirectionUpSpeed2(false);
			collider.setX(posXAutoInicial);
			this.dead = true;
			Auto.velocidad = VELOCIDAD_INICIAL;
			crash.play();
			new java.util.Timer().schedule(new java.util.TimerTask() {
				@Override
				public void run() {
					explosionAudio.play();
				}
			}, 100);

			new java.util.Timer().schedule(new java.util.TimerTask() {
				@Override
				public void run() {
					crash.stop();
					resetViewPort();
					Auto.this.dead = false;
					setX(posXAutoInicial);
				}
			}, 1100);

			System.out.println("Mori");
		}
	}

	public void setX(double x) {
		if (!dead) {
			collider.setX(x);
			Auto.ubicacion.setX(x);
			render.setX(x);
		}
//		System.out.println("Posicion actual: " + Auto.ubicacion.toString());

	}

	public void setY(double y) {
		if (!dead) {
			Auto.ubicacion.setY(y);/// esta es la unica Y que se va cambiar, porque es la que determina que tan
		}
	}

	public void update(double deltaTime) {
		if (dead) {
			return;
		}

		if (flagFueraDeMapa) {
			die();
//			System.out.println("me fui del mapa :(");
		}
		/// acelerando mientras no me pase del limite
		flagFueraDeMapa = true;

		if ((directionUpSpeed1 || directionUpSpeed2) && velocidad < topeVelocidad) {
			velocidad += aceleracion;
		}

		/// si estoy en la primer velocidad y vengo de la segunda velocidad, desacelero
		// si no estoy tocando para acelerar, desacelero
		if ((directionUpSpeed1 && velocidad >= topeVelocidad) || (!directionUpSpeed1 && !directionUpSpeed2)) {
			velocidad -= TASA_FRENADO;
		}

		/// para que la velocidad no sea negativa
		if (velocidad < VELOCIDAD_INICIAL) {
			velocidad = VELOCIDAD_INICIAL;
		}

		int direction = directionLeft ? -1 : (directionRight ? 1 : 0);
		setX(Auto.ubicacion.getX() + direction * VEL_HORIZONTAL * deltaTime);
		setY(Auto.ubicacion.getY() + Auto.velocidad * deltaTime);
	}

	public void setDirectionRight(boolean b) {
		this.directionRight = b;
	 	this.ultimaDireccionRight = true;
	}

	public void setDirectionLeft(boolean b) {
		this.directionLeft = b;
		this.ultimaDireccionRight = false;
	}

	public void setDirectionUpSpeed1(boolean b) {
		if (!tienePowerUp) {
			topeVelocidad = VELOCIDAD_MAX1;
		}
		activeDriveSound(b);
		this.directionUpSpeed1 = b;
		if (Auto.velocidad <= topeVelocidad) {
			this.aceleracion = TASA_ACELERACION1;
		}

	}

	public void setDirectionUpSpeed2(boolean b) {
		if (!tienePowerUp) {
			topeVelocidad = VELOCIDAD_MAX2;
		}
		activeDriveSound(b);
		this.directionUpSpeed2 = b;
		this.aceleracion = TASA_ACELERACION2;
	}
	
	private void activeDriveSound(boolean b) {
		if (b && !noEstoyAcelerando) {
			driveAudio.play();
			noEstoyAcelerando = true;
			System.err.println("Active el drive sound");
		} else if (!b && noEstoyAcelerando) {
			driveAudio.stop();
			noEstoyAcelerando = false;
			System.err.println("Desactive el drive sound");
		}
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