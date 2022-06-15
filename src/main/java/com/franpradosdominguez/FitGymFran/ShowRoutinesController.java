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

public class ShowRoutinesController {

	private Cliente c = new Cliente();
	private List<Rutina> todasMisRutinas;
	private ClienteDAO cdao = new ClienteDAO();
	private RutinaDAO rdao = new RutinaDAO();

	@FXML
	private TableView<Rutina> rutinas;
	@FXML
	private TableColumn<Rutina, Integer> id;
	@FXML
	private TableColumn<Rutina, String> nameR;
	@FXML
	private TableColumn<Rutina, String> desc;

	@FXML
	private Button btadd;
	@FXML
	private Button back;

	/**
	 * Este método se encarga de cambiarnos de vista, que en este caso nos dirigimos
	 * a la vista secundaria.
	 * 
	 * @param event
	 * @throws IOException: controla la excepción de la ruta a la que nos dirijimos.
	 */
	@SuppressWarnings("exports")
	@FXML
	public void switchToSecundary(ActionEvent event) throws IOException {

		Object eventSource = event.getSource();
		Node node = (Node) eventSource;
		Scene oldScene = node.getScene();
		Window w = oldScene.getWindow();
		Stage stage = (Stage) w;
		stage.hide();

		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("secondary.fxml"));
		Parent r = loader1.load();
		SecondaryController sc = loader1.getController();
		sc.initAttributes(c);
		Scene scene = new Scene(r, 600, 400);
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

	/**
	 * Este método se encarga de iniciar los valores del propio cliente seleccionado
	 * en los campos de la vista anterior.
	 * 
	 * @param client: cliente que se le pasa seleccionado.
	 */
	protected void initAttributes(Cliente client) {
		this.c = client;
		this.initialize();
	}

	@FXML
	protected void initialize() {
		this.configuraTabla();
		todasMisRutinas = (List<Rutina>) rdao.getAll();
		rutinas.setItems(FXCollections.observableArrayList(todasMisRutinas));

	}

	private void configuraTabla() {
		id.setCellValueFactory(routine -> {
			ObservableValue<Integer> ov = new SimpleIntegerProperty().asObject();
			((ObjectProperty<Integer>) ov).setValue(routine.getValue().getIdRutina());
			return ov;
		});

		nameR.setCellValueFactory(routine -> {
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
	 * Este método se encarga de añadir una rutina al cliente que previamente
	 * hayamos seleccionado en la vista anterior (Vista Secundaria).
	 * 
	 * @throws Exception: controla la excepción de la ruta a la que nos dirijimos.
	 */
	@FXML
	private void addRoutineByClient() throws Exception {

		Rutina rutina = this.rutinas.getSelectionModel().getSelectedItem();

		if (rutina == null) {
			Dialog.showError("Message", "Selecciona una rutina",
					"Seleccionando una rutina podrás asignarsela al cliente");

		} else {

			try {

				FXMLLoader loader1 = new FXMLLoader(getClass().getResource("secondary.fxml"));
				Parent r = loader1.load();
				SecondaryController sc = loader1.getController();
				sc.initAttributes(c);

				Scene scene = new Scene(r, 600, 400);
				Stage newStage = new Stage();
				newStage.setScene(scene);
				newStage.setTitle("Sala");
				newStage.show();

				this.cdao.addRoutineForClient(c, rutina);
				
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

			Stage stage = (Stage) this.btadd.getScene().getWindow();
			stage.close();
		}
	}
}
