package road_fighter.objects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import road_fighter.Config;
import road_fighter.interfaces.Actualizable;
import road_fighter.interfaces.Renderizable;
import road_fighter.utils.GameObject;

public class Background extends GameObject implements Actualizable, Renderizable {
	
	private VBox render;
	private double posX = 0;

//	private final int cityWidth = 136;
//	private final int cityHeight = 152;
//	private final int grassHeight = 100;
//	private final int margenInicialCalle;
//	private final int margenFinalCalle;
//	private final int anchoTotalVentana = 1200;
	private final int anchoTotal = 150;
	private final int altoTotal = 166;

	public Background()  {
		//TODO: cambiar por nuestro mapa
		Image backgroundImage = new Image(
				"file:src/main/resources/img/background.png", anchoTotal, altoTotal, false, false);

		ImagePattern image_pattern = new ImagePattern(backgroundImage, anchoTotal, 
				altoTotal, anchoTotal, altoTotal, false);

//		Rectangle sky = new Rectangle(Config.ANCHO_FRAME + anchoTotal, 
//				Config.ALTO_FRAME - altoTotal);
//		Rectangle city = new Rectangle(Config.ANCHO_FRAME + anchoTotal, altoTotal);
//		Rectangle grass = new Rectangle(Config.ANCHO_FRAME + anchoTotal, altoTotal);

//		sky.setFill(Color.rgb(84, 192, 201));
//		city.setFill(image_pattern);
//		grass.setFill(Color.rgb(100, 224, 117));
//
//		render = new VBox(sky, city, grass);
		
		render.setViewOrder(10);
	}

	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void update(double deltaTime) {
		posX += -Config.INITIAL_SPEED * deltaTime * 0.01;
		render.setTranslateX(posX % anchoTotal);
	}

	@Override
	public void destroy() { }

}
