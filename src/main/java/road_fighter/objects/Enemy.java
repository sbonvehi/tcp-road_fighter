package road_fighter.objects;


import coordenada.Coordenada;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import road_fighter.Config;
import road_fighter.interfaces.Actualizable;
import road_fighter.interfaces.Renderizable;
import road_fighter.utils.GameObject;

public class Enemy extends GameObject implements Actualizable, Renderizable {
	
	private Image carImage = new Image(Config.BLUE_CAR, Config.ANCHO_AUTO, Config.ALTO_AUTO, false, false);
	private Coordenada ubicacion;
    private final int VELOCIDAD = 150;

    private ImageView render; 
    	
    public Enemy(double x, double y) {
    	ubicacion = new Coordenada(x, y);

        render = new ImageView(carImage);
        render.setViewport( new Rectangle2D(0,0, Config.ANCHO_AUTO, Config.ALTO_AUTO));
		render.relocate(300, 550);
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
    	double difVelocidadAutos = VELOCIDAD - Auto.getVelocidad();
		setY(this.ubicacion.getY() - difVelocidadAutos * deltaTime);
    }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}



	
}