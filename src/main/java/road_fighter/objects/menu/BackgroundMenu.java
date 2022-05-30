package road_fighter.objects.menu;

import java.lang.StackWalker.StackFrame;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

public class BackgroundMenu extends GameObject implements Renderizable {
	private Image imagenMenu;
	private ImageView renderImagenMenu;
	
	StackPane contenedorMenu;

	public BackgroundMenu()  {
		imagenMenu = new Image(Config.MENU_IMG, Config.ANCHO_FRAME_ESCENA, Config.ALTO_FRAME_ESCENA, false, false);
		renderImagenMenu = new ImageView(imagenMenu);
		renderImagenMenu.setViewport(new Rectangle2D(0, 0, Config.ANCHO_FRAME_ESCENA, Config.ALTO_FRAME_ESCENA));
		renderImagenMenu.setViewOrder(50);
		
		Text textoJuego = new Text("ENTER para empezar \n ESCAPE para salir");
		textoJuego.setFont(Font.font (Config.FONT_TYPE, Config.FONT_SIZE_MARCADOR));
		textoJuego.setFill(Color.WHITE);
		textoJuego.setTranslateY(100);
		
		
		contenedorMenu = new StackPane();
		contenedorMenu.getChildren().addAll(renderImagenMenu, textoJuego);	
	}

	@Override
	public Node getRender() {
		return contenedorMenu;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}




}
