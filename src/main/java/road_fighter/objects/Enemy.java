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
    private double velocidad;

    private ImageView render; 
    private Rectangle collider;
    
    private static final int posXAutoInicial = 260;
	private static final int posYAutoInicial = 260;
    
    private double OFFSET_X_POSICION;
    private double OFFSET_Y_POSICION;
    	
    public Enemy(double x, double y, double velocidad) {
    	OFFSET_X_POSICION = x;
    	OFFSET_Y_POSICION = y;
    	ubicacion = new Coordenada(OFFSET_X_POSICION, OFFSET_Y_POSICION);
    	this.velocidad = velocidad; 
    	
    	collider = new Rectangle(Config.ANCHO_AUTO, Config.ALTO_AUTO);
		collider.setFill(null);
		collider.setStroke(Color.FUCHSIA);
    	
        render = new ImageView(carImage);
        render.setViewport( new Rectangle2D(0,0, Config.ANCHO_AUTO, Config.ALTO_AUTO));
        
        
		///ubicacion inicial (tiene por defecto una posicion y se le ajusta segun los parametros del constructor)

        render.setX(posXAutoInicial + OFFSET_X_POSICION);
        render.setY(posYAutoInicial + OFFSET_Y_POSICION);
		collider.setX(posXAutoInicial + OFFSET_X_POSICION);
		collider.setY(posYAutoInicial + OFFSET_Y_POSICION);
    }
    
    public ImageView getRender() {
		return render;
	}
    
    public void setY(double y) {
		this.ubicacion.setY(y);
		render.setY(y);
		collider.setY(y);
		
//		System.out.println(ubicacion.toString());
//		System.out.println("render   NPC: " + render.getX() + " " + render.getY());
//		System.out.println("collider NPC: " + collider.toString());
	}
    
    
    @Override
    public void update(double deltaTime) {
    	double difVelocidadAutos = velocidad - Auto.getVelocidad();
		setY(this.ubicacion.getY() - difVelocidadAutos * deltaTime * 2.15); //el 2.15 es para que el autoNPC se mueva proporcionalmente en relacion al mapa
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