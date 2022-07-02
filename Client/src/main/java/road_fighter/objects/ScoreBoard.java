package road_fighter.objects;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import road_fighter.Config;
import road_fighter.interfaces.Renderizable;
import road_fighter.utils.GameObject;

public class ScoreBoard extends GameObject implements Renderizable {

	private int ANCHO_MARCADOR = 400;
	private int ALTO_MARCADOR = 200;
	private static GridPane grid;
	private static int cantJugadores;

	public ScoreBoard() {
		cantJugadores = 0;

		grid = new GridPane();
		grid.setAlignment(Pos.CENTER_LEFT);
		grid.setHgap(50);
		grid.setVgap(20);
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setMinWidth(ANCHO_MARCADOR);
		grid.setMinHeight(ALTO_MARCADOR);
		grid.setStyle("-fx-background-color: #000000;");

		grid.setAlignment(Pos.TOP_LEFT);
		grid.setTranslateX(75);
		grid.setTranslateY(100);
		grid.setOpacity(0);

		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(65);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(35);
		grid.getColumnConstraints().addAll(column1, column2);

		Text textoGameOver = new Text("GAME OVER");
		textoGameOver.setFont(Font.font(Config.FONT_TYPE, Config.FONT_SIZE_MARCADOR));
		textoGameOver.setFill(Color.WHITE);
		textoGameOver.setTranslateX(110);

		grid.add(textoGameOver, 0, 0);
	}

	@Override
	public Node getRender() {
		return grid;
	}

	public static void mostrar() {
		grid.setOpacity(0.9);
	}

	public static void agregarJugadorAlTablero(String nombreJugador, String tiempo) {
		int largoNombre = nombreJugador.length() >= 14 ? 14 : nombreJugador.length();
		Text textoNombreJugador = new Text(nombreJugador.substring(0, largoNombre));
		textoNombreJugador.setFont(Font.font("MS Outlook", Config.FONT_SIZE_MARCADOR));
		textoNombreJugador.setFill(Color.WHITE);

		Text textoTiempo = new Text(tiempo);
		textoTiempo.setFont(Font.font("MS Outlook", Config.FONT_SIZE_MARCADOR));
		textoTiempo.setFill(Color.WHITE);
		textoTiempo.setTextAlignment(TextAlignment.RIGHT);

		grid.add(textoNombreJugador, 0, cantJugadores + 1);
		grid.add(textoTiempo, 1, cantJugadores + 1);

		cantJugadores++;
	}

	@Override
	public void destroy() {
	}

}
