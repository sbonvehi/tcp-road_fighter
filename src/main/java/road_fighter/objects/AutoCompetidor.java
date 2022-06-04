package road_fighter.objects;

import animation.SpriteAnimation;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import road_fighter.Config;
import road_fighter.GameSceneHandler;
import road_fighter.interfaces.Colisionable;
import road_fighter.interfaces.Colisionador;
import road_fighter.utils.AudioResources;
import road_fighter.utils.Utils;
import usuario.Usuario;

public class AutoCompetidor extends Enemy implements Colisionador{

	private static final double VELOCIDAD_INICIAL = 0.0000000001;
	private final int VELOCIDAD_MAX1 = 200;
	private final int VELOCIDAD_MAX2 = 400;
	private final double TASA_ACELERACION1 = 1.5;
	private final double TASA_ACELERACION2 = 0.9;
	private final double TASA_FRENADO = 0.8;
	private final int VEL_HORIZONTAL = 300;
  
	private SpriteAnimation crash;
	private Image imageLostControl;
	private SpriteAnimation lostControlSpriteLeft;
	private SpriteAnimation lostControlSpriteRight;
	private int multiplic = 3;
	private int anchoAuto = 14;
	private int altoAuto = 19;
	private int espaciador = 2;
	private int anchoImagen = 328;
	private int altoImagen = 179;

	public int posXAutoInicial = 270;
	public static int posYAutoInicial = 650;
	private boolean flagFueraDeMapa = false;

	private int topeVelocidad = VELOCIDAD_MAX1;
	private double aceleracion = TASA_ACELERACION1;
	private double velocidad = VELOCIDAD_INICIAL;

	private Usuario piloto;

	private boolean directionLeft = false;
	private boolean directionRight = false;
	private boolean directionUpSpeed1 = false;
	private boolean directionUpSpeed2 = false;

	private boolean dead;
	private boolean terminoCarrera = false;
	private boolean perdiElControl;
	private boolean ultimaDireccionRight;
	private boolean noEstoyAcelerando;
	private boolean tienePowerUp = false;

	private AudioClip driveAudio;
	private AudioClip drive2Audio;
	private AudioClip skidAudio;
	private AudioClip explosionAudio;
	private AudioClip powerUpAudio;
	private boolean colisioneConObstaculo;
	 
	private String nombreCompetidor = "JUGADOR 2";

	private Image spriteImages;
	private ImageView imagenAuto;
	private VBox render;
	private boolean noEstoyAcelerando2;
	
	
	public AutoCompetidor(String nombreCompetidor, double x, double y, double velocidad) {
		super(x, y, velocidad);
		this.nombreCompetidor = nombreCompetidor;
		

		render = new VBox();
		
		Text textoNombre = new Text(nombreCompetidor);
		textoNombre.setFont(Font.font (Config.FONT_TYPE, 18));
		textoNombre.setFill(Color.WHITE);
		textoNombre.setViewOrder(5);
		textoNombre.setTranslateX(-21);
		textoNombre.setTranslateY(-18);
		
		initImages();
		imagenAuto = new ImageView(spriteImages);
		imagenAuto.setViewport(new Rectangle2D(espaciador, espaciador, anchoAuto * multiplic, altoAuto * multiplic));
		imagenAuto.setTranslateY(-18);
		imagenAuto.setViewOrder(5);
		
		render.getChildren().addAll(textoNombre, imagenAuto);
		
		
		
        render.setTranslateX(posXAutoInicial + OFFSET_X_POSICION);
        render.setTranslateY(posYAutoInicial + OFFSET_Y_POSICION);
		collider.setX(posXAutoInicial + OFFSET_X_POSICION);
		collider.setY(posYAutoInicial + OFFSET_Y_POSICION);
        
		
		initSpriteAnimations();
		initAudios();
	}
	
	@Override
	public Node getRender() {
		return this.render;
	}
	
