package road_fighter.objects.menu;

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
import road_fighter.Config;
import road_fighter.RoadFighterGame;
import road_fighter.interfaces.Renderizable;
import road_fighter.utils.GameObject;

public class BackgroundMenu extends GameObject implements Renderizable {
	private Image imagenMenu;
	private ImageView renderImagenMenu;
	private GridPane grid;
	private RoadFighterGame juego;

	private Label anfitrionLabel;
	private Label jugarLabel;
	private Label crearLabel;
	private Label unirseLabel;
	private Label logoutLabel;
	private Label salirLabel;

	StackPane contenedorMenu;

	public BackgroundMenu(RoadFighterGame juego) {
		this.juego = juego;
		imagenMenu = new Image(Config.MENU_IMG, Config.ANCHO_FRAME_ESCENA, Config.ALTO_FRAME_ESCENA, false, false);

		renderImagenMenu = new ImageView(imagenMenu);
		renderImagenMenu.setViewport(new Rectangle2D(0, 0, Config.ANCHO_FRAME_ESCENA, Config.ALTO_FRAME_ESCENA));
		renderImagenMenu.setViewOrder(50);

		contenedorMenu = new StackPane();
		contenedorMenu.getChildren().addAll(renderImagenMenu);

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
		jugarLabel = new Label("Single Player");
		crearLabel = new Label("Elegir mapa");
		unirseLabel = new Label("Unirse a sala");
		logoutLabel = new Label("Cerrar Sesion");
		salirLabel = new Label("Salir");

		anfitrionLabel.setTextFill(Color.WHITE);
		anfitrionLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
		anfitrionLabel.setText(String.join(" ", "Hola,", juego.getAnfitrion().getNombre()));
		anfitrionLabel.setTranslateY(-350);
		anfitrionLabel.setTranslateX(220);

		jugarLabel.setTextFill(Color.WHITE);
		jugarLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		jugarLabel.setTranslateY(Config.X_OPCION1);

		crearLabel.setTextFill(Color.WHITE);
		crearLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		crearLabel.setTranslateY(Config.X_OPCION2);

		unirseLabel.setTextFill(Color.WHITE);
		unirseLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		unirseLabel.setTranslateY(Config.X_OPCION3);

		logoutLabel.setTextFill(Color.WHITE);
		logoutLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		logoutLabel.setTranslateY(Config.X_OPCION4);

		salirLabel.setTextFill(Color.WHITE);
		salirLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		salirLabel.setTranslateY(Config.X_OPCION5);

		jugarLabel.setOnMouseClicked(e -> {
			juego.startGame(Config.MAP_IMG);
		});

		crearLabel.setOnMouseClicked(e -> {
			contenedorMenu.getChildren().removeAll(anfitrionLabel, jugarLabel, crearLabel, unirseLabel, logoutLabel,
					salirLabel);
			elegirMapa(false);
		});

		logoutLabel.setOnMouseClicked(e -> {
			juego.logout();
		});

		salirLabel.setOnMouseClicked(e -> {
			System.exit(0);
		});

		contenedorMenu.getChildren().addAll(anfitrionLabel, jugarLabel, crearLabel, unirseLabel, logoutLabel,
				salirLabel);
	}

	private void elegirMapa(boolean b) {
		Label mapa1Label = new Label("Mapa 1");
		Label mapa2Label = new Label("Mapa 2");
		Label mapa3Label = new Label("Mapa 3");

		mapa1Label.setTextFill(Color.WHITE);
		mapa1Label.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		mapa1Label.setTranslateY(Config.X_OPCION1);

		mapa2Label.setTextFill(Color.WHITE);
		mapa2Label.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		mapa2Label.setTranslateY(Config.X_OPCION2);

		mapa3Label.setTextFill(Color.WHITE);
		mapa3Label.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		mapa3Label.setTranslateY(Config.X_OPCION3);

		mapa1Label.setOnMouseClicked(e -> {
			juego.startGame(Config.MAP_IMG);
		});

		mapa2Label.setOnMouseClicked(e -> {
			juego.startGame(Config.MAP_IMG2);
		});

		mapa3Label.setOnMouseClicked(e -> {
			juego.startGame(Config.MAP_IMG3);
		});

		contenedorMenu.getChildren().addAll(mapa1Label, mapa2Label, mapa3Label);
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
				contenedorMenu.getChildren().addAll(jugarLabel, crearLabel, unirseLabel, salirLabel);
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
