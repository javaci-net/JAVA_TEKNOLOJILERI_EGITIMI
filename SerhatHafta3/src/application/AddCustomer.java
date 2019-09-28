package application;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AddCustomer extends Application {

	Scene scene;
	BooleanProperty isCustomerAdded;
	String email = "";
	String fullName = "";
	String phoneNumber = "";
	int index = -1;

	public AddCustomer(Stage stage) {
		isCustomerAdded = new SimpleBooleanProperty(Boolean.FALSE);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		GridPane pane = new GridPane();

		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(12, 8, 12, 8));
		pane.setHgap(8);
		pane.setVgap(8);

		Label fullNameLabel = new Label("Full Name");
		TextField fullNameTextField = new TextField();
		fullNameTextField.setText(fullName);
		Label emailLabel = new Label("Email");
		TextField emailTextField = new TextField();
		emailTextField.setText(email);
		Label phoneNumberLabel = new Label("Phone Number");
		TextField phoneTextField = new TextField();
		phoneTextField.setText(phoneNumber);
		Button buttonSubmit = new Button("Submit");
		
		buttonSubmit.setOnAction(e -> {
			setFullName(fullNameTextField.getText());
			setPhoneNumber(phoneTextField.getText());
			 setEmail(emailTextField.getText());
			 isCustomerAdded.set(!isCustomerAdded.get());
			 primaryStage.close();
		});
		
		Button buttonCancel = new Button("Cancel");
		buttonCancel.setOnAction(e -> {
			clearCustomerPage();
			primaryStage.close();
		});
		HBox buttonGroup = new HBox();
		buttonGroup.setAlignment(Pos.BASELINE_RIGHT);
		buttonGroup.setMargin(buttonCancel, new Insets(0, 0, 0, 8) );
		buttonGroup.getChildren().addAll(buttonSubmit, buttonCancel);
		
		pane.add(emailLabel, 0, 0);
		pane.add(emailTextField, 1, 0);
		pane.add(phoneNumberLabel, 0, 1);
		pane.add(phoneTextField, 1, 1);
		pane.add(fullNameLabel, 0, 2);
		pane.add(fullNameTextField, 1, 2);
		
		
		pane.add(buttonGroup, 1, 3);
		scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Add Customer");
		primaryStage.show();
		
	}
	
	protected Scene getScene() {
		return scene;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}
	
	public void clearCustomerPage() {
		setEmail("");
		setPhoneNumber("");
		setFullName("");
		setIndex(-1);
	}

	


	

}
