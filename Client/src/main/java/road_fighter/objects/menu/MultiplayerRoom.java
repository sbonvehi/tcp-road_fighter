package road_fighter.objects.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import road_fighter.Client;
import road_fighter.Config;
import road_fighter.RoadFighterGame;
import road_fighter.interfaces.Renderizable;
import road_fighter.utils.GameObject;
import util.Room;

public class MultiplayerRoom extends GameObject implements Renderizable {
	private Image imagenMenu;
	private ImageView renderImagenMenu;
	private GridPane grid;
	private RoadFighterGame juego;

	private Client client;
	private Room room;
	private Label playersLabel;
	private Label jugador1Label;
	private Label jugador2Label;
	private Label jugador3Label;
	private Label jugador4Label;
	private Label anfitrionLabel;
	private Label jugarLabel;
	private Label iniciarLabel;
	private Label salirLabel;
	private List<String> players;
	
	StackPane contenedorMenu;

	public MultiplayerRoom(RoadFighterGame juego, Client client) {
		this.client = client;
		this.juego = juego;
		imagenMenu = new Image(Config.MENU_IMG, Config.ANCHO_FRAME_ESCENA, Config.ALTO_FRAME_ESCENA, false, false);

		renderImagenMenu = new ImageView(imagenMenu);
		renderImagenMenu.setViewport(new Rectangle2D(0, 0, Config.ANCHO_FRAME_ESCENA, Config.ALTO_FRAME_ESCENA));
		renderImagenMenu.setViewOrder(50);

		contenedorMenu = new StackPane();
		contenedorMenu.getChildren().addAll(renderImagenMenu);

		room = client.getCurrentRoom();
		
		players = client.getPlayersInSala();

		while(players.size() < 5)
		{
			players.add("");
		}
		
		iniciarLabels();
	}

	@Override
	public Node getRender() {
		return contenedorMenu;
	}

	@Override
	public void destroy() {
	}

	private void iniciarLabels() {
		anfitrionLabel = new Label();
		playersLabel = new Label();
		
		jugador1Label = new Label();
		jugador2Label = new Label();
		jugador3Label = new Label();
		jugador4Label = new Label();
		
		iniciarLabel = new Label("Iniciar");
		salirLabel = new Label("Salir");

		anfitrionLabel.setTextFill(Color.WHITE);
		anfitrionLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
		anfitrionLabel.setText(String.join(" ", "Hola,", juego.getAnfitrion().getNombre()));
		anfitrionLabel.setTranslateY(-350);
		anfitrionLabel.setTranslateX(220);

		playersLabel.setTextFill(Color.WHITE);
		playersLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
		playersLabel.setText("Jugadores: " + room.getActual() + "/" + room.getMax());
		playersLabel.setTranslateY(-250);
		playersLabel.setTranslateX(120);
		
		jugador1Label.setTextFill(Color.WHITE);
		jugador1Label.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		jugador1Label.setText(players.get(0));
		jugador1Label.setTranslateY(Config.X_OPCION1);

		jugador2Label.setTextFill(Color.WHITE);
		jugador2Label.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		jugador2Label.setText(players.get(1));
		jugador2Label.setTranslateY(Config.X_OPCION2);
		
		jugador3Label.setTextFill(Color.WHITE);
		jugador3Label.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		jugador3Label.setText(players.get(2));
		jugador3Label.setTranslateY(Config.X_OPCION3);
		
		jugador4Label.setTextFill(Color.WHITE);
		jugador4Label.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		jugador4Label.setText(players.get(4));
		jugador4Label.setTranslateY(Config.X_OPCION4);


		iniciarLabel.setTextFill(Color.WHITE);
		iniciarLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		iniciarLabel.setTranslateY(Config.X_OPCION5);

		salirLabel.setTextFill(Color.WHITE);
		salirLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		salirLabel.setTranslateY(Config.X_OPCION6);

		jugador1Label.setOnMouseClicked(e -> {
			juego.startGame(Config.MAP_IMG);
		});
		
		iniciarLabel.setOnMouseClicked(e -> {
			//juego.logout();
		});

		salirLabel.setOnMouseClicked(e -> {
			System.exit(0);
		});

		contenedorMenu.getChildren().addAll(playersLabel, anfitrionLabel, jugador1Label, jugador2Label, jugador3Label, jugador4Label, iniciarLabel,
				salirLabel);
	}
	
	private void getGrid(boolean estaLogeado) {
		grid = new GridPane();
		grid.setAlignment(Pos.BASELINE_CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(450, 150, 100, 100));

		contenedorMenu.getChildren().add(grid);

		Text scenetitle = new Text("Ingrese nombre de sala");

		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		scenetitle.setFill(Color.WHITE);
		grid.add(scenetitle, 0, 0, 2, 1);

		final TextField salaTextField = new TextField();
		grid.add(salaTextField, 1, 1);

		Button btnCancel = new Button("Cancelar");
		Button btnOk = new Button("Confirmar");
		btnCancel.setTextFill(Color.WHITE);
		btnCancel.setStyle("-fx-background-color: transparent");
		btnOk.setTextFill(Color.WHITE);
		btnOk.setStyle("-fx-background-color: transparent");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btnOk);
		hbBtn.getChildren().add(btnCancel);
		grid.add(hbBtn, 1, 4);

		final Text actiontarget = new Text();

		grid.add(actiontarget, 1, 6);

		btnCancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				contenedorMenu.getChildren().remove(grid);
				contenedorMenu.getChildren().addAll(jugarLabel, salirLabel);
			}
		});

		btnOk.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				try {
					juego.getAnfitrion().crearSala(salaTextField.toString());

				} catch (Exception ex) {
					System.out.println(ex.getMessage());
					;
				}
			}
		});
	}

}
