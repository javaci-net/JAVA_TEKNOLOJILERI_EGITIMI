package application;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	// Login icin bi logic yok
	// protected boolean isLogin = false;

	protected Scene scene;

	// tableview a gecis icin gerekli scene
	protected Scene scene2;
	// ayri classtaki addcustomer nesnesinin start metoduna gecmek icin
	protected Stage addCustomerStage = new Stage();
	protected AddCustomer addCustomerPage = new AddCustomer(addCustomerStage);
	// contextmenude secili satirin indexi
	protected int indexOfTableviewSelection;

	@Override
	public void start(Stage primaryStage) throws Exception {

// Login Page
		/**
		 * Deneme amacli farkli class a aldim Classlar arasi veri gecmek cok zahmetli
		 * geldi kesin daha kolay yollari vardir.
		 */
		DisplayLoginPage loginPage = new DisplayLoginPage();
		// Login olmus gibi gosterebilmek icin onceki scene in propertysine listener
		// ekledim
		loginPage.isButtonClicked.addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

				changeStage(scene2, primaryStage);
			}
		});

// Customer List Page
		// TableView pseudo data list
		ObservableList<Customer> data = FXCollections.observableArrayList(new Customer("John", "jhon@jhon", "321321"),
				new Customer("Jane", "jane@jhon", "6546543"));
		// ana sayfa yapisi
		BorderPane borderPane = new BorderPane();
		TableView tableView = new TableView();
		// add customer butonu ve basligi icin
		HBox hBox = new HBox();
		Label addCustomerLabel = new Label("Add Customer");
		Button buttonAddCustomer = new Button("+");
		buttonAddCustomer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				try {
					addCustomerPage.start(addCustomerStage);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		// check if Customer Added

		addCustomerPage.isCustomerAdded.addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

				String addedName = addCustomerPage.getFullName();
				String addedEmail = addCustomerPage.getEmail();
				String addedPhone = addCustomerPage.getPhoneNumber();

				int edittedCustomerIndex = addCustomerPage.getIndex();
				// musteri ekleme ekrani ile ayni oldugu icin sifirladim
				addCustomerPage.clearCustomerPage();
				Customer newOrEdittedCustomer = new Customer(addedName, addedEmail, addedPhone);

				// Yeni kayit
				if (edittedCustomerIndex < 0) {
					// tamami null ise ekleme
					if (!(((addedName.length() == 0) ^ (addedEmail.length() == 0)) ^ (addedPhone.isEmpty()))) {
						data.add(newOrEdittedCustomer);
					} 
				} 
				// Edit
				if (edittedCustomerIndex >= 0) {
					data.remove(edittedCustomerIndex);
					data.add(edittedCustomerIndex, newOrEdittedCustomer);
				}
			}
		});

		hBox.setSpacing(8);
		hBox.getChildren().addAll(addCustomerLabel, buttonAddCustomer);

		hBox.setMargin(buttonAddCustomer, new Insets(8, 8, 8, 0));
		hBox.setAlignment(Pos.BASELINE_RIGHT);

		// Tableview icerigi
		tableView.setItems(data);

		TableColumn nameColumn = new TableColumn("First Name");
		// en sondaki string objenin property degiskeni ile ayni ad olmali yoksa tablda
		// gozukmuyor
		nameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
		TableColumn mailColumn = new TableColumn("Email");
		mailColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("mail"));
		TableColumn phoneColumn = new TableColumn("Phone Number");
		phoneColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNumber"));

		tableView.getColumns().addAll(nameColumn, mailColumn, phoneColumn);

		
		/**
		 * Sag click menu
		 */
		ContextMenu contextMenu = new ContextMenu();

		MenuItem addCustomerMenuItem = new MenuItem("Add New Customer");
		MenuItem deleteCustomerMenuItem = new MenuItem("Delete Customer");

		contextMenu.getItems().addAll(addCustomerMenuItem, deleteCustomerMenuItem);

		tableView.setContextMenu(contextMenu);

		// Context menu acilmasi
		tableView.setOnMouseClicked((MouseEvent event) -> {
			if (event.getButton().equals(MouseButton.SECONDARY)) {
				System.out.println(tableView.getSelectionModel().getSelectedItem());
				indexOfTableviewSelection = data.indexOf(tableView.getSelectionModel().getSelectedItem());
				System.out.println(indexOfTableviewSelection);
			}
		});
		
		// Cift tiklayinca duzenleme gorunumu
		tableView.setOnMouseClicked((MouseEvent event) -> {
			if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
				System.out.println(tableView.getSelectionModel().getSelectedItem());
				indexOfTableviewSelection = data.indexOf(tableView.getSelectionModel().getSelectedItem());
				try {
					addCustomerPage.setIndex(indexOfTableviewSelection);
					Customer selectedCustomer = data.get(indexOfTableviewSelection);
					addCustomerPage.setEmail(selectedCustomer.getMail());
					addCustomerPage.setPhoneNumber(selectedCustomer.getPhoneNumber());
					addCustomerPage.setFullName(selectedCustomer.getName());
					addCustomerPage.start(addCustomerStage);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		// context menudeki addcustomer item i
		addCustomerMenuItem.setOnAction(e -> {
			try {
				addCustomerPage.start(addCustomerStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		// context menudeki deletecustom item i
		deleteCustomerMenuItem.setOnAction(e -> data.remove(indexOfTableviewSelection));

		borderPane.setBottom(hBox);
//		borderPane.getBottom().setStyle("-fx-background-color: rgba(255,255,255,0.5)");
		borderPane.setCenter(tableView);

		// tableview a gecis icin olusturulan scene
		scene2 = new Scene(borderPane, 500, 300);

		tableView.setPrefHeight(scene2.getHeight());
		tableView.setPrefWidth(scene2.getWidth());


		primaryStage.setTitle("Login");
		primaryStage.setResizable(false);
		primaryStage.setScene(loginPage.getScene());
		primaryStage.show();
	}

	
	// tableview a gecis metodu
	private void changeStage(Scene scene2, Stage primaryStage) {

		primaryStage.setScene(scene2);
		primaryStage.show();

		primaryStage.setTitle("Cok gizli musteri listesi");
	}
}
