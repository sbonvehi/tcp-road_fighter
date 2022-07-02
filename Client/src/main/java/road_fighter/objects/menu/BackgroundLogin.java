package road_fighter.objects.menu;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import road_fighter.Config;
import road_fighter.interfaces.Renderizable;
import road_fighter.utils.GameObject;

public class BackgroundLogin extends GameObject implements Renderizable {
	private Image imagenMenu;
	private ImageView renderImagenMenu;

	StackPane contenedorMenu;

	public BackgroundLogin() {
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
	public void destroy() {}
}