	private void initImages() {

		spriteImages = new Image(Config.GENERAL_SPRITES_IMG, anchoImagen * multiplic, altoImagen * multiplic, false,
				false);
		imageLostControl = new Image(Config.LOST_CONTROL_SPRITES_IMG, 328 * multiplic, 40 * multiplic, false, false);
		
		Color[] original = { Color.rgb(216, 40, 0), Color.rgb(252, 252, 252), Color.rgb(164, 0, 0),
				Color.rgb(0, 0, 0) };
		
		Color[] green = { Color.rgb(13, 210, 48), Color.rgb(255, 255, 255), Color.rgb(0, 141, 0),
				Color.rgb(0, 0, 0)};
		
		spriteImages = Utils.reColor(spriteImages, original, green);
		imageLostControl = Utils.reColor(imageLostControl, original, green);
	}
	
	private void initSpriteAnimations() { /// estan bien cargados los sprites.
		crash = new SpriteAnimation(imagenAuto, Duration.millis(1000), 3, 3, 41 * 3, 34 * 3, 3 * 3, 14 * 3, 19 * 3);
		crash.setCycleCount(Animation.INDEFINITE);
	}

	private void initAudios() {
		driveAudio = AudioResources.getDriveAudio();
		driveAudio.setVolume(0.1);
		driveAudio.setCycleCount(AudioClip.INDEFINITE);
		drive2Audio = AudioResources.getDrive2Audio();
		drive2Audio.setVolume(0.1);
		drive2Audio.setCycleCount(AudioClip.INDEFINITE);
		skidAudio = AudioResources.getSkidAudio();
		skidAudio.setVolume(0.15);
		skidAudio.setCycleCount(AudioClip.INDEFINITE);
		explosionAudio = AudioResources.getExplosionAudio();
		explosionAudio.setVolume(0.3);
		powerUpAudio = AudioResources.getPowerUpAudio();
		powerUpAudio.setVolume(0.2);	
	}

	
	public void setY(double y) {
		this.ubicacion.setY(y);
		render.setTranslateY(y);
		collider.setY(y);
		
	}
	
	public void setX(double x) {
		if (!dead) {
			collider.setX(x);
			ubicacion.setX(x);
			imagenAuto.setX(x);
			render.setTranslateX(x);
		}

	}

	@Override
	public void update(double deltaTime) {

		int direction = directionLeft ? -1 : (directionRight ? 1 : 0);
		setX(collider.getX() + direction * VEL_HORIZONTAL * deltaTime);

		double difVelocidadAutos = velocidad - Auto.getVelocidad();
		setY(this.ubicacion.getY() - difVelocidadAutos * deltaTime * Background.FACTOR_DESPLAZAMIENTO); 
		// FACTOR_DESPLAZAMIENTO es para que el autoNPC se mueva proporcionalmente en relacion al mapa
		
		if (dead) {
			return;
		}

		if (flagFueraDeMapa) {
			die();
		}
		

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

	}

	public void setDirectionRight(boolean b) {
		if(terminoCarrera){
			return;
		}
		this.directionRight = b;
		this.ultimaDireccionRight = true;
	}

	public void setDirectionLeft(boolean b) {
		if(terminoCarrera){
			return;
		}
		this.directionLeft = b;
		this.ultimaDireccionRight = false;
	}

	public void setDirectionUpSpeed1(boolean b) {
		
		if(terminoCarrera){
			return;
		}
		activeDriveSound(b, driveAudio);
		
		if (!tienePowerUp) {
			topeVelocidad = VELOCIDAD_MAX1;
		}
		this.directionUpSpeed1 = b;
		if (velocidad <= topeVelocidad) {
			this.aceleracion = TASA_ACELERACION1;
		}

	}

	public void setDirectionUpSpeed2(boolean b) {
		if(terminoCarrera){
			return;
		}
		activeDrive2Sound(b, drive2Audio);
		
		if (!tienePowerUp) {
			topeVelocidad = VELOCIDAD_MAX2;
		}
		this.directionUpSpeed2 = b;
		this.aceleracion = TASA_ACELERACION2;
	}

	private void activeDriveSound(boolean b, AudioClip audio) {
		if (b && !noEstoyAcelerando) {
			driveAudio.play();
			noEstoyAcelerando = true;
		} else if (!b && noEstoyAcelerando) {
			driveAudio.stop();
			noEstoyAcelerando = false;
		}
	}
	private void activeDrive2Sound(boolean b, AudioClip audio) {
		if (b && !noEstoyAcelerando2) {
			audio.play();
			noEstoyAcelerando2 = true;
		} else if (!b && noEstoyAcelerando2) {
			audio.stop();
			noEstoyAcelerando2 = false;
		}
	}
	
