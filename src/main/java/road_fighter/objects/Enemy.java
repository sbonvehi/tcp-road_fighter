package road_fighter.objects;


import coordenada.Coordenada;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import road_fighter.Config;
import road_fighter.interfaces.Actualizable;
import road_fighter.interfaces.Colisionable;
import road_fighter.interfaces.Renderizable;
import road_fighter.utils.GameObject;

public class Enemy extends GameObject implements Actualizable, Renderizable, Colisionable {
	
	private Image carImage = new Image(Config.BLUE_CAR, Config.ANCHO_AUTO, Config.ALTO_AUTO, false, false);
	private Coordenada ubicacion;
    private double velocidad = 150;

    private ImageView render; 
    private Rectangle collider;

    	
    public Enemy(double x, double y, double velocidad) {
    	ubicacion = new Coordenada(x, y);
    	this.velocidad = velocidad; 
    	
    	collider = new Rectangle(Config.ANCHO_AUTO, Config.ANCHO_AUTO);
		collider.setFill(null);
		collider.setStroke(Color.FUCHSIA);
    	
        render = new ImageView(carImage);
        render.setViewport( new Rectangle2D(0,0, Config.ANCHO_AUTO, Config.ALTO_AUTO));
		render.relocate(300 + x, 550 + y);
    }
    
    public ImageView getRender() {
		return render;
	}
    
    public void setY(double y) {
		this.ubicacion.setY(y);
		render.setY(y);
	}
    
    
    @Override
    public void update(double deltaTime) {
    	double difVelocidadAutos = velocidad - Auto.getVelocidad();
		setY(this.ubicacion.getY() - difVelocidadAutos * deltaTime * 2.15 ); //el 2.15 es para que el autoNPC se mueva proporcionalmente en relacion al mapa
    }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Shape getCollider() {
		// TODO Auto-generated method stub
		return collider;
	}



	
}