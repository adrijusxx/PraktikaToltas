package dungeon.Controller;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import dungeon.Model.User;
import dungeon.Model.UsersDao;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;


public class Register {
	private BorderPane root;
	private Stage primaryStage; 
	
	
	Register(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.root = new BorderPane();
		
		Scene scene = new Scene(this.root,405,250);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		
		this.primaryStage.setScene(scene);
		this.primaryStage.setTitle("");
		this.primaryStage.setResizable(false);
		this.primaryStage.centerOnScreen();
		
		addElementsToScene();						
		primaryStage.show();
		
	}
	
	private void addElementsToScene (){
		Label lblUsername = new Label("Prisijungimo vardas:");
		TextField tfUsername = new TextField();
		
		HBox hbRegisterText = new HBox();
		
		//drop shadow effect
		DropShadow dropshadow = new DropShadow();
		dropshadow.setOffsetX(5);
		dropshadow.setOffsetY(5);
		
		
		hbRegisterText.setPadding(new Insets(10,10,10,10));
		hbRegisterText.setAlignment(Pos.CENTER);
		Text txtRegister = new Text("VARTOTOJO REGISTRACIJA");
		txtRegister.setFont(Font.font("Courier New", FontWeight.BOLD, 22));
		txtRegister.setFill(Color.WHITE);
	    txtRegister.setStrokeWidth(1);
	    txtRegister.setStroke(Color.BLACK);
		//txtRegister.setEffect(dropshadow);
	
		hbRegisterText.getChildren().add(txtRegister);
		
		Label lblEmail = new Label("El.pastas:");
		TextField tfEmail = new TextField();
		
		Label lblPassword = new Label("Slaptazodis:");
		PasswordField pfPassword = new PasswordField();
		Label lblPassword1 = new Label("Pakartokite slaptazodi:");
		PasswordField pfPassword1 = new PasswordField();
		Button btnRegister = new Button("Registruotis");
		btnRegister.setMinWidth(100);
		btnRegister.setAlignment(Pos.CENTER);
		
		Button btnGrizti = new Button("Grizti");
		btnGrizti.setMinWidth(99);
		btnGrizti.setMinHeight(27);
		btnGrizti.setAlignment(Pos.CENTER);
		
		btnGrizti.setOnAction((ActionEvent e)->{
			Login grizti = new Login(this.primaryStage);
		});
		
		GridPane gpRegister = new GridPane();
		gpRegister.add(lblUsername,0,0);
		gpRegister.add(tfUsername,1,0);
		gpRegister.add(lblEmail,0,1);
		gpRegister.add(tfEmail,1,1);
		gpRegister.add(lblPassword,0,2);
		gpRegister.add(pfPassword,1,2);
		gpRegister.add(lblPassword1,0,3);
		gpRegister.add(pfPassword1,1,3);
		gpRegister.add(btnRegister, 1, 4);
		gpRegister.add(btnGrizti, 2, 4);
		gpRegister.setPadding(new Insets(10,10,10,10));
		gpRegister.setVgap(10);
		gpRegister.setHgap(10);
				
		//susiejimas su style.css
		gpRegister.setId("gridPane");
		root.setId("borderPane");
		btnRegister.setId("buttons");
		btnGrizti.setId("buttons");
		lblUsername.setId("text");	
		lblPassword.setId("text");
		lblPassword1.setId("text");
		lblEmail.setId("text");
		txtRegister.setId("text");
		
		this.root.setTop(hbRegisterText);
		this.root.setCenter(gpRegister);
		
		btnRegister.setOnAction(new EventHandler<ActionEvent>(){
		@Override
		public void handle(ActionEvent event){
			if(!Validation.isValidCredentials(tfUsername.getText().toString())){
				showAlert(Alert.AlertType.ERROR, gpRegister.getScene().getWindow(), "Klaida!", "Iveskite varda…");
			return;
			}
		if(!Validation.isValidEmail(tfEmail.getText().toString())){
			showAlert(Alert.AlertType.ERROR, gpRegister.getScene().getWindow(), "Klaida!", "Iveskite el. pasta…");
			return;
			}
		if(!Validation.isValidCredentials(pfPassword.getText().toString())){
			showAlert(Alert.AlertType.ERROR, gpRegister.getScene().getWindow(), "Klaida!", "Netinkamas slaptazodis");
			return;
			}
		if(!pfPassword.getText().toString().equals(pfPassword1.getText().toString())){
			showAlert(Alert.AlertType.ERROR, gpRegister.getScene().getWindow(), "Klaida!", "Slaptazodziai nesutampa");
			return;
			}
		try {
			//String username, String password, int userlevel, String email
			User useris = new User(tfUsername.getText().toString(),pfPassword.getText().toString(),1,tfEmail.getText().toString());
			UsersDao userDao = new UsersDao();
			userDao.addUser(useris);
			showAlert(Alert.AlertType.INFORMATION, gpRegister.getScene().getWindow(), "Registracija sekminga!", "Sveiki " + tfUsername.getText().toString());
			Login login = new Login(primaryStage);
		} catch (MySQLIntegrityConstraintViolationException e) {
			showAlert(Alert.AlertType.INFORMATION, gpRegister.getScene().getWindow(), "Form Info!", "Toks vartotojo vardas arba el.pastas jau egzistuoja");
			// e.printStackTrace();
		}
		}
	});
	
	
	}
	private void showAlert(Alert.AlertType alerType, Window owner, String title, String message){
		Alert alert = new Alert(alerType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
		}
}
