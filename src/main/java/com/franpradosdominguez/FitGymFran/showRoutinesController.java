package com.franpradosdominguez.FitGymFran;

import java.io.IOException;
import java.util.List;

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
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class showRoutinesController {
	
	private static Cliente c = new Cliente();
	private List<Rutina> todasMisRutinas;
	private RutinaDAO rdao = new RutinaDAO();

	@FXML
	private TableView<Rutina> rutinas;
	@FXML
	private TableColumn<Rutina, Integer> idCli;
	@FXML
	private TableColumn<Rutina, String> nameCli;
	@FXML
	private TableColumn<Rutina, String> descCli;
	
	@FXML
	private Button btadd;
	@FXML
	private Button back;
	
	@FXML
	public void switchToSecundary(ActionEvent event) throws IOException {
		SecondaryController.getC();
		
		Object eventSource = event.getSource();
		Node node = (Node) eventSource;
		Scene oldScene = node.getScene();
		Window w = oldScene.getWindow();
		Stage stage = (Stage) w;
		stage.hide();

		Parent root = FXMLLoader.load(getClass().getResource("secondary.fxml"));
		Scene scene = new Scene(root, 600, 400);
		Stage newStage = new Stage();
		newStage.setScene(scene);
		newStage.setTitle("Sala");
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
	protected void initialize() {
		this.configuraTabla();
		todasMisRutinas = (List<Rutina>) rdao.getAll();
		rutinas.setItems(FXCollections.observableArrayList(todasMisRutinas));
		
	}
	
	private void configuraTabla() {
		idCli.setCellValueFactory(routine -> {
			ObservableValue<Integer> ov = new SimpleIntegerProperty().asObject();
			((ObjectProperty<Integer>) ov).setValue(routine.getValue().getIdRutina());
			return ov;
		});
		
		nameCli.setCellValueFactory(routine -> {
			SimpleStringProperty ssp = new SimpleStringProperty();
			ssp.setValue(routine.getValue().getNombreRutina());
			return ssp;
		});
		
		descCli.setCellValueFactory(routine -> {
			SimpleStringProperty ssp = new SimpleStringProperty();
			ssp.setValue(routine.getValue().getDescripcion());
			return ssp;
		});
	}
	
	@FXML
	private void addRoutineByClient(ActionEvent event) {
		Rutina r = this.rutinas.getSelectionModel().getSelectedItem();
		
		if (r == null) {
			Dialog.showError("Message", "Selecciona una rutina", "Seleccionando una rutina podrá asignarsela al cliente");
			
		}else {
			try {
				this.switchToSecundary(event);
				this.rdao.addRoutineForClient(c, r);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static Cliente getC() {
		return c;
	}
	
}
