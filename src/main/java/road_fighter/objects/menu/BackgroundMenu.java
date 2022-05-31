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
		
		Label nom = new Label(g.getAnfitrion().getNombre());
		nom.setTextFill(Color.WHITE);
		nom.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		contenedorMenu.getChildren().add(nom);
		System.out.println(nom.getText());
		//iniciarLabels();
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
		crearLabel = new Label("Crear sala");
		unirseLabel = new Label("Unirse a sala");
		salirLabel = new Label("Salir");
		
		crearLabel.setTextFill(Color.WHITE);
		crearLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		crearLabel.setTranslateY(Config.X_OPCION1);
		
		unirseLabel.setTextFill(Color.WHITE);
		unirseLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		unirseLabel.setTranslateY(Config.X_OPCION2);
		
		salirLabel.setTextFill(Color.WHITE);		
		salirLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		salirLabel.setTranslateY(Config.X_OPCION3);
		
		crearLabel.setOnMouseClicked(e -> {
			contenedorMenu.getChildren().removeAll(crearLabel, unirseLabel, salirLabel);	
			getGrid(false);
			});
		
		unirseLabel.setOnMouseClicked(e -> {
			contenedorMenu.getChildren().removeAll(crearLabel, unirseLabel, salirLabel);	
			getGrid(true);
			;});
		
		salirLabel.setOnMouseClicked(e -> {System.exit(0);});
		
		contenedorMenu.getChildren().addAll(crearLabel, unirseLabel, salirLabel);	
	}

	private void getGrid(boolean isLogin)
	{
		grid = new GridPane();
		grid.setAlignment(Pos.BASELINE_CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(450, 150, 100, 100));
		
		contenedorMenu.getChildren().add(grid);
				
		Text scenetitle = new Text();
		
		if(isLogin)
			scenetitle.setText("Login");
		else
			scenetitle.setText("Registrarse");
		
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		scenetitle.setFill(Color.WHITE);
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("User Name:");
		userName.setTextFill(Color.WHITE);
		grid.add(userName, 0, 1);

		final TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Label pw = new Label("Password:");
		pw.setTextFill(Color.WHITE);
		grid.add(pw, 0, 2);

		final PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);
		
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
            	contenedorMenu.getChildren().addAll(crearLabel, unirseLabel, salirLabel);
            }
        });
        
        btnOk.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent e) {
            	try
            	{
            		boolean result = false;
                	if(isLogin)
                	{
                		result = gestorLogin.login(userTextField.getText(), pwBox.getText());
                		
                		if(result)
            			{
            				actiontarget.setText("Se inicio correctamente");
            				actiontarget.setFill(Color.GREEN);
            				Thread.sleep(2000);
            				g.startMenu();
            			}
                		else
                		{
                			actiontarget.setText("Error al logearse");
                			actiontarget.setFill(Color.RED);
                			Thread.sleep(2000);
                		}
                		
                	}
                	else
                	{
                		result = gestorLogin.registrarUsuario(userTextField.getText(), pwBox.getText());
                		if(result)
                		{
                			actiontarget.setText("Se registro correctamente");
                			actiontarget.setFill(Color.GREEN);
                		}
                		else
                		{
                			actiontarget.setFill(Color.RED);
                			actiontarget.setText("No se pudo registrarse");
                		}
                			
                		//ARREGLAR ESTO PORQUE NO SE MUESTRAN LOS MENSAJES
                		Thread.sleep(2000);
                		contenedorMenu.getChildren().remove(grid);
                    	contenedorMenu.getChildren().addAll(crearLabel, unirseLabel, salirLabel);
                	}      
            	}
            	catch(Exception ex)
            	{
            		 System.out.println(ex.getMessage());;
            	}
            }
        });
	}

}
