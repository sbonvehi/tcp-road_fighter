package road_fighter.objects;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import road_fighter.Config;
import road_fighter.interfaces.Actualizable;
import road_fighter.interfaces.Renderizable;
import road_fighter.utils.GameObject;

public class BarraProgresoCarrera extends GameObject implements Renderizable, Actualizable {
	private ImageView renderImagen;
	private ImageView renderAuto;
	private VBox renderTotal;

	private double posY = 0;

	public BarraProgresoCarrera() {
		Image imagenBarraProgreso = new Image(Config.BARRA_PROGRESO_CARRERA_IMG, 62, Config.ALTO_FRAME_ESCENA, false,
				false);
		renderImagen = new ImageView(imagenBarraProgreso);
		renderImagen.setViewOrder(10);

		Image spriteImages = new Image(Config.GENERAL_SPRITES_IMG, 328 * 3, 179 * 3, false, false);
		renderAuto = new ImageView(spriteImages);
		renderAuto.setViewport(new Rectangle2D(5, 34 * 3, 14 * 3, 19 * 3));
		renderAuto.setTranslateX(15);
		renderAuto.setTranslateY(-75);

		renderTotal = new VBox(renderImagen, renderAuto);
		renderTotal.setTranslateX(450);
	}

	@Override
	public void update(double deltaTime) {
		posY = 0.068 * Auto.getUbicacion().getY();
		renderAuto.setTranslateY(-75 - posY); // FACTOR_DESPLAZAMIENTO es un numero que uso para que se mueva el mapa en
												// conjunto con la meta
	}

	@Override
	public Node getRender() {
		// TODO Auto-generated method stub
		return renderTotal;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
