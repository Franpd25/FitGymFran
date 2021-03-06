package com.franpradosdominguez.FitGymFran;

import java.io.IOException;
import java.util.List;

import com.franpradosdominguez.FitGymFran.model.DAO.RutinaDAO;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class PSControllerButtonRoutine {
	
	private List<Rutina> todasMisRutinas;
	private RutinaDAO rdao = new RutinaDAO();

	@FXML
	private TableView<Rutina> misRutinas;
	@FXML
	private TableColumn<Rutina, Integer> id_Routine;
	@FXML
	private TableColumn<Rutina, String> nameRoutine;
	@FXML
	private TableColumn<Rutina, String> desc;
	
	@FXML
	private Button btadd;
	@FXML
	private Button btedit;
	@FXML
	private Button btdelete;
	@FXML
	private Button back;
	
	@FXML
	protected void initialize() {
		this.configuraTabla();
		todasMisRutinas = (List<Rutina>) rdao.getAll();
		misRutinas.setItems(FXCollections.observableArrayList(todasMisRutinas));
		misRutinas.refresh();
	}
	
	private void configuraTabla() {
		id_Routine.setCellValueFactory(routine -> {
			ObservableValue<Integer> ov = new SimpleIntegerProperty().asObject();
			((ObjectProperty<Integer>) ov).setValue(routine.getValue().getIdRutina());
			return ov;
		});
		
		nameRoutine.setCellValueFactory(routine -> {
			SimpleStringProperty ssp = new SimpleStringProperty();
			ssp.setValue(routine.getValue().getNombreRutina());
			return ssp;
		});
		
		desc.setCellValueFactory(routine -> {
			SimpleStringProperty ssp = new SimpleStringProperty();
			ssp.setValue(routine.getValue().getDescripcion());
			return ssp;
		});
	}
	
	/**
	 * Este m??todo se encarga de a??adir una rutina, para ello hace
	 * referencia al bot??n A??adir que nos lleva a la vista de A??adir Rutina.
	 * @throws Exception: controla la excepci??n de la ruta a la que nos dirijimos.
	 */
	@SuppressWarnings("unused")
	@FXML
	private void newAddRoutine() throws Exception {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("addRoutine.fxml"));
			Parent root = loader.load();
			AddRoutineController arc = loader.getController();
			
			Scene scene = new Scene(root, 250, 440);
			Stage stage = new Stage();
			stage.setTitle("A??adir Rutina");
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.showAndWait();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Este m??todo se encarga de editar una rutina, para ello hace
	 * referencia al bot??n Modificar que nos lleva a la vista de Editar Rutina.
	 * @throws Exception: controla la excepci??n de la ruta a la que nos dirijimos.
	 */
	@FXML
	private void editRoutine() throws Exception {
		Rutina r = this.misRutinas.getSelectionModel().getSelectedItem();
		
		if (r == null) {
			Dialog.showError("Message", "ERROR. Selecciona una rutina", "Seleccionando una rutina podr??s editarla");
			
		}else {
			
			try {
				FXMLLoader loader1 = new FXMLLoader(getClass().getResource("editRoutine.fxml"));
				Parent root = loader1.load();
				EditRoutineController erc = loader1.getController();
				erc.initAttributes(r);
				
				Scene scene = new Scene(root, 250, 475);
				Stage stage = new Stage();
				stage.setTitle("Editar Rutina");
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setScene(scene);
				stage.showAndWait();
				
				Rutina aux = erc.getR();
				if (aux != null) {
					rdao.update(r);
					misRutinas.refresh();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Este m??todo se encarga de elimiar una rutina, para ello hace
	 * referencia al bot??n Borrar. Hay que seleccionar la rutina
	 * para poder borrarla, si no nos salta un mensaje de ERROR.
	 */
	@FXML
	private void deleteRoutine() {
		Rutina r = this.misRutinas.getSelectionModel().getSelectedItem();
		
		if (r == null) {
			Dialog.showError("Message", "Error. Selecciona una rutina", "Seleccionando una rutina podr??s borrarla");
			
		}else {
			Dialog.showConfirm("Message", "??Estas seguro de querer borrar esta rutina?", "");
			this.rdao.delete(r);
			misRutinas.refresh();
		}
	}
	
	/**
	 * Este m??todo se encarga de volver a la vista anterior, en nuestra caso,
	 * de poder volver a la vista del Men?? Principal.
	 * @throws Exception: controla la excepci??n de la ruta a la que nos dirijimos.
	 */
	@FXML
	private void hadleBack(ActionEvent event) throws Exception {
		try {
			Object eventSource = event.getSource();
			Node node = (Node) eventSource;
			Scene oldScene = node.getScene();
			Window w = oldScene.getWindow();
			Stage stage = (Stage) w;
			stage.hide();

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
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void Refresh() {
		initialize();
	}
}
