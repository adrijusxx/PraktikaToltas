
package dungeon.View;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import dungeon.Controller.Login;
import dungeon.Controller.Validation;
import dungeon.Model.Dungeon;
import dungeon.Model.DungeonDao;
import dungeon.Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
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

public class Dashboard {
	private BorderPane bpRoot;
	private Scene scene;
	private Stage primaryStage;
	private String FaultMessage = "KLAIDA!!!!!!!!";
	private TextField IDField;
	private TextField PlayerTaskField;
	private TextField PlayerNameField;
	private TextField PlayerGuild;
	private CheckBox cb1;
	private CheckBox cb2;
	private CheckBox cb3;
	private CheckBox cb4;
	private CheckBox cb5;
	private ToggleGroup group;
	private RadioButton rb1;
	private RadioButton rb2;
	private RadioButton rb3;
	private GridPane gridDungeon;
	private RadioButton selectedRadioButton;
	private ObservableList<Dungeon> data;
	private User user;

	public final static int ADMIN_LEVEL = 9;

	public Dashboard(Stage primaryStage, User user) {
		this.bpRoot = new BorderPane();
		scene = new Scene(this.bpRoot, 1160, 653);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

		this.primaryStage = primaryStage;
		this.user = user;

		this.primaryStage.setScene(scene);
		this.primaryStage.setTitle("Anketa");
		this.primaryStage.setResizable(false);
		this.primaryStage.centerOnScreen();
		addElementsToScene();
		this.primaryStage.show();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void addElementsToScene() {
		Label ID = new Label("ID:");
		Label PlayerName =  new Label("Zaidejo vardas");
		Label PlayerTask = new Label("Zaidejo uzduotis:");
		Label Gearscore = new Label("Uzduoties stiprumas:");
		Label Role = new Label("Role:");
		Label Type = new Label("Klase:");
		Label Guild = new Label("Zaidejo Guild:");

		Label lblPrisijungesV = new Label("Prisijunges vartotojas:");
		lblPrisijungesV.setFont(Font.font("Times New Roman", FontWeight.BOLD, 12));
		lblPrisijungesV.setId("text2");
		Label lblUsername2 = new Label();
		lblUsername2.setText(user.getUsername());

		IDField = new TextField();
		PlayerNameField = new TextField();
		PlayerTaskField = new TextField();
		PlayerGuild = new TextField();

		cb1 = new CheckBox();
		cb2 = new CheckBox();
		cb3 = new CheckBox();
		cb4 = new CheckBox();
		cb5 = new CheckBox();

		cb1.setSelected(true);
		cb1.setText("Tank");
		cb2.setText("Heal");
		cb3.setText("DPS");

		group = new ToggleGroup();
		rb1 = new RadioButton();
		rb2 = new RadioButton();
		rb3 = new RadioButton();
		rb1.setText("4000-5000");
		rb2.setText("5000-6000");
		rb3.setText("6000-7000");
		rb1.setSelected(true);
		rb1.setToggleGroup(group);
		rb2.setToggleGroup(group);
		rb3.setToggleGroup(group);

		@SuppressWarnings("rawtypes")
		ComboBox cbKlase = new ComboBox();
		cbKlase.getItems().addAll("Death Knight", "Druid", "Hunter", "Mage", "Paladin", "Priest", "Rogue", "Shaman",
				"Warlock", "Warrior");
		cbKlase.setValue("Hunter");

		Button btnAdd = new Button("Prideti");
		btnAdd.setMinWidth(99);
		btnAdd.setMinHeight(27);
		Button btnDelete = new Button("Istrinti");
		btnDelete.setMinWidth(99);
		btnDelete.setMinHeight(27);
		Button btnUpdate = new Button("Redaguoti");
		btnUpdate.setMinWidth(99);
		btnUpdate.setMinHeight(27);
		Button btnAssign = new Button("Priskirti");
		btnAssign.setMinWidth(99);
		btnAssign.setMinHeight(27);

		Button btnLogout = new Button("Atsijungti");
		btnLogout.setMinWidth(99);
		btnLogout.setMinHeight(27);
		HBox hbDashboardText = new HBox();

		// drop shadow effect
		DropShadow dropshadow = new DropShadow();
		dropshadow.setOffsetX(5);
		dropshadow.setOffsetY(5);
		hbDashboardText.setAlignment(Pos.TOP_LEFT);
		hbDashboardText.setPadding(new Insets(10, 10, 10, 10));
		/*
		 * Text txtDashboard = new Text("ZAIDEJU REGISTRACIJA");
		 * txtDashboard.setFont(Font.font("Courier New", FontWeight.BOLD, 30));
		 * txtDashboard.setEffect(dropshadow); txtDashboard.setId("textDB");
		 * txtDashboard.setFill(Color.WHITE); txtDashboard.setStrokeWidth(1);
		 * txtDashboard.setStroke(Color.BLACK);
		 * hbDashboardText.getChildren().add(txtDashboard);
		 */

		GridPane gridMygtukai = new GridPane();
		gridMygtukai.add(btnAdd, 0, 0);
		gridMygtukai.add(btnDelete, 1, 0);
		gridMygtukai.add(btnUpdate, 2, 0);
		gridMygtukai.add(btnAssign, 3, 0);

		GridPane gpUserInfo = new GridPane();
		gpUserInfo.add(lblPrisijungesV, 0, 1);
		gpUserInfo.add(lblUsername2, 1, 1);
		gpUserInfo.add(btnLogout, 2, 1);

		GridPane gpTop = new GridPane();
		gpTop.add(hbDashboardText, 0, 0);
		gpTop.add(gpUserInfo, 1, 0);

		HBox hbUserInfo = new HBox();
		hbUserInfo.setAlignment(Pos.TOP_RIGHT);
		hbUserInfo.getChildren().add(gpTop);

		TableView table = new TableView();
		table.setId("table");
		table.setEditable(true);

		// Table columns
		TableColumn IDCol = new TableColumn("ID");
		IDCol.setMinWidth(20);

		TableColumn playernameCol = new TableColumn("Zaidejo Vardas");
		playernameCol.setMinWidth(70);

		TableColumn playertaskCol = new TableColumn("Zaidejo Uzduotis");
		playertaskCol.setMinWidth(70);

		TableColumn playergearscoreCol = new TableColumn("Uzduoties stiprumas");
		playergearscoreCol.setMinWidth(40);

		TableColumn playerroleCol = new TableColumn("Role");
		playerroleCol.setMinWidth(60);

		TableColumn playerclassCol = new TableColumn("Klase");
		playerclassCol.setMinWidth(60);

		TableColumn playerguildCol = new TableColumn("Guild");
		playerguildCol.setMinWidth(50);

		TableColumn selectCol = new TableColumn("Pazymeti");

		if (user.getUserlevel() == ADMIN_LEVEL) {// Adminas mato vartotojus
			table.getColumns().addAll(IDCol, playernameCol, playertaskCol, playergearscoreCol, playerroleCol,
					playerclassCol, playerguildCol);
		} else {// Vartotojas mato
			table.getColumns().addAll(IDCol, playertaskCol, selectCol);

			// Vartotojas nemato
			ID.setVisible(false);
			PlayerTask.setVisible(false);
			Gearscore.setVisible(false);
			Role.setVisible(false);
			Type.setVisible(false);
			Guild.setVisible(false);

			btnAdd.setVisible(false);
			btnDelete.setVisible(false);
			btnAssign.setVisible(false);
			btnUpdate.setVisible(false);

			cbKlase.setVisible(false);
			cb1.setVisible(false);
			cb2.setVisible(false);
			cb3.setVisible(false);

			rb1.setVisible(false);
			rb2.setVisible(false);
			rb3.setVisible(false);

			IDField.setVisible(false);
			PlayerName.setVisible(false);
			PlayerNameField.setVisible(false);
			PlayerTaskField.setVisible(false);
			PlayerGuild.setVisible(false);
		}
		table.setMaxHeight(290);
		table.setMaxWidth(600);

		GridPane pirmasGrid = new GridPane();
		pirmasGrid.addRow(1, rb1);
		pirmasGrid.addRow(2, rb2);
		pirmasGrid.addRow(3, rb3);

		GridPane gridDungeonAbilities = new GridPane();
		gridDungeonAbilities.add(cb1, 1, 1);
		gridDungeonAbilities.add(cb2, 1, 2);
		gridDungeonAbilities.add(cb3, 1, 3);

		IDCol.setCellValueFactory(new PropertyValueFactory<Dungeon, Integer>("id"));
		playernameCol.setCellValueFactory(new PropertyValueFactory<Dungeon, String>("playername"));
		playertaskCol.setCellValueFactory(new PropertyValueFactory<Dungeon, String>("playertask"));
		playergearscoreCol.setCellValueFactory(new PropertyValueFactory<Dungeon, String>("playergearscore"));
		playerroleCol.setCellValueFactory(new PropertyValueFactory<Dungeon, String>("playerrole"));
		playerclassCol.setCellValueFactory(new PropertyValueFactory<Dungeon, String>("playerclass"));
		playerguildCol.setCellValueFactory(new PropertyValueFactory<Dungeon, String>("PlayerGuild"));
		selectCol.setCellValueFactory(new PropertyValueFactory<Dungeon, String>("select"));

		data = FXCollections.observableArrayList();
		DungeonDao dungeonDao = new DungeonDao();
		dungeonDao.showPlayer(data, user);
		table.setItems(data);

		gridDungeon = new GridPane();
		gridDungeon.add(ID, 0, 0);
		gridDungeon.add(IDField, 1, 0);

		gridDungeon.add(PlayerTask, 0, 1);
		gridDungeon.add(PlayerTaskField, 1, 1);
		gridDungeon.add(Guild, 0, 4);
		gridDungeon.add(PlayerGuild, 1, 4);
		gridDungeon.add(Gearscore, 0, 5);
		gridDungeon.add(pirmasGrid, 1, 5);
		gridDungeon.add(Role, 0, 6);
		gridDungeon.add(gridDungeonAbilities, 1, 6);
		gridDungeon.add(Type, 0, 7);
		gridDungeon.add(cbKlase, 1, 7);
		gridDungeon.add(PlayerName, 0, 2);
		gridDungeon.add(PlayerNameField, 1, 2);
		
		gridDungeon.add(gridMygtukai, 0, 9, 2, 1);
		gridDungeon.setPadding(new Insets(15, 15, 15, 15));
		gridDungeon.setVgap(20);
		gridDungeon.setHgap(5); // tarpai tarp teksto ir textfield
		// showAlert(Alert.AlertType.ERROR, gridDungeon.getScene().getWindow(), "Form
		// Klaida!", user.getUsername());

		lblUsername2.setId("text3");
		PlayerTaskField.setId("textfield");
		PlayerNameField.setId("textfield");
		PlayerGuild.setId("textfield");
		IDField.setId("textfield");
		ID.setId("text");
		PlayerTask.setId("text");
		PlayerName.setId("text");
		Gearscore.setId("text");
		Role.setId("text");
		Type.setId("text");
		Guild.setId("text");
		cb1.setId("text");
		cb2.setId("text");
		cb3.setId("text");
		rb1.setId("text");
		rb2.setId("text");
		rb3.setId("text");
		btnAdd.setId("buttons");
		btnLogout.setId("buttons");
		btnUpdate.setId("buttons");
		btnAssign.setId("buttons");
		btnDelete.setId("buttons");

		bpRoot.setId("bpRootDashboard");

		bpRoot.setTop(hbUserInfo);
		bpRoot.setLeft(gridDungeon);
		bpRoot.setCenter(table);

		btnLogout.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Login login = new Login(primaryStage);
			}
		});

		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (dungeon_validate("add")) {
					Dungeon dungeonas = new Dungeon(Integer.parseInt(IDField.getText()), user.getUsername(),
							PlayerTaskField.getText().toString(), selectedRadioButton.getText().toString(), FaultMessage,
							cbKlase.getValue().toString(),
							PlayerGuild.getText().toString());
					try {
						dungeonDao.addPlayer(dungeonas);
					} catch (MySQLIntegrityConstraintViolationException e) {
						showAlert(Alert.AlertType.INFORMATION, gridDungeon.getScene().getWindow(), "Klaida!",
								"Toks zaidejo vardas jau egzistuoja");
					}
					// isvalyti table ir sudeti is duombazes su naujai ideta reiksme
					table.getItems().clear();

					dungeonDao.showPlayer(data, user);
/*					PlayerTaskField.clear();
					PlayerGuild.clear();*/
				}
			}

		});

		btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (dungeon_validate("update")) {
					Dungeon updatedungeonas = new Dungeon(Integer.parseInt(IDField.getText()), PlayerNameField.getText().toString(),
							PlayerTaskField.getText().toString(), selectedRadioButton.getText().toString(), FaultMessage,
							cbKlase.getValue().toString(),
							PlayerGuild.getText().toString());

					boolean isExistingEntryIdByUser = false;
					for (int i = 0; i < data.size(); i++) {
						if (data.get(i).getId() == Integer.parseInt(IDField.getText())) {
							isExistingEntryIdByUser = true;
							dungeonDao.updatePlayer(updatedungeonas, user);
							table.getItems().clear();
							dungeonDao.showPlayer(data, user);
						}
					}
					if (!isExistingEntryIdByUser) {
						showAlert(Alert.AlertType.INFORMATION, gridDungeon.getScene().getWindow(), "Klaida!",
								"Toks Id neegzistuoja");
					}

				}
			}
		});
		
		btnAssign.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (dungeon_validate("update")) {
					Dungeon updatedungeonas = new Dungeon(Integer.parseInt(IDField.getText()), PlayerNameField.getText().toString(),
							PlayerTaskField.getText().toString(), selectedRadioButton.getText().toString(), FaultMessage,
							cbKlase.getValue().toString(),
							PlayerGuild.getText().toString());

					boolean isExistingEntryIdByUser = false;
					for (int i = 0; i < data.size(); i++) {
						if (data.get(i).getId() == Integer.parseInt(IDField.getText())) {
							isExistingEntryIdByUser = true;
							dungeonDao.assignPlayer(updatedungeonas, user);
							table.getItems().clear();
							dungeonDao.showPlayer(data, user);
						}
					}
					if (!isExistingEntryIdByUser) {
						showAlert(Alert.AlertType.INFORMATION, gridDungeon.getScene().getWindow(), "Klaida!",
								"Toks Id neegzistuoja");
					}

				}
			}
		});

