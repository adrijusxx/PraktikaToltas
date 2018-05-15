package dungeon.Controller;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import dungeon.Model.User;
import dungeon.Model.UsersDao;
import dungeon.View.Dashboard;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Login {
	private BorderPane bpRoot;
	private Stage primaryStage;
	private User user;
	
	public Login(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
		addElementsToScene();
		
		primaryStage.show();
		
	}
	private void addElementsToScene (){	
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images/faviconx.png")));
		//primaryStage.getIcons().add(new Image("https://wowcircle.com/themes/wowcircle/images/horde_small.png"));
		primaryStage.setTitle("");
		this.bpRoot = new BorderPane();
		Scene loginscene = new Scene(this.bpRoot,400,250);
		//butinai reikia sitos eilutes kad veiktu css
		loginscene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
		Label lblUsername = new Label("Prisijungimo vardas:");
		TextField tfUsername = new TextField();
		Label lblPassword = new Label("Slaptazodis:");
		PasswordField pfPassword = new PasswordField();
		
		
		Button btnLogin = new Button("Prisijungti");
		btnLogin.setMinWidth(99);
		btnLogin.setMinHeight(27);
		btnLogin.setAlignment(Pos.CENTER);
		
		Button btnRegister = new Button("Registruotis");
		btnRegister.setMinWidth(99);
		btnRegister.setMinHeight(27);
		btnRegister.setAlignment(Pos.CENTER);
		
		Label lblMessage = new Label ();
		btnRegister.setOnAction((ActionEvent e)->{
			Register register = new Register(this.primaryStage);
		});
		

		
		btnLogin.setOnAction((ActionEvent e)->{
			UsersDao userDao = new UsersDao();
			 //String passwordencoded = encodePassword();
			User user = userDao.getUser(tfUsername.getText().toString(), 
					pfPassword.getText().toString());
				if (user.getUsername() != null) { // CIA GALI BUTI BEDA 
				Dashboard dashBoard = new Dashboard(this.primaryStage, user);
			}else if(!Validation.isValidCredentials(tfUsername.getText().toString())){
				lblMessage.setText("Neteisingas vartotojo vardas");
				lblMessage.setTextFill(Color.RED);
			}else if(!Validation.isValidCredentials(pfPassword.getText().toString())){
				lblMessage.setText("Neteisingas slaptazodis");
				lblMessage.setTextFill(Color.RED);
			}else{
				lblMessage.setText("Blogi prisijungimo duomenys");
				lblMessage.setTextFill(Color.RED);
			}
			
		});

		HBox hbLoginText = new HBox();
		
		//drop shadow effect
		DropShadow dropshadow = new DropShadow();
		dropshadow.setOffsetX(5);
		dropshadow.setOffsetY(5);
		
		
		hbLoginText.setPadding(new Insets(10,10,10,10));
		hbLoginText.setAlignment(Pos.CENTER);
		Text txtLogin = new Text("PRISIJUNGIMAS");
		txtLogin.setFont(Font.font("Courier New", FontWeight.BOLD, 22));
		txtLogin.setEffect(dropshadow);
		txtLogin.setFill(Color.WHITE);
	    txtLogin.setStrokeWidth(1);
	    txtLogin.setStroke(Color.BLACK);
	
		hbLoginText.getChildren().add(txtLogin);
		
		GridPane gpLogin = new GridPane();
		gpLogin.add(lblUsername,0,0);
		gpLogin.add(tfUsername,1,0);
		gpLogin.add(lblPassword,0,1);
		gpLogin.add(pfPassword,1,1);
		gpLogin.add(lblMessage, 1, 2);
		gpLogin.add(btnLogin, 0, 4);
		gpLogin.add(btnRegister, 2, 4);
		gpLogin.setPadding(new Insets(10,10,10,10));
		gpLogin.setVgap(10);
		gpLogin.setHgap(10);
		
		//reflection for GridPane
		Reflection reflection = new Reflection();
		reflection.setFraction(1f);
		gpLogin.setEffect(reflection);
		
		//sudedam elementus i BorderPane konteineri
		
		bpRoot.setTop(hbLoginText);
		bpRoot.setCenter(gpLogin);
		
		//susiejimas su style.css
		lblMessage.setId("errortext");
		gpLogin.setId("gridPane");
		lblUsername.setId("logintext");
		lblPassword.setId("logintext");
		bpRoot.setId("borderPane"); //loginas
		btnLogin.setId("buttons");
		btnRegister.setId("buttons");
		txtLogin.setId("text");
		
		
		primaryStage.setScene(loginscene);
		primaryStage.show();
		
	}
	public String encodePassword(String password){
		String str = "";
		MessageDigest md;
		try {
			byte[] bytesOfMessage = password.getBytes("UTF-8");
			md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);
			 str = new String(thedigest, StandardCharsets.UTF_8);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return str;
	}
}

	


