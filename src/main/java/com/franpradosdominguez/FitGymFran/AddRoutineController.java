package com.franpradosdominguez.FitGymFran;

import java.io.IOException;

import com.franpradosdominguez.FitGymFran.model.DAO.RutinaDAO;
import com.franpradosdominguez.FitGymFran.model.DataObject.Rutina;
import com.franpradosdominguez.FitGymFran.utils.Dialog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddRoutineController {

	private RutinaDAO rdao = new RutinaDAO();
	private Rutina rutina;

	@FXML
	private TextField nameRoutine;
	@FXML
	private TextArea descRoutine;
	@FXML
	private Button confirm;
	@FXML
	private Button cancel;

	/**
	 * Este método se encarga de añadir una rutina mediante una ventaña en la cuál
	 * podemos rellenar los campos para poder añadirla en nuestra BBDD. Controlo que
	 * todos los campos tienen que estar rellenados para poder añadir dicha rutina.
	 * 
	 * @throws IOException: para poder controlar las excepciones en el caso de no
	 *                      introducir datos en algunos de los campos.
	 */
	@SuppressWarnings("unused")
	@FXML
	private void addRoutine(ActionEvent event) throws Exception {
		Object evt = event.getSource();
		String nombre = nameRoutine.getText();
		String descripcion = descRoutine.getText();
		
		if (evt.equals(confirm)) {
			if (!nameRoutine.getText().isEmpty() && !descRoutine.getText().isEmpty()) {
				if (nameRoutine.getText().equals(nombre) && descRoutine.getText().equals(descripcion)) {
					
					rutina = new Rutina(nombre, descripcion);
					this.rdao.insert(rutina);
					Dialog.showConfirm("Message", "Nombre y Descripcion introducidos correctamente", "");
					
					Stage stage = (Stage) this.confirm.getScene().getWindow();
					stage.close();
					
				}else {
					Dialog.showError("Message", "ERROR. Introduce Nombre y Descripción", "");
				}
			}else {
				Dialog.showError("Message", "Inserta el Nombre y la Descripcion de la Rutina", null);
			}
		}
	}

	/**
	 * Este método se encarga de volver a la vista anterior, en nuestra caso, de
	 * poder volver a la vista rutina (botón Rutina del Menú Principal), en el caso
	 * de que no queramos añadir ninguna rutina.
	 */
	@SuppressWarnings("unused")
	@FXML
	public void handleCancel() {

		try {
			FXMLLoader loader1 = new FXMLLoader(getClass().getResource("routineview.fxml"));
			Parent r = loader1.load();
			PSControllerButtonRoutine pcbr = loader1.getController();

			Scene scene = new Scene(r, 600, 400);
			Stage stage = (Stage) this.cancel.getScene().getWindow();
			stage.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