/*		btnAssign.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				data = dungeonDao.searchPlayerByName(PlayerTaskField.getText().toString(), user);
				table.getItems().clear();
				table.setItems(data);
				if (data.size() == 0) {
					showAlert(Alert.AlertType.INFORMATION, gridDungeon.getScene().getWindow(), "Klaida!",
							"Zaidejas tokiu vardu neegzistuoja");
				}
			}
		});*/

		btnDelete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				dungeon_validate("delete");

				boolean isExistingEntryIdByUser = false;
				for (int i = 0; i < data.size(); i++) {
					if (data.get(i).getId() == Integer.parseInt(IDField.getText())) {
						isExistingEntryIdByUser = true;
						dungeonDao.deletePlayer(Integer.parseInt(IDField.getText()));
						table.getItems().clear();
						dungeonDao.showPlayer(data, user);
					}
				}
				if (!isExistingEntryIdByUser) {
					showAlert(Alert.AlertType.INFORMATION, gridDungeon.getScene().getWindow(), "Klaida!",
							"Toks Id neegzistuoja");
				}
			}
		});
	}

	private boolean dungeon_validate(String action) {
		FaultMessage = "";
		switch (action) {
		case "delete":
			if (!Validation.isValidID(IDField.getText().toString())) {
				showAlert(Alert.AlertType.ERROR, gridDungeon.getScene().getWindow(), "Klaida!",
						"Neteisingas ID formatas");
				return false;
			} else
				return true;

		default:
			if (!Validation.isValidID(IDField.getText().toString())) {
				showAlert(Alert.AlertType.ERROR, gridDungeon.getScene().getWindow(), "Klaida!",
						"Neteisingas ID formatas");
				return false;
			} else if (!Validation.isValidPlayerNameForAdd(PlayerTaskField.getText().toString())) {
				showAlert(Alert.AlertType.ERROR, gridDungeon.getScene().getWindow(), "Klaida!",
						"Netinkamas zaidejo pavadinimas");
				return false;
			} else if (!Validation.isValidPlayerNameForAdd(PlayerGuild.getText().toString())) {
				showAlert(Alert.AlertType.ERROR, gridDungeon.getScene().getWindow(), "Klaida!",
						"Netinkamai ivestas zaidejo guildas");
				return false;
			} else if (!(cb1.isSelected() || cb2.isSelected() || cb3.isSelected() || cb4.isSelected()
					|| cb5.isSelected())) {
				showAlert(Alert.AlertType.ERROR, gridDungeon.getScene().getWindow(), "Klaida!",
						"Pasirinkite bent viena");
				return false;
			} else {
				if (cb1.isSelected()) {
					FaultMessage = cb1.getText();
				}
				if (cb2.isSelected()) {
					FaultMessage = FaultMessage + "\n" + cb2.getText();
				}
				if (cb3.isSelected()) {
					FaultMessage = FaultMessage + "\n" + cb3.getText();
				}
				if (cb4.isSelected()) {
					FaultMessage = FaultMessage + "\n" + cb4.getText();
				}
				if (cb5.isSelected()) {
					FaultMessage = FaultMessage + "\n" + cb5.getText();
				}
				selectedRadioButton = (RadioButton) group.getSelectedToggle();
			}
		}
		return true;
	}

	private void showAlert(Alert.AlertType alerType, Window owner, String title, String message) {
		Alert alert = new Alert(alerType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}
}
