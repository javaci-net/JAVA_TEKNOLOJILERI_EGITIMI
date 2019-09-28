package application;

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

public class DisplayLoginPage {
	
	Scene scene;
	BooleanProperty isButtonClicked;
	
	public DisplayLoginPage() {
		isButtonClicked = new SimpleBooleanProperty(Boolean.FALSE);
		GridPane pane = new GridPane();

		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(12, 8, 12, 8));
		pane.setHgap(8);
		pane.setVgap(8);

		Label emailLabel = new Label("email");
		TextField emailTextField = new TextField();
		Label passwordLabel = new Label("password");
		TextField passwordTextField = new TextField();
		Button buttonLogin = new Button("Login");
		
		buttonLogin.setOnAction(e -> isButtonClicked.set(!isButtonClicked.get()));
		
		Button buttonSignUp = new Button("SignUp");
		HBox buttonGroup = new HBox();
		buttonGroup.setAlignment(Pos.BASELINE_RIGHT);

		buttonGroup.setMargin(buttonSignUp, new Insets(0, 0, 0, 8) );
		buttonGroup.getChildren().addAll(buttonLogin, buttonSignUp);
		
		pane.add(emailLabel, 0, 0);
		pane.add(emailTextField, 1, 0);
		pane.add(passwordLabel, 0, 1);
		pane.add(passwordTextField, 1, 1);
		
		pane.add(buttonGroup, 1, 2);
		scene = new Scene(pane);
	}
	
	protected Scene getScene() {
		return scene;
	}

}
