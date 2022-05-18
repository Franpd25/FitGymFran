package com.franpradosdominguez.FitGymFran;

import java.io.IOException;
import java.util.List;

import com.franpradosdominguez.FitGymFran.model.DAO.ClienteDAO;
import com.franpradosdominguez.FitGymFran.model.DataObject.Cliente;
import com.franpradosdominguez.FitGymFran.utils.Dialog;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class editClientController {

	private ClienteDAO cdao = new ClienteDAO();
	private Cliente cliente;
	// private List<Cliente> clientes;

	@FXML
	private TextField id_Client;
	@FXML
	private TextField nameClient;
	@FXML
	private TextField emailClient;
	@FXML
	private TextField dniClient;
	@FXML
	private TextField phoneClient;

	@FXML
	private Button save;
	@FXML
	private Button back;

	public void initAttributes(List<Cliente> clientes, Cliente c) {
		//this.clientes = clientes;
		this.cliente = c;
		this.id_Client.setText(c.getId() + "");
		this.nameClient.setText(c.getName());
		this.emailClient.setText(c.getEmail());
		this.dniClient.setText(c.getDni());
		this.phoneClient.setText(c.getPhone() + "");
	}

	@FXML
	private void saveClient() throws IOException {

		int id = Integer.parseInt(id_Client.getText());
		String nombre = nameClient.getText();
		String em = emailClient.getText();
		String dni_c = dniClient.getText();
		int telefono = Integer.parseInt(phoneClient.getText());

		// Modificando...
		if (this.cliente != null) {
			// this.cliente.setId(id);
			this.cliente.setName(nombre);
			this.cliente.setEmail(em);
			this.cliente.setDni(dni_c);
			this.cliente.setPhone(telefono);
			cdao.update(cliente);
			Dialog.showConfirm("Message", "Cliente correctamente editado", "");
		}

		Stage stage = (Stage) this.save.getScene().getWindow();
		stage.close();

	}

	@FXML
	public void handleBack() {

		try {
			FXMLLoader loader1 = new FXMLLoader(getClass().getResource("primary.fxml"));
			Parent r = loader1.load();
			PrimaryController pcc = loader1.getController();
			Scene scene = new Scene(r, 600, 400);
			Stage stage = (Stage) this.back.getScene().getWindow();
			stage.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Cliente getCliente() {
		return cliente;
	}

}
