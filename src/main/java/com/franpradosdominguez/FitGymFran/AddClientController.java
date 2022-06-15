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

public class AddClientController {

	private ClienteDAO cdao = new ClienteDAO();
	private Cliente cliente;

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

	/**
	 * Este método se encarga de añadir un cliente mediante una ventaña en la cuál
	 * podemos rellenar los campos para poder añadirlo en nuestra BBDD. En el método
	 * tenemos algunas validaciones de los campos y controlamos que todos los campos
	 * tienen que estar rellenados para poder añadir dicho cliente.
	 * 
	 * @throws IOException: para poder controlar las excepciones en el caso de no
	 *                      introducir datos en algunos de los campos.
	 */

	@FXML
	private void addCli(ActionEvent event) throws IOException {
		Object evt = event.getSource();
		String nombre = name.getText();
		String correo = email.getText();
		String DNI = dni.getText();
		String telefono = phone.getText();
		
		if (evt.equals(addClient)) {
			if (!name.getText().isEmpty() && !email.getText().isEmpty() && !dni.getText().isEmpty()
					&& !phone.getText().isEmpty()) {
				if (name.getText().equals(nombre) && email.getText().equals(correo) && dni.getText().equals(DNI)
						&& phone.getText().equals(telefono)) {
					cliente = new Cliente(nombre, correo, DNI, telefono);
					cdao.insert(cliente);
					Dialog.showConfirm("Message", "Nombre, Email, DNI y Teléfono introducidos correctamente", "");
					
					Stage stage = (Stage) this.addClient.getScene().getWindow();
					stage.close();
					
				}else {
					Dialog.showError("Message", "ERROR. Introduce Nombre, Email, DNI, Teléfono", "");
				}
			}else {
				Dialog.showError("Message",
						"Inserta los campos: \n" + "- NOMBRE \n" + "- EMAIL \n" + "- DNI \n" + "- TELEFONO",
						"Hay que insertar los campos para poder inscribir al cliente");
			}
			
		}
	}

	/**
	 * Este método se encarga de volver a la vista anterior, en nuestra caso, de
	 * poder volver a la vista primaria, en el caso de que no queramos añadir ningún
	 * cliente.
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
	 * 
	 * @return cliente obtenido.
	 */
	@SuppressWarnings("exports")
	public Cliente getCliente() {
		return cliente;
	}
}
