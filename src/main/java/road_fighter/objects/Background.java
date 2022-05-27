package road_fighter.objects;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import road_fighter.Config;
import road_fighter.interfaces.Actualizable;
import road_fighter.interfaces.Renderizable;
import road_fighter.utils.GameObject;

public class Background extends GameObject implements Actualizable, Renderizable {
	
	private HBox render;
	private double posY = 0;

//	private final int cityWidth = 136;
//	private final int cityHeight = 152;
//	private final int grassHeight = 100;
//	private final int margenInicialCalle;
//	private final int margenFinalCalle;
//	private final int anchoTotalVentana = 1200;
	private final int anchoTotal = Config.ANCHO_FRAME_MAPA;
	private final int altoTotal = Config.ALTO_FRAME_MAPA;

	public Background()  {
		Image fondo = new Image(Config.MAP_IMG, anchoTotal, altoTotal, false, false);

		ImagePattern imagePattern = new ImagePattern(fondo, anchoTotal, altoTotal, anchoTotal, anchoTotal , false);
		Rectangle fondoRepetido = new Rectangle(anchoTotal, altoTotal * 2);
	
		Rectangle barraMarcador = new Rectangle(Config.ANCHO_FRAME_MAPA, Config.ALTO_FRAME_MAPA * 2);
		barraMarcador.setFill(Color.BLACK);
		fondoRepetido.setFill(imagePattern);
		
		
		render =  new HBox(fondoRepetido, barraMarcador);
		render.setViewOrder(10);
		

	}

	@Override
	public HBox getRender() {
		return render;
	}

	@Override 
	public void update(double deltaTime) {
		///el 100 seria la velocidad del auto, pero esta hardcodeado por ahora
		posY += 300 * deltaTime;
		render.setTranslateY(-Config.ALTO_FRAME_MAPA + posY % altoTotal);
	}

	@Override
	public void destroy() { }

}
