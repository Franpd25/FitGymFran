package com.franpradosdominguez.FitGymFran.model.DataObject;

import java.util.ArrayList;
import java.util.List;

public class Rutina {
	
	protected int idRutina;
	protected String nombreRutina;
	protected String descripcion;
	protected List<Cliente> misClientes;

	public Rutina() {
		this(-1, "", "", new ArrayList<>());		
	}

	public Rutina(int idRutina, String nombreRutina, String descripcion, List<Cliente> misClientes) {
		this.idRutina = idRutina;
		this.nombreRutina = nombreRutina;
		this.descripcion = descripcion;
		this.misClientes = misClientes;
	}

	public Rutina(String nombreRutina, String descripcion) {
		this.nombreRutina = nombreRutina;
		this.descripcion = descripcion;
	}

	public int getIdRutina() {
		return idRutina;
	}

	public void setIdRutina(int id_rut) {
		this.idRutina = id_rut;
	}

	public String getNombreRutina() {
		return nombreRutina;
	}

	public void setNombreRutina(String type) {
		this.nombreRutina = type;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Cliente> getMisClientes() {
		return misClientes;
	}

	public void setMisClientes(List<Cliente> misClientes) {
		this.misClientes = misClientes;
	}

	@Override
	public String toString() {
		return "Identificador = " + idRutina + "\n" +
				"Nombre de la Rutina = " + nombreRutina + ", descripcion = " + descripcion
				+ "misClientes=" + misClientes;
	}

	

	
}
