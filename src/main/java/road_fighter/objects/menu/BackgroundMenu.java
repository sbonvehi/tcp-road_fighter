package road_fighter.objects.menu;

import java.lang.StackWalker.StackFrame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import login.GestorLogin;
import road_fighter.Config;
import road_fighter.RoadFighterGame;
import road_fighter.interfaces.Actualizable;
import road_fighter.interfaces.Colisionable;
import road_fighter.interfaces.Renderizable;
import road_fighter.utils.GameObject;

public class BackgroundMenu extends GameObject implements Renderizable {
	private Image imagenMenu;
	private ImageView renderImagenMenu;
	private GridPane grid;
	private GestorLogin gestorLogin;
	private RoadFighterGame g;

	private Label jugarLabel;
	private Label crearLabel;
	private Label unirseLabel;
	private Label salirLabel;
	
	StackPane contenedorMenu;

	public BackgroundMenu(RoadFighterGame g)  {
		this.g = g;
		imagenMenu = new Image(Config.MENU_IMG, Config.ANCHO_FRAME_ESCENA, Config.ALTO_FRAME_ESCENA, false, false);
		renderImagenMenu = new ImageView(imagenMenu);
		renderImagenMenu.setViewport(new Rectangle2D(0, 0, Config.ANCHO_FRAME_ESCENA, Config.ALTO_FRAME_ESCENA));
		renderImagenMenu.setViewOrder(50);
		
//		Text textoJuego = new Text("ENTER para empezar \n ESCAPE para salir");
//		textoJuego.setFont(Font.font (Config.FONT_TYPE, Config.FONT_SIZE_MARCADOR));
//		textoJuego.setFill(Color.WHITE);
//		textoJuego.setTranslateY(100);
		
		
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
		// TODO Auto-generated method stub
		
	}

	private void iniciarLabels()
	{
		jugarLabel = new Label("Single Player");
		crearLabel = new Label("Elegir mapa");
		unirseLabel = new Label("Unirse a sala");
		salirLabel = new Label("Salir");
		
		jugarLabel.setTextFill(Color.WHITE);
		jugarLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		jugarLabel.setTranslateY(Config.X_OPCION1);
		
		crearLabel.setTextFill(Color.WHITE);
		crearLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		crearLabel.setTranslateY(Config.X_OPCION2);
		
		unirseLabel.setTextFill(Color.WHITE);		
		unirseLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		unirseLabel.setTranslateY(Config.X_OPCION3);
		
		salirLabel.setTextFill(Color.WHITE);		
		salirLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		salirLabel.setTranslateY(Config.X_OPCION4);
		
		jugarLabel.setOnMouseClicked(e -> {
				g.startGame(Config.MAP_IMG);
			});
		
		crearLabel.setOnMouseClicked(e -> {
			contenedorMenu.getChildren().removeAll(jugarLabel, crearLabel, unirseLabel, salirLabel);	
			elegirMapa(false);
			});
		
		unirseLabel.setOnMouseClicked(e -> {
//			contenedorMenu.getChildren().removeAll(crearLabel, unirseLabel, salirLabel);	
//			getGrid(true);
			;});
		
		salirLabel.setOnMouseClicked(e -> {System.exit(0);});
		
		contenedorMenu.getChildren().addAll(jugarLabel, crearLabel, unirseLabel, salirLabel);	
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
			g.startGame(Config.MAP_IMG);
			});
		
		mapa2Label.setOnMouseClicked(e -> {
			g.startGame(Config.MAP_IMG2);
			});
		
		mapa3Label.setOnMouseClicked(e -> {
			g.startGame(Config.MAP_IMG3);
			});
		
		contenedorMenu.getChildren().addAll(mapa1Label, mapa2Label, mapa3Label);	
	}

	private void getGrid(boolean isLogin)
	{
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
            	try
            	{
            		g.getAnfitrion().crearSala(salaTextField.toString());
            		
            	}
            	catch(Exception ex)
            	{
            		 System.out.println(ex.getMessage());;
            	}
            }
        });
	}

}
