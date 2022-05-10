package com.franpradosdominguez.FitGymFran;

import java.io.IOException;
import java.util.List;

import com.franpradosdominguez.FitGymFran.model.DAO.ClienteDAO;
import com.franpradosdominguez.FitGymFran.model.DAO.RutinaDAO;
import com.franpradosdominguez.FitGymFran.model.DataObject.Cliente;
import com.franpradosdominguez.FitGymFran.model.DataObject.ExercisesTypes;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class SecondaryController {
	
	private ClienteDAO cdao = new ClienteDAO();
	private RutinaDAO rdao = new RutinaDAO();
	private List<Cliente> todosMisClientes;
	
	@FXML
	private TableView<Cliente> misClientes;
	@FXML
	private TableColumn<Cliente, String> nombre;
	@FXML
    private ChoiceBox<String> tipoLunes;
	@FXML
    private ChoiceBox<String> tipoMartes;
	@FXML
    private ChoiceBox<String> tipoMiercoles;
	@FXML
    private ChoiceBox<String> tipoJueves;
	@FXML
    private ChoiceBox<String> tipoViernes;
	@FXML
	private CheckBox si;
	@FXML
	private CheckBox no;
	@FXML
	private DatePicker fecha;
	
	@FXML
	private Button back;
	@FXML
	private Button save;
	@FXML
	private Button go_out;

	@FXML
	protected void initialize() {
		// TODO Auto-generated method stub
		this.configuraTabla();
		todosMisClientes = (List<Cliente>) cdao.getAll();
		misClientes.setItems(FXCollections.observableArrayList(todosMisClientes));
		
		this.rollOutRoutineExercises();
	}

	private void configuraTabla() {
		nombre.setCellValueFactory(client -> {
			SimpleStringProperty ssp = new SimpleStringProperty();
			ssp.setValue(client.getValue().getName());
			return ssp;
		});
	}
	
	public void rollOutRoutineExercises() {
		Cliente c = this.misClientes.getSelectionModel().getSelectedItem();
		
		if (tipoLunes != null) {
			for (ExercisesTypes et : ExercisesTypes.values()) {
				tipoLunes.getItems().add(et.getType());				
			}
		}
		if (tipoMartes != null) {
			for (ExercisesTypes et : ExercisesTypes.values()) {
				tipoMartes.getItems().add(et.getType());				
			}
		}
		if (tipoMiercoles != null) {
			for (ExercisesTypes et : ExercisesTypes.values()) {
				tipoMiercoles.getItems().add(et.getType());				
			}
		}
		if (tipoJueves != null) {
			for (ExercisesTypes et : ExercisesTypes.values()) {
				tipoJueves.getItems().add(et.getType());				
			}
		}
		if (tipoViernes != null) {
			for (ExercisesTypes et : ExercisesTypes.values()) {
				tipoViernes.getItems().add(et.getType());				
			}
		}
	}
	
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
}