	public void die() {
		if (!dead) {
			setDirectionRight(false);
			setDirectionLeft(false);
			setDirectionUpSpeed1(false);
			setDirectionUpSpeed2(false);
			
			collider.setX(posXAutoInicial + OFFSET_X_POSICION);
			this.dead = true;
			velocidad = VELOCIDAD_INICIAL;
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
					dead = false;
					setX(posXAutoInicial + OFFSET_X_POSICION);
				}
			}, 1100);

			System.out.println("Mori");
		}
	}

	@Override
	public void colisionar(Colisionable colisionable) {
		if (colisionable.getClass() == Enemy.class && !perdiElControl) {
			perderControl();
			System.out.println("choque contra auto NPC");
		}			
		if (colisionable.getClass() == Auto.class && !perdiElControl) {
			perderControl();
			System.out.println("choque contra auto de competidor");
		}	
		
		/// Si "colisiono" con la calle es que estoy bien, si dejo de colisionar
		/// entonces me fui del mapa
		if (colisionable.getClass() == Background.class) {
			flagFueraDeMapa = false;
		}

		
		if (colisionable.getClass() == FinishLine.class && !terminoCarrera) {
			setDirectionUpSpeed1(false);
			setDirectionUpSpeed2(false);
			terminoCarrera = true;
			GameSceneHandler.apagarMusica();
			ScoreBoard.mostrar();
			ScoreBoard.agregarJugadorAlTablero(nombreCompetidor, GameSceneHandler.getTiempoMeta());		
		}

		if (colisionable.getClass() == ColisionPowerUp.class) {
			this.aumentarVelocidadPowerUp();
			if (!colisioneConObstaculo) {
				colisioneConObstaculo = true;
				((ColisionPowerUp) colisionable).remove();
				powerUpAudio.play();
				new java.util.Timer().schedule(new java.util.TimerTask() {
					@Override
					public void run() {
						colisioneConObstaculo = false;
					}
				}, 800);
				System.out.println("COLISION CON POWER-UP");
			}
		}
		
		
		if (colisionable.getClass() == ColisionObstaculo.class) {
//			this.reducirVelocidadObstaculo();
			if (!colisioneConObstaculo) {
				colisioneConObstaculo = true;
				skidAudio.play();
				perderControl();
				new java.util.Timer().schedule(new java.util.TimerTask() {
					@Override
					public void run() {
						skidAudio.stop();
						colisioneConObstaculo = false;
					}
				}, 800);
			}
		}

		
	}
	

	public void perderControl() {
		if (!perdiElControl) {
			velocidad /= 2;
			perdiElControl = true;
			skidAudio.play();
			imagenAuto.setImage(imageLostControl);

			if (ultimaDireccionRight) {
				lostControlSpriteLeft = new SpriteAnimation(imagenAuto, Duration.millis(1000), 13, 13, 0, 2 * 3, 2 * 3,
						15 * 3, 18 * 3);
				lostControlSpriteLeft.setCycleCount(Animation.INDEFINITE);
			} else {
				lostControlSpriteRight = new SpriteAnimation(imagenAuto, Duration.millis(1000), 13, 13, 0, 20 * 3, 2 * 3,
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
					imagenAuto.setImage(spriteImages);
					resetViewPort();
					perdiElControl = false;
					skidAudio.stop();
				}
			}, 1100);
		}
	}
	

	public void aumentarVelocidadPowerUp() {
		tienePowerUp = true;
		topeVelocidad = VELOCIDAD_MAX2 + 200;

		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				topeVelocidad = VELOCIDAD_MAX2;
				if (velocidad > topeVelocidad) {
					velocidad = topeVelocidad;
				}
				tienePowerUp = false;
			}
		}, 10000);
	}
	
	private void resetViewPort() {
		imagenAuto.setViewport(new Rectangle2D(espaciador, espaciador, anchoAuto * multiplic, altoAuto * multiplic));
	}

	public void reducirVelocidadObstaculo() {
		velocidad /= 1.1;
	}

	public void teletransportar() {
	}

}
