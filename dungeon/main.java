package dungeon;

import dungeon.Controller.Login;
import javafx.application.Application;
import javafx.stage.Stage;

public class main extends Application {	
	@Override
	public void start(Stage primaryStage) {
		Login login = new Login(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}	
}
