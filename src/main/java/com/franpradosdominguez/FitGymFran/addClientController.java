package com.franpradosdominguez.FitGymFran;

import java.io.IOException;
import java.util.regex.Pattern;

import com.franpradosdominguez.FitGymFran.model.DAO.ClienteDAO;
import com.franpradosdominguez.FitGymFran.model.DataObject.Cliente;
import com.franpradosdominguez.FitGymFran.utils.Dialog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addClientController {

	private ClienteDAO cdao = new ClienteDAO();
	private Cliente cliente;
	// private List<Cliente> clientes;

	@FXML
	private TextField id_c;
	@FXML
	private TextField name;
	@FXML
	private TextField email;
	@FXML
	private TextField dni;
	@FXML
	private TextField phone;

	@FXML
	private Button addClient;
	@FXML
	private Button back;

	@SuppressWarnings("unused")
	@FXML
	private void addCli() throws IOException {

		String nombre = name.getText();
		String e = email.getText();
		String dni_c = dni.getText();
		String telefono = phone.getText();
		
		if (cliente == null) {
			Dialog.showError("Message",
					"Inserta los campos: \n" + "- NOMBRE \n" + "- EMAIL \n" + "- DNI \n" + "- TELEFONO",
					"Hay que insertar los campos para poder inscribir al cliente");
			
			if (nombre == null) {
				Dialog.showError("Message", "Inserta el Nombre",
						"Hay que insertar el Nombre para completar la inscripción del cliente");
			} else {
				nombre = name.getText();
			}

			if (e == null) {
				Dialog.showError("Message", "Inserta el Email",
						"Hay que insertar el email para completar la inscripción del cliente");

			} else {
				e = email.getText();
				Pattern pat = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");
			}

			if (dni_c == null) {
				Dialog.showError("Message", "Inserta el DNI",
						"Hay que insertar el DNI para completar la inscripción del cliente");
				
			} else {
				//EXPRESION REGULAR
				dni_c.matches("^[0-9]{8}[A-Z]$");
				dni_c = dni.getText();
			}

			if (telefono == null) {
				Dialog.showError("Message", "Inserta el Teléfono",
						"Hay que insertar el teléfono para completar la inscripción del cliente");
				
			} else {
				telefono = phone.getText();
			}
			
			
		}else {
			cliente = new Cliente(nombre, e, dni_c, telefono);
			cdao.insert(cliente);
			Dialog.showConfirm("Message", "Cliente insertado", "Has insertado correctamente al cliente con todos sus campos");
			
			Stage stage = (Stage) this.addClient.getScene().getWindow();
			stage.close();
		}

		

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
