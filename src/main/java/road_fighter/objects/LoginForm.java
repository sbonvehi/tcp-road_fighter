package road_fighter.objects;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import login.GestorLogin;
import road_fighter.RoadFighterGame;
import road_fighter.interfaces.Renderizable;
import road_fighter.utils.GameObject;
import usuario.Usuario;

public class LoginForm extends GameObject implements Renderizable {

	private GridPane grid;
	private Text scenetitle;
	private GestorLogin gestorLogin;
	private RoadFighterGame g;
	private TextField userTextField;
	private PasswordField pwBox;
	private Button btnCancel;
	private Button btnOk;
	private Text actiontarget;
	private Label registerLabel;
	private Label iniciarLabel;
	private Label salirLabel;
	private boolean isLogin;

	StackPane contenedorMenu;
	public static String nombreUsuario = "JUGADOR 1";

	public LoginForm(RoadFighterGame g) {
		this.g = g;
		gestorLogin = new GestorLogin();
		contenedorMenu = new StackPane();

		iniciarGrid();
		iniciarLabels();
	}

	@Override
	public Node getRender() {
		return contenedorMenu;
	}

	@Override
	public void destroy() {
	}

	private void iniciarGrid() {
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER_LEFT);
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(450, 300, 100, 200));

		scenetitle = new Text();
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
		scenetitle.setFill(Color.WHITE);
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("User Name:");
		userName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		userName.setTextFill(Color.WHITE);
		grid.add(userName, 0, 1);

		userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Label pw = new Label("Password:");
		pw.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		pw.setTextFill(Color.WHITE);
		grid.add(pw, 0, 2);

		pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);

		btnCancel = new Button("Cancelar");
		btnOk = new Button("Confirmar");
		btnCancel.setTextFill(Color.WHITE);
		btnCancel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		btnCancel.setStyle("-fx-background-color: transparent");
		btnOk.setTextFill(Color.WHITE);
		btnOk.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		btnOk.setStyle("-fx-background-color: transparent");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btnOk);
		hbBtn.getChildren().add(btnCancel);
		grid.add(hbBtn, 1, 4);

		actiontarget = new Text();

		grid.add(actiontarget, 1, 6);

	}

	private void getGrid() {
		if (isLogin)
			scenetitle.setText("Login");
		else
			scenetitle.setText("Registrarse");

		btnCancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				contenedorMenu.getChildren().remove(grid);
				contenedorMenu.getChildren().addAll(registerLabel, iniciarLabel, salirLabel);
			}
		});

		btnOk.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				try {
					if (isLogin)
						loggearUsuario();
					else
						registraUsuario();
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
					;
				}
			}
		});

		contenedorMenu.getChildren().removeAll(registerLabel, iniciarLabel, salirLabel);
		contenedorMenu.getChildren().add(grid);
	}

	private void loggearUsuario() throws InterruptedException {
		boolean result = gestorLogin.login(userTextField.getText(), pwBox.getText());

		if (result) {
			actiontarget.setText("Se inicio correctamente");
			actiontarget.setFill(Color.GREEN);
			g.setAnfitrion(new Usuario(userTextField.getText(), pwBox.getText()));
			g.startMenu();
			nombreUsuario = userTextField.getText();
		} else {
			actiontarget.setText("Error al logearse");
			actiontarget.setFill(Color.RED);
		}
	}

	private void registraUsuario() throws InterruptedException {
		boolean result = gestorLogin.registrarUsuario(userTextField.getText(), pwBox.getText());
		if (result) {
			actiontarget.setText("Se registro correctamente");
			actiontarget.setFill(Color.GREEN);
			nombreUsuario = userTextField.getText();
		} else {
			actiontarget.setFill(Color.RED);
			actiontarget.setText("No se pudo registrarse");
		}

		// ARREGLAR ESTO PORQUE NO SE MUESTRAN LOS MENSAJES.
		Thread.sleep(2000);
		contenedorMenu.getChildren().remove(grid);
		contenedorMenu.getChildren().addAll(registerLabel, iniciarLabel, salirLabel);
	}

	private void iniciarLabels() {
		registerLabel = new Label("Registrarse");
		iniciarLabel = new Label("Iniciar sesion");
		salirLabel = new Label("Salir");

		registerLabel.setTextFill(Color.WHITE);
		registerLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		registerLabel.setTranslateX(250);
		registerLabel.setTranslateY(450);

		iniciarLabel.setTextFill(Color.WHITE);
		iniciarLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		iniciarLabel.setTranslateX(250);
		iniciarLabel.setTranslateY(525);

		salirLabel.setTextFill(Color.WHITE);
		salirLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		salirLabel.setTranslateX(250);
		salirLabel.setTranslateY(600);

		registerLabel.setOnMouseClicked(e -> {
			this.isLogin = false;
			getGrid();
		});

		iniciarLabel.setOnMouseClicked(e -> {
			this.isLogin = true;
			getGrid();
			;
		});

		salirLabel.setOnMouseClicked(e -> {
			System.exit(0);
		});

		contenedorMenu.getChildren().addAll(registerLabel, iniciarLabel, salirLabel);
	}
}
