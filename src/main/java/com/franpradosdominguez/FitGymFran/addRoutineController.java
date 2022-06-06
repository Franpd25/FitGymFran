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

public class addRoutineController {
	
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
	
	@FXML
	private void addRoutine() {
		String nombreR = nameRoutine.getText();
		String desc = descRoutine.getText();
		
		rutina = new Rutina(nombreR, desc);
		rdao.insert(rutina);
		Dialog.showConfirm("Message", "Rutina insertada", "Has introducido correctamente la rutina");
		
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
}
