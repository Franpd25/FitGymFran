package com.franpradosdominguez.FitGymFran.model.DataObject;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

	protected int idCliente;
	protected String name;
	protected String email;
	protected String dni;
	protected String phone;
	protected List<Rutina> misRutinas;

	public Cliente() {
		this(-1, "", "", "", "", new ArrayList<>());
	}
	
	public Cliente(int id, String name, String email, String dni, String phone, List<Rutina> misRutinas) {
		super();
		this.idCliente = id;
		this.name = name;
		this.email = email;
		this.dni = dni;
		this.phone = phone;
		this.misRutinas = misRutinas;
	}
	
	public Cliente(String name, String email, String dni, String phone) {
		this.name = name;
		this.email = email;
		this.dni = dni;
		this.phone = phone;
	}

	public Cliente(String name) {
		this.idCliente = -1;
		this.name = name;
		this.email = "";
		this.dni = "";
		this.phone = "";
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Rutina> getMisRutinas() {
		return misRutinas;
	}

	public void setMisRutinas(List<Rutina> misRutinas) {
		this.misRutinas = misRutinas;
	}

	@Override
	public String toString() {
		return "Identificador Cliente = " + idCliente + "\n" +
			   "Nombre Cliente = " + name + "\n" +
			   "Correo Electrónico = " + email + "\n" +
			   "DNI = " + dni + "\n" +
			   "Teléfono = " + phone + "\n" +
			   "Lista de mi Rutina = " + misRutinas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}
	
	
}
