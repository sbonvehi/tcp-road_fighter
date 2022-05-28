package road_fighter.objects;


import coordenada.Coordenada;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import road_fighter.Config;

public class Enemy {
	
	private Image carImage = new Image(Config.BLUE_CAR, Config.ANCHO_AUTO, Config.ALTO_AUTO, false, false);
	private Coordenada ubicacion;
    private final int VELOCIDAD_MAX = 200;
    private int velocidad = VELOCIDAD_MAX;

    private ImageView render; 
    	
    public Enemy(int x, int y) {
    	ubicacion = new Coordenada(x, y);

        render = new ImageView(carImage);
        render.setViewport( new Rectangle2D(0,0, Config.ANCHO_AUTO, Config.ALTO_AUTO));
		render.relocate(300, 550);
    }
    
    public ImageView getRender() {
		return render;
	}
    
    public void setY(int y) {
		this.ubicacion.setY(y);
		render.setY(y);
	}
    
    public void update(double deltaTime, double velocidadAutoJugador) { //delta time es el tiempo que paso desde la ultima actualizacion.

		double difVelocidadAutos = velocidad - velocidadAutoJugador;
		setY((int) (this.ubicacion.getY() - difVelocidadAutos * deltaTime));

	}
	
}