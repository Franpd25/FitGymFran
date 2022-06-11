package com.franpradosdominguez.FitGymFran;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuPrincipal {

	@FXML
	private Button client;
	@FXML
	private Button routine;
	
	/**
	 * Este se encarga de dirigirse a la vista de los clientes mediante 
	 * el botón CLIENTES.
	 * @param event
	 * @throws Exception: controla la excepción de la ruta a la que 
	 * se dirije.
	 */
	@SuppressWarnings("exports")
	@FXML
	public void switchToButtonClients(ActionEvent event) throws Exception {
		((Node) (event.getSource())).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("primary.fxml"));
		Scene scene = new Scene(root, 600, 400);
		Stage newStage = new Stage();
		newStage.setScene(scene);
		newStage.setTitle("Clientes");
		newStage.show();
	}
	
	/**
	 * Este se encarga de dirigirse a la vista de las rutinas mediante 
	 * el botón RUTINAS.
	 * @param event
	 * @throws Exception: controla la excepción de la ruta a la que 
	 * se dirije.
	 */
	@SuppressWarnings("exports")
	@FXML
	public void switchToButtonRoutine(ActionEvent event) throws Exception {
		((Node) (event.getSource())).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("routineview.fxml"));
		Scene scene = new Scene(root, 600, 400);
		Stage newStage = new Stage();
		newStage.setScene(scene);
		newStage.setTitle("Rutinas");
		newStage.show();
	}
}