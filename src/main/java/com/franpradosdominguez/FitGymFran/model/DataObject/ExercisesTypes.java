package com.franpradosdominguez.FitGymFran.model.DataObject;

public enum ExercisesTypes {

	PECHO("Pectorales y Abdominales/Cintura"),
	ESPALDA("Dorsales y Espalda Baja"),
	PIERNAS("Cuádriceps, Femoral y Gemelos"),
	HOMBROS("Trapecios, Deltoides y Glúteos/Aductores"),
	BRAZOS("Bíceps y Tríceps");

	private String type;

	private ExercisesTypes(String tipo) {
		// TODO Auto-generated constructor stub
		this.type = tipo;
	}
	
	public String getType(){
        return this.type;
    }
}
