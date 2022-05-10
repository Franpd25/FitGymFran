package com.franpradosdominguez.FitGymFran.model.DataObject;

public class Cliente {

	protected int id;
	protected String name;
	protected String email;
	protected String dni;
	protected int phone;

	public Cliente() {
		this(-1, "", "", "", -1);
	}
	
	public Cliente(int id, String name, String email, String dni, int phone) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.dni = dni;
		this.phone = phone;
	}

	public Cliente(String name, String email, String dni, int phone) {
		this.name = name;
		this.email = email;
		this.dni = dni;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", name=" + name + ", email=" + email + ", dni=" + dni + ", phone=" + phone + "]";
	}
}
