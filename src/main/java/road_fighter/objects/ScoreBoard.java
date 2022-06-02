package road_fighter.objects;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import login.GestorLogin;
import road_fighter.Config;
import road_fighter.interfaces.Renderizable;
import road_fighter.utils.GameObject;

public class ScoreBoard extends GameObject implements Renderizable{

	private Rectangle fondo;
	private static VBox render;
	
	public ScoreBoard()
	{
		
		//fondo
		fondo = new Rectangle(400,250);
		fondo.setFill(Color.BLACK);
		fondo.setViewOrder(1);
		
		//contenedor
		render = new VBox();
		render.getChildren().add(fondo);
		render.setTranslateX(75);
		render.setTranslateY(100);
		
		
		Text textoGameOver = new Text("GAME OVER");
		textoGameOver.setFont(Font.font (Config.FONT_TYPE, Config.FONT_SIZE_MARCADOR));
		textoGameOver.setFill(Color.WHITE);
		textoGameOver.setTranslateX(120);
		textoGameOver.setTranslateY(-240);
		render.getChildren().add(textoGameOver);
//		
		
//		Text nombreJugadorUser = new Text(LoginForm.nombreUsuario + "      " + "01:05:54");
		Text nombreJugadorUser = new Text(String.format("%1$-15s", LoginForm.nombreUsuario) + "01:05:54");
		nombreJugadorUser.setFont(Font.font (Config.FONT_TYPE, Config.FONT_SIZE_MARCADOR));
		nombreJugadorUser.setFill(Color.WHITE);
		nombreJugadorUser.setTranslateX(10);		
		nombreJugadorUser.setTranslateY(-220);		
		render.getChildren().add(nombreJugadorUser);
		
		
		//esto se va a implementar solo cuando haya mas jugadores
		for(int i = 2; i < 5; i++) {
			Text nombreJugador = new Text( String.format("%1$-15s", "JUGADOR " + i) + "01:05:54");
			nombreJugador.setFont(Font.font (Config.FONT_TYPE, Config.FONT_SIZE_MARCADOR));
			nombreJugador.setFill(Color.WHITE);
			nombreJugador.setTranslateX(10);		
			nombreJugador.setTranslateY(-220);		
			render.getChildren().add(nombreJugador);
		}	
		
		render.setOpacity(0);
		
		
		//TODO: usar timer para ver cuanto tarda el jugador en la carrera
		
//		render.setOpacity(1);
//		final long time = System.currentTimeMillis();
//        new java.util.Timer().schedule(new java.util.TimerTask() {
//            @Override
//            public void run() {
//                long time2 = System.currentTimeMillis();
//                double tiempo = time2 - time;
//                System.err.println("El tiempo es: " + tiempo);
//                tiempo /= 1000;
//                
//                tiempo %= 3600;
//                long minutos =(long)tiempo / 60;  
//                long segundos =(long) tiempo % 60;
//                tiempo %= 1;
//                double miliSegundos = (tiempo * 10);
//                System.out.println("Tiempo transcurrido: " + minutos + " " + segundos + " " + miliSegundos );
//            }
//        }, 1500);	
	}
	

	@Override
	public Node getRender() {
		// TODO Auto-generated method stub
		return render;
	}
	
	public static void mostrar() {
		render.setOpacity(0.9);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
