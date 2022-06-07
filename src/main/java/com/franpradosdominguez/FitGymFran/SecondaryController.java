package com.franpradosdominguez.FitGymFran;

import java.io.IOException;
import java.util.List;

import com.franpradosdominguez.FitGymFran.model.DAO.ClienteDAO;
import com.franpradosdominguez.FitGymFran.model.DAO.RutinaDAO;
import com.franpradosdominguez.FitGymFran.model.DataObject.Cliente;
import com.franpradosdominguez.FitGymFran.model.DataObject.Rutina;
import com.franpradosdominguez.FitGymFran.utils.Dialog;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class SecondaryController {
	
	private Cliente c = new Cliente();
	private ClienteDAO cdao = new ClienteDAO();
	private RutinaDAO rdao = new RutinaDAO();

	@FXML
	private TableView<Rutina> rutinasClientes;
	@FXML	
	private TableColumn<Rutina, Integer> idRoutine;
	@FXML
	private TableColumn<Rutina, String> nameRoutine;
	@FXML
	private TableColumn<Rutina, String> description;
	
	@FXML
	private TextField id_cliente;
	@FXML
	private TextField name;
	
	@FXML
	private Button showRutine;
	@FXML
	private Button addRoutine;
	@FXML
	private Button btdeleteRoutine;
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
		Dialog.showConfirm("Message", "¿Estás seguro de salir de la Aplicación?", "");
		System.exit(0);
	}

	protected void initAttributes(Cliente client) {
		this.c = client;
		this.id_cliente.setText(c.getIdCliente() + "");
		this.name.setText(c.getName());
		
		this.showClientByTextField();
		this.initialize();
	}
	
	private void showClientByTextField() {
		
		int id = Integer.parseInt(id_cliente.getText());
		String nombre = name.getText();
		
		if (this.c != null) {
			this.c.setIdCliente(id);
			this.c.setName(nombre);
		}
	}
	
	@FXML
	protected void initialize() {
		
		rutinasClientes.getItems().clear();
		
		this.configuraTabla();
		
		List<Rutina> misRutinas = cdao.getAllRutineForClient(c);
		ObservableList<Rutina> obList = FXCollections.observableArrayList(misRutinas);
		rutinasClientes.getItems().addAll(obList);
	}
	
	private void configuraTabla() {
		
		idRoutine.setCellValueFactory(routine -> {
			ObservableValue<Integer> ov = new SimpleIntegerProperty().asObject();
			((ObjectProperty<Integer>) ov).setValue(routine.getValue().getIdRutina());
			return ov;
		});

		nameRoutine.setCellValueFactory(routine -> {
			SimpleStringProperty ssp = new SimpleStringProperty();
			ssp.setValue(routine.getValue().getNombreRutina());
			return ssp;
		});
		
		description.setCellValueFactory(routine -> {
			SimpleStringProperty ssp = new SimpleStringProperty();
			ssp.setValue(routine.getValue().getDescripcion());
			return ssp;
		});
	}
	
	@FXML
	private void newAddRoutine() {

		try {
			
			FXMLLoader loader1 = new FXMLLoader(getClass().getResource("showRoutines.fxml"));
			Parent r = loader1.load();
			showRoutinesController src = loader1.getController();
			
			Scene scene = new Scene(r, 600, 400);
			Stage newStage = new Stage();
			newStage.setScene(scene);
			newStage.setTitle("Mostrar Rutinas");
			newStage.show();
			
			newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					// TODO Auto-generated method stub
					Platform.exit();
				}
			});
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Stage stage = (Stage) this.addRoutine.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	private void deleteRoutine() {
		Rutina r = this.rutinasClientes.getSelectionModel().getSelectedItem();
		
		if (r == null) {
			Dialog.showError("Message", "Error. Selecciona una rutina", "Seleccionando una rutina podrás borrarla");
			
		}else {
			Dialog.showConfirm("Message", "¿Estas seguro de querer borrar esta rutina?", "");
			this.rdao.delete(r);
			rutinasClientes.refresh();
		}
	}
	
}