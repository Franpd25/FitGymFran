package com.franpradosdominguez.FitGymFran;

//import java.awt.event.ActionEvent;
import javafx.event.ActionEvent;
import java.io.IOException;

import com.franpradosdominguez.FitGymFran.model.DAO.ClienteDAO;
import com.franpradosdominguez.FitGymFran.utils.Dialog;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LoginController {
	
	private ClienteDAO cdao = new ClienteDAO();

	@FXML
	private TextField nickname;
	@FXML
	private PasswordField password;
	@FXML
	private Button sign;

	/**
	 * Este método sirve para logearse como usuario administrador
	 * @param event
	 */
	@FXML
	private void eventAction(ActionEvent event) {
		Object evt = event.getSource();
		if (evt.equals(sign)) {
			if (!nickname.getText().isEmpty() && !password.getText().isEmpty()) {

				//String user = nickname.getText();
				//String pwd = password.getText();
				//int state = udao.loginUser(user, pwd);

				if (nickname.getText().equals("Admin") && password.getText().equals("1234")) {
					Dialog.showConfirm("Message", "Usuario y contraseña correctos", "");
					
					try {
						this.switchToPrimary(event);
						cdao.getAll();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					Dialog.showError("Message", "Error al iniciar sesión, datos de acceso incorrectos", "");
				}
				

			} else {
				Dialog.showError("Message", "ERROR. Introduce usuario y contraseña", "");
			}
		}
	}

	/**
	 * Este método sirve para cambiarse de ventana, en nuestro caso
	 * a la ventana principal
	 * @param event 
	 * @throws IOException excepción que controla este método
	 */
	private void switchToPrimary(ActionEvent event) throws IOException {

		((Node) (event.getSource())).getScene().getWindow().hide();
		/*Object eventSource = event.getSource();
		Node node = (Node) eventSource;
		Scene oldScene = node.getScene();
		Window w = oldScene.getWindow();
		Stage stage = (Stage) w;
		stage.hide();*/

		Parent root = FXMLLoader.load(getClass().getResource("primary.fxml"));
		Scene scene = new Scene(root, 600, 400);
		Stage newStage = new Stage();
		newStage.setScene(scene);
		newStage.setTitle("Clientes");
		newStage.show();

		newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				// TODO Auto-generated method stub
				Platform.exit();
			}
		});
	}
}
