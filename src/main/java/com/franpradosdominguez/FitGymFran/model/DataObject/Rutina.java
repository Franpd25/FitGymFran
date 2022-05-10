package com.franpradosdominguez.FitGymFran.model.DataObject;


public class Rutina {
	public static ExercisesTypes ejerTypes;
	
	protected int id_rut;
	protected String type;

	public Rutina() {
		this.id_rut = -1;
		this.type = ExercisesTypes.PECHO.getType();
		this.type = ExercisesTypes.ESPALDA.getType();
		this.type = ExercisesTypes.PIERNAS.getType();
		this.type = ExercisesTypes.HOMBROS.getType();
		this.type = ExercisesTypes.BRAZOS.getType();
	}

	public Rutina(int id_rut, String type) {
		this.id_rut = id_rut;
		this.type = type;
	}

	public int getId_rut() {
		return id_rut;
	}

	public void setId_rut(int id_rut) {
		this.id_rut = id_rut;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Rutina [id_rut=" + id_rut + ", type=" + type + "]";
	}
}
