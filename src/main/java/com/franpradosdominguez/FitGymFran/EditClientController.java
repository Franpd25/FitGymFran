package com.franpradosdominguez.FitGymFran;

import java.io.IOException;

import com.franpradosdominguez.FitGymFran.model.DAO.ClienteDAO;
import com.franpradosdominguez.FitGymFran.model.DataObject.Cliente;
import com.franpradosdominguez.FitGymFran.utils.Dialog;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditClientController {

	private ClienteDAO cdao = new ClienteDAO();
	private Cliente cliente;

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

	/**
	 * Este método se encarga de iniciar los valores del propio
	 * cliente seleccionado en los campos de la vista.
	 * @param c: cliente que se le pasa seleccionado.
	 */
	@SuppressWarnings("exports")
	public void initAttributes(Cliente c) {
		this.cliente = c;
		this.id_Client.setText(c.getIdCliente() + "");
		this.nameClient.setText(c.getName());
		this.emailClient.setText(c.getEmail());
		this.dniClient.setText(c.getDni());
		this.phoneClient.setText(c.getPhone());
	}

	/**
	 * Este método se encarga de editar al cliente obteniendo todos
	 * sus campos inicializados para después modificar cualquier campo
	 * para actualizar dicho cliente.
	 * @throws IOException: controlar la excepción para que se
	 * modifique el cliente seleccionado.
	 */
	@SuppressWarnings("unused")
	@FXML
	private void saveClient() throws IOException {

		int id = Integer.parseInt(id_Client.getText());
		String nombre = nameClient.getText();
		String em = emailClient.getText();
		String dni_c = dniClient.getText();
		String telefono = phoneClient.getText();

		// Modificando...
		if (this.cliente != null) {
			this.cliente.setName(nombre);
			this.cliente.setEmail(em);
			this.cliente.setDni(dni_c);
			this.cliente.setPhone(telefono);
			cdao.update(cliente);
			Dialog.showConfirm("Message", "Cliente editado", "Has editado correctamente al cliente seleccionado.");
		}

		Stage stage = (Stage) this.save.getScene().getWindow();
		stage.close();

	}

	/**
	 * Este método se encarga de volver a la vista primaria, en el caso
	 * de que no quieras editar el cliente que se haya seleccionado.
	 */
	@SuppressWarnings("unused")
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

	/**
	 * Este es un método getter para poder obtener el cliente.
	 * @return cliente obtenido.
	 */
	@SuppressWarnings("exports")
	public Cliente getCliente() {
		return cliente;
	}
}
