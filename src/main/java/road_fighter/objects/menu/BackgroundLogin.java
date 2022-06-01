package road_fighter.objects.menu;

import java.lang.StackWalker.StackFrame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import login.GestorLogin;
import road_fighter.Config;
import road_fighter.RoadFighterGame;
import road_fighter.interfaces.Actualizable;
import road_fighter.interfaces.Colisionable;
import road_fighter.interfaces.Renderizable;
import road_fighter.utils.GameObject;
import usuario.Usuario;

public class BackgroundLogin extends GameObject implements Renderizable {
	private Image imagenMenu;
	private ImageView renderImagenMenu;

	StackPane contenedorMenu;

	public BackgroundLogin()  {
		
		imagenMenu = new Image(Config.MENU_IMG, Config.ANCHO_FRAME_ESCENA, Config.ALTO_FRAME_ESCENA, false, false);
		renderImagenMenu = new ImageView(imagenMenu);
		renderImagenMenu.setViewport(new Rectangle2D(0, 0, Config.ANCHO_FRAME_ESCENA, Config.ALTO_FRAME_ESCENA));
		renderImagenMenu.setViewOrder(50);
				
		contenedorMenu = new StackPane();
		contenedorMenu.getChildren().addAll(renderImagenMenu);
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
