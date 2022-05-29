package road_fighter.objects;

import java.lang.StackWalker.StackFrame;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import road_fighter.Config;
import road_fighter.interfaces.Actualizable;
import road_fighter.interfaces.Colisionable;
import road_fighter.interfaces.Renderizable;
import road_fighter.utils.GameObject;

public class Background extends GameObject implements Actualizable, Renderizable, Colisionable {
	
	private HBox render;
	private HBox renderMapa;
	private HBox renderBarraMarcador;
	private Text textoVelocidadJugador;
	
	private Rectangle calleCollider;
	
	private double posY = 0;

	public Background()  {
		/// no se por que pero la si achica el margen de colision de la calle  (119px) se rompe en el medio
		calleCollider =  new Rectangle(125, 999999);
		calleCollider.setFill(Color.FUCHSIA);
		calleCollider.setStroke(Color.FUCHSIA);
		calleCollider.relocate(160, 0);
		//MAPA
		Image imagenMapa = new Image(Config.MAP_IMG, Config.ANCHO_FRAME_MAPA, Config.ALTO_FRAME_MAPA, false, false);
		ImagePattern imagePatternMapa = new ImagePattern(imagenMapa, -Config.ANCHO_FRAME_MAPA, Config.ALTO_FRAME_MAPA, Config.ANCHO_FRAME_MAPA, Config.ALTO_FRAME_MAPA , false);
		Rectangle fondoMapa = new Rectangle(Config.ANCHO_FRAME_MAPA, Config.ALTO_FRAME_MAPA);
		fondoMapa.setFill(imagePatternMapa);

		//BARRA DE MARCADORES (podria ser una clase fuera de background)
		Rectangle fondoBarraMarcador = new Rectangle(Config.ANCHO_FRAME_ESCENA - Config.ANCHO_FRAME_MAPA, Config.ALTO_FRAME_ESCENA);

		fondoBarraMarcador.setFill(Color.BLACK);
		
		
		Text textoNombreJugador = new Text("JUGADOR 1");
		textoNombreJugador.setFont(Font.font (Config.FONT_TYPE, Config.FONT_SIZE_MARCADOR));
		textoNombreJugador.setFill(Color.WHITE);
		
		///Hay que ver como hacer para que el texto de la velocidad sea dinamico segun la velocidad del auto
		textoVelocidadJugador = new Text("0 KM/H");
		textoVelocidadJugador.setFont(Font.font (Config.FONT_TYPE, Config.FONT_SIZE_MARCADOR));
		textoVelocidadJugador.setFill(Color.WHITE);
		textoVelocidadJugador.setTranslateY(200);
		
		
		VBox contenedorTexto = new VBox(textoNombreJugador, textoVelocidadJugador);
		contenedorTexto.setAlignment(Pos.CENTER);
		
		//El stackPane es un contenedor que tiene al texto sobre el fondo negro
		StackPane contenedorMarcador = new StackPane();
		contenedorMarcador.getChildren().addAll(fondoBarraMarcador, contenedorTexto);	
		
		//---FIN BARRA DE MARCADORES--
		
		
		//tengo dos renders, uno para el mapa y otro para la barra de marcadores
		//de esta manera, puedo desplazar el mapa verticalmente sin que se 
		//desplace la barra de marcadores tambien. Luego, las agrupo en un render solo para devolverlo en el metodo getRender()
		
		renderMapa = new HBox(fondoMapa);
		renderBarraMarcador = new HBox(contenedorMarcador);
		
		///este  -1450 es para situar la barra de marcadores correctamente
		renderBarraMarcador.setTranslateY(-1450);
		render = new HBox(renderMapa, renderBarraMarcador);
		
		render.setViewOrder(10);
	}

	@Override
	public HBox getRender() {
		return render;
	}

	@Override
	public void update(double deltaTime) {
		posY += Auto.getVelocidad() * deltaTime;
		///estos valores de 3.5 y 2.2 me los saqué de la galera, pero es para que el movimiento el mapa sea fluido
		textoVelocidadJugador.setText(Auto.getVelocidad() + "KM/H");
		renderMapa.setTranslateY(-Config.ALTO_FRAME_ESCENA * 3.5 + (posY % Config.ALTO_FRAME_ESCENA * 2.2) );
	}
	
	@Override
	public void destroy() { }

	@Override
	public Shape getCollider() {
		return calleCollider;
	}



}
