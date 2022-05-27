package road_fighter.objects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	private final int anchoTotal = 720;
	private final int altoTotal = 720;

	public Background()  {
		Image fondo = new Image(Config.MAP_IMG, anchoTotal, altoTotal, false, false);
		
//		ImageView imageView = new ImageView(fondo);
//		  
		ImagePattern imagePattern = new ImagePattern(fondo, anchoTotal,altoTotal, anchoTotal, anchoTotal , false);
		Rectangle fondoRepetido = new Rectangle(anchoTotal, altoTotal * 1.2);
		fondoRepetido.setFill(imagePattern);
		
		render =  new VBox(fondoRepetido);
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
	
	//------HAY QUE DESARROLLAR ESTO !!!!-*--------
	
	
	
//	public void setDirectionUpSpeed1(boolean b) {
//		
//		this.directionUpSpeed1 = b;
////		this.velocidad = -100;
//		System.out.println("Posicion actual: " + this.ubicacion.toString());
//	} 
//	
//	//todo esto habria que hacerlo en el mapa y no en el auto
//	public void setDirectionUpSpeed2(boolean b) {
//		
//		this.directionUpSpeed2 = b;
//		if(directionUpSpeed2)
//			this.directionUpSpeed1 = false;
//		System.out.println("Posicion actual: " + this.ubicacion.toString());
//	}
	
}
