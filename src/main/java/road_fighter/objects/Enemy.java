package road_fighter.objects;

import java.util.concurrent.TimeUnit;

import coordenada.Coordenada;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import road_fighter.Config;
import usuario.Usuario;

public class Enemy {
	
	private Image carImage = new Image(Config.BLUE_CAR, Config.ANCHO_AUTO, Config.ALTO_AUTO, false, false);
	private Coordenada ubicacion;
    private double velocidad = 0;
    private double acceleration = 0.1;

    private ImageView render; 
    	
    public Enemy(int x, int y) {
    	ubicacion = new Coordenada(x, y);


        render = new ImageView(carImage);
        render.setViewport( new Rectangle2D(0,0, Config.ANCHO_AUTO, Config.ALTO_AUTO));
		render.relocate(397, 550);
    }
    
    public ImageView getRender() {
		return render;
	}
    
    public void setY(int y) {
		this.ubicacion.setY(y);
		render.setY(y);
	}
    
    public void update(double deltaTime) { //delta time es el tiempo que paso desde la ultima actualizacion.
		
		setY((int) (this.ubicacion.getY() + this.velocidad * deltaTime));
		this.velocidad = this.velocidad - 1;
		System.out.println("la velocidad del npc es: " + this.velocidad);

	}
    
    public void setCarImage(Image carImage) {
        this.carImage = carImage;
    }


    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

	
}