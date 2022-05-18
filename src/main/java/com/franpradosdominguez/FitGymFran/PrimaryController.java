package com.franpradosdominguez.FitGymFran;

import java.io.IOException;
import java.util.List;

import com.franpradosdominguez.FitGymFran.model.DAO.ClienteDAO;
import com.franpradosdominguez.FitGymFran.model.DataObject.Cliente;

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
	private TableColumn<Cliente, Integer> telefono;
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
			((ObjectProperty<Integer>) ov).setValue(client.getValue().getId());
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
			ObservableValue<Integer> ov = new SimpleIntegerProperty().asObject();
			((ObjectProperty<Integer>) ov).setValue(client.getValue().getPhone());
			return ov;
		});
	}

	@FXML
	private void newAddClient() {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("addClient.fxml"));
			Parent root = loader.load();
			addClientController acc = loader.getController();

			Scene scene = new Scene(root, 250, 450);
			Stage stage = new Stage();
			stage.setTitle("Añadir Cliente");
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.showAndWait();
			
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

	@FXML
	private void editClient() {

		Cliente c = this.misClientes.getSelectionModel().getSelectedItem();
		
		if (c == null) {
			System.out.println("ERROR. Debes seleccionar un cliente para editarlo...");
			
		}else {
			try {

				FXMLLoader loader1 = new FXMLLoader(getClass().getResource("editClient.fxml"));
				Parent r = loader1.load();
				editClientController ecc = loader1.getController();
				ecc.initAttributes(todosMisClientes, c);

				Scene scene = new Scene(r, 250, 450);
				Stage stage = new Stage();
				stage.setTitle("Editar Cliente");
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setScene(scene);
				stage.showAndWait();
				
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

	@FXML
	private void deleteClient() {

		Cliente c = this.misClientes.getSelectionModel().getSelectedItem();

		if (c == null) {
			System.out.println("Error. Debes seleccionar un cliente");

		} else {
			this.cdao.delete(c);
			misClientes.refresh();
		}
	}
	@FXML
	private void Refresh() {
		initialize();
	}
}
