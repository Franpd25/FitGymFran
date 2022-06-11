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
	 * Este método se encarga de logearse como usuario Administrador. Para ello,
	 * debemos de introducir el usuario y la contraseña correctamente para poder
	 * acceder a nuestra App.
	 * @param event
	 * @throws Exception: controla todo tipo de excepción en el caso de
	 * que no inicies correctamente con el usuario indicado.
	 */
	@FXML
	private void eventAction(ActionEvent event) throws Exception {
		Object evt = event.getSource();
		if (evt.equals(sign)) {
			if (!nickname.getText().isEmpty() && !password.getText().isEmpty()) {

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
					Dialog.showError("Message", "Error al iniciar sesión", "Los datos de acceso son incorrectos");
				}
				
			} else {
				Dialog.showError("Message", "ERROR. Introduce usuario y contraseña", "");
			}
		}
	}

	/**
	 * Este método se encarga de cambiar de vista, en nuestro caso
	 * a la vista del Menú Principal.
	 * @param event 
	 * @throws IOException excepción que controla este método por
	 * ruta indicada, donde nos dirigimos.
	 */
	private void switchToPrimary(ActionEvent event) throws IOException {

		((Node) (event.getSource())).getScene().getWindow().hide();

		Parent root = FXMLLoader.load(getClass().getResource("menuPrincipal.fxml"));
		Scene scene = new Scene(root, 501, 219);
		Stage newStage = new Stage();
		newStage.setScene(scene);
		newStage.setTitle("Elegir Cliente - Rutina");
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
