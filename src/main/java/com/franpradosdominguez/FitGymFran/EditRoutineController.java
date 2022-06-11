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

public class EditRoutineController {
	
	private Rutina r;
	private RutinaDAO rdao = new RutinaDAO();

	@FXML
	private TextField idRoutine;
	@FXML
	private TextField nameRoutine;
	@FXML
	private TextArea descRoutine;
	@FXML
	private Button confirm;
	@FXML
	private Button cancel;
	
	/**
	 * Este método se encarga de iniciar los valores de la propia
	 * rutina seleccionada en los campos de la vista.
	 * @param rut: rutina que se le pasa seleccionada.
	 */
	@SuppressWarnings("exports")
	public void initAttributes(Rutina rut) {
		this.r = rut;
		this.idRoutine.setText(rut.getIdRutina() + "");
		this.nameRoutine.setText(rut.getNombreRutina());
		this.descRoutine.setText(rut.getDescripcion());
	}
	
	/**
	 * Este método se encarga de editar la rutina obteniendo todos
	 * sus campos inicializados para después modificar cualquier campo
	 * para actualizar dicha rutina.
	 * @throws IOException: controlar la excepción para que se
	 * modifique la rutina seleccionada.
	 */
	@SuppressWarnings("unused")
	@FXML
	private void saveRoutine() throws IOException {
		int id = Integer.parseInt(idRoutine.getText());
		String nombre = nameRoutine.getText();
		String descripcion = descRoutine.getText();
		
		// Modificando...
		if (this.r != null) {
			this.r.setNombreRutina(nombre);
			this.r.setDescripcion(descripcion);
			rdao.update(r);
			Dialog.showConfirm("Message", "Rutina editada", "Has editado correctamente la rutina");
		}
		
		Stage stage = (Stage) this.confirm.getScene().getWindow();
		stage.close();
	}
	
	/**
	 * Este método se encarga de volver a la vista rutina, en el caso
	 * de que no quieras editar la rutina que se haya seleccionado.
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

	/**
	 * Este es un método getter para poder obtener la rutina.
	 * @return rutina obtenida.
	 */
	@SuppressWarnings("exports")
	public Rutina getR() {
		return r;
	}
}
