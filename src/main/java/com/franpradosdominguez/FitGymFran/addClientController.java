package com.franpradosdominguez.FitGymFran;

import java.io.IOException;

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
	//private List<Cliente> clientes;
	
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

	@FXML
	private void addCli() throws IOException {
		
		int id = Integer.parseInt(id_c.getText());
		String nombre = name.getText();
		String em = email.getText();
		String dni_c = dni.getText();
		int telefono = Integer.parseInt(phone.getText());
		
		cliente = new Cliente(id, nombre, em, dni_c, telefono);
		cdao.insert(cliente);
		Dialog.showConfirm("Message", "Cliente insertado correctamente", "");
		
		Stage stage = (Stage) this.addClient.getScene().getWindow();
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
