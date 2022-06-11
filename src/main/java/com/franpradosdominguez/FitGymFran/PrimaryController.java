package com.franpradosdominguez.FitGymFran;

import java.io.IOException;
import java.util.List;

import com.franpradosdominguez.FitGymFran.model.DAO.ClienteDAO;
import com.franpradosdominguez.FitGymFran.model.DataObject.Cliente;
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

public class PrimaryController {

	private ClienteDAO cdao = new ClienteDAO();
	private List<Cliente> todosMisClientes;

	@FXML
	private TableView<Cliente> misClientes;
	@FXML
	private TableColumn<Cliente, Integer> id;
	@FXML
	private TableColumn<Cliente, String> nombre;
	@FXML
	private TableColumn<Cliente, String> email;
	@FXML
	private TableColumn<Cliente, String> dni;
	@FXML
	private TableColumn<Cliente, String> telefono;
	@FXML
	private Button btadd;
	@FXML
	private Button btedit;
	@FXML
	private Button btdelete;
	@FXML
	private Button btsala;
	@FXML
	private Button update;
	@FXML
	private Button back;

	/**
	 * Este método se encarga de cambiarnos de vista, que en este caso
	 * nos dirigimos a la vista secundaria.
	 * @param event
	 * @throws IOException: controla la excepción de la ruta a la que
	 * nos dirijimos.
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
		// TODO Auto-generated method stub
		this.configuraTabla();
		todosMisClientes = (List<Cliente>) cdao.getAll();
		misClientes.setItems(FXCollections.observableArrayList(todosMisClientes));
		misClientes.refresh();
	}

	private void configuraTabla() {
		// TODO Auto-generated method stub
		id.setCellValueFactory(client -> {
			ObservableValue<Integer> ov = new SimpleIntegerProperty().asObject();
			((ObjectProperty<Integer>) ov).setValue(client.getValue().getIdCliente());
			return ov;
		});

		nombre.setCellValueFactory(client -> {
			SimpleStringProperty ssp = new SimpleStringProperty();
			ssp.setValue(client.getValue().getName());
			return ssp;
		});

		email.setCellValueFactory(client -> {
			SimpleStringProperty ssp = new SimpleStringProperty();
			ssp.setValue(client.getValue().getEmail());
			return ssp;
		});

		dni.setCellValueFactory(client -> {
			SimpleStringProperty ssp = new SimpleStringProperty();
			ssp.setValue(client.getValue().getDni());
			return ssp;
		});

		telefono.setCellValueFactory(client -> {
			SimpleStringProperty ssp = new SimpleStringProperty();
			ssp.setValue(client.getValue().getPhone());
			return ssp;
		});
	}
	
	/**
	 * Este método se encarga de seleccionar un cliente para mostrar
	 * la información de su rutina en la siguiente vista, en mi caso, se trata
	 * de la vista secundaria.
	 * @throws Exception: controla la excepción de la ruta dirijida.
	 */
	@FXML
	private void selectClient() throws Exception {
		Cliente c = this.misClientes.getSelectionModel().getSelectedItem();

		if (c == null) {
			Dialog.showError("Message", "ERROR. Selecciona un cliente", "Mostrará la información en la siguiente ventana");
			
		}else {
			
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
			
			Stage stage = (Stage) this.btsala.getScene().getWindow();
			stage.close();
		}
	}
	
	/**
	 * Este método se encarga de añadir un cliente, para ello hace
	 * referencia al botón Inscribir que nos lleva a la vista de Añadir Cliente.
	 * @throws Exception: controla la excepción de la ruta a la que nos dirijimos.
	 */
	@FXML
	private void newAddClient() throws Exception {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("addClient.fxml"));
			Parent root = loader.load();
			AddClientController acc = loader.getController();

			Scene scene = new Scene(root, 250, 450);
			Stage stage = new Stage();
			stage.setTitle("Añadir Cliente");
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.showAndWait();
			
			//Añadiendo un cliente y que se actualize la tabla
			//controlando si el cliente existe para añadirlo...
			Cliente c = acc.getCliente();
			if (c != null) {
				this.todosMisClientes.add(c);
				misClientes.refresh();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Este método se encarga de editar un cliente, para ello hace
	 * referencia al botón Editar que nos lleva a la vista de Editar Cliente.
	 * @throws Exception: controla la excepción de la ruta a la que nos dirijimos.
	 */
	@FXML
	private void editClient() throws Exception {

		Cliente c = this.misClientes.getSelectionModel().getSelectedItem();
		
		if (c == null) {
			Dialog.showError("Message", "ERROR. Selecciona un cliente", "Seleccionando un cliente podrás editarlo");
			
		}else {
			try {

				FXMLLoader loader1 = new FXMLLoader(getClass().getResource("editClient.fxml"));
				Parent r = loader1.load();
				EditClientController ecc = loader1.getController();
				ecc.initAttributes(c);

				Scene scene = new Scene(r, 250, 450);
				Stage stage = new Stage();
				stage.setTitle("Editar Cliente");
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setScene(scene);
				stage.showAndWait();
				
				//Editando un cliente y que se actualize la tabla
				//controlando si el cliente existe para editarlo...
				Cliente aux = ecc.getCliente();
				if (aux != null) {
					cdao.update(c);
					misClientes.refresh();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Este método se encarga de elimiar un cliente, para ello hace
	 * referencia al botón Dar de Baja. Hay que seleccionar el cliente
	 * para poder borrarlo, si no nos salta un mensaje de ERROR.
	 */
	@FXML
	private void deleteClient() {

		Cliente c = this.misClientes.getSelectionModel().getSelectedItem();

		if (c == null) {
			Dialog.showError("Message", "Error. Selecciona un cliente", "Seleccionando un cliente podrás borrarlo");

		} else {
			Dialog.showConfirm("Message", "¿Estas seguro de querer borrar este cliente?", "");
			this.cdao.delete(c);
			misClientes.refresh();
		}
	}
	
	/**
	 * Este método se encarga de volver a la vista anterior, en nuestra caso,
	 * de poder volver a la vista del Menú Principal.
	 * @throws Exception: controla la excepción de la ruta a la que nos dirijimos.
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
