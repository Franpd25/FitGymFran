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

public class editRoutineController {
	
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
	
	public void initAttributes(Rutina rut) {
		this.r = rut;
		this.idRoutine.setText(rut.getIdRutina() + "");
		this.nameRoutine.setText(rut.getNombreRutina());
		this.descRoutine.setText(rut.getDescripcion());
	}
	
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
	
	@FXML
	public void handleCancel() {
		
		try {
			FXMLLoader loader1 = new FXMLLoader(getClass().getResource("secondary.fxml"));
			Parent r = loader1.load();
			SecondaryController sc = loader1.getController();
			
			Scene scene = new Scene(r, 600, 400);
			Stage stage = (Stage) this.cancel.getScene().getWindow();
			stage.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Rutina getR() {
		return r;
	}
}
