package com.franpradosdominguez.FitGymFran.model.DataObject;

public class ClientRutine {

	protected Cliente cliente;
	protected Rutina rutina;
	
	public ClientRutine() {
		this(null, null);
	}

	public ClientRutine(Cliente c, Rutina r) {
		this.cliente = c;
		this.rutina = r;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Rutina getRutina() {
		return rutina;
	}

	public void setRutina(Rutina rutina) {
		this.rutina = rutina;
	}

	@Override
	public String toString() {
		return "ClientRutine [cliente=" + cliente + ", rutina=" + rutina + "]";
	}
	
	
}
