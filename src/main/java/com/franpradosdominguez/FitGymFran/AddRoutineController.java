package com.franpradosdominguez.FitGymFran;

import java.io.IOException;

import com.franpradosdominguez.FitGymFran.model.DAO.RutinaDAO;
import com.franpradosdominguez.FitGymFran.model.DataObject.Rutina;
import com.franpradosdominguez.FitGymFran.utils.Dialog;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddRoutineController {
	
	private Rutina rutina;
	private RutinaDAO rdao = new RutinaDAO();

	@FXML
	private TextField nameRoutine;
	@FXML
	private TextArea descRoutine;
	@FXML
	private Button confirm;
	@FXML
	private Button cancel;
	
	/**
	 * Este método se encarga de añadir una rutina mediante una ventaña en la 
	 * cuál podemos rellenar los campos para poder añadirla en nuestra BBDD.
	 * Controlo que todos los campos tienen que estar rellenados para poder añadir 
	 * dicha rutina. 
	 * @throws IOException: para poder controlar las excepciones en el caso
	 * de no introducir datos en algunos de los campos.
	 */
	@FXML
	private void addRoutine() throws Exception {
		String nombreR = nameRoutine.getText();
		String desc = descRoutine.getText();
		
		if (rutina == null) {
			Dialog.showError("Message",
					"Inserta los campos: \n" + "- NOMBRE \n" + "- DESCRIPCION",
					"Hay que insertar los campos para poder insertar la rutina en la tabla Rutina.");
			
			if (nombreR == null) {
				Dialog.showError("Message", "Inserta el Nombre",
						"Hay que insertar el Nombre para completar la inserción de la rutina");
			} else {
				nombreR = nameRoutine.getText();
			}
			
			if (desc == null) {
				Dialog.showError("Message", "Inserta la Descripción",
						"Hay que insertar la Descripción para completar la inserción de la rutina");
			} else {
				desc = descRoutine.getText();
			}
			
		}else {
			rutina = new Rutina(nombreR, desc);
			rdao.insert(rutina);
			Dialog.showConfirm("Message", "Rutina insertada", "Has introducido correctamente la rutina con todos sus campos");
			
			Stage stage = (Stage) this.confirm.getScene().getWindow();
			stage.close();
		}
	}
	
	/**
	 * Este método se encarga de volver a la vista anterior, en nuestra caso,
	 * de poder volver a la vista rutina (botón Rutina del Menú Principal),
	 * en el caso de que no queramos añadir ninguna rutina.
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
