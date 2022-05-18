package com.franpradosdominguez.FitGymFran;

import java.io.IOException;
import java.util.List;

import com.franpradosdominguez.FitGymFran.model.DAO.ClientRutineDAO;
import com.franpradosdominguez.FitGymFran.model.DAO.ClienteDAO;
import com.franpradosdominguez.FitGymFran.model.DataObject.ClientConverter;

import com.franpradosdominguez.FitGymFran.model.DataObject.Cliente;
import com.franpradosdominguez.FitGymFran.model.DataObject.Rutina;

import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class SecondaryController {
	
	private Cliente c = new Cliente();
	private ClienteDAO cdao = new ClienteDAO();
	private ClientRutineDAO crdao = new ClientRutineDAO();

	@FXML
	private ChoiceBox<Cliente> nombClientes;
	@FXML
	private TextArea texto;
	
	@FXML
	private Button showRutine;
	@FXML
	private Button back;
	@FXML
	private Button salir;

	@FXML
	public void switchToPrimary(ActionEvent event) throws IOException {
		Object eventSource = event.getSource();
		Node node = (Node) eventSource;
		Scene oldScene = node.getScene();
		Window w = oldScene.getWindow();
		Stage stage = (Stage) w;
		stage.hide();

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

	@FXML
	public void go_out() {
		System.exit(0);
	}

	public void initialize() {
		// TODO Auto-generated method stub
		List<Cliente> c = (List<Cliente>) cdao.getAll();
		for (Cliente cliente : c) {
			nombClientes.getItems().add(cliente);
		}
		nombClientes.setConverter(new ClientConverter());
		
	}
	
	@FXML
	public void handleBtnShowRutine() {
		c = nombClientes.getSelectionModel().getSelectedItem();
		List<Rutina> rutinas = crdao.getAllRutineForClient(c);
		texto.setText(rutinas.toString());
	}
	
}