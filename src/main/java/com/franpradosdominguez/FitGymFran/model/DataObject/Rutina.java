package com.franpradosdominguez.FitGymFran.model.DataObject;


public class Rutina {
	public static ExercisesTypes ejerTypes;
	
	protected int id_rut;
	protected String nombreRutina;

	public Rutina() {
		this.id_rut = -1;
		this.nombreRutina = ExercisesTypes.PECHO.getType();
		this.nombreRutina = ExercisesTypes.ESPALDA.getType();
		this.nombreRutina = ExercisesTypes.PIERNAS.getType();
		this.nombreRutina = ExercisesTypes.HOMBROS.getType();
		this.nombreRutina = ExercisesTypes.BRAZOS.getType();
	}

	public Rutina(int id_rut, String type) {
		this.id_rut = id_rut;
		this.nombreRutina = type;
	}

	public int getId_rut() {
		return id_rut;
	}

	public void setId_rut(int id_rut) {
		this.id_rut = id_rut;
	}

	public String getNombreRutina() {
		return nombreRutina;
	}

	public void setNombreRutina(String type) {
		this.nombreRutina = type;
	}

	@Override
	public String toString() {
		return "id_rut = " + id_rut + ", nombreRutina = " + nombreRutina + "\n";
	}
}